package com.example.ezen.user;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	
	private final SqlSession template;

	@Autowired
	public UserRepository(SqlSession template) {
		this.template = template;
	}
	
	//ȸ������ �޼���
	public int insertUsersOne(UserVO vo){
		return template.insert("userMapper.insertUsersOne", vo);
	}
	
	//�α��� �޼���
	public UserVO selectUsersOne(UserVO vo){
		//select id, pw, nick from users where id = ? and pw = ?
		return template.selectOne("userMapper.selectUsersOne", vo);
	}
	
	//���̵� �ߺ�Ȯ�� �޼���
	public int selectUsersCntById(String id){
		return template.selectOne("userMapper.selectUserCntById", id);
	}
	
}
