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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Hashtag> hashtags = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<File> files = new ArrayList<>();

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
        post.setMember(member);

        return post;
    }

    public void setMember(Member member) {
        this.member = member;
        member.getPosts().add(this);
    }
}
