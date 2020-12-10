package com.increpas.cls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.increpas.cls.dao.*;
import com.increpas.cls.service.BoardService;
import com.increpas.cls.vo.*;
import com.increpas.cls.util.*;
import java.util.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class Board {
	
	@Autowired
	BoardDao bDao;
	@Autowired
	BoardService bSrvc;
	
	// 게시글 리스트 폼 요청 처리함수
	@RequestMapping("/boardList.cls")
	public ModelAndView board(ModelAndView mv, PageUtil page) {
		// 할일
		// page 완성하고
		// 게시글의 총 갯수를 구한다.
		int total = bDao.getTotal();
		page.setPage(total, 5, 5);
		
		List<BoardVO> list = bDao.getList(page);
		
		mv.addObject("PAGE", page);
		mv.addObject("LIST", list);
		
		mv.setViewName("board/boardList");
		return mv;
	}
	
	@RequestMapping("/boardDetail.cls")
	public ModelAndView boardDetail(ModelAndView mv, int bno) {
		// 할일
		// 게시글 내용 가져오고
		BoardVO bVO = bDao.getDetail(bno);
		List<FileVO> list = bDao.getImg(bno);
		// 데이터 심고
		mv.addObject("DATA", bVO);
		mv.addObject("LIST", list);
		
		// 뷰 부르고
		mv.setViewName("board/boardDetail");
		return mv;
	}
	
	@RequestMapping("/boardWrite.cls")
	public ModelAndView boardWrite(ModelAndView mv, HttpSession session) {
		// 세션 검사
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			mv.setViewName("redirect:/member/login.cls");
			return mv;
		}
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@RequestMapping("/redirect.cls")
	public ModelAndView boardRedirect(ModelAndView mv, int nowPage) {
		mv.addObject("VIEW", "boardList.cls");
//		mv.addObject("BNO", bno);

		mv.addObject("NOWPAGE", nowPage);
		
		mv.setViewName("board/boardRedirect");
		return mv;
	}
	
	@RequestMapping("/boardEdit.cls")
	public ModelAndView boardEdit(ModelAndView mv, int bno, HttpSession session) {
		// 세션 검사
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			mv.setViewName("redirect:/member/login.cls");
			return mv;
		}
		// 게시글 내용 가져오고
		BoardVO bVO = bDao.getDetail(bno);
		List<FileVO> list = bDao.getImg(bno);
		// 데이터 심고
		mv.addObject("DATA", bVO);
		mv.addObject("LIST", list);
		
		// 뷰 부르고
		mv.setViewName("board/boardEdit");
		return mv;
	}
	
	// 게시글 등록 요청 처리 함수
	@RequestMapping("/boardWriteProc.cls")
	public ModelAndView boardWriteProc(ModelAndView mv, BoardVO bVO, PageUtil page, HttpSession session) {
		/*
		if(session.getAttribute("SID") == null) {
			mv.setViewName("redirect:/member/login.cls");
		} else {
			mv.addObject("PAGE", page);
			mv.addObject("VIEW", "/cls/board");
			
		}
		*/
		/*
		mv.addObject("NOWPAGE", page.getNowPage());
		mv.addObject("VIEW", "boardWrite.cls");
		*/
		bSrvc.saveProc(bVO.getFile());
		mv.setViewName("redirect:/board/boardList.cls");
		return mv;
	}
}
