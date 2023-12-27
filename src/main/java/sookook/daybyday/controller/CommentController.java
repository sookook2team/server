package sookook.daybyday.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sookook.daybyday.entity.Comment;
import sookook.daybyday.entity.Post;
import sookook.daybyday.entity.dto.CommentDto;
import sookook.daybyday.repository.CommentRepository;
import sookook.daybyday.service.CommentService;
import sookook.daybyday.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final PostService postService;
    private final CommentService commentService;


    // 해당 게시글의 댓글 생성
    @PostMapping("/create/{postId}")
    public void createComment(@PathVariable Long postId,
                              @RequestBody CommentDto commentDto) {
        // 댓글 생성
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        commentService.save(comment);
    }

    @DeleteMapping("/{}")
    public void deleteComment(@PathVariable Long postId,
                              @PathVariable Long commentId) {

        Comment comment = commentService.findById(commentId);
        if (commentService.findById(commentId) != null) commentService.delete(comment);

    }

}