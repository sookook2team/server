package sookook.daybyday.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sookook.daybyday.entity.Comment;
import sookook.daybyday.entity.Member;
import sookook.daybyday.entity.Post;
import sookook.daybyday.entity.dto.CommentDto;
import sookook.daybyday.service.CommentService;
import sookook.daybyday.service.MemberService;
import sookook.daybyday.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    // 서비스에서 create하고 controller는 그것을 연결함.

    private final CommentService commentService;
    private final MemberService memberService;
    private final PostService postService;
    @PostMapping("/create")
    public String createComment(HttpSession session, @RequestBody CommentDto dto){
        Long memberId = (Long) session.getAttribute("memberId");
        Member member = memberService.findById(memberId);
        // 어떤 멤버인지 알아야하고 comment에 add memeber를 해야. 한 멤버가 여러 댓글을 만들수 있으니

        // comment dto에서 값을 가져오ㅘ서 생성하고 comment가 member를 받음
        // 댓긋릉 멤버에 속하고 comment는 각 멤버에 대해서 setMember해서
        // comment.setMeber
        Comment comment = Comment.createComment(dto.getCommentId(), dto.getContent());
        comment.setMember(member);
        return "redirect:/comment";

        //many to one : 투원인관계에서 매니쪽인관계에 넣어주고 매니인거에 자ㅣㄱ도

    }

    @GetMapping("/comment")
    public List<Comment> readComment(HttpSession httpSession){
        // 멤버 id에 해당하는 게시글을 보여준다.
        Long memberId = (Long) httpSession.getAttribute("memberId");
        Member member = memberService.findById(memberId);
        //List<Comment> comments = member.
        Post post = postService.

        // pk fk이면


    }

    @DeleteMapping("/delete")
    public void deleteComment(){

    }


}
