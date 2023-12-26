package sookook.daybyday.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sookook.daybyday.entity.Member;
import sookook.daybyday.service.MemberService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberControllerTest {

    @Autowired private MemberService memberService;
    @Autowired private HttpSession session;
    @Autowired private HttpServletRequest request;

    @Test
    @DisplayName("세션값 확인")
    public void 세션값_확인() throws Exception {
        // given
        Member member = memberService.createMember("asdf", "asdf", "asdf");
        // when
//        Member login = memberService.login("asdf", "asdf");
        request.getSession().setAttribute("memberEmail", "asdf");
        // then
        assertEquals("asdf", session.getAttribute("memberEmail"));
    }

    @Test
    @DisplayName("로그아웃")
    public void 로그아웃() throws Exception {
        // given
        Member member = memberService.createMember("asdf", "asdf", "asdf");
//        Member login = memberService.login("asdf", "asdf");
        request.getSession().setAttribute("memberEmail", "asdf");

        // when
        session.removeAttribute("asdf");
        // then
        assertEquals("asdf", session.getAttribute("memberEmail"));
    }

    @Test
    @DisplayName("")
    public void 회원_탈퇴() throws Exception {
        // given
        Member member = memberService.createMember("asdf", "asdf", "asdf");
        // when
//        Member login = memberService.login("asdf", "asdf");
        request.getSession().setAttribute("memberEmail", "asdf");

        memberService.deleteMember(member);
        // then
        assertNull(member);
    }
}