package com.example.board.service;

import com.example.board.dto.BoardCreateRequestDto;
import com.example.board.dto.BoardUpdateRequestDto;
import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    // 서비스는 dto에서 toEntity함으로써 얻은 변수들로 Entity에 넣고
    // 이 Entity를 레파지토리에 저장을 한다
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardCreateRequestDto requestDto){
        Board board = requestDto.toEntity();
        Board saveBoard = boardRepository.save(board);

        return saveBoard.getId();
    }

    // 1. 게시글 수정 (UPDATE)

    @Transactional
    public Long update(Long id, BoardUpdateRequestDto requestDto) {
        // DB에서 수정할 데이터를 먼저 찾아서 가져옵니다. ( 없으면 에러 발생 )
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        // 가져온 Entity의 값을 새로운 데이터로 변경합니다.
        board.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public Long delete(Long id){
        Board board = boardRepository.findById(id) // optional로 받음 (null이 있을 수 있으니깐)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        boardRepository.delete(board);

        return id;
    }
}
