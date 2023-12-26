package sookook.daybyday.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sookook.daybyday.repository.LikeHistoryRepository;

@Service
@RequiredArgsConstructor
public class LikeHistoryService {

    private final LikeHistoryRepository likeHistoryRepository;


}
