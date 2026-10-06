package com.example.board;

import com.example.board.dto.BoardCreateRequestDto;
import com.example.board.dto.member.LoginRequestDto;
import com.example.board.dto.member.MemberResponseDto;
import com.example.board.dto.member.SignUpRequestDto;
import com.example.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDto requestDto){
         Long createId = memberService.signUp(requestDto);

         return ResponseEntity.ok(createId+"번 계정이 생성되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<MemberResponseDto> login(@RequestBody LoginRequestDto loginDto){
        return ResponseEntity.ok(memberService.memberLogin(loginDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long id){
        MemberResponseDto dto =  memberService.findById(id);
        return ResponseEntity.ok(dto);
    }
}