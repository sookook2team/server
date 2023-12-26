package sookook.daybyday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sookook.daybyday.entity.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.member.id = :memberId")
    List<Post> findByMemberId(@Param("memberId") Long memberId);

    @Query("SELECT p FROM Post p WHERE FUNCTION('MONTH', p.date) = :month AND FUNCTION('YEAR', p.date) = :year")
    List<Post> findByMonth(@Param("year") int year, @Param("month") int month);

//    @Query("SELECT p FROM Post p fetch join Comment c WHERE p.member.id = :memberId AND p.title = :title")
//    List<Post> findByIdAndComments(Long memberId, String title);


}
