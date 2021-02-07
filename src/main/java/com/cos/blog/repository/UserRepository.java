package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.User;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.data.domain.Page;




// DAO
// 자동으로 bean 등록이 된다.
//@Repository //생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {

	//Page<User> findAll(Pageable pageable); //관리테이블, PK자료형
	// SELECT * FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);
	
}


//JPA Naming 쿼리 //직접 만든 메서드에서 대문자를 구분하여 쿼리가 작동한다.
//SELECT * FROM user WHERE username = ? AND password = ?;
//User findByUsernameAndPassword(String username, String password);

//	@Query(value ="SELECT * FROM user WHERE username = ? AND password = ?", nativeQuery = true)
//	User login(String username, String password);
