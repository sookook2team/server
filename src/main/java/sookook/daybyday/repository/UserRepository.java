package sookook.daybyday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import sookook.daybyday.entity.Member;

public interface UserRepository extends JpaRepository<Member, Long> {
}
