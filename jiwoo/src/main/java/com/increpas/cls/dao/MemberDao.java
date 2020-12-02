package com.increpas.cls.dao;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.increpas.cls.vo.MemberVO;

public class MemberDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	/*
	 * selectOne : 한 줄
	 * selectList : 여러 줄
	 */
	
	// 로그인 조회 전담 처리함수
	public int loginCnt(MemberVO mVO) {
		return sqlSession.selectOne("mSQL.login", mVO);
	}
	// 회원 정보조회 전담 처리함수
	public MemberVO getInfo(String id) {
		return sqlSession.selectOne("mSQL.getInfo", id);
	}
	// 회원 탈퇴 전담 처리 함수
	public int outMember(MemberVO mVO) {
		return sqlSession.update("mSQL.del_memb", mVO);
	}
	// 회원 이름 리스트 조회 전담 처리 함수
	public List<MemberVO> getNameList() {
		return sqlSession.selectList("mSQL.nameList");
	}
	
	// 회원 이름에 따른 회원 정보 조회 전담 처리함수
	public MemberVO getInfoByName(int mno) {
		return sqlSession.selectOne("mSQL.getInfoByName", mno);
	}
}
