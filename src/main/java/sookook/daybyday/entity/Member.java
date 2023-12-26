package sookook.daybyday.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity  // 설정 클래스
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) //
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String profileImage;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    public static Member createUser(String email,
                                    String password,
                                    String username) {

        Member member = new Member();

        member.email = email;
        member.password = password;
        member.username = username;
        return member;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }
}
