package com.example.ezen.user;

public interface UserService {
	//회원가입 추상메서드
	int insertUsersOne(UserVO vo);
	
	//로그인 추상메서드
	UserVO selectUsersOne(UserVO vo);
	
	//아이디 중복체크 추상메서드
	int selectUsersCntById(String id);
}
