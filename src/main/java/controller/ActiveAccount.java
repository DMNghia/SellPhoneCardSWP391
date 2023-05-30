package controller;

import FunctionUtils.Function;
import dal.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;

import java.io.IOException;

@WebServlet(name = "ActiveAccount", value = "/activeAccount")
public class ActiveAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String tokenValue = "";
        Cookie[] cookies = request.getCookies();
        Cookie tokenCookie = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("tokenValue-" + user.getId())) {
                tokenValue = c.getValue();
                tokenCookie = c;
            }
        }
        String tokenInput = request.getParameter("tokenInput");
        String option = request.getParameter("option");
        String captchaInput = request.getParameter("captchaInput");
        String captchaValue = (String) session.getAttribute("captchaValue");
        if (option.equals("confirm")) {
            if (captchaValue.equals(captchaInput)) {
                if ((!tokenValue.isEmpty()) && tokenValue.equals(tokenInput)) {

                    user.setActive(true);
                    UserDAO ud = new UserDAO();
                    ud.update(user, user.getId());
                    session.removeAttribute("user");
                    session.removeAttribute("captchaValue");
                    tokenCookie.setMaxAge(0);
                    response.addCookie(tokenCookie);
                    response.sendRedirect("login");
                } else {
                    String tokenMessageErr = "Token is not correct! Please check again!";
                    request.setAttribute("tokenMessageErr", tokenMessageErr);
                    request.getRequestDispatcher("activeAccount.jsp").forward(request, response);
                }
            } else {
                String captchaMessageErr = "Captcha is not correct!";
                request.setAttribute("captchaMessageErr", captchaMessageErr);
                request.getRequestDispatcher("activeAccount.jsp").forward(request, response);
            }
        } else {
            doPost(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Function f = new Function();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Cookie[] cookies = request.getCookies();
        String token = "";
        for (Cookie c : cookies) {
            if (c.getName().equals("tokenValue-" + user.getId())) {
                token = c.getValue();
            }
        }
        if (token.isEmpty()) {
            token = f.tokenGenerate();
            f.authenEmail("swp391grou5@gmail.com", "duhphxeehayasotx", user.getEmail(), token);
            Cookie tokenCookie = new Cookie("tokenValue-" + user.getId(), token);
            tokenCookie.setMaxAge(60 * 30);
            response.addCookie(tokenCookie);
        } else {
            request.setAttribute("messageErrForSendMail", "Token only send to your email every 30 minutes, Please check your email or wait");
        }
        request.getRequestDispatcher("activeAccount.jsp").forward(request, response);
    }
}
