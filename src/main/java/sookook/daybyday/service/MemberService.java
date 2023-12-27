package sookook.daybyday.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sookook.daybyday.entity.Member;
import sookook.daybyday.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    // 회원 정보 조회는 session에서
    // 회원 가입은 Member entity에서 함.
    private final MemberRepository memberRepository;

    // 회원 로그인
    public Member login(String email, String password)
    {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if (optionalMember.isEmpty()) {
            throw new IllegalStateException("없는 회원입니다.");
        }

        Member member = optionalMember.get();
        if (!member.getPassword().equals(password)) {
            throw new IllegalStateException("비밀번호가 틀립니다.");
        }
        return member;
    }

    // 회원 가입
    public Member createMember(String email, String username, String password)
    {
        if (!emailDoubleCheck(email)) {
            Member member = Member.createMember(email, username, password);
            return memberRepository.save(member);
        } else {
            throw new IllegalStateException("이미 있는 회원입니다.");
        }
    }
    // 회원 정보 수정
    public Member updateMember(Long memberId, String email, String password, String username)
    {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        try {
            Member member = optionalMember.get();

            member.setEmail(email);
            member.setPassword(password);
            member.setUsername(username);

            return memberRepository.save(member);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 회원 탈퇴
    public void deleteMember(Member member) {
        memberRepository.delete(member);
    }

    // 회원 가입 시 이메일 중복확인
    public boolean emailDoubleCheck(String email) {
        return memberRepository.existsByEmail(email);
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId).get();
    }

}