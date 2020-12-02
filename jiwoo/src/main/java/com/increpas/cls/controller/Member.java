package com.increpas.cls.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.cls.dao.MemberDao;
import com.increpas.cls.util.*;
import com.increpas.cls.vo.MemberVO;
import java.util.*;

@Controller
@RequestMapping("/member")
public class Member {
	
	@Autowired
	MemberDao mDao;
	@Autowired
	W3Color color;
	
	@RequestMapping("/login.cls") // ==> 클래스의 /member 와 함수의 /login.cls 를 합쳐서 /member/login.cls 로 처리한다.
	public String loginPage() {
		return "member/Login";
	}
	
	
	@RequestMapping(path="/loginProc.cls", params= {"id", "pw"}, method=RequestMethod.POST)
	public ModelAndView loginProc(ModelAndView mv, RedirectView rd, HttpSession session, MemberVO mVO) {
		
		int cnt = mDao.loginCnt(mVO);
//		System.out.println("### cont login cnt : " + cnt);
		if(cnt == 0) {
			rd.setUrl("/cls/member/login.cls");
		} else {
			session.setAttribute("SID", mVO.getId());
			rd.setUrl("/cls/main.cls");
		}
		mv.setView(rd);
		return mv;
	}
	
	@RequestMapping("/logout.cls")
	public ModelAndView logoutProc(HttpSession session, RedirectView rd, ModelAndView mv) {
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			rd.setUrl("/cls/main.cls");
			mv.setView(rd);
		} else {
			session.removeAttribute("SID");
			rd.setUrl("/cls/member/login.cls");
			mv.setView(rd);
		}
		
		return mv;
	}
	
	
	@RequestMapping("/join.cls")
	public ModelAndView joinForm(HttpSession session, RedirectView rd, ModelAndView mv) {
		/*
			이 컨트롤러는 회원가입 뷰를 띄워주는 역할을 한다.
			이 말은 뷰만 보여주면 된다.
			그런데 반환값 타입이 void인 경우는 요청내용을 이용해서
			보여줄 뷰를 선택한다.
			이 함수에서는 요청 내용이
				localhost/cls/member/join.cls
			이고 이것을 이용해서 보여주는 뷰는
			prefix + member/join + suffix
			로 만들어질 것이다.
			이때
				prefix : /WEB-INF/views/
				surfix : .jsp
				
			결과적으로 부르는 뷰는
				/WEB-INF/views/member/join.jsp
			를 보여주게 된다.
			
			이 함수내에서 처리할 내용을 로그인이 되어있는 회원의 경우는 리다이렉트로 main.cls 로 보내야 한다.
		 */
		String sid = (String) session.getAttribute("SID");
		/*
		if(sid != null || sid.length() != 0 ) {
			rd.setUrl("/cls/main.cls");
			mv.setView(rd); // redirect로 뷰를 호출하는 경우
		} else {
			mv.setViewName("member/join"); // forward로 뷰 부르는 경우
		}
		*/
		if(sid == null || sid.length() == 0) {
			mv.setViewName("member/join");
		} else {
			rd.setUrl("/cls/main.cls");
			mv.setView(rd);
		}
		return mv;
	}
	
	@RequestMapping("/memberInfo.cls")
	public ModelAndView getInfo(ModelAndView mv, HttpSession session, RedirectView rv) {
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			rv.setUrl("/cls/member/login.cls");
			mv.setView(rv);
		} else {
			mv.setViewName("member/memberInfo");
			MemberVO mVO = mDao.getInfo(sid);
			
			mv.addObject("DATA", mVO);
		}
		
		return mv;
	}
	
	@RequestMapping(path="/memberDel.cls", method=RequestMethod.POST)
	public ModelAndView memberDel(ModelAndView mv, HttpSession session, MemberVO mVO) {
		int cnt = mDao.outMember(mVO);
//		System.out.println("########## cnt : " + cnt);
		if(cnt == 1) {
			// 이 경우는 회원 탈퇴에 성공한 경우이므로
			// 세션에 기록된 데이터 지우고
			session.removeAttribute("SID");
			// 메인페이지로 돌려보낸다.
			mv.setViewName("redirect:/main.cls");
		} else {
			// 처리에 실패한 경우이므로 회원상세정보페이지로 다시 보낸다.
			mv.setViewName("redirect:/member/memberInfo.cls");
		}
		
		return mv;
	}
	
	@RequestMapping("/nameList.cls")
	public ModelAndView getList(ModelAndView mv, HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		
		if(sid == null) {
			// 로그인을 하지 않은 경우
			mv.setViewName("redirect:/member/login.cls");
		} else {
			List<MemberVO> list = mDao.getNameList();
			ArrayList<String> colors = color.getList();
			// 데이터를 뷰에 전달하는 방법
			mv.addObject("COLORS", colors);
			mv.addObject("LIST", list);
			mv.setViewName("member/memberList");
		}
		return mv;
	}
	
	// 이름버튼 클릭하면 회원정보 보기(포워드 방식)
	@RequestMapping(path="/memberInfo2.cls", params="mno", method=RequestMethod.POST)
	public ModelAndView getInfoByName(ModelAndView mv, String mno, HttpSession session) {
//		System.out.println(mno);
		String sid = (String) session.getAttribute("SID");
		
		if(sid == null) {
			// 로그인을 하지 않은 경우
			mv.setViewName("redirect:/member/login.cls");
		} else if(mno == null) {
			// 입력된 회원번호가 없을 경우
			mv.setViewName("redirect:/main.cls");
		} else {
			int no = Integer.parseInt(mno);
			MemberVO mVO = mDao.getInfoByName(no);
			
			mv.setViewName("member/memberInfo2");
			mv.addObject("DATA", mVO);
		}
		return mv;
	}
	
	// 이름버튼 클릭하면 회원정보 보기(비동기 방식)
	@ResponseBody
	@RequestMapping(path="/memberInfoAjax.cls", method=RequestMethod.POST)
	public MemberVO getInfoByName(int mno) {
		MemberVO mVO = mDao.getInfoByName(mno);
		
		return mVO;
	}
	
	/*
	// 회원가입 아이디 체크
	@ResponseBody
	@RequestMapping(path="/idCheck.cls" , method=RequestMethod.POST)
	public String idCheck() {
		String result = "";
		
		return result;
	}
	*/
	
}
