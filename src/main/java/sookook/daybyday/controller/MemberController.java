package sookook.daybyday.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sookook.daybyday.entity.Member;
import sookook.daybyday.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {

}
