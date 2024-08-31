package com.ex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	// username을 받아 DB 테이블에서 회원을 조회하는 메소드 작성
	UserEntity findByUsername(String username);
}
