package dev.yohans.services;

import dev.yohans.models.Email;
import dev.yohans.models.Post;
import dev.yohans.models.dtos.Letter;
import dev.yohans.repositories.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired PostRepository postRepository;
    @Autowired SubscriberService subscriberService;
    @Autowired EmailService emailService;

    @Transactional
    public boolean postingLetter(Letter letter){
        //Salva no banco de dados
        var post = new Post(letter);
        postRepository.save(post);

        try {
            //Envia para o email dos inscritos
            var email = new Email(post);
            List<String> emails = subscriberService.getAllEmailsFromActiveSubscribers();
            email.setTo(emails);
            emailService.sendEmail(email);
        }catch (Exception ex){
            return false;
        }

        return true;
    }
}