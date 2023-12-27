package sookook.daybyday.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sookook.daybyday.entity.LikeHistory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
public class LikePostDto {
    private Long id;
    private String title;
    private String content;
    private LocalDate date;
    private List<FilesDto> files;
    private Integer views;
    private Integer likes;
    private String username;

    public LikePostDto(LikeHistory likeHistory) {
        id = likeHistory.getPost().getId();
        title = likeHistory.getPost().getTitle();
        content = likeHistory.getPost().getContent();
        date = likeHistory.getPost().getDate();
        files = likeHistory.getPost().getFiles().stream().map(FilesDto::new).collect(Collectors.toList());
        views = likeHistory.getPost().getViews();
        likes = likeHistory.getPost().getLikes();
        username = likeHistory.getMember().getUsername();
    }
}
