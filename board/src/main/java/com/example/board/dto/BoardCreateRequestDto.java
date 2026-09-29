package com.example.board.dto;

import lombok.Data;

@Data
public class BoardCreateRequestDto {
    private String title;
    private String content;
    private String writer;
}
