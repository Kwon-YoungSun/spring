package com.increpas.cls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.increpas.cls.dao.*;
import com.increpas.cls.util.PageUtil;
import com.increpas.cls.vo.*;
import java.util.*;

import javax.servlet.http.HttpSession;
@Controller
@RequestMapping("/reBoard")
public class ReBoard {
	@Autowired
	ReBoardDao rDao;
	
	@RequestMapping("/reBoardList.cls")
	public ModelAndView reBoardList(ModelAndView mv, PageUtil page, ReBoardVO rVO, HttpSession session) {
		// 게시물 총 갯수 계산하고 pageutil에 셋팅하고
		int total = rDao.getTotal();
		page.setTotalCount(total);
		
		// pageutil 만들고 vo에 셋팅하고
		page.setPage();
		rVO.setPage(page);
		// dao 처리하고 리스트 받고
		List<ReBoardVO> list = rDao.getList(rVO);
		// 아바타 가져오고
		String id = (String) session.getAttribute("SID");
		if(id != null) {
			String avatar = rDao.getAvt(id);
			session.setAttribute("AVTIMG", avatar);
		}
		// 데이터 뷰에 심고
		mv.addObject("LIST", list);
		mv.addObject("PAGE", page);
		
		mv.setViewName("reBoard/reBoard");
		return mv;
	}
	
	@RequestMapping("/reBoardWriteProc.cls")
	public ModelAndView reBoardWriteProc(ModelAndView mv, ReBoardVO rVO, HttpSession session) {
		// 세션 검사
		String id = (String) session.getAttribute("SID");
		if(id == null) {
			mv.setViewName("redirect:/member/login.cls");
			return mv;
		}
		
		int cnt = 0;
		// 데이터 처리하고 결과 받고
		cnt = rDao.addBoard(rVO);
		
		mv.setViewName("redirect:/reBoard/reBoardList.cls");
		return mv;
	}
	
	@RequestMapping("/reBoardDelProc.cls")
	public ModelAndView reBoardDelProc(ModelAndView mv, int nowPage, ReBoardVO rVO, HttpSession session) {
		// 세션 검사
		String id = (String) session.getAttribute("SID");
		if(id == null) {
			mv.setViewName("redirect:/member/login.cls");
			return mv;
		}
		int cnt = 0;
		cnt = rDao.delReboard(rVO);
		mv.setViewName("redirect:/reBoard/reBoardList.cls?nowPage=" + nowPage);
		return mv;
	}
	
	@RequestMapping("/reBoardEditProc.cls")
	public ModelAndView reBoardEdit(ModelAndView mv, int nowPage, ReBoardVO rVO, HttpSession session) {
		// 세션 검사
		String id = (String) session.getAttribute("SID");
		if(id == null) {
			mv.setViewName("redirect:/member/login.cls");
			return mv;
		}
		int cnt = 0;
		cnt = rDao.editReboard(rVO);
		mv.setViewName("redirect:/reBoard/reBoardList.cls?nowPage=" + nowPage);
		return mv;
	}
	
	@RequestMapping("/reBoardComment.cls")
	public ModelAndView reBoardComment(ModelAndView mv, HttpSession session, int bno, int nowPage) {
		// 세션 검사
		String id = (String) session.getAttribute("SID");
		if(id == null) {
			mv.setViewName("redirect:/member/login.cls");
			return mv;
		}
		mv.addObject("bno", bno);
		mv.addObject("nowPage", nowPage);
		mv.setViewName("reBoard/reBoardComment");
		return mv;
	}
}
