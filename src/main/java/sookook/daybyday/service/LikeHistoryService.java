package sookook.daybyday.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sookook.daybyday.entity.LikeHistory;
import sookook.daybyday.entity.Post;
import sookook.daybyday.repository.LikeHistoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeHistoryService {

    private final LikeHistoryRepository likeHistoryRepository;

    // 사용자별 좋아요 게시글 조회
    @Transactional(readOnly = true)
    public List<LikeHistory> getLikeByMemberId(Long memberId) {
        return likeHistoryRepository.findByMemberId(memberId);
    }


}
