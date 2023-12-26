package sookook.daybyday.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sookook.daybyday.entity.Hashtag;

@Getter @Setter
@NoArgsConstructor
public class HashtagDto {
    private String name;

    public HashtagDto(Hashtag hashtag) {
        this.name = hashtag.getName();
    }
}
