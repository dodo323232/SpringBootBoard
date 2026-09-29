package com.example.board;

import com.example.board.dto.BoardCreateRequestDto;
import com.example.board.dto.BoardResponseDto;
import com.example.board.dto.BoardUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @GetMapping
    public List<BoardResponseDto> getBoardList(){
        List<BoardResponseDto> boardList = new ArrayList<>();

        boardList.add(
                new BoardResponseDto(
                        1L,
                        "원신",
                        "비틱 기원",
                        "이성진"
                )
        );

        boardList.add(
                new BoardResponseDto(
                        2L,
                        "GTA6",
                        "플스 사고싶다",
                        "이썽진"
                )
        );

        return boardList;
    }

    @GetMapping("/{id}")
    public BoardResponseDto getBoard(@PathVariable Long id){
        return new BoardResponseDto(
                id,
                id+"빈 개시글",
                "내용입니다",
                "이썽성찐진"
        );
    }

    @PostMapping("/post")
    public String createBoard(@RequestBody BoardCreateRequestDto requestDto){
        System.out.println("제목: "+ requestDto.getTitle());
        System.out.println("내용: "+ requestDto.getContent());
        System.out.println("작성자: "+ requestDto.getWriter());

        return "게시글이 성공적으로 등록 되었습니다";
    }

    @PutMapping("/put/{id}")
    public String updateBoard(
            @PathVariable Long id,
            @RequestBody BoardUpdateRequestDto requestDto
    ){
        System.out.println("수정할 개시글 ID " + id);
        System.out.println("수정할 제목: "+ requestDto.getTitle());
        System.out.println("수정할 내용: "+ requestDto.getContent());

        return id + " 빈 개시글이 성공적으로 수정되었습니다.";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBoard(@PathVariable Long id){
        System.out.println("삭제할 게시글 ID : "+id);

        return id + "빈 게시글이 성공적으로 삭제되었습니다.";
    }
}













