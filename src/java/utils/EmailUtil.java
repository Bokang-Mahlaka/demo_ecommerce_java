package utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * Utility class for sending emails.
 */
public class EmailUtil {

    private static final String SMTP_HOST = "smtp.example.com"; // Replace with your SMTP host
    private static final String SMTP_PORT = "587"; // Replace with your SMTP port
    private static final String USERNAME = "your-email@example.com"; // Replace with your email
    private static final String PASSWORD = "your-email-password"; // Replace with your email password

    public static void sendEmail(String toAddress, String subject, String messageContent) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
        message.setSubject(subject);
        message.setText(messageContent);

        Transport.send(message);
    }
}
