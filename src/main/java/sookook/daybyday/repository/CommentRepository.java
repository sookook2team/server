package sookook.daybyday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sookook.daybyday.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
