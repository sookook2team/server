package sookook.daybyday.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sookook.daybyday.entity.Comment;
import sookook.daybyday.entity.Member;
import sookook.daybyday.entity.Post;
import sookook.daybyday.repository.CommentRepository;
import sookook.daybyday.repository.PostRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // 답글 생성
    public Comment create(String content, Member member, Post post)
    {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setPost(post);
        comment.setMember(member);
        commentRepository.save(comment);
        return comment;
    }

    // 답글 조회
    @Transactional(readOnly = true)
    public Comment findById(Long id) {
        return commentRepository.findById(id).get();
    }

    // 답글 삭제
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
