package sookook.daybyday.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sookook.daybyday.service.PostService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostControllerTest {

    @Autowired private PostService postService;

    @Test
    @DisplayName("게시글 생성")
    public void 게시글_생성() throws Exception {
        // given
        postService.create("안녕하세요", );
        // when

        // then
    }

}