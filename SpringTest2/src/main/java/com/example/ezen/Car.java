package com.example.ezen;

public class Car {
	//�̱��� ����
	//��ü�� ������ �����Ǿ ������ �޸� �ּҸ� �����ϰԲ� ��ü�� ����� ��
	
	//�ʵ� ����
	private static Car instance;
	
	//�����ڸ� ȣ�����ִ� �޼���
	public static Car getInstance() {
		if(instance == null) {
			instance = new Car();
		}
		return instance;
	}
}
