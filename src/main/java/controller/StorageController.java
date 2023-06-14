package controller;

import dal.StorageDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Storage;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "StorageController", urlPatterns = "/storage")
public class StorageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean isAdmin = (boolean) session.getAttribute("isAdmin");
        StorageDAO storageDAO = new StorageDAO();
        List<Storage> list = storageDAO.getAllStorage();
        if (isAdmin) {
            request.setAttribute("listStorage", list);
            request.getRequestDispatcher("admin/storage.jsp").forward(request, response);
        } else {
            response.sendRedirect("logout");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}