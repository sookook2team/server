package sookook.daybyday.controller;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sookook.daybyday.entity.Member;
import sookook.daybyday.entity.Post;
import sookook.daybyday.entity.dto.PostDto;
import sookook.daybyday.service.MemberService;
import sookook.daybyday.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    /**
     * 날짜별 게시글 조회
     */
    @GetMapping("/")
    public List<PostDto> postsByMonth(@RequestParam(required = true) Integer year,
                                      @RequestParam(required = true) Integer month) {
        List<Post> postsByMonth = postService.getPostsByMonth(year, month);

        return postsByMonth.stream().map(PostDto::new).toList();
    }

    /**
     * 내 게시글 조회
     */
    @GetMapping("/my")
    public List<PostDto> myPosts(HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");

        List<Post> postsByMemberId = postService.getPostsByMemberId(memberId);

        return postsByMemberId.stream().map(PostDto::new).toList();
    }

    /** 게시글 생성 */
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
