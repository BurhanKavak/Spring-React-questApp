package com.example.questappbackend.services;

import com.example.questappbackend.dto.CommentDtoForCreate;
import com.example.questappbackend.dto.CommentDtoForUpdate;
import com.example.questappbackend.entities.Comment;
import com.example.questappbackend.entities.Post;
import com.example.questappbackend.entities.User;
import com.example.questappbackend.repository.CommentRepository;
import com.example.questappbackend.services.abs.CommentService;
import com.example.questappbackend.services.abs.PostService;
import com.example.questappbackend.services.abs.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {

    private final CommentRepository commentRepository;

    private final UserService userService;

    private final PostService postService;



    @Override
    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()){
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()){
            return commentRepository.findByPostId(postId.get());
        } else {
           return commentRepository.findAll();
        }
    }

    @Override
    public Comment getOneComment(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @Override
    public Comment createOneComment(CommentDtoForCreate request) {
        User user = userService.getOneUserById(request.getUserId());
        Post post = postService.getOnePost(request.getPostId());

        if (user != null && post != null){
            Comment commentToSave = new Comment();
            commentToSave.setId(request.getId());
            commentToSave.setText(request.getText());
            commentToSave.setUser(user);
            commentToSave.setPost(post);
            commentRepository.save(commentToSave);
            return commentToSave;
        } else {
            return null;
        }

    }

    @Override
    public Comment updateOneComment(Long commentId, CommentDtoForUpdate request) {
        Comment coment = getOneComment(commentId);
        coment.setText(request.getText());
        return commentRepository.save(coment);

    }

    @Override
    public void deleteOneComment(Long commentId) {
        commentRepository.deleteById(getOneComment(commentId).getId());
    }
}
