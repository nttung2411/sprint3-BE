package com.example.sprint3.controllers;

import com.example.sprint3.models.Comment;
import com.example.sprint3.services.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/save-comment")
    public ResponseEntity<Void> saveComment(@RequestBody Comment comment){
        try{
            if(comment == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            commentService.saveComment(comment);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
