package com.example.ezen;

public class CarTest {

	public static void main(String[] args) {
		
		HankookTire tire = new HankookTire();
		KumhoTire tire2 = new KumhoTire();
		Tire t = new KumhoTire();
		t = new HankookTire();
		
		Car c1 = new Car();
		Car c2 = new Car();
		//������Ÿ�� ����
		//������ ȣ���ϴ� ��
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c1 == c2);
		
		Car c3 = Car.getInstance();
		Car c4 = Car.getInstance();
		System.out.println(c3);
		System.out.println(c4);
		System.out.println(c3 == c4);
	}

}
