package dev.yohans.application.services;

import dev.yohans.application.interfaces.IEmailService;
import dev.yohans.core.models.Email;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {
    private final JavaMailSender mailSender;
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(Email email){
        var mail = new SimpleMailMessage();

        mail.setFrom(email.getFrom());
        mail.setTo(email.getTo().toArray(new String[0]));
        mail.setSubject(email.getSubject());
        mail.setText(email.getBody());

        mailSender.send(mail);
    }

}
