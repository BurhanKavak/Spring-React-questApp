package com.example.questappbackend.dto;

import lombok.Data;

@Data
public class PostDtoForCreate {

    private Long id;

    private String title;

    private String text;

    private Long userId;
}
