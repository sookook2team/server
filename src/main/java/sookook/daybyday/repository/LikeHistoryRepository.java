package sookook.daybyday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sookook.daybyday.entity.LikeHistory;

public interface LikeHistoryRepository extends JpaRepository<LikeHistory, Long> {
}
