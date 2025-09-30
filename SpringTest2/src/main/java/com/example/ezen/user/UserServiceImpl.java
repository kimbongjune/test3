package com.example.ezen.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//비즈니스로직
//repository와 통신
@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public int insertUsersOne(UserVO vo) {
		//비밀번호 암호화 후 인서트
		String encPw = passwordEncoder.encode(vo.getPw());
		vo.setPw(encPw);
		return repository.insertUsersOne(vo);
	}

	@Override
	public UserVO selectUsersOne(UserVO vo) {
		//select * from users where id = 'jeon'
		//id : jeon
		//pw : 4321
		UserVO user = repository.selectUsersOne(vo);
		
		//id 잘못 입력
		if(user == null) {
			return null;
		}else {
			//db에 저장된 비밀번호랑 사용자가 입력한 비밀번호가 일치하는지 확인
			boolean match = passwordEncoder.matches(vo.getPw(), user.getPw());
			if(match == true) {
				return user;
			}else {
				return null;
			}
		}
	}

	@Override
	public int selectUsersCntById(String id) {
		return repository.selectUsersCntById(id);
	}

}
