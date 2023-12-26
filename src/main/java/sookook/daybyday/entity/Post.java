package sookook.daybyday.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity  // 설정 클래스
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // createdAt 필드 추가
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(length = 20)
    private String title;

    @Column(nullable = false)
    private String content;

    private LocalDate date;

    @ColumnDefault("0")
    private Integer views;

    @ColumnDefault("0")
    private Integer likes;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Post createPost(String title,
                                  String content,
                                  LocalDate date,
                                  Category category,
                                  Member member) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setDate(date);
        post.setCategory(category);
        member.addPost(post);

        return post;
    }
}
