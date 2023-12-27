package sookook.daybyday.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sookook.daybyday.entity.LikeHistory;
import sookook.daybyday.entity.Member;
import sookook.daybyday.repository.LikeHistoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeHistoryService {

    private final LikeHistoryRepository likeHistoryRepository;

    public LikeHistory create() {
        LikeHistory likeHistory = new LikeHistory();
        return likeHistory;
    }

    public void save(LikeHistory likeHistory) {
        likeHistoryRepository.save(likeHistory);
    }

    public void delete(LikeHistory likeHistory) {
        likeHistoryRepository.delete(likeHistory);
    }

    public List<LikeHistory> findByMemberLike(Member member) {
        return likeHistoryRepository.findLikeHistoriesWithMemberAndPostByMember(member);
    }

    public Optional<LikeHistory> findByPostId(Long postId, Long memberId) {
        return likeHistoryRepository.findLikeHistoriesByPostId(postId, memberId);
    }
}
