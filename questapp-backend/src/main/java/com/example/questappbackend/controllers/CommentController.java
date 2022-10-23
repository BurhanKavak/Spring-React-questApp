package com.example.questappbackend.controllers;

import com.example.questappbackend.dto.CommentDtoForCreate;
import com.example.questappbackend.dto.CommentDtoForUpdate;
import com.example.questappbackend.entities.Comment;
import com.example.questappbackend.services.abs.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments (@RequestParam Optional<Long> userId,@RequestParam Optional<Long> postId){
        return commentService.getAllCommentsWithParam(userId,postId);
    }

    @GetMapping("/{commentId}")
    public Comment getOneComment (@PathVariable("commentId") Long commentId){
        return commentService.getOneComment(commentId);
    }

    @PostMapping
    public Comment createOneComment (@RequestBody CommentDtoForCreate request) {
        return commentService.createOneComment(request);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment (@PathVariable("commentId") Long commentId,@RequestBody CommentDtoForUpdate request) {
        return commentService.updateOneComment(commentId,request);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment (@PathVariable("commentId") Long commentId) {
        commentService.deleteOneComment(commentId);
    }
}
