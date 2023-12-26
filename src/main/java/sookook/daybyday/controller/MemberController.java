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
public class MemberController {

    private final MemberService memberService;
    private final HttpSession session;

    @PostMapping("/signup")
    public String signup(@RequestBody SignDto dto) {
        try {
            Member member = memberService.createMember(dto.getEmail(), dto.getUsername(), dto.getPassword());
            log.info(String.valueOf(dto));
            return "{ msg : 성공, redirect: login 화면으로! }";
        } catch (Exception e) {
            throw new IllegalStateException("잘못된 접근입니다.");
        }
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, @RequestBody SignDto dto) {
        try {
            Member login = memberService.login(dto.getEmail(), dto.getPassword());

            request.getSession().setAttribute("memberEmail", dto.getEmail());

            return "{ msg : 로그인 성공}";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/")
    public String delete() {
        try {
            session.removeAttribute("memberEmail");
            return "회원 탈퇴 성공";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
