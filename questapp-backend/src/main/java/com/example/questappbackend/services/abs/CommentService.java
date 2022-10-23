package com.example.questappbackend.services.abs;

import com.example.questappbackend.dto.CommentDtoForCreate;
import com.example.questappbackend.dto.CommentDtoForUpdate;
import com.example.questappbackend.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    List<Comment> getAllCommentsWithParam (Optional<Long> userId, Optional<Long> postId);

    Comment getOneComment (Long commentId);

    Comment createOneComment (CommentDtoForCreate request);

    Comment updateOneComment (Long commentId, CommentDtoForUpdate request);

    void deleteOneComment (Long commentId);

}
