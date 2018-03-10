package email;

import login.UserAccount;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Sends emails through project gmail account using the sendEmail static method
 */
public class Emailer {

    /**
     * Used to test the class
     * simply set the local String as follows
     * to: recipient's email address
     * sub: subject of the email
     * body: body of the email
     */
    public static void main(String[] args) {
        String to = null;
        String sub = null;
        String body = null;

        sendEmail(to, sub, body);
    }

    /**
     *
     * Sends an email through project gmail account
     * @param toEmail recipient of the email
     * @param subject subject line of the email
     * @param body body of the email
     * @throws RuntimeException if literally anything goes wrong
     */
    public static void sendEmail(String toEmail,
                          String subject,
                          String body) {
        if (toEmail == null || subject == null || body == null) {
            throw new RuntimeException("Parameters cannot be null!");
        }
        else if (!UserAccount.isValidEmail(toEmail)) {

        }
        Session session = Session.getDefaultInstance(
                getProperties(),
                getAuthenticator()
        );

        try {
            //create message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ACCOUNT));
            message.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while emailing!", e);
        }

    }

    //Private

    //gmail account for email
    private static final String ACCOUNT = "bsucs271emailer@gmail.com";
    private static final String PASSWORD = "Jtmb)\"Z}@/(6h\"Fj";

    /**
     * @return returns the properties for the email session
     */
    private static Properties getProperties() {
        Properties p = new Properties();
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", "587");
        return p;
    }

    /**
     * @return an authenticator for the email session
     */
    private static Authenticator getAuthenticator() {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ACCOUNT, PASSWORD);
            }
        };
    }
}
