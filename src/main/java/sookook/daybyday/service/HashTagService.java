package sookook.daybyday.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sookook.daybyday.entity.Hashtag;
import sookook.daybyday.repository.HashtagRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HashTagService {

    private final HashtagRepository hashtagRepository;
    private Hashtag hashtag;

    // 해시태그 생성
    // 이미 있다면 -> 있는 것 가져오기
    // 없다면 -> 해시태그 입력 후 입력값으로 새로 생성하기 -> 단 해시태그는 계속해서 입력할 수 있다.
    public Hashtag createHashtag(String content)
    {
        if (hashtagRepository.existsByHashtag(content))
        {
            return hashtagRepository.findByHashtagName(content);
        }
        else
        {
            Hashtag hashtag = Hashtag.createHashtag(content);
            return hashtagRepository.save(hashtag);
        }
    }

    // 해시태그(저장된 리스트) 조회 -> Post의 <List>hashtags

}
