package com.increpas.cls.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.increpas.cls.vo.*;
import java.util.*;

public class GBoardDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 게시물 총 갯수 가져오기 전담함수
	public int getTotal() {
		return sqlSession.selectOne("gSQL.getTotal");
	}
	
	// 게시물 리스트 가져오기 전담 처리함수
	public List<GBoardVO> getList(GBoardVO gVO){
		return sqlSession.selectList("gSQL.getList", gVO);
	}
	
	// 사용자 아바타조회 전담 처리함수
	public String getAvatar(String id) {
		return sqlSession.selectOne("gSQL.getAvatar", id);
	}
	
	// 방명록 작성 전담 처리함수
	public int gBoardWrite(GBoardVO gVO) {
		return sqlSession.insert("gSQL.gBoardWrite", gVO);
	}
}
