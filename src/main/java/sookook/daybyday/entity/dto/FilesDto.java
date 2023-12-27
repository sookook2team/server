package sookook.daybyday.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sookook.daybyday.entity.File;

@Getter @Setter
@NoArgsConstructor
public class FilesDto {
    private String url;

    public FilesDto(File file) {
        url = file.getUrl();
    }
}
