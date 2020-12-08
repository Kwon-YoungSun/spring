package com.increpas.cls.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.increpas.cls.vo.*;

public class SurveyDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public List<SurveyVO> getCurList(String id){
		return sqlSession.selectList("sSQL.infoList", id);
	}
	
	public List<SurveyVO> getQuestList(int sno){
		return sqlSession.selectList("sSQL.questList", sno);
	}
	
	// 설문결과 가져오는 전담 처리 함수
	public List<SurveyVO> getResultList(int sno){
		return sqlSession.selectList("sSQL.answerList", sno);
	}
	
	@Transactional
	public int addAnswer(ArrayList<SurveyVO> list) {
		int cnt = 0;
		for(SurveyVO sVO : list) {
			cnt += sqlSession.insert("sSQL.addAnswer", sVO);
		}
		return cnt;
	}
}
