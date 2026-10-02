package com.example.board.dto.member;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequestDto {
    private String id;
    private String password;
    private String nickname;

    
}
