package com.example.board.repository;

import com.example.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // jpaRepository의 제너릭에는 어떤 Entity를 가져올지 두번째는 primary key의 타입
    
}
