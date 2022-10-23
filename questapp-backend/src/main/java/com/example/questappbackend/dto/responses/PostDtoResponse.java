package com.example.questappbackend.dto.responses;

import com.example.questappbackend.entities.Post;
import lombok.Data;

@Data
public class PostDtoResponse {

    private Long id;

    private Long userId;

    private String userName;

    private String text;

    private String title;


    public PostDtoResponse (Post entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.text = entity.getText();
        this.title = entity.getTitle();

    }
}
