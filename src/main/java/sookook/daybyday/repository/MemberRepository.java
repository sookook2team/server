package sookook.daybyday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sookook.daybyday.entity.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);
}