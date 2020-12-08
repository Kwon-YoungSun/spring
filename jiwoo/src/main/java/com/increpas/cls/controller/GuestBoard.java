package com.increpas.cls.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.increpas.cls.util.*;
import com.increpas.cls.vo.GBoardVO;
import com.increpas.cls.dao.*;
import com.increpas.cls.service.GBoardService;

import java.util.*;

@Controller
@RequestMapping("/guestBoard")
public class GuestBoard {
	
	@Autowired
	GBoardDao gDao;
	
	@Autowired
	GBoardService gService;
	
	// 방명록 리스트 폼 보기 요청 처리함수
	@RequestMapping("/gBoardList.cls")
	public ModelAndView gBoardForm(ModelAndView mv, PageUtil page, HttpSession session, GBoardVO gVO) {
		gService.setGBoardPage(mv, page, session, gVO);
//		System.out.println(page.getNowPage());
		return mv;
	}
	
	// 방명록 글 작성 요청 처리함수
	@RequestMapping("/gBoardWrite.cls")
	public ModelAndView gBoardWrite(ModelAndView mv, HttpSession session, GBoardVO gVO) {
		String sid = (String) session.getAttribute("SID");
		// 세션이 끊어진 경우
		if(sid == null) {
			mv.setViewName("redirect:/member/login.cls");
			return mv;
		}
		// 아이디 vo에 채우고
		gVO.setId(sid);
//		System.out.println(gVO.getId());
//		System.out.println(gVO.getBody());
		// dao 실행하고
		int cnt = gDao.gBoardWrite(gVO);
		mv.setViewName("redirect:/guestBoard/gBoardList.cls");
		
		return mv;
	}
}
