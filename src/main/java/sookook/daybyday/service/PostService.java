package sookook.daybyday.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sookook.daybyday.entity.Category;
import sookook.daybyday.entity.Member;
import sookook.daybyday.entity.Post;
import sookook.daybyday.entity.dto.PostDto;
import sookook.daybyday.repository.PostRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;

    /**
     * 게시글 생성
     */
    public void create(String title, String content, LocalDate date, Category category , Member member) {
        Post post = Post.createPost(title, content, date, category, member);
        postRepository.save(post);
    }

    /**
     * 사용자별 게시글 조회
     */
    @Transactional(readOnly = true)
    public List<Post> getPostsByMemberId(Long memberId) {
        return postRepository.findByMemberId(memberId);
    }

    /**
     * 특정 게시글 조회
     *
     * @return
     */
    public Post findById(Long postId) {
        return postRepository.findById(postId).get();
    }


    /** 날짜별 게시글 조회 */
    @Transactional(readOnly = true)
    public List<Post> getPostsByMonth(int year, int month) {
        return postRepository.findByMonth(year, month);
    }

    /** 게시글 삭제 */
    public void delete(Post post) {
        postRepository.delete(post);
    }

    /** 게시글 수정 */
    public void update(Long postId, PostDto postDto) {
        Optional<Post> byId = postRepository.findById(postId);
        if(byId.isPresent()) {
            Post post = byId.get();
            post.setTitle(postDto.getTitle());
            post.setContent(postDto.getContent());
            post.setDate(postDto.getDate());
            post.setCategory(postDto.getCategory());
        }
    }




}
