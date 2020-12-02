package com.increpas.cls.dao;

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
	public int loginCnt(MemberVO mVO) {
		return sqlSession.selectOne("mSQL.login", mVO);
	}
}
