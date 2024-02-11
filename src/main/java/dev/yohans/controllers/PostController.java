package dev.yohans.controllers;

import dev.yohans.models.Post;
import dev.yohans.models.dtos.Letter;
import dev.yohans.repositories.PostRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @PostMapping
    @Transactional
    public ResponseEntity postingLetter(@RequestBody @Valid Letter dto){
        var post = new Post(dto);
        postRepository.save(post);

        return ResponseEntity.ok("Post cadastrado com sucesso.");

    }

    @GetMapping
    public List<Post> getAllPosts(){
        var res = postRepository.findAll();
        return res;
    }


}
