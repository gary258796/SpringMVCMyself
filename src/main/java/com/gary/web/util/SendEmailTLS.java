package com.gary.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SendEmailTLS {

    @Autowired
    private MailSender mailSender ;

    @Async
    public void sendSimpleEmail(String to, String subject, String text) {

        SimpleMailMessage msg = new SimpleMailMessage() ;
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);

        try {
            mailSender.send(msg);
        }
        catch (Exception e) {
            System.out.println("\n >>> Error When Sending email : " + e);
        }
    }

}
