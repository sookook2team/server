package sookook.daybyday.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sookook.daybyday.entity.Category;
import sookook.daybyday.entity.Post;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

// 연도와 월을 보내주는 DTO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostGetDto {
    private Integer year;
    private Integer month;


    public PostGetDto(Post post) {
        year = post.getDate().getYear();
        month = post.getDate().getMonthValue();
    }

}
