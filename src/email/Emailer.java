package email;

import com.sun.mail.smtp.SMTPMessage;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import javax.mail.*;
import javax.mail.internet.*;
public class Emailer {
    private Properties config;

    //gmail account for email
    private static final String ACCOUNT = "bsucs271emailer@gmail.com";
    private static final String PASSWORD = "Jtmb)\"Z}@/(6h\"Fj";

    public static void main(String[] args) {
//        Properties c = new Properties();
//        try (InputStream is = Files.newInputStream(Paths.get("mailconfig"))) {
//            c.load(is);
//            Emailer emailer = new Emailer(c);
//
//            emailer.sendEmail("",
//                    "jordanpaoletti@u.boisestate.edu",
//                    "test",
//                    "test");
//        }
//        catch (IOException e) {
//            System.err.println(e);
//        }

        String to = "jordanpaoletti@u.boisestate.edu";
        String from = ACCOUNT;

        System.out.println("Getting properties");
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.Class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.user", ACCOUNT);
        properties.put("mail.password", PASSWORD);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", 805);
        System.out.println("Finished Getting properties");

        System.out.println("Creating Session");
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(ACCOUNT, PASSWORD);
                    }
                });

        System.out.println("Finished Creating Session");

        try {
            System.out.println("Creating Message");
            SMTPMessage message = new SMTPMessage(session);

            System.out.println("Populating message");
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject("Test email from java");
            message.setText("This is a test email from CS271 project");
            message.setNotifyOptions(SMTPMessage.NOTIFY_SUCCESS);
            int returnOpt = message.getReturnOption();
            System.out.println(returnOpt);


            System.out.println("Sending Message");
            Transport.send(message);
            System.out.println("Sent message successfully");

        } catch (MessagingException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
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
