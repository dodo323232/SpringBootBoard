package com.example.board.dto;

import lombok.Data;

@Data
public class BoardUpdateRequestDto {
    private String title;
    private String content;
}
