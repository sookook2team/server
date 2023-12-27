package sookook.daybyday.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommentDto {
    public CommentDto(String content) {
        //this.commentId = commentId;
        this.content = content;
    }

    //private Long commentId;
    private String content;

    // fetch
    //public CommentDto()


}
