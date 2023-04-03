package com.example.questappbackend.services;

import com.example.questappbackend.dto.PostDtoForCreate;
import com.example.questappbackend.dto.PostDtoForUpdate;
import com.example.questappbackend.dto.responses.LikeDtoResponse;
import com.example.questappbackend.dto.responses.PostDtoResponse;
import com.example.questappbackend.entities.Post;
import com.example.questappbackend.entities.User;
import com.example.questappbackend.repository.PostRepository;
import com.example.questappbackend.services.abs.LikeService;
import com.example.questappbackend.services.abs.PostService;
import com.example.questappbackend.services.abs.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {

    private PostRepository postRepository;

    private LikeService likeService;

    private UserService userService;

    public PostServiceImp(PostRepository postRepository,
                       UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Autowired
    public void setLikeService(LikeService likeService) {
        this.likeService = likeService;
    }


    @Override
    public List<PostDtoResponse> getAllPost(Optional<Long> userId) {
        List<Post> list;
        if (userId.isPresent()){
            list = postRepository.findByUserId(userId.get());
        } else {
            list = postRepository.findAll();
        }
        return list.stream().map(post -> {
            List<LikeDtoResponse> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null),Optional.of(post.getId()));
            return new PostDtoResponse(post,likes);}).collect(Collectors.toList());

    }

    @Override
    public Post getOnePost(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    @Override
    public Post createOnePost(PostDtoForCreate request) {
        User user = userService.getOneUserById(request.getUserId());
        if (user == null) {
            return null;
        }
        Post toSave = new Post();
        toSave.setId(request.getId());
        toSave.setText(request.getText());
        toSave.setTitle(request.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    @Override
    public Post updateOnePost(Long postId, PostDtoForUpdate updatePost) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Post toUpdate = post.get();
            toUpdate.setText(updatePost.getText());
            toUpdate.setTitle(updatePost.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        return null;

    }

    @Override
    public void deleteOnePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
