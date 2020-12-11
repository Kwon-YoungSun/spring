package com.increpas.cls.vo;

import java.util.*;
import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

import java.text.*;
import java.sql.*;

public class BoardVO {
	/*
	 	bno		: board number
	 	mno		: member number
	 	fno		: file number
	 	ano		: avatar number
		fdown 	: fileinfo table click count
		click	: board click count
		sdate	: board written date
		sfdate	: file upload date
	*/
	private int bno, mno, fno, ano, click, cnt;
	private String title, body, id, avatar, sdate;
	private Date wdate;
	private Time wtime;
	private ArrayList<String> filekeys;
	private ArrayList<FileVO> list;
	
	private MultipartFile[] file;
	private int[] delfile;
	/*
	 	업로드된 파일을 기억할 변수는
	 		MultipartFile
	 	이라는 클래스 형태로 만들어야 한다.
	 	그러면 그 안에 스트림 형태로 파일의 내용이 기억되게 한다.
	 	이때 주의사항
	 		만약 
	 */
	
	public MultipartFile[] getFile() {
		return file;
	}
	public int[] getDelfile() {
		return delfile;
	}
	public void setDelfile(int[] delfile) {
		this.delfile = delfile;
	}
	public void setFile(MultipartFile[] file) {
		this.file = file;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getClick() {
		return click;
	}
	public void setClick(int click) {
		this.click = click;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	/*
	public void setSdate() {
		SimpleDateFormat form1 = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat form2 = new SimpleDateFormat("HH:mm");
		sdate = form1.format(wdate) + " " + form2.format(wtime);
	}
	*/
	public void setSdate() {
		SimpleDateFormat form1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		sdate = form1.format(wdate);
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	public Time getWtime() {
		return wtime;
	}
	public void setWtime(Time wtime) {
		this.wtime = wtime;
		setSdate();
	}
	public ArrayList<FileVO> getList() {
		return list;
	}
	public void setList(ArrayList<FileVO> list) {
		this.list = list;
	}
	public ArrayList<String> getFilekeys() {
		return filekeys;
	}
	public void setFilekeys(ArrayList<String> filekeys) {
		this.filekeys = filekeys;
	}
	
	
}
