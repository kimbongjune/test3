package com.example.ezen;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Human {
	
    @NotBlank(message = "�̸��� �ʼ��Դϴ�.")
	private String name;
    
    @Min(value = 18, message = "���̴� 18�� �̻��̾�� �մϴ�.")
	private int age;
	
	@Email(message = "�̸��� ������ �ƴմϴ�.")
	private String email;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
