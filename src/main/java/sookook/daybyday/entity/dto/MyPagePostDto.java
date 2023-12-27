package sookook.daybyday.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sookook.daybyday.entity.LikeHistory;
import sookook.daybyday.entity.Post;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
public class MyPagePostDto {
    private Long id;
    private String title;
    private String content;
    private LocalDate date;
    private List<FilesDto> files;
    private Integer views;
    private Integer likes;
    private Boolean hasLike;
    private String username;

    public MyPagePostDto(Post post) {
        id = post.getId();
        title = post.getTitle();
        content = post.getContent();
        date = post.getDate();
        files = post.getFiles().stream().map(FilesDto::new).collect(Collectors.toList());
        views = post.getViews();
        likes = post.getLikes();
        username = post.getMember().getUsername();
    }

    public MyPagePostDto(Post post, boolean flag) {
        id = post.getId();
        hasLike = flag;
        title = post.getTitle();
        content = post.getContent();
        date = post.getDate();
        files = post.getFiles().stream().map(FilesDto::new).collect(Collectors.toList());
        views = post.getViews();
        likes = post.getLikes();
        username = post.getMember().getUsername();
    }
}
