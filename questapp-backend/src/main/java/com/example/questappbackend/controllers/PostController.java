package com.example.questappbackend.controllers;

import com.example.questappbackend.dto.PostDtoForCreate;
import com.example.questappbackend.dto.PostDtoForUpdate;
import com.example.questappbackend.dto.responses.PostDtoResponse;
import com.example.questappbackend.entities.Post;
import com.example.questappbackend.services.abs.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDtoResponse> getAllPost(@RequestParam Optional<Long> userId){
        return postService.getAllPost(userId);
    }

    @GetMapping("/{postId}")
    public Post getOnePostById(@PathVariable("postId") Long postId) {
        return postService.getOnePost(postId);
    }

    @PostMapping
    public Post creatOnePost(@RequestBody PostDtoForCreate request) {
        return postService.createOnePost(request);
    }

    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable("postId") Long postId,@RequestBody PostDtoForUpdate request) {
        return postService.updateOnePost(postId,request);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable("postId") Long postId){
        postService.deleteOnePost(postId);
    }

}
