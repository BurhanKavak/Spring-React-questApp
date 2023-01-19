package com.example.questappbackend.services;

import com.example.questappbackend.dto.LikeDtoForCreate;
import com.example.questappbackend.dto.responses.LikeDtoResponse;
import com.example.questappbackend.entities.Like;
import com.example.questappbackend.entities.Post;
import com.example.questappbackend.entities.User;
import com.example.questappbackend.repository.LikeRepository;
import com.example.questappbackend.services.abs.LikeService;
import com.example.questappbackend.services.abs.PostService;
import com.example.questappbackend.services.abs.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeServiceImp implements LikeService {

    private final LikeRepository likeRepository;

    private final UserService userService;

    private final PostService postService;

    @Override
    public List<LikeDtoResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Like> likes;
       if (userId.isPresent() && postId.isPresent()) {
           likes = likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
       } else if (userId.isPresent()) {
           likes = likeRepository.findByUserId(userId.get());
       } else if (postId.isPresent()) {
           likes = likeRepository.findByPostId(postId.get());
       } else {
           likes = likeRepository.findAll();
       }
        return likes.stream().map(like -> new
                LikeDtoResponse(like)).collect(Collectors.toList());
    }

    @Override
    public Like getOneLike(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    @Override
    public Like createOneLike(LikeDtoForCreate request) {
        User user = userService.getOneUser(request.getUserId());
        Post post = postService.getOnePost(request.getPostId());

        if (user != null && post != null) {
            Like like = new Like();
            like.setId(request.getId());
            like.setUser(user);
            like.setPost(post);
            return likeRepository.save(like);
        } else {
            return null;
        }
    }

    @Override
    public void deleteOneLike(Long likeId) {
        likeRepository.deleteById(getOneLike(likeId).getId());
    }


}
