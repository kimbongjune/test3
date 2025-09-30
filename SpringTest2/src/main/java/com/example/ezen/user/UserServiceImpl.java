package com.example.ezen.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//����Ͻ�����
//repository�� ���
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
		//��й�ȣ ��ȣȭ �� �μ�Ʈ
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
		
		//id �߸� �Է�
		if(user == null) {
			return null;
		}else {
			//db�� ����� ��й�ȣ�� ����ڰ� �Է��� ��й�ȣ�� ��ġ�ϴ��� Ȯ��
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
