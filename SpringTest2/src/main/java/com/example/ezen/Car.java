package com.example.ezen;

public class Car {
	//싱글톤 패턴
	//객체가 여러번 생성되어도 동일한 메모리 주소를 참조하게끔 객체를 만드는 것
	
	//필드 선언
	private static Car instance;
	
	//생성자를 호출해주는 메서드
	public static Car getInstance() {
		if(instance == null) {
			instance = new Car();
		}
		return instance;
	}
}
