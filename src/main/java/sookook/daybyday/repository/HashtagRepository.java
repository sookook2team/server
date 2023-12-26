package sookook.daybyday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sookook.daybyday.entity.Hashtag;
import sookook.daybyday.entity.Member;
import sookook.daybyday.entity.Post;

import java.util.List;
import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    boolean existsByHashtag(String content);

    @Query("SELECT t FROM Hashtag t WHERE t.name = :hashtag_name")
    Hashtag findByHashtagName(@Param("hashtag_name") String hashtagName);

    @Query("SELECT t FROM Hashtag t WHERE t.name = :hashtag_name")
    Hashtag findByHashtagName(@Param("hashtag_name") String hashtagName);
}

