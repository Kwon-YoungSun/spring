package com.increpas.cls.dao;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;
import com.increpas.cls.vo.*;
import java.util.*;

public class ReBoardDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 게시물 총 갯수 반환하는 전담 처리 함수
	public int getTotal() {
		return sqlSession.selectOne("rSQL.rbdCnt");
	}
	
	// 게시물 리스트 가져오는 전담 처리 함수
	public List<ReBoardVO> getList(ReBoardVO rVO){
		return sqlSession.selectList("rSQL.getList", rVO);
	}
	
	// 회원 아이디에 해당하는 아바타 가져오는 전담 처리 함수
	public String getAvt(String id) {
		return sqlSession.selectOne("rSQL.getAvt", id);
	}
	
	// 게시판 원글 작성 전담 처리 함수
	public int addBoard(ReBoardVO rVO) {
		return sqlSession.insert("rSQL.addBoard", rVO);
	}
	
	// 게시글 삭제 전담 처리 함수
	public int delReboard(ReBoardVO rVO) {
		return sqlSession.update("rSQL.delReboard", rVO);
	}
	
	// 게시글 수정 전담 처리 함수
	public int editReboard(ReBoardVO rVO) {
		return sqlSession.update("rSQL.editReboard", rVO);
	}
	
	// 게시판 댓글 작성 전담 처리 함수
	public int addReboard(ReBoardVO rVO) {
		return sqlSession.insert("rSQL.addReboard", rVO);
	}
}
