package dev.yohans.application.services;

import dev.yohans.application.interfaces.IEmailService;
import dev.yohans.application.interfaces.IPostService;
import dev.yohans.application.interfaces.ISubscriberService;
import dev.yohans.application.gateways.PostGateway;
import dev.yohans.core.models.Email;
import dev.yohans.core.models.Post;
import dev.yohans.core.models.dtos.Letter;
import dev.yohans.core.models.dtos.PostDetails;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {
    private final PostGateway postGateway;
    private final ISubscriberService subscriberService;
    private final IEmailService emailService;

    public PostService(PostGateway postGateway, ISubscriberService subscriberService, IEmailService emailService) {
        this.postGateway = postGateway;
        this.subscriberService = subscriberService;
        this.emailService = emailService;
    }

    @Transactional
    public boolean postingLetter(Letter letter){
        //Salva no banco de dados
        var post = new Post(letter);
        postGateway.savePost(post);

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
        var postList = postGateway.findAllPosts(pageable);
        ModelMapper modelMapper = new ModelMapper();
        return postList.stream().map(post -> modelMapper.map(post, PostDetails.class)).collect(Collectors.toList());
    }

    public Post getPostById(Long id){
        Optional<Post> response = postGateway.findPostById(id);
        Post post = new Post();

        if(response.isPresent()){
            post = response.get();
            return post;
        }

        return null;
    }

    public Long getTotalNumberOfPosts(){
        return postGateway.getTotalNumberOfPosts();
    }
}
