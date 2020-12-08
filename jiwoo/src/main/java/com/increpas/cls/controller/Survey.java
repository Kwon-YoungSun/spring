package com.increpas.cls.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.increpas.cls.dao.*;
import com.increpas.cls.util.W3Color;
import com.increpas.cls.vo.*;
import java.util.*;

@Controller
@RequestMapping("/survey")
public class Survey {
	@Autowired
	SurveyDao sDao;
	@Autowired
	W3Color color;
	
	@RequestMapping("/surveyInfo.cls")
	public ModelAndView surveyInfo(ModelAndView mv, HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			mv.setViewName("redirect:/member/login.cls");
			return mv;
		}
		List<SurveyVO> list = sDao.getCurList(sid);
		mv.addObject("LIST", list);
		
		// 뷰 부르고
		mv.setViewName("survey/surveyInfo");
		return mv;
	}
	
	@RequestMapping("/survey.cls")
	public ModelAndView survey(ModelAndView mv, int sno) {
		List<SurveyVO> list = sDao.getQuestList(sno);
		// 데이터 심고
		mv.addObject("SNO", sno);
		mv.addObject("LIST", list);
		mv.setViewName("survey/survey");
		return mv;
	}
	
	@RequestMapping("/surveyResult.cls")
	public ModelAndView surveyResult(ModelAndView mv, int sno, HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			mv.setViewName("redirect:/member/login.cls");
			return mv;
		}
		List<SurveyVO> list = sDao.getResultList(sno);
		// 데이터 심고
		mv.addObject("LIST", list);
		mv.addObject("COLOR", color);
		mv.setViewName("survey/surveyResult");
		return mv;
	}
	
	@RequestMapping("/surveyProc.cls")
	public ModelAndView surveyProc(ModelAndView mv, HttpServletRequest req, SurveyVO VO) {
		// 할일
		// 파라미터 꺼내고
		// 문제는 어떤 키값으로 데이터가 넘어오는지 모른다는 것이다.
		Enumeration en = req.getParameterNames();
		ArrayList<SurveyVO> list = new ArrayList<SurveyVO>();
		while(en.hasMoreElements()) {
			SurveyVO sVO = new SurveyVO();
			sVO.setSno(VO.getSno());
			sVO.setId(VO.getId());
			String key = (String) en.nextElement();
			if(key.equals("cnt") || key.equals("id") || key.equals("sno")) {
				continue;
			}
			sVO.setQno(Integer.parseInt(req.getParameter(key)));
			
			list.add(sVO);
		}
		
		int cnt = 0;
		
		try {
			cnt = sDao.addAnswer(list);
			mv.addObject("SNO", VO.getSno());
			mv.addObject("VIEW", "/cls/survey/surveyResult.cls");
			mv.setViewName("survey/surveyRedirect");
		} catch(Exception e) {
			e.printStackTrace();
			cnt = 0;
			mv.setViewName("redirect:/survey/survey.cls");
		}
		return mv;
	}
}
