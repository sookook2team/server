package sookook.daybyday.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import sookook.daybyday.entity.Category;
import sookook.daybyday.entity.Member;
import sookook.daybyday.entity.Post;
import sookook.daybyday.service.MemberService;
import sookook.daybyday.service.PostService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostControllerTest {

    @Autowired private PostService postService;
    @Autowired private MemberService memberService;
    @Autowired private HttpSession session;
    @Autowired private HttpServletRequest request;

    @Test
    @DisplayName("게시글 생성")
    @Rollback(value = false)
    public void 게시글_생성() throws Exception {
        // given
        Member member = memberService.createMember("asdf", "asdf", "asdf");

        request.getSession().setAttribute("memberId", member.getId());

//        Post post = postService.create("으하하", "안녕하신가", LocalDate.parse("2023-11-11"), Category.DAILY, member);

        // when

        // then
//        System.out.println(post);
    }

}