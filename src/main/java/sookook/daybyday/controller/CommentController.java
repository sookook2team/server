package sookook.daybyday.controller;

import com.amazonaws.services.kms.model.NotFoundException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sookook.daybyday.entity.Category;
import sookook.daybyday.entity.Comment;
import sookook.daybyday.entity.Member;
import sookook.daybyday.entity.Post;
import sookook.daybyday.entity.dto.CommentDto;
import sookook.daybyday.repository.CommentRepository;
import sookook.daybyday.repository.PostRepository;
import sookook.daybyday.service.CommentService;
import sookook.daybyday.service.MemberService;
import sookook.daybyday.service.PostService;

import javax.sound.sampled.Port;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final PostRepository postRepository;
    private final PostService postService;
    private final CommentService commentService;
    private final HttpSession session;
    private final MemberService memberService;

    // 해당 게시글 답글 생성
    @PostMapping("/create/{postId}")
    public String createComment(
            @RequestParam Long postId,
            @RequestParam String content) {
        Post post = postService.findById(postId);
        try {
            Long memberId = (Long) session.getAttribute("memberId");
            Member member = memberService.findById(memberId);
            commentService.create(content, member, post);
            return "답글 생성 성공";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 끝
    }


    // 해당 게시글 답글 삭제
    @DeleteMapping("/")
    public String deleteComment(@RequestParam Long commentId) {
        try {
            Comment comment = commentService.findById(commentId);
            commentService.deleteComment(comment);
            return "답글 삭제 성공";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // 게시글 답변들 조회
    @GetMapping("/")
    public List<Comment> viewComments(@RequestParam Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            List<Comment> commentList = post.getComments();
            return commentList;
        } else {
            // 게시글이 존재하지 않는 경우에 대한 예외 처리
            throw new NotFoundException("해당 ID의 게시글을 찾을 수 없습니다.");
        }
    }


}
