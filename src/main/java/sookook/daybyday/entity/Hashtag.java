package sookook.daybyday.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Hashtag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id")
    private Long id;

    // hashtag의 내용은 15자를 넘어가면 안됨. 총 3글자씩 4단어까지 입력 가능
    // 3자 + 3자 + 3자 + 3자
    @Column(nullable = false, length = 16)
    private String name;

    public static Hashtag createHashtag(String content) {

        Hashtag hashtag = new Hashtag();
        hashtag.setName(content);
        return hashtag;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;

}
