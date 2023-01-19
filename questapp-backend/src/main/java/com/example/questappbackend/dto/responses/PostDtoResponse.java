package com.example.questappbackend.dto.responses;

import com.example.questappbackend.entities.Like;
import com.example.questappbackend.entities.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostDtoResponse {

    private Long id;

    private Long userId;

    private String userName;

    private String text;

    private String title;

    private List<LikeDtoResponse> postLikes;


    public PostDtoResponse (Post entity, List<LikeDtoResponse> postLikes) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.text = entity.getText();
        this.title = entity.getTitle();
        this.postLikes = postLikes;

    }
}
