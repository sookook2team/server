package sookook.daybyday.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sookook.daybyday.entity.Comment;
import sookook.daybyday.repository.CommentRepository;

import java.awt.*;
import java.util.Optional;

import static sookook.daybyday.entity.Comment.createComment;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    // 서비스에서는 DB에 CRUD를 한다.
    public Comment create(Long id, String content){
        Comment comment = createComment(content);
        return commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public Optional<Comment> read(Comment comment){
        return commentRepository.findById(comment.getId());
    }

    public void delete(Comment comment){

        commentRepository.delete(comment);
    }

    public Comment findById(Long commentID){
        return commentRepository.findById(commentID).get();
    }

    public Comment save(Comment comment){
        return commentRepository.save(comment);

    }

//    public Comment findById(Long MemberID){
//        return commentRepository.findById(commentID).get();
//    }
//


}
