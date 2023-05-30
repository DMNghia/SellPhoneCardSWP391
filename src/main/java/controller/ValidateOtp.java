package controller;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 * Servlet implementation class ValidateOtp
 */
@WebServlet("/ValidateOtp")
public class ValidateOtp extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("EnterOtp.jsp");
        dispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tokenInput = request.getParameter("otp");
        String tokenValue = "";
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Cookie[] cookies = request.getCookies();
        Cookie tokenCookie = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("tokenValue-" + user.getId())) {
                tokenValue = c.getValue();
                tokenCookie = c;
            }
        }

        RequestDispatcher dispatcher = null;

        if (!tokenInput.isEmpty() && tokenValue.equals(tokenInput)) {

            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("status", "success");
            tokenCookie.setMaxAge(0);
            response.addCookie(tokenCookie);
            dispatcher = request.getRequestDispatcher("newPassword.jsp");
            dispatcher.forward(request, response);

        } else {
            request.setAttribute("message", "wrong otp");

            dispatcher = request.getRequestDispatcher("EnterOtp.jsp");
            dispatcher.forward(request, response);
        }

    }

}
