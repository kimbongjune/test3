package com.example.ezen.user;

public interface UserService {
	//ȸ������ �߻�޼���
	int insertUsersOne(UserVO vo);
	
	//�α��� �߻�޼���
	UserVO selectUsersOne(UserVO vo);
	
	//���̵� �ߺ�üũ �߻�޼���
	int selectUsersCntById(String id);
}
