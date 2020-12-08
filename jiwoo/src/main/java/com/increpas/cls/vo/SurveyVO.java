package com.increpas.cls.vo;

import java.sql.*;
import java.text.*;

public class SurveyVO {
	// sno : 설문분류번호 ==> 1001 : 아이돌 선호도 조사
	// qno : (== sqno) ==> 설문 질문 번호 10001(당신이 좋아하는 가수는? 10002 제시, 제시카...)
	// sano : 응답 결과 관리 일련 번호
	// mno : 사원번호
	// sbody : 설문 제목 (2020년 11월 class2 아이돌 선호도 조사)
	// qbody : 설문 질문 내용들
	private int sno, qno, sano, mno, upno, cnt;
	private double per;
	private String sbody, qbody, id, sdate, ssdate, sedate;
	private Date startdate, enddate, sadate;
	private Time startTime, endTime, saTime;
	private int[] aqno;
	
	public int[] getAqno() {
		return aqno;
	}
	public void setAqno(int[] aqno) {
		this.aqno = aqno;
	}
	public double getPer() {
		return per;
	}
	public void setPer(double per) {
		this.per = per;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getQno() {
		return qno;
	}
	public void setQno(int qno) {
		this.qno = qno;
	}
	public int getSano() {
		return sano;
	}
	public void setSano(int sano) {
		this.sano = sano;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getUpno() {
		return upno;
	}
	public void setUpno(int upno) {
		this.upno = upno;
	}
	public String getSbody() {
		return sbody;
	}
	public void setSbody(String sbody) {
		this.sbody = sbody;
	}
	public String getQbody() {
		return qbody;
	}
	public void setQbody(String qbody) {
		this.qbody = qbody;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	
	public void setSdate() {
		sdate = strForm(sadate, saTime);
	}
	
	public String getSsdate() {
		return ssdate;
	}
	public void setSsdate(String ssdate) {
		this.ssdate = ssdate;
	}
	
	public void setSsdate() {
		ssdate = strForm(startdate, startTime);
	}
	
	public String getSedate() {
		return sedate;
	}
	public void setSedate(String sedate) {
		this.sedate = sedate;
	}
	
	public void setSedate() {
		sedate = strForm(enddate, endTime);
	}
	
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public Date getSadate() {
		return sadate;
	}
	public void setSadate(Date sadate) {
		this.sadate = sadate;
	}
	public Time getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
		setSsdate();
	}
	
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
		strForm(enddate, endTime);
	}
	public Time getSaTime() {
		return saTime;
	}
	public void setSaTime(Time saTime) {
		this.saTime = saTime;
		setSdate();
	}
	
	public String strForm(Date d, Time t) {
		SimpleDateFormat form1 = new SimpleDateFormat("yyyy/MM/dd ");
		SimpleDateFormat form2 = new SimpleDateFormat("HH:mm");
		return form1.format(d) + form2.format(t);
	}
}
