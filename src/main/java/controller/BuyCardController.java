package controller;

import dal.OrderDAO;
import dal.StorageDAO;
import dal.SupplierDAO;
import dal.UserDAO;
import functionUtils.Function;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Order;
import model.Storage;
import model.Supplier;
import model.User;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BuyCardController", urlPatterns = "/buyCard")
public class BuyCardController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String price_raw = request.getParameter("price");
        String supplier = request.getParameter("supplier");
        String quantity_raw = request.getParameter("quantity");
        User user = (User) session.getAttribute("user");
        StorageDAO storageDAO = new StorageDAO();
        OrderDAO orderDAO = new OrderDAO();
        UserDAO userDAO = new UserDAO();
        ArrayList<Supplier> list = (new SupplierDAO()).getListSupplier();
        if (user == null) {
            response.sendRedirect("logout");
        } else {
            int quantity = 0;
            int price = 0;
            if (quantity_raw != null) {
                quantity = Integer.parseInt(quantity_raw);
            }
            if (price_raw != null) {
                price = Integer.parseInt(price_raw);
            }
            List<Storage> listStorage = storageDAO.getListStorageWithNearestExpiredAt(quantity, supplier, price);
            int totalStorage = listStorage.size();
            if (quantity > totalStorage) {
                request.setAttribute("message", "Số lượng không đủ chỉ còn " + totalStorage + " sản phẩm tương ứng. Vui lòng thực hiện lại!");
            } else if (user.getBalance() < (quantity * price)) {
                request.setAttribute("message", "Số dư trong tài khoản không đủ để thực hiện giao dịch vui lòng nạp tiền thêm!");
            } else {
                // Create subject and content to send mail
                String subject = "Thông tin các thẻ điện thoại mà bạn đã mua";
                String content = "Cảm ơn bạn đã mua thẻ của chúng tôi dưới đây là tên nhà phát hành, các số seri và mã thẻ cũng như ngày hêt hạn của chúng:\n";
                for (int i = 0; i < listStorage.size(); i++) {
                    listStorage.get(i).setUsed(false);
                    listStorage.get(i).setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
                    listStorage.get(i).setUpdatedBy(user);
                    content += ((i + 1) + ". " + listStorage.get(i).getProduct().getSupplier().getName() +
                            " - " + listStorage.get(i).getSerialNumber() + " - " +
                            listStorage.get(i).getCardNumber() + " - " +
                            listStorage.get(i).getExpiredAt() + "\n");
                }
                Function f = new Function();
                f.send(user.getEmail(), subject, content);

                // Create new order and add order and order_detail to database
                Order newOrder = new Order();
                newOrder.setUser(user);
                newOrder.setStatus("Thành công");
                newOrder.setTotalAmount(quantity * price);
                newOrder.setDelete(false);
                newOrder.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
                newOrder.setCreatedBy(user);
                newOrder.setListStorage(listStorage);
                orderDAO.add(newOrder);
                user.setBalance(user.getBalance() - (quantity * price));
                userDAO.update(user, user.getId());
                session.setAttribute("user", user);
                request.setAttribute("message", "Thực hiện giao dịch thành công vui lòng kiểm tra email của bạn để biết thông tim giao dịch!");
            }
            request.setAttribute("imgList", list);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }
}
