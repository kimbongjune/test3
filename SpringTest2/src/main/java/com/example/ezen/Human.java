package com.example.ezen;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Human {
	
    @NotBlank(message = "이름은 필수입니다.")
	private String name;
    
    @Min(value = 18, message = "나이는 18세 이상이어야 합니다.")
	private int age;
	
	@Email(message = "이메일 형식이 아닙니다.")
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
