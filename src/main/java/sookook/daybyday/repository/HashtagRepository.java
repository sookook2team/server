package sookook.daybyday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sookook.daybyday.entity.Hashtag;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
}
