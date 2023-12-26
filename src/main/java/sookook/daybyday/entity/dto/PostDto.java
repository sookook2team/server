package sookook.daybyday.entity.dto;

import lombok.Getter;
import lombok.Setter;
import sookook.daybyday.entity.Category;
import sookook.daybyday.entity.Hashtag;
import sookook.daybyday.entity.Post;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class PostDto {
    private String title;
    private String content;
    private LocalDate date;
    private Category category;
    private List<HashtagDto> hashtags;

    public PostDto() {
    }

    public PostDto(Post post) {
        title = post.getTitle();
        content = post.getContent();
        date = post.getDate();
        category = post.getCategory();
        hashtags = post.getHashtags().stream()
                .map(HashtagDto::new)
                .collect(Collectors.toList());
    }
}
