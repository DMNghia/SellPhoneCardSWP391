package functionUtils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Properties;

public class Function {

    private final String FROMEMAIL = "swp391grou5@gmail.com";
    private final String PASSWORD = "duhphxeehayasotx";
    public String hash(String str) {
        String salt = "as@c.$";
        return salt + DigestUtils.sha256Hex(str).toString();
    }

    public String tokenGenerate() {
        String result = "";
        for (int i = 0; i < 6; i++) {
            result += (int)(Math.random() * 10) + "";
        }
        return result;
    }

    public void send(String fromEmail, String password, String toEmail, String subject, String content) {
        String host = "smtp.gmail.com";
        String port = "587";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port); // for TLS
        props.put("mail.smtp.starttls.enable", "true"); // for TLS

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void resetPasswordMail(String toEmail, String token) {
        String host = "smtp.gmail.com";
        String port = "587";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port); // for TLS
        props.put("mail.smtp.starttls.enable", "true"); // for TLS

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROMEMAIL, PASSWORD);
            }
        });

        try {
            String content = "Bạn đang thực hiện việc reset mật khẩu. Đây là mã xác thực: " + token +
                    "\nLưu ý: Mã xác thực chỉ có hiệu nghiệm trong vòng 30 phút và bạn sẽ chỉ có thể nhận được mail mã xác thực mới sau 30 phút";

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROMEMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("[Thông báo] Mã OPT reset mật khẩu");
            message.setText(content);
            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void authenEmail(String toEmail, String token) {
        String host = "smtp.gmail.com";
        String port = "587";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port); // for TLS
        props.put("mail.smtp.starttls.enable", "true"); // for TLS

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROMEMAIL, PASSWORD);
            }
        });

        try {
            String content = "Thank you for sign up our website. Below is your token to authenticate email:\nToken: " + token +
                    "\nNote: You can only use this token for 30 minutes and during this period you will not be able to receive any more tokens";

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROMEMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Authentication email");
            message.setText(content);
            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
