package controller;

import dal.StorageDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;
import model.Storage;
import model.User;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "StorageController", urlPatterns = "/storage")
public class StorageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean isAdmin = false;
        if (session.getAttribute("isAdmin") != null) {
            isAdmin = (boolean) session.getAttribute("isAdmin");
        } else {
            response.sendRedirect("login");
        }
        StorageDAO storageDAO = new StorageDAO();
        String page_raw = request.getParameter("page");
        String supplier_raw = request.getParameter("supplier");
        String price_raw = request.getParameter("price");
        String search_raw = request.getParameter("search");
        if (isAdmin) {
            List<Product> listProduct = storageDAO.getListDistinctProduct();
            Long totalStorage = storageDAO.getTotalStorage();
            double totalPages = (double) totalStorage / 10;
            List<Storage> list;
            if (page_raw == null) {
                list = storageDAO.getListStorageForPage(0);
                request.setAttribute("listStorage", list);
                request.setAttribute("totalPageNumbers", Math.ceil(totalPages));
                request.setAttribute("pageNumber", 1);
                request.setAttribute("listProduct", listProduct);
            } else {
                list = storageDAO.getListStorageForPage((Integer.parseInt(page_raw) * 10) - 10);
                request.setAttribute("totalPageNumbers", Math.ceil(totalPages));
                request.setAttribute("listStorage", list);
                request.setAttribute("pageNumber", page_raw);
                request.setAttribute("listProduct", listProduct);
            }
            if (search_raw != null || price_raw != null || supplier_raw != null) {
                int price = -1;
                int productId = -1;
                String search = "%";
                if (search_raw != null && !search_raw.isEmpty()) {
                    search += (search_raw + "%");
                }
                if (price_raw != null && !price_raw.equals("all")) {
                    price = Integer.parseInt(price_raw);
                    totalPages = (double) totalStorage / 10;
                }
                if (supplier_raw != null && !supplier_raw.equals("all")) {
                    productId = Integer.parseInt(supplier_raw);
                }
                list = storageDAO.searchStorage(price, productId, search);
                totalStorage = (long) list.size();
                totalPages = (double) totalStorage / 10;
                request.setAttribute("pageNumber", 1);
                request.setAttribute("listStorage", list);
                request.setAttribute("totalPageNumbers", Math.ceil(totalPages));
            }
            request.getRequestDispatcher("admin/storage.jsp").forward(request, response);
        } else {
            response.sendRedirect("logout");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String option = request.getParameter("option");
        StorageDAO storageDAO = new StorageDAO();
        User user = (User) session.getAttribute("user");
        String page = request.getParameter("page");
        if (option.equals("update")) {
            String id_raw = request.getParameter("id");
            String serialNumber = request.getParameter("seri");
            String cardNumber = request.getParameter("cardNumber");
            String expiredAt = request.getParameter("expiredAt");

            try {
                Long id = Long.parseLong(id_raw);
                Storage storage = storageDAO.getStorageById(id);
                storage.setSerialNumber(serialNumber);
                storage.setCardNumber(cardNumber);
                storage.setExpiredAt(Timestamp.valueOf(expiredAt));
                storage.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
                storage.setUpdatedBy(user);
                storageDAO.update(storage);
                System.out.println("Update storage success");
                request.setAttribute("message", "Cập nhật thông tin sản phẩm thành công");
            } catch (NumberFormatException e) {
                request.setAttribute("message", "Cập nhật thông tin sản phẩm thất bại");
                System.out.println(e.getMessage());
            } catch (Exception e) {
                request.setAttribute("message", "Cập nhật thông tin sản phẩm thất bại, hạn sử dụng không đúng format \"năm-tháng-ngày giờ:phút:giây\"");
                System.out.println(e.getMessage());
            }
        } else {
            String id_raw = request.getParameter("id");
            try {
                Long id = Long.parseLong(id_raw);
                Storage storage = storageDAO.getStorageById(id);
                storage.setDelete(true);
                storage.setDeletedAt(Timestamp.valueOf(LocalDateTime.now()));
                storage.setDeletedBy(user);
                storageDAO.delete(storage);
                System.out.println("Delete storage success");
                request.setAttribute("message", "Xóa sản phẩm thành công");
            } catch (Exception e) {
                request.setAttribute("message", "Xóa sản phẩm thất bại");
                System.out.println(e.getMessage());
            }
        }
        Long totalStorage = storageDAO.getTotalStorage();
        double totalPages = (double) totalStorage / 10;
        if (page == null || page.isEmpty()) {
            List<Storage> list = storageDAO.getListStorageForPage(0);
            request.setAttribute("listStorage", list);
            request.setAttribute("totalPageNumbers", Math.ceil(totalPages));
            request.setAttribute("pageNumber", 1);
        } else {
            List<Storage> list = storageDAO.getListStorageForPage((Integer.parseInt(page) * 10) - 10);
            request.setAttribute("totalPageNumbers", Math.ceil(totalPages));
            request.setAttribute("listStorage", list);
            request.setAttribute("pageNumber", page);
        }
        request.getRequestDispatcher("admin/storage.jsp").forward(request, response);
    }
}