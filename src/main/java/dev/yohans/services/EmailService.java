package dev.yohans.services;

import dev.yohans.models.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(Email email){
        var mail = new SimpleMailMessage();

        mail.setFrom(email.getFrom());
        mail.setTo(email.getTo().toArray(new String[0]));
        mail.setSubject(email.getSubject());
        mail.setText(email.getBody());

        mailSender.send(mail);
    }

}
