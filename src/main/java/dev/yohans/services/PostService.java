package dev.yohans.services;

import dev.yohans.models.Email;
import dev.yohans.models.Post;
import dev.yohans.models.dtos.Letter;
import dev.yohans.models.dtos.PostDetails;
import dev.yohans.repositories.PostRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<PostDetails> getAllPosts(Pageable pageable){
        //var postList = postRepository.findAllByOrderByPublicationDateDesc(pageable).stream().toList();
        var postList = postRepository.findAll(pageable).stream().toList();
        ModelMapper modelMapper = new ModelMapper();
        //Transformar Lista de Post em Lista de PostDetails.
        return postList.stream().map(post -> modelMapper.map(post, PostDetails.class)).collect(Collectors.toList());
    }

    public Post getPostById(Long id){
        Optional<Post> response = postRepository.findById(id);
        Post post;

        if(response.isPresent()){
            post = response.get();
            return post;
        }

        return null;
    }

    public Long getTotalNumberOfPosts(){
        return postRepository.countBy();
    }
}
