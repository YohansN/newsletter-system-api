package dev.yohans.application.services;

import dev.yohans.application.interfaces.IEmailService;
import dev.yohans.application.interfaces.ISubscriberService;
import dev.yohans.core.models.Email;
import dev.yohans.core.models.Post;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService implements IEmailService {
    private final JavaMailSender mailSender;
    private final ISubscriberService subscriberService;

    public EmailService(JavaMailSender mailSender, ISubscriberService subscriberService) {
        this.mailSender = mailSender;
        this.subscriberService = subscriberService;
    }

    public Email emailSetUp(Post emailContent){
        List<String> subscribersEmailList = subscriberService.getAllEmailsFromActiveSubscribers();
        Email email = new Email(emailContent);
        email.setTo(subscribersEmailList);
        return email;
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
