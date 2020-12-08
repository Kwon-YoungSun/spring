package com.increpas.cls.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.increpas.cls.vo.*;

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
	
	// 아바타 리스트 가져오기 전담 처리함수
	public List<AvatarVO> getAvtList() {
		return sqlSession.selectList("aSQL.getAll");
	}
	
	// 회원 아이디 체크 전담 처리 함수
	public int getIdCnt(String id) {
		return sqlSession.selectOne("mSQL.idCount", id);
	}
	
	// 회원 가입 전담 처리 함수
	public int insertMember(MemberVO mVO) {
		return sqlSession.insert("mSQL.addMember", mVO);
	}
	
	// 회원 정보 수정 전담 처리함수
	public int editMember(MemberVO mVO) {
		return sqlSession.update("mSQL.editMember", mVO);
	}
	
	// 여러 명 회원 가입 트랜젝션 테스트 전담 처리 함수
	@Transactional
	public int insertMember(ArrayList<MemberVO> list) {
		int cnt = 0;
		for(MemberVO mVO : list) {
			cnt += insertMember(mVO);
		}
		return cnt;
	}
}
