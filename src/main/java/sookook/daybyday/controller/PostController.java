package sookook.daybyday.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sookook.daybyday.entity.LikeHistory;
import sookook.daybyday.entity.Member;
import sookook.daybyday.entity.Post;
import sookook.daybyday.entity.dto.DatedPostDto;
import sookook.daybyday.entity.dto.LikePostDto;
import sookook.daybyday.entity.dto.MyPagePostDto;
import sookook.daybyday.service.LikeHistoryService;
import sookook.daybyday.service.MemberService;
import sookook.daybyday.service.PostService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/article")
public class PostController {

    private final PostService postService;
    private final MemberService memberService;
    private final LikeHistoryService likeHistoryService;

    /**
     * 날짜별 게시글 조회
     */
    @GetMapping
    public List<DatedPostDto> postsByMonth(@RequestParam("year") Integer year,
                                           @RequestParam("month") Integer month) {

        return postService.getPostsByMonth(year, month).stream()
                .map(DatedPostDto::new).collect(Collectors.toList());
    }

    /**
     * 내 게시글 조회
     */
    @GetMapping("/my")
    public List<MyPagePostDto> myPosts(@RequestParam("memberId") String memberId) {
        Long l = Long.parseLong(memberId);
        List<Post> postsByMemberId = postService.getPostsByMemberId(l);

        return postsByMemberId.stream()
                .map(MyPagePostDto::new)
                .toList();
    }


    /**
     * 게시글 생성
     */
    @PostMapping("/create")
    public void createPost(@RequestParam("files") List<MultipartFile> files,
                           @RequestParam("title") String title,
                           @RequestParam("content") String content,
                           @RequestParam("date") LocalDate date,
                           @RequestParam("memberId") Long memberId,
                           @RequestParam("hashtags") List<String> hashtags) {
        try {
            Member member = memberService.findById(memberId);
            postService.create(title, content, date, member, hashtags, files);
            log.info(String.valueOf(member));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 좋아요 게시판
    @GetMapping("/my/like")
    public List<LikePostDto> findLikePosts(@RequestParam("memberId") String memberId) {
        Long l = Long.parseLong(memberId);
        // memberId에 해당하는 Member를 찾음
        Member member = memberService.findById(l);

        if (member != null) {
            // LikeHistoryRepository를 통해 해당 Member가 좋아요한 모든 게시물 조회
            return likeHistoryService.findByMemberLike(member).stream().map(LikePostDto::new).collect(Collectors.toList());

        }
        return null;
    }

    // 좋아요 누르기 로직
    @PostMapping("/like")
    public void updateLike(@RequestParam("postId") String postId,
                           @RequestParam("memberId") String memberId) {
        Long id = Long.parseLong(postId);
        Long l = Long.parseLong(memberId);
        Post p = postService.findById(id);
        Member m = memberService.findById(l);
        Optional<LikeHistory> post = likeHistoryService.findByPostId(id, l);
        if(post.isPresent() && p.getLikes() > 0) {
            p.setLikes(p.getLikes() - 1);
            likeHistoryService.delete(post.get());
        } else if(!post.isPresent()) {
            p.setLikes(p.getLikes() + 1);
            LikeHistory likeHistory = new LikeHistory();
            likeHistory.setPost(p);
            likeHistory.setMember(m);
            likeHistoryService.save(likeHistory);
        }
    }


    // 게시글 상세
    @GetMapping("/detail")
    public MyPagePostDto detail(@RequestParam("postId") String postId,
                                @RequestParam("memberId") String memberId) {
        Long id = Long.parseLong(postId);
        Long l = Long.parseLong(memberId);

        Post post = postService.findById(id);
        Optional<LikeHistory> bool = likeHistoryService.findByPostId(id, l);
        boolean flag = false;
        if(bool.isPresent()) {
            flag = true;
        }
        return new MyPagePostDto(post, flag);
    }


}
