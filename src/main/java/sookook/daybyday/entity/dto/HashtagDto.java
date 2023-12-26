package sookook.daybyday.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HashtagDto {

    private String name;

    public HashtagDto() {
    }

    public HashtagDto(String name)
    {
        this.name = name;
    }

}
