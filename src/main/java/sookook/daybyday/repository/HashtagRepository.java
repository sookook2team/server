package sookook.daybyday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sookook.daybyday.entity.Hashtag;
import sookook.daybyday.entity.Member;

import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    boolean existsByHashtag(String content);
    Hashtag findByHashtag(String name);
}
