package sookook.daybyday.controller;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sookook.daybyday.entity.Category;
import sookook.daybyday.entity.Member;
import sookook.daybyday.entity.Post;
import sookook.daybyday.entity.dto.PostByMemberDto;
import sookook.daybyday.entity.dto.PostDto;
import sookook.daybyday.service.FileService;
import sookook.daybyday.service.MemberService;
import sookook.daybyday.service.PostService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class PostController {

    private final PostService postService;
    private final MemberService memberService;
    private final HttpSession session;
    private final FileService fileService;

    /**
     * 날짜별 게시글 조회
     */
    @GetMapping("/")
    public List<Post> postsByMonth(@RequestParam(required = true) Integer year, @RequestParam(required = true) Integer month) {
        return postService.getPostsByMonth(year, month);
    }

    /**
     * 내 게시글 조회
     */
    @GetMapping("/my")
    public PostByMemberDto myPosts() {
        Long memberId = (Long) session.getAttribute("memberId");
        Member member = memberService.findById(memberId);

        List<PostDto> list = postService.getPostsByMemberId(memberId).stream().map(PostDto::new).toList();

        return new PostByMemberDto(member, list);
    }


    /** 게시글 생성 */
//    @PostMapping("/create")
//    public void createPost(@RequestParam List<MultipartFile> files,
//                              @RequestParam String title,
//                              @RequestParam String content,
//                              @RequestParam LocalDate date,
//                              @RequestParam Category category) {
//        try {
//            Long memberId = (Long) session.getAttribute("memberId");
//            Member member = memberService.findById(memberId);
//
//            postService.create(title, content, date, category, member);
//
//            ResponseEntity<List<String>> listResponseEntity = fileService.uploadFiles(files);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    @PostMapping("/create")
    public ResponseEntity<List<String>> createPost(
            @RequestParam List<MultipartFile> files,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam LocalDate date,
            @RequestParam Category category) {
        try {
            Long memberId = (Long) session.getAttribute("memberId");
            Member member = memberService.findById(memberId);

            postService.create(title, content, date, category, member);

            // 파일 업로드 결과를 반환
            return fileService.uploadFiles(files);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


//}


}
