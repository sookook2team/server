package sookook.daybyday.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity  // 설정 클래스
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String title;

    @Column(nullable = false)
    private String content;

    private LocalDate date;

    private Integer views;

    private Integer likes;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<File> files = new ArrayList<>();

    public static Post createPost(String title,
                                  String content,
                                  LocalDate date,
                                  Member member) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setDate(date);
        post.setMember(member);
        post.setLikes(0);
        post.setViews(0);

        return post;
    }

    public void setMember(Member member) {
        this.member = member;
        member.getPosts().add(this);
    }
}
