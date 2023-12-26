package sookook.daybyday.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity  // 설정 클래스
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String profileImage;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    public static Member createMember(String email,
                                      String password,
                                      String username) {


        Member member = new Member();
        member.setEmail(email);
        member.setPassword(password);
        member.setUsername(username);
        return member;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }
}
