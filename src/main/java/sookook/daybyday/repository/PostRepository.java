package sookook.daybyday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sookook.daybyday.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
