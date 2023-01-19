package com.example.questappbackend.services.abs;


import com.example.questappbackend.dto.LikeDtoForCreate;
import com.example.questappbackend.dto.responses.LikeDtoResponse;
import com.example.questappbackend.entities.Like;

import java.util.List;
import java.util.Optional;

public interface LikeService  {

    List<LikeDtoResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId);

    Like getOneLike (Long likeId);

    Like createOneLike (LikeDtoForCreate request);

    void deleteOneLike (Long likeId);
}
