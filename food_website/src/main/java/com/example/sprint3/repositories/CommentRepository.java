package com.example.sprint3.repositories;

import com.example.sprint3.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("select comment from Comment comment where comment.food.foodId = :foodId")
    List<Comment> getCommentsOfFood(Integer foodId);
}
