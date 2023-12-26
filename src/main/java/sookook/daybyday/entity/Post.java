package sookook.daybyday.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity  // 설정 클래스
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // createdAt 필드 추가
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(length = 20)
    private String title;

    @Column(nullable = false)
    private String content;

    private Date date;

    private Integer views;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static void createPost(String title,
                                  String content,
                                  Date date) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setDate(date);

    }
}
