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

    // 회원 가입
    public Member createUser(String email, String username, String password)
    {
        if (emailDoubleCheck(email))
        {
            Member member = Member.createUser(email, username, password);
            return memberRepository.save(member);
        }
        else
        {
            Exception e = null;
            throw new RuntimeException(e);
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
    public void deleteMember(Member member)
    {
        memberRepository.delete(member);
    }

    // 회원 가입 시 이메일 중복확인
    public boolean emailDoubleCheck(String email)
    {
        return memberRepository.existsByEmail(email);
    }

}
