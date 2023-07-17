package controller;

import dal.AdminDAO;
import dal.DAO;
import dal.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AdminAddMoneyController", value = "/addmoney")
public class AdminAddMoneyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();
        List<User> list;
        list = DAO.userDAO.getAllUser();
        request.setAttribute("listUser", list);
        request.getRequestDispatcher("admin/addmoney.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int  userId = Integer.parseInt(request.getParameter("userId"));
        double balance = Double.parseDouble(request.getParameter("balance"));
        AdminDAO addmoney = new AdminDAO();
        addmoney.AddMoneyUser(userId,balance);

        response.sendRedirect("user");
    }
}