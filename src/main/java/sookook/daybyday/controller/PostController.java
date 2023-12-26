package sookook.daybyday.controller;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sookook.daybyday.entity.Member;
import sookook.daybyday.entity.Post;
import sookook.daybyday.entity.dto.PostDto;
import sookook.daybyday.service.MemberService;
import sookook.daybyday.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @PostMapping("/create")
    public PostDto createPost(HttpSession session, @RequestBody PostDto dto) {
        try {
            Long memberId = (Long) session.getAttribute("memberId");
            Member member = memberService.findById(memberId);

            postService.create(dto.getTitle(), dto.getContent(), dto.getDate(), dto.getCategory() ,member);

            return dto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
