package sookook.daybyday.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sookook.daybyday.entity.Hashtag;
import sookook.daybyday.repository.HashtagRepository;
import sookook.daybyday.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HashTagService {

    private final HashtagRepository hashtagRepository;
    private final PostRepository postRepository;
    private Hashtag hashtag;


    // 해시태그 조회
    // 해당 post에 저장된 해시태그 리스트 조회
    public List<Hashtag> getHashtags(Long postId) {
        return hashtagRepository.findByPostId(postId);
    }

    // 해시태그 생성
    // 이미 있다면 -> 있는 것 가져오기
    // 없다면 -> 해시태그 입력 후 입력값으로 새로 생성하기
    // 입력값은 띄어쓰기로 구분할 수 있음.
    public Hashtag[] createHashtag(Long postId, String content) {
        // 존재하는 post의 해시태그 리스트
        List<Hashtag> existingHashtags = getHashtags(postId);

        // 띄어쓰기로 구분된 해시태그들을 분리
        String[] hashtagArray = content.split("\\s+");
        List<Hashtag> hashtags = new ArrayList<>();

        for (String hashtagContent : hashtagArray) {
            // 이미 존재하는 해시태그인지 확인
            boolean exists = existingHashtags.stream()
                    .anyMatch(hashtag -> hashtag.getName().equals(hashtagContent));

            if (exists) {
                // 이미 존재하는 해시태그라면 해당 해시태그 가져오기
                Hashtag existingHashtag = existingHashtags.stream()
                        .filter(hashtag -> hashtag.getName().equals(hashtagContent))
                        .findFirst()
                        .orElse(null);
                // 기존의 해시태그를 사용하거나 추가적인 로직 수행
                hashtags.add(existingHashtag); // 이미 존재하는 경우 리스트에 추가
            } else {
                // 존재하지 않는 해시태그라면 새로 생성 후 저장
                Hashtag hashtag = Hashtag.createHashtag(hashtagContent);
                // 추가적인 로직 수행
                hashtag.setPost(postRepository.findById(postId).orElse(null));
                hashtags.add(hashtagRepository.save(hashtag)); // 새로 생성한 경우 리스트에 추가
            }
        }

        // 배열로 변환
        return hashtags.toArray(new Hashtag[0]);
    }

    // 해시태그 삭제
    public void delete(Hashtag hashtag) {
        hashtagRepository.delete(hashtag);
    }

}