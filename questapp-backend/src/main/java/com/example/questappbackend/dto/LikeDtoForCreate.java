package com.example.questappbackend.dto;

import lombok.Data;

@Data
public class LikeDtoForCreate {

    private Long id;

    private Long userId;

    private Long postId;
}
