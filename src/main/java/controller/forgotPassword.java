package controller;

import jakarta.mail.Session;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import FunctionUtils.Function;
import dal.UserDAO;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import dal.UserDAO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/forgotPassword")
public class forgotPassword extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("forgotPassword.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        //get information user
        String account = request.getParameter("account");
        String captchaValue = (String) session.getAttribute("captchaValue");
        String captchaInput = request.getParameter("captcha");

        UserDAO ud = new UserDAO();
        String messageErr;

        if (ud.isAccountAvailable(account)) {
            messageErr = "Do not have a account";
            request.setAttribute("accountMessageErr", messageErr);
        } else {
            if (!captchaValue.equals(captchaInput)) {
                messageErr = "Captcha is not correct!";
                request.setAttribute("captchaMessageErr", messageErr);
            } else {
                Function f = new Function();
                User user = ud.getUserbyAccount(account);
                Cookie[] cookies = request.getCookies();
                String token = "";
                for (Cookie c : cookies) {
                    if (c.getName().equals("tokenValue-" + user.getId())) {
                        token = c.getValue();
                    }
                }
                if (token.isEmpty()) {
                    session.setAttribute("user", user);
                    token = f.tokenGenerate();
                    f.authenEmail("swp391grou5@gmail.com", "duhphxeehayasotx",user.getEmail(), token);
                    Cookie tokenCookie = new Cookie("tokenValue-" + user.getId(), token);
                    tokenCookie.setMaxAge(60 * 30);
                    response.addCookie(tokenCookie);
                } else {
                    request.setAttribute("messageErrForSendMail", "Token only send to your email every 30 minutes, Please check your email or wait");
                }
                request.getRequestDispatcher("EnterOtp.jsp").forward(request, response);
            }
        }

    }

//        String email = request.getParameter("email");
//        
//        RequestDispatcher dispatcher = null;
//        int otpvalue = 0;
//        HttpSession mySession = request.getSession();
//        Function f = new Function();
//                
//
//        if (account != null || !account.equals("")) {
//            // sending otp
//            Random rand = new Random();
//            otpvalue = rand.nextInt(1255650);
//
//            String to = email;// change accordingly
//            // Get the session object
//            Properties props = new Properties();
//            props.put("mail.smtp.host", "smtp.gmail.com");
//            props.put("mail.smtp.socketFactory.port", "465");
//            props.put("mail.smtp.socketFactory.class", "jakarta.net.ssl.SSLSocketFactory");
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.port", "465");
//            Session session = Session.getDefaultInstance(props, new jakarta.mail.Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication("hieumthe163841@fpt.edu.vn", "Hieu02042002@");// Put your email
//                    // id and
//                    // password here
//                }
//            });
//            // compose message
//            try {
//                MimeMessage message = new MimeMessage(session);
//                message.setFrom(new InternetAddress(email));// change accordingly
//                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//                message.setSubject("Hello");
//                message.setText("your OTP is: " + otpvalue);
//                // send message
//                Transport.send(message);
//                System.out.println("message sent successfully");
//            } catch (MessagingException e) {
//                throw new RuntimeException(e);
//            }
//            dispatcher = request.getRequestDispatcher("EnterOtp.jsp");
//            request.setAttribute("message", "OTP is sent to your email id");
//            //request.setAttribute("connection", con);
//            mySession.setAttribute("otp", otpvalue);
//            mySession.setAttribute("account", email);
//            dispatcher.forward(request, response);
//            //request.setAttribute("status", "success");
//        }
}
