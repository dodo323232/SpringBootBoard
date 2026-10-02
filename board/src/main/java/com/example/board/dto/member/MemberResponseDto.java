package com.example.board.dto.member;


import com.example.board.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MemberResponseDto {
    private Long id;
    private String nickname;
    private String email;
    private String password;

    public MemberResponseDto(Member member){
        this.id = member.getNumber();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.password = member.getPassword();
    }
}
