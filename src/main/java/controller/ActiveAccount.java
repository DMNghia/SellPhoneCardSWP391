package controller;

import FunctionUtils.Function;
import dal.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;

@WebServlet(name = "ActiveAccount", value = "/activeAccount")
public class ActiveAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String tokenValue = (String) session.getAttribute("token");
        String tokenInput = request.getParameter("tokenInput");
        String option = request.getParameter("option");
        String captchaInput = request.getParameter("captchaInput");
        String captchaValue = (String) session.getAttribute("captchaValue");
        String messageErr;
        if (option.equals("confirm")) {
            if (captchaValue.equals(captchaInput)) {
                if (tokenValue.equals(tokenInput)) {
                    User user = (User) session.getAttribute("user");
                    user.setActive(true);
                    UserDAO ud = new UserDAO();
                    ud.update(user, user.getId());
                    session.removeAttribute("user");
                    session.removeAttribute("token");
                    session.removeAttribute("captchaValue");
                    response.sendRedirect("login");
                } else {
                    messageErr = "Token is not correct! Please check again!";
                }
            } else {
                messageErr = "Captcha is not correct!";
                request.setAttribute("captchaMessageErr", messageErr);
            }
        } else {
            doPost(request, response);
        }
        request.getRequestDispatcher("activeAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Function f = new Function();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String token = f.tokenGenerate();
        f.authenEmail("nghiadmhe160858@fpt.edu.vn", "Dmnghi@15112002", user.getEmail(), token);
        session.setAttribute("token", token);
        request.getRequestDispatcher("activeAccount.jsp").forward(request, response);
    }
}
