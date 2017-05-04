package com.gameOfNerds.areas.connections;

import com.gameOfNerds.areas.connections.models.GmailConnectionInfo;
import com.gameOfNerds.areas.connections.services.ConnectionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Component
public class EmailSender {
    private static ConnectionInfoService connectionInfoService;

    @Autowired
    public EmailSender(ConnectionInfoService connectionInfoService) {
        EmailSender.connectionInfoService = connectionInfoService;
    }

    public static void sendEmail(String receiverEmail,String subject,String text){
        GmailConnectionInfo gmailConnectionInfo = connectionInfoService.getGmailInfo();

        Properties props = new Properties();
        props.put("mail.from", gmailConnectionInfo.getEmail());
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(gmailConnectionInfo.getEmail(), gmailConnectionInfo.getSecretKey());
                    }
                });

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(gmailConnectionInfo.getEmail());
            msg.setRecipients(Message.RecipientType.TO,
                    receiverEmail);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(text);
            Transport.send(msg);
        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
        }
    }
}
