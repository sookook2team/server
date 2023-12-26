package sookook.daybyday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sookook.daybyday.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
