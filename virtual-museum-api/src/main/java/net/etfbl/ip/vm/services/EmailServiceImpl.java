package net.etfbl.ip.vm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {
    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String to, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("virtualmuseum.ip@gmail.com");
        message.setTo(to);
        message.setSubject("Notification about the tour");
        message.setText(text);
        emailSender.send(message);
    }

}
