package email;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import javax.mail.*;
import javax.mail.internet.*;
public class Emailer {
    private Properties config;

    public static void main(String[] args) {
        Properties c = new Properties();
        try (InputStream is = Files.newInputStream(Paths.get("mailconfig"))) {
            c.load(is);
            Emailer emailer = new Emailer(c);

            emailer.sendEmail("",
                    "jordanpaoletti@u.boisestate.edu",
                    "test",
                    "test");
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }

    public Emailer(Properties config) {
        this.config = config;
    }

    public void sendEmail(String fromEmail,
                          String toEmail,
                          String subject,
                          String body) {
        Session session = Session.getDefaultInstance(config, null);
        MimeMessage message = new MimeMessage(session);

        try {
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(toEmail));

            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
        }
        catch (MessagingException e) {
            System.err.println("Unable to send email:" + e);
        }
    }
}
