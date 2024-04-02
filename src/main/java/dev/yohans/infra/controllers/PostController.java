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
@RequestMapping("api/v1/post")
public class PostController {

    private final IPostService postService;

    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> postingLetter(@RequestBody @Valid Letter postDto){
        postService.postingLetter(postDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDetails>> getAllPosts(@PageableDefault(size = 10, page = 0) Pageable pageable){
        var response = postService.getAllPosts(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id){
        Post response = postService.getPostById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/count")
    public Long getTotalNumberOfPosts(){
        return postService.getTotalNumberOfPosts();
    }
}
