package com.example.questappbackend.controllers;

import com.example.questappbackend.dto.LikeDtoForCreate;
import com.example.questappbackend.entities.Like;
import com.example.questappbackend.services.abs.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }


    @GetMapping
    public List<Like> getAllLikes (@RequestParam Optional<Long> userId,@RequestParam Optional<Long> postId){
        return likeService.getAllLikesWithParam(userId,postId);
    }

    @GetMapping("/{likeId}")
    public Like getOneLike (@PathVariable("likeId") Long likeId) {
        return likeService.getOneLike(likeId);
    }

    @PostMapping
    public Like createOneLike (@RequestBody LikeDtoForCreate request) {
        return likeService.createOneLike(request);
    }

    @DeleteMapping("/{likeId}")
    public void deleteOneLike (@PathVariable("likeId") Long likeId) {
        likeService.deleteOneLike(likeId);
    }
}
