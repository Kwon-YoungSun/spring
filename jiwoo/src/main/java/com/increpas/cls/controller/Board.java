package com.increpas.cls.controller;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
	public ModelAndView boardWrite(ModelAndView mv, HttpSession session, PageUtil page) {
		// 세션 검사
		/*
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			mv.setViewName("redirect:/member/login.cls");
		}*/ 
			mv.addObject("PAGE", page);
			mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@RequestMapping("/boardEdit.cls")
	public ModelAndView boardEdit(ModelAndView mv, // 실행결과 데이터와 뷰를 모두 필요로 하기 때문에 사용
			int bno, HttpSession session) {
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
		
		if(session.getAttribute("SID") == null) {
			mv.setViewName("redirect:/member/login.cls");
			return mv;
		} 
		bSrvc.addBoard(mv, bVO, page);
//		mv.setViewName("redirect:/board/boardList.cls");
		return mv;
	}
	
	@RequestMapping("/boardEditProc.cls")
	public ModelAndView boardEditProc(ModelAndView mv, BoardVO bVO, HttpSession session, PageUtil page) {
		// 세션처리
		if(session.getAttribute("SID") == null) {
			mv.setViewName("redirect:/member/login.cls");
			return mv;
		}
		bSrvc.editBoard(mv, bVO, page);
		
		return mv;
	}
}
