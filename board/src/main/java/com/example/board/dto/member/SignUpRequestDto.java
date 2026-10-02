package com.example.board.dto.member;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpRequestDto {
    private String password;
    private String nickname;
    private String email;

    public SignUpRequestDto(SignUpRequestDto requestDto){
        this.nickname = requestDto.nickname;
        this.password = requestDto.password;
        this.email = requestDto.email;
    }
}
