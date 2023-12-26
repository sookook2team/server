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

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false)
    private String password;

    private String profileImage;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    public static Member createUser(String email,
                                    String password,
                                    String memberName) {

        Member member = new Member();

        member.setEmail(email);
        member.setPassword(password);
        member.setMemberName(memberName);
        return member;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }
}
