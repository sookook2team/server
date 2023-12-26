package sookook.daybyday.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sookook.daybyday.entity.Hashtag;
import sookook.daybyday.entity.Member;
import sookook.daybyday.repository.HashtagRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HashTagService {

    private final HashtagRepository hashtagRepository;
    private Hashtag hashtag;

    private Hashtag createHashtag(String content)
    {
        if (hashtagRepository.existsByHashtag(content))
        {
            return hashtagRepository.findByHashtag(content);
        }
        else
        {
            Hashtag hashtag = Hashtag.createHashtag(content);
            return hashtagRepository.save(hashtag);
        }
    }

}
