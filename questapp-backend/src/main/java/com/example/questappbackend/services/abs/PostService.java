package com.example.questappbackend.services.abs;

import com.example.questappbackend.dto.PostDtoForCreate;
import com.example.questappbackend.dto.PostDtoForUpdate;
import com.example.questappbackend.dto.responses.PostDtoResponse;
import com.example.questappbackend.entities.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<PostDtoResponse> getAllPost(Optional<Long> userId);

    Post getOnePost(Long postId);

    Post createOnePost(PostDtoForCreate postRequest);

    Post updateOnePost (Long postId, PostDtoForUpdate updatePost);

    void deleteOnePost(Long postId);
}
