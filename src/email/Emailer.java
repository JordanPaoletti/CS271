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

    public static void main(String[] args) { //tls Attempt
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ACCOUNT, PASSWORD);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ACCOUNT));
            message.setRecipient(Message.RecipientType.TO,
                    new InternetAddress("sammcmahon@u.boisestate.edu"));
            message.setSubject("Got it ;)");
            message.setText("The emailer is working!");

            Transport.send(message);

            System.out.println("sent");

        } catch (MessagingException e) {
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
