package sookook.daybyday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sookook.daybyday.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);
}
