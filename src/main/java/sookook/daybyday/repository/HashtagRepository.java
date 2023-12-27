package sookook.daybyday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sookook.daybyday.entity.Hashtag;

import java.util.List;


public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    // post에 hashtag리스트를 조회함.
    @Query("SELECT t FROM Hashtag t WHERE t.post.id = :post_id")
    List<Hashtag> findByPostId(@Param("post_id") Long post_id);

}

