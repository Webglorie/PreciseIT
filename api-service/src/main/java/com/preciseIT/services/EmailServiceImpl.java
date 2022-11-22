package com.preciseIT.services;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailServiceImpl implements EmailService{

    private Properties prop;

    public EmailServiceImpl() {
        prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.starttls.required", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
    }

    @Override
    public void sendEmail(String from, String receiver, String title, String message) throws MessagingException {
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("bartrijnderstest@gmail.com", "kmndrvotybmsbvqh");
            }
        });
        Message messageToSend = new MimeMessage(session);
        messageToSend.setFrom(new InternetAddress(from));
        messageToSend.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
        messageToSend.setSubject(title);
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(message, "text/html; charset=utf-8");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        messageToSend.setContent(multipart);
        Transport.send(messageToSend);

    }
}
