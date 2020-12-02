package com.increpas.cls.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.cls.dao.MemberDao;
import com.increpas.cls.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class Member {
	
	@Autowired
	MemberDao mDao;
	
	@RequestMapping("/login.cls") // ==> 클래스의 /member 와 함수의 /login.cls 를 합쳐서 /member/login.cls 로 처리한다.
	public String loginPage() {
		return "member/Login";
	}
	
	
	@RequestMapping(path="/loginProc.cls", params= {"id", "pw"}, method=RequestMethod.POST)
	public ModelAndView loginProc(ModelAndView mv, RedirectView rd, HttpSession session, MemberVO mVO) {
		
		int cnt = mDao.loginCnt(mVO);
		System.out.println("### cont login cnt : " + cnt);
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
		session.removeAttribute("SID");
		
		rd.setUrl("/cls/main.cls");
		mv.setView(rd);
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
}
