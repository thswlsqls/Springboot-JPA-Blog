package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	//Page<Board> findAll(Pageable pageable); //관리테이블, PK자료형
	
}
