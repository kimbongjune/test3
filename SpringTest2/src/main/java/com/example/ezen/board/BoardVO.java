package com.example.ezen.board;

import java.util.List;

import com.example.ezen.file.FileVO;
import com.example.ezen.user.UserVO;

//값을 저장하는 VO(Value Object)객체
//board테이블에서 조회한 데이터를 저장할 자바 객체
//board테이블에 저장할 데이터를 담는 자바 객체
//모든 필드는 반드시 private
public class BoardVO {
	private int bno;
	private String title;
	private String body;
	private String author;
	private String createDate;
	private List<FileVO> fileList;
	private UserVO user;
	
	public BoardVO(int bno, String title, String body, String author, String createDate) {
		this.bno = bno;
		this.title = title;
		this.body = body;
		this.author = author;
		this.createDate = createDate;
	}
	
	public BoardVO() {}
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public List<FileVO> getFileList() {
		return fileList;
	}
	public void setFileList(List<FileVO> fileList) {
		this.fileList = fileList;
	}
	public UserVO getUser() {
		return user;
	}
	public void setUser(UserVO user) {
		this.user = user;
	}
}
