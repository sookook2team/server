package sookook.daybyday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sookook.daybyday.entity.Hashtag;
import sookook.daybyday.entity.LikeHistory;

import java.util.List;

public interface LikeHistoryRepository extends JpaRepository<LikeHistory, Long> {
    @Query("SELECT l FROM LikeHistory l WHERE l.member.id= :member_id")
    List<LikeHistory> findByMemberId(@Param("member_id") Long member_id);
}
