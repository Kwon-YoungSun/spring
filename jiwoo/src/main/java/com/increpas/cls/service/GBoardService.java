package com.increpas.cls.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.increpas.cls.dao.GBoardDao;
import com.increpas.cls.util.PageUtil;
import com.increpas.cls.vo.GBoardVO;
public class GBoardService {
	
	@Autowired
	GBoardDao gDao;
	
	public void setGBoardPage(ModelAndView mv, PageUtil page, HttpSession session, GBoardVO gVO) {
		// 먼저 세션에서 아이디 꺼내오고
		String sid = (String) session.getAttribute("SID");
		if(sid != null) {
			gVO.setId(sid);
			mv.addObject("AVATAR", gDao.getAvatar(sid));
		}
//		System.out.println(nowPage);

		
		// page 완성하고
		int total = gDao.getTotal();
		page.setTotalCount(total);
		page.setPage();
		// vo 에 페이지 담고
		gVO.setPage(page);

		// 방명록 리스트 가져오고
		List<GBoardVO> list = gDao.getList(gVO);
		// 뷰에 데이터 심고
		mv.addObject("LIST", list);
		mv.addObject("CNT", list.get(0).getCnt());
		mv.addObject("PAGE", page);
		// 뷰를 부르고
		mv.setViewName("guestBoard/gBoardList");
	}
}
