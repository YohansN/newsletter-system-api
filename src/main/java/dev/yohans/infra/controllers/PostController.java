package dev.yohans.infra.controllers;

import dev.yohans.application.interfaces.IPostService;
import dev.yohans.core.models.Post;
import dev.yohans.core.models.dtos.Letter;
import dev.yohans.core.models.dtos.PostDetails;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final IPostService postService;

    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> postingLetter(@RequestBody @Valid Letter postDto){
        if(postService.postingLetter(postDto))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<PostDetails>> getAllPosts(@PageableDefault(size = 10, page = 0) Pageable pageable){
        var response = postService.getAllPosts(pageable);
        if(response!=null){
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id){
        Post response = postService.getPostById(id);
        if(response!=null)
            return ResponseEntity.status(HttpStatus.OK).body(response);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/count")
    public Long getTotalNumberOfPosts(){
        return postService.getTotalNumberOfPosts();
    }
}
