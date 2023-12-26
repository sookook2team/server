package sookook.daybyday.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sookook.daybyday.entity.Member;
import sookook.daybyday.entity.dto.SignDto;
import sookook.daybyday.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final HttpSession session;

    @PostMapping("/signup")
    public String signup(@RequestBody SignDto dto) {
        try {
            Member member = memberService.createMember(dto.getEmail(), dto.getUsername(), dto.getPassword());
            return "{ msg : 성공, redirect: login 화면으로! }";
        } catch (Exception e) {
            throw new IllegalStateException("잘못된 접근입니다.");
        }
    }

    @PostMapping("/login")
    public Long login(HttpServletRequest request, @RequestBody SignDto dto) {
        try {
            Long sessionId = memberService.login(dto.getEmail(), dto.getPassword());

            request.getSession().setAttribute("memberId", sessionId);
            log.info("로그인 성공!");
            return sessionId;
        } catch (Exception e) {
            log.info("로그인 실패!");
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/")
    public String delete() {
        try {
            Long memberId = (Long) session.getAttribute("memberId");
            Member member = memberService.findById(memberId);
            memberService.deleteMember(member);
            session.removeAttribute("memberEmail");
            return "회원 탈퇴 성공";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
