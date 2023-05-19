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

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        //Get information from user
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String captchaValue = (String) session.getAttribute("captchaValue");
        String captchaInput = req.getParameter("captcha");

        //Create new user data access object
        UserDAO ud = new UserDAO();
        String messageErr;

        if (!ud.isAccountAvailable(account)) {
            messageErr = "Account has been used!";
            req.setAttribute("accountMessageErr", messageErr);
        } else {
            if (!ud.isEmailAvailable(email)) {
                messageErr = "Email has been used!";
                req.setAttribute("emailMessageErr", messageErr);
            } else if (!captchaInput.equals(captchaValue)) {
                messageErr = "Captcha is not correct!";
                req.setAttribute("captchaMessageErr", messageErr);
            } else {
                Function f = new Function();
                User newUser = new User(account, f.hash(password), email, 1, false, false);
                ud.add(newUser);
                User activeUser = ud.getUser(account, f.hash(password));
                session.setAttribute("user", activeUser);
                req.getRequestDispatcher("activeAccount").forward(req, resp);
            }
        }

        req.setAttribute("account", account);
        req.setAttribute("password", password);
        req.setAttribute("email", email);
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }
}
