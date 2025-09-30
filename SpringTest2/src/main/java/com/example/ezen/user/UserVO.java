package com.example.ezen.user;

public class UserVO {
	private String id;
	private String pw;
	private String nick;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", pw=" + pw + ", nick=" + nick + "]";
	}
}
