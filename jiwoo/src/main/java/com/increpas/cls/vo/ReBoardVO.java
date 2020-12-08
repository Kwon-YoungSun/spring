package com.increpas.cls.vo;

import java.sql.*;
import java.text.*;
import com.increpas.cls.util.*;
public class ReBoardVO {
	private int rno, ano, step, bno, b_mno, upno;
	private String body, swDate, id, avatar;
	private Date wdate;
	private Time wtime;
	private PageUtil page;
	
	public PageUtil getPage() {
		return page;
	}
	public void setPage(PageUtil page) {
		this.page = page;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getB_mno() {
		return b_mno;
	}
	public void setB_mno(int b_mno) {
		this.b_mno = b_mno;
	}
	public int getUpno() {
		return upno;
	}
	public void setUpno(int upno) {
		this.upno = upno;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSwDate() {
		return swDate;
	}
	public void setSwDate(String swDate) {
		this.swDate = swDate;
	}
	public void setSwDate() {
		SimpleDateFormat form1 = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat form2 = new SimpleDateFormat("HH:mm");
		
		this.swDate = form1.format(wdate) + " " + form2.format(wtime);
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
		setSwDate();
	}
	@Override
	public String toString() {
		return "ReBoardVO [rno=" + rno + ", ano=" + ano + ", step=" + step + ", bno=" + bno + ", b_mno=" + b_mno
				+ ", upno=" + upno + ", body=" + body + ", swDate=" + swDate + ", id=" + id + ", wdate=" + wdate
				+ ", wtime=" + wtime + "]";
	}
}
