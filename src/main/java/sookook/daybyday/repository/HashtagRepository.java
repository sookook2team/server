package sookook.daybyday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sookook.daybyday.entity.Hashtag;


public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    // post에 hashtag리스트를 조회함.
    @Query("SELECT t FROM Hashtag t WHERE t.post.id = :post_id")
    List<Hashtag> findByPostId(@Param("post_id") Long post_id);

}

