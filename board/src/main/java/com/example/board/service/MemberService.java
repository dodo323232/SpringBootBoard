package com.example.board.service;

import com.example.board.dto.member.LoginRequestDto;
import com.example.board.dto.member.MemberResponseDto;
import com.example.board.dto.member.SignUpRequestDto;
import com.example.board.entity.Member;
import com.example.board.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signUp(SignUpRequestDto requestDto){
        if(memberRepository.findByEmail(requestDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        Member member = Member.builder()
                .email(requestDto.getEmail())
                .password(encodedPassword)
                .nickname(requestDto.getNickname())
                .build();

        return memberRepository.save(member).getNumber();
    }

    @Transactional
    public MemberResponseDto memberLogin(LoginRequestDto loginDto){

        Member member = memberRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 틀렸습니다."));

        boolean isMatch = passwordEncoder.matches(
                    loginDto.getPassword(), member.getPassword());

        if(!isMatch){
            throw new IllegalArgumentException("아이디 또는 비밀번호가 틀렸습니다.");
        }

        return new MemberResponseDto(member);
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findById(Long id){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id+"번 사용자가 존재하지 않습니다."));

        return new MemberResponseDto(member);
    }


}
