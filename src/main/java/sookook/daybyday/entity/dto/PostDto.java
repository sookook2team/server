package sookook.daybyday.entity.dto;

import lombok.Getter;
import lombok.Setter;
import sookook.daybyday.entity.Category;
import sookook.daybyday.entity.Hashtag;

import java.time.LocalDate;

@Getter @Setter
public class PostDto {
    private String title;
    private String content;
    private LocalDate date;
    private Category category;
    private Hashtag hashtag;

    public PostDto() {
    }

    public PostDto(String title,
                   String content,
                   LocalDate date,
                   Category category) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.category = category;
    }
}
