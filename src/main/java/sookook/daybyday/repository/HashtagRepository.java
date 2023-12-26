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

//    @Query("SELECT t FROM Hashtag t WHERE t.name = :hashtag_name")
//    Hashtag findByHashtagName(@Param("hashtag_name") String hashtagName);

    // post에 hashtag리스트를 조회함.
    @Query("SELECT t FROM Hashtag t WHERE t.post.id = :post_id")
    List<Hashtag> findByPostId(@Param("post_id") Long post_id);
}

