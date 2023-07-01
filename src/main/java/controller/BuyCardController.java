package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dal.*;
import functionUtils.Function;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@WebServlet(name = "BuyCardController", urlPatterns = "/buyCard")
public class BuyCardController extends HttpServlet {

    // Create only thread to buy card
    private final static Object lock = new Object();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String price_raw = request.getParameter("price");
        String supplier = request.getParameter("supplier");
        String quantity_raw = request.getParameter("quantity");
        String note = request.getParameter("noteValue");
        User user = (User) session.getAttribute("user");
        StorageDAO storageDAO = new StorageDAO();
        OrderDAO orderDAO = new OrderDAO();
        UserDAO userDAO = new UserDAO();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        JsonObject responseData = null;
        if (user == null) {
            response.sendRedirect("logout");
        } else {
            response.setContentType("application/json");
            responseData = new JsonObject();
            Gson gson = new Gson();
            Map<String, String> map = new HashMap<>();
            int quantity = 0;
            int price = 0;
            if (quantity_raw != null) {
                quantity = Integer.parseInt(quantity_raw);
            }
            if (price_raw != null) {
                price = Integer.parseInt(price_raw);
            }
            int finalQuantity = quantity;
            int finalPrice = price;
            if (note == null) {
                note = "";
            }
            String finalNote = note;
            synchronized (lock) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                List<Storage> listStorage = DAO.storageDAO.getListStorageWithNearestExpiredAt(finalQuantity, Integer.parseInt(supplier), finalPrice);
                int totalStorage = listStorage.size();
                if (finalQuantity > totalStorage) {
                    map.put("message", "Số lượng không đủ chỉ còn " + totalStorage + " sản phẩm tương ứng. Vui lòng thực hiện lại!");
                } else if (user.getBalance() < (finalQuantity * finalPrice)) {
                    map.put("message", "Số dư trong tài khoản không đủ để thực hiện giao dịch vui lòng nạp tiền thêm!");
                } else {
                    // Create subject and content to send mail
                    String subject = "[THÔNG BÁO] Thông tin các thẻ điện thoại mà bạn đã mua";
                    String content = "Cảm ơn bạn đã mua thẻ của chúng tôi dưới đây là tên nhà phát hành, các số seri và mã thẻ cũng như ngày hêt hạn của chúng:\n";
                    for (int i = 0; i < listStorage.size(); i++) {

                        // Update each storage set isUsed to true
                        listStorage.get(i).setUsed(true);
                        listStorage.get(i).setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
                        listStorage.get(i).setUpdatedBy(user);
                        storageDAO.update(listStorage.get(i));

                        // Add phone card's info to content in mail
                        content += ((i + 1) + ". " + listStorage.get(i).getProduct().getSupplier().getName() +
                                " - " + listStorage.get(i).getSerialNumber() + " - " +
                                listStorage.get(i).getCardNumber() + " - " +
                                listStorage.get(i).getExpiredAt() + "\n");
                    }

                    Function f = new Function();
                    f.send(user.getEmail(), subject, content);

                    // Create new order and add order and order_detail to database
                    Order newOrder = new Order();
                    LocalDateTime orderCreatedAt = LocalDateTime.now();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String time = orderCreatedAt.format(dtf);
                    newOrder.setUser(user);
                    newOrder.setStatus("Thành công");
                    newOrder.setTotalAmount(finalQuantity * finalPrice);
                    newOrder.setDelete(false);
                    newOrder.setCreatedAt(Timestamp.valueOf(time));
                    newOrder.setCreatedBy(user);
                    newOrder.setListStorage(listStorage);
                    orderDAO.add(newOrder);

                    // Minus quantity in product
                    Product product = DAO.productDAO.findProductByPriceAndSupplier(finalPrice, Integer.parseInt(supplier));
                    product.setQuantity(product.getQuantity() - finalQuantity);
                    product.setUpdatedAt(Timestamp.valueOf(time));
                    product.setUpdatedBy(user);
                    DAO.productDAO.update(product, product.getId());

                    // Add order and storage to order_detail
                    Order thisOrder = orderDAO.findOrderByTimeAndUser(user.getId(), time);
                    orderDetailDAO.add(thisOrder, listStorage);

                    // Update balance
                    user.setBalance(user.getBalance() - (finalQuantity * finalPrice));
                    userDAO.update(user, user.getId());

                    //Create new transaction
                    Transactions transactions = new Transactions().builder()
                            .user(user)
                            .money(finalQuantity * finalPrice)
                            .createAt(Timestamp.valueOf(LocalDateTime.now()))
                            .createBy(user)
                            .note(finalNote)
                            .status(true)
                            .orderId(thisOrder.getId())
                            .type(false)
                            .build();
                    new TransactionsDAO().insert(transactions);
                    System.out.println("Insert new transaction success");
                    session.setAttribute("user", user);
                    map.put("message", "Thực hiện giao dịch thành công vui lòng kiểm tra email của bạn để biết thông tim giao dịch!");
                }
            }
            response.getWriter().write(gson.toJson(map));
            response.getWriter().flush();
        }
    }
}
