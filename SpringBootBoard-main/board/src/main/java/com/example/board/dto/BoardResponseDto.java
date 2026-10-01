package com.example.board.dto;


import lombok.Data;

@Data
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private String writer;

    public BoardResponseDto(
            Long id,
            String title,
            String content,
            String writer
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
