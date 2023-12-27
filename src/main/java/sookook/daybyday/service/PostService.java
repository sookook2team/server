package sookook.daybyday.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sookook.daybyday.entity.*;
import sookook.daybyday.entity.dto.PostDto;
import sookook.daybyday.repository.PostRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final PostRepository postRepository;
    private final FileService fileService;

    /**
     * 게시글 생성
     * @return
     */

    public void create(String title,
                       String content,
                       LocalDate date,
                       Member member,
                       List<String> hashtags,
                       List<MultipartFile> files) {

        try {
            Post post = Post.createPost(title, content, date, member);
            postRepository.save(post);

            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                String fileUrl = "https://" +
                        bucket + "/test/" + fileName;

                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType(file.getContentType());
                metadata.setContentLength(file.getSize());

                amazonS3.putObject(bucket, fileName, file.getInputStream(), metadata);

                File fileAttr = fileService.create(fileUrl);
                fileAttr.setPost(post);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public List<Post> getPostsByMonth(Integer year, Integer month) {
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
