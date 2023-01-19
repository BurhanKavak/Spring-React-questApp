package com.example.questappbackend.dto.responses;

import com.example.questappbackend.entities.Like;
import lombok.Data;

@Data
public class LikeDtoResponse {

    private Long id;

    private Long userId;

    private Long postId;


    public LikeDtoResponse(Like entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.postId = entity.getPost().getId();
    }
}
