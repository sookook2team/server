package sookook.daybyday.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Member;

@Getter @Setter
@NoArgsConstructor
public class SignDto {
    private String email;
    private String password;
    private String username;

    public SignDto(String email, String password) {
        this.email = email;
        this.password = password;
        this.username = null;
    }

    public SignDto(Member member) {
        this.email = email;
        this.password = password;
        this.username = username;
    }
}
