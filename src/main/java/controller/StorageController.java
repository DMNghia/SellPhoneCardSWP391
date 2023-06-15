package controller;

import dal.StorageDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Storage;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "StorageController", urlPatterns = "/storage")
public class StorageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean isAdmin = (boolean) session.getAttribute("isAdmin");
        StorageDAO storageDAO = new StorageDAO();
        String page = request.getParameter("page");
        if (isAdmin) {
            Long totalPages = storageDAO.getTotalStorage();
            if (page == null) {
                List<Storage> list = storageDAO.getListStorageForPage(0);
                request.setAttribute("listStorage", list);
                request.setAttribute("totalPageNumbers", totalPages / 10);
                request.setAttribute("pageNumber", 1);
                request.getRequestDispatcher("admin/storage.jsp").forward(request, response);
            } else {
                List<Storage> list = storageDAO.getListStorageForPage((Integer.parseInt(page) * 10) - 10);
                request.setAttribute("totalPageNumbers", totalPages / 10);
                request.setAttribute("listStorage", list);
                request.setAttribute("pageNumber", page);
                request.getRequestDispatcher("admin/storage.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("logout");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}