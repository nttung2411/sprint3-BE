package com.example.sprint3.services.comment;

import com.example.sprint3.models.Comment;

import java.util.List;

public interface CommentService {
    void saveComment(Comment comment);
    List<Comment> getCommentsOfFood(Integer foodId);
}
