package FunctionUtils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Properties;

public class Function {
    public String hash(String str) {
        return DigestUtils.sha256Hex(str).toString();
    }

    public String tokenGenerate() {
        String result = "";
        for (int i = 0; i < 6; i++) {
            result += (int)(Math.random() * 10) + "";
        }

        return result;
    }

    public void send(String fromEmail, String password, String toEmail) {
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
            String text = "Thank you for sign up our website. Below is your token to authenticate email:\nToken: " + tokenGenerate();

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Authentication email");
            message.setText("Thank you for sign up our website. Below is your token to authenticate email:");
            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
