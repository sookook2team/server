package sookook.daybyday.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sookook.daybyday.entity.Member;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class PostByMemberDto {
    private String username;
    private List<PostDto> posts;

    public PostByMemberDto(Member member, List<PostDto> posts) {
        username = member.getUsername();
        this.posts = posts;
    }
}