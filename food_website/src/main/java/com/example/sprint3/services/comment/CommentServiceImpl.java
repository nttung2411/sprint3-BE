package com.example.sprint3.services.comment;

import com.example.sprint3.models.Comment;
import com.example.sprint3.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentRepository commentRepository;

    @Override
    public void saveComment(Comment comment) {
        String content = decodeContent(comment.getCommentContent());
        comment.setCommentContent(content);
        comment.setCommentTime(new Timestamp(System.currentTimeMillis()));
        commentRepository.save(comment);
    }

    public String decodeContent(String content){
        String contentDecode = null;
        try {
            contentDecode = URLDecoder.decode(content, "UTF-8");
        }catch (UnsupportedEncodingException e){
            return contentDecode;
        }
        return contentDecode;
    }
}
