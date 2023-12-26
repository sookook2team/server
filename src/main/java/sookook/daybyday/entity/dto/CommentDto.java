package sookook.daybyday.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sookook.daybyday.entity.Comment;

@Getter @Setter
@NoArgsConstructor
public class CommentDto {
    private String content;
    private String username;
    private String profile;

    public CommentDto(Comment comment) {
        content = comment.getContent();
        username = comment.getMember().getUsername();
        profile = comment.getMember().getProfileImage();
    }
}
