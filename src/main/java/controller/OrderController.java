package controller;

import dal.OrderDAO;
import dal.StorageDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Order;
import model.Storage;
import model.User;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderController", urlPatterns = "/order")
public class OrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean isAdmin = (boolean) session.getAttribute("isAdmin");

        String page = request.getParameter("page");
        //getAllOrder
        OrderDAO orderDAO = new OrderDAO();
        List<Order> list;
        list = orderDAO.getAllOrder();




        request.setAttribute("listOrder",list);
        request.getRequestDispatcher("admin/order.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}