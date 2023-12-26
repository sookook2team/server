package sookook.daybyday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sookook.daybyday.entity.Member;

public interface UserRepository extends JpaRepository<Member, Long> {
}
