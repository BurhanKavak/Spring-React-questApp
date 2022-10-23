package com.example.questappbackend.dto;

import lombok.Data;

@Data
public class CommentDtoForCreate {

    private Long id;

    private Long userId;

    private Long postId;

    private String text;


}
