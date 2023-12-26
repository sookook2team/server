package sookook.daybyday.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sookook.daybyday.entity.Comment;
import sookook.daybyday.entity.Post;
import sookook.daybyday.entity.dto.CommentDto;
import sookook.daybyday.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final PostService postService;


    // 해당 게시글의 댓글 생성
    @PostMapping("/create/{postId}")
    public void createComment(@PathVariable Long postId,
            @RequestBody CommentDto commentDto) {
        // 댓글 생성
        // content.setPost()
        Post post = postService.findById(postId);

        // 끝
    }

    @DeleteMapping("/{}")
    public void deleteComment() {

    }

}
