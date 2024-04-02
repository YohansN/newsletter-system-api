package dev.yohans.application.services;

import dev.yohans.application.interfaces.IEmailService;
import dev.yohans.application.interfaces.IPostService;
import dev.yohans.application.gateways.PostGateway;
import dev.yohans.core.exceptions.post.FailedToFindAllPosts;
import dev.yohans.core.exceptions.post.FailedToSavePostException;
import dev.yohans.core.exceptions.email.FailedToSendEmailException;
import dev.yohans.core.exceptions.post.PostNotFoundException;
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
    private final IEmailService emailService;

    public PostService(PostGateway postGateway, IEmailService emailService) {
        this.postGateway = postGateway;
        this.emailService = emailService;
    }

    @Transactional
    public void postingLetter(Letter letter){
        var post = new Post(letter);

        try {
            postGateway.savePost(post);
        }catch (Exception ex){
            throw new FailedToSavePostException();
        }

        try {
            var email = emailService.emailSetUp(post);
            emailService.sendEmail(email);
        }catch (Exception ex){
            throw new FailedToSendEmailException();
        }
    }

    public List<PostDetails> getAllPosts(Pageable pageable){
        List<Post> postList;
        try{
            postList = postGateway.findAllPosts(pageable);
        }catch (Exception ex){
            throw new FailedToFindAllPosts();
        }

        //Passando as lista de Post para PostDetails
        ModelMapper modelMapper = new ModelMapper();
        return postList.stream().map(post -> modelMapper.map(post, PostDetails.class)).collect(Collectors.toList());
    }

    public Post getPostById(Long id){
        Optional<Post> response = postGateway.findPostById(id);
        if(response.isEmpty()){ throw new PostNotFoundException(); }
        return response.get();
    }

    public Long getTotalNumberOfPosts(){
        return postGateway.getTotalNumberOfPosts();
    }
}
