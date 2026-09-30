package com.example.board.dto;

import com.example.board.entity.Board;
import lombok.Data;

@Data
public class BoardCreateRequestDto {
    private String title;
    private String content;
    private String writer;

    public Board toEntity(){
        return Board.builder()    // 생성자랑 똑같음.
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }
}
