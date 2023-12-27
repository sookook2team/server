package sookook.daybyday.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Entity
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    public static Comment createComment(
                                    String content) {
                                    //Member member

        Comment comment = new Comment();
        //comment.setId(id);
        comment.setContent(content);
        //comment.setMember(member);
        return comment;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")

    private Member member;

    public void setPost(Post post){
        this.post = post;
        post.getComments().add(this);

    }


}
