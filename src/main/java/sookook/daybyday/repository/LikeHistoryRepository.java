package sookook.daybyday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sookook.daybyday.entity.LikeHistory;
import sookook.daybyday.entity.Member;
import sookook.daybyday.entity.Post;

import java.util.List;
import java.util.Optional;

public interface LikeHistoryRepository extends JpaRepository<LikeHistory, Long> {

    @Query("SELECT lh FROM LikeHistory lh WHERE lh.member = :member")
    List<LikeHistory> findLikeHistoriesWithMemberAndPostByMember(@Param("member") Member member);

    @Query("SELECT lh FROM LikeHistory lh WHERE lh.post.id = :postId and lh.member.id = :memberId")
    Optional<LikeHistory> findLikeHistoriesByPostId(@Param("postId") Long postId, @Param("memberId") Long memberId);
}
