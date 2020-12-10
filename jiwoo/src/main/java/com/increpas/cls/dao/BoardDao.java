package com.increpas.cls.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.increpas.cls.util.*;
import com.increpas.cls.vo.*;
import java.util.*;

public class BoardDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 게시글 총 갯수 조회 전담 처리 함수
	public int getTotal() {
		return sqlSession.selectOne("bSQL.getTotal");
	}
	
	// 게시글 리스트 조회 전담 처리 함수
	public List<BoardVO> getList(PageUtil page) {
		return sqlSession.selectList("bSQL.getList", page);
	}
	
	// 게시글 내용 조회 전담 처리함수
	public BoardVO getDetail(int bno) {
		return sqlSession.selectOne("bSQL.getDetail", bno);
	}
	
	// 첨부파일 조회 전담 처리 함수
	public List<FileVO> getImg(int bno) {
		return sqlSession.selectList("bSQL.getImage", bno);
	}
	
	// 게시글 데이터베이스 저장 전담 처리함수
	public int addBoard(BoardVO bVO) {
		return sqlSession.insert("bSQL.addBoard", bVO);
	}
	
	// 첨부파일 정보 저장 전담 처리함수
	public int addFile(FileVO fVO) {
		return sqlSession.insert("bSQL.addFile", fVO);
	}
	
	// 게시글 모두 입력 전담 처리 함수
	@Transactional
	public int addAll(BoardVO bVO) {
		int cnt = 0;
		// 게시글 등록
		addBoard(bVO);
		int bno = bVO.getBno();
		// 첨부파일 등록
		List<FileVO> list = bVO.getList();
		for(FileVO fVO : list) {
			fVO.setBno(bVO.getBno());
			addFile(fVO);
		}
		return cnt;
	}
}
