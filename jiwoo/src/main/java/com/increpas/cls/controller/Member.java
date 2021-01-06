package com.increpas.cls.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.cls.dao.*;
import com.increpas.cls.util.*;
import com.increpas.cls.vo.*;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
	
	private static final Logger logger = LoggerFactory.getLogger(Member.class);
	
	@RequestMapping(path="/loginProc.cls", params= {"id", "pw"}, method=RequestMethod.POST)
	public ModelAndView loginProc(ModelAndView mv, RedirectView rd, HttpSession session, MemberVO mVO) {
		
		int cnt = mDao.loginCnt(mVO);
//		System.out.println("### cont login cnt : " + cnt);
		if(cnt == 0) {
			rd.setUrl("/cls/member/login.cls");
		} else {
			session.setAttribute("SID", mVO.getId());
//			logger.info(mVO.getId() + " ] - ***** Login *****");
			rd.setUrl("/cls/main.cls");
		}
		mv.setView(rd);
		return mv;
	}
	
	@RequestMapping("/logout.cls")
	public ModelAndView logoutProc(HttpSession session, RedirectView rd, ModelAndView mv) {
//		String sid = (String) session.getAttribute("SID");
		/*
		if(sid == null) {
			rd.setUrl("/cls/main.cls");
			mv.setView(rd);
		} else {
		}
		 */
		session.removeAttribute("SID");
//		logger.info(sid + " ] - ##### Logout #####");
		rd.setUrl("/cls/member/login.cls");
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
		
		if(sid != null || sid.length() != 0 ) {
			rd.setUrl("/cls/main.cls");
			mv.setView(rd); // redirect로 뷰를 호출하는 경우
		} else {
			mv.setViewName("member/join"); // forward로 뷰 부르는 경우
		}
		
		if(sid == null || sid.length() == 0) {
			mv.setViewName("member/join");
			// 데이터 만들고
			List<AvatarVO> list = mDao.getAvtList();
			// 데이터 뷰에 보내고
			mv.addObject("LIST", list);
		} else {
			rd.setUrl("/cls/main.cls");
			mv.setView(rd);
		}
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/idCheck.cls")
	public HashMap<String, String> idCheck(String id) {
		// 할일
		// 데이터베이스에서 조회하고
		// 참고 : json 형식 var 변수 = {키값:데이터, 키값:데이터}
		/*
		int cnt = mDao.getIdCnt(id);
		String result = (cnt == 0)? "OK" : "NO";
		map.put("result", result);
		*/
		// HashMap을 매개변수로 받아 사용하면 에러가 난다. 왜냐? 생성이 안되있어서...
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", (mDao.getIdCnt(id) == 0) ? "OK" : "NO");
		// ==> {'result' : 'OK'} or {'result': 'NO'}
		return map;
	}
	
	@RequestMapping("/joinProc.cls")
	public ModelAndView joinProc(ModelAndView mv, MemberVO mVO, HttpSession session) {
		// 할 일
		// 데이터베이스 작업하고
		int cnt = mDao.insertMember(mVO);
		
		if(cnt == 1) {
			// 성공하면 로그인 처리하고
			session.setAttribute("SID", mVO.getId());
			// 메인페이지로 이동하고
			mv.setViewName("redirect:/main.cls");
		} else {
			// 실패한 경우
			// 회원가입페이지로 다시 이동시키고
			mv.setViewName("redirect:/member/join.cls");
		}
		return mv;
	}
	
	/*
	@RequestMapping(value="/joinAjaxProc.cls", method=RequestMethod.POST)
	@ResponseBody
	public String joinAjaxProc(HttpServletRequest req, MemberVO mVO, HttpSession session){
		MultipartRequest multi;
		String path = session.getServletContext().getRealPath("resources/img/upload");
		String result = "OK";
		try {
			multi = new MultipartRequest(req, path, 1024*1024*10, "UTF-8", new DefaultFileRenamePolicy());
		
			mVO.setName(multi.getParameter("name"));
			mVO.setId(multi.getParameter("id"));
			mVO.setPw(multi.getParameter("pw"));
			mVO.setMail(multi.getParameter("mail"));
			mVO.setGen(multi.getParameter("gen"));
			mVO.setAvt(Integer.parseInt(multi.getParameter("avt")));
			
			int cnt = mDao.insertMember(mVO);
			
			if(cnt == 1) {
				// 성공하면 로그인 처리하고
				session.setAttribute("SID", mVO.getId());
			} else {
				result = "NO";
			}
		} catch(Exception e) {
			System.out.println("############## 데이터 전송 실패 ################");
			result = "NO";
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
	*/
	
	@RequestMapping(value="/joinAjaxProc.cls", method=RequestMethod.POST)
	@ResponseBody
	public String joinAjaxProc(MemberVO mVO, HttpSession session){
		
		String result = "OK";
//		System.out.println("### cont vo id : " + mVO.getId());
		// VO가 완성됬으니 데이터베이스 작업하고
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		list.add(mVO);
//		list.add(new MemberVO());
		int cnt = 0;
		try {
			cnt = mDao.insertMember(list);			
		} catch(Exception e) {
			cnt = 0;
		}
		/*
		int cnt = mDao.insertMember(mVO);
		*/
		if(cnt == 1) {
			// 성공하면 로그인 처리하고
			session.setAttribute("SID", mVO.getId());
		} else {
			result = "NO";
		}
		
		return result;
	}
	
	@RequestMapping("/memberInfo.cls")
	public ModelAndView getInfo(ModelAndView mv, HttpSession session, RedirectView rv, String id, String msg) {
//		String sid = (String) session.getAttribute("SID");
		if(msg != null) {
			mv.addObject("MSG", msg);
		}
		if(id != null) {
			mv.addObject("ID", id);
		}
		
		if(id == null) {
			rv.setUrl("/cls/member/login.cls");
			mv.setView(rv);
		} else {
			mv.setViewName("member/memberInfo");
			MemberVO mVO = mDao.getInfo(id);
			List<AvatarVO> list = mDao.getAvtList();
			mv.addObject("DATA", mVO);
			mv.addObject("LIST", list);
		}
		
		return mv;
	}
	
	@RequestMapping("/memberEditProc.cls")
	public ModelAndView memberEditProc(ModelAndView mv, MemberVO mVO) {
		String msg = "수정에 성공했습니다.";
		mv.setViewName("redirect:/member/memberInfo.cls?id=" + mVO.getId() + "&msg=" + msg);
		/*
			jsp에서 파라미터 꺼내서 사용하는 방법
				${param.msg}
				
			이 경우 전달되는 데이터는 주소표시줄에 노출이 되고
			데이터를 꺼내는 구문도 길어진다.
			따라서 여기서는 리다이렉트 jsp 페이지를 만들고
			해당 페이지가 열리면 바로 post 방식으로 리다이렉트가 이루어지도록
			처리를 해보자.
		 */
		int cnt = mDao.editMember(mVO);
		if(cnt != 1) {
			// 수정에 실패한 경우
			msg = "정보수정에 실패했습니다!";
		}
		
		mv.setViewName("member/redirect");
		mv.addObject("ID", mVO.getId());
		mv.addObject("MSG", msg);
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
	
	@RequestMapping("/chatting.cls")
	public ModelAndView chatting(ModelAndView mv, HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			mv.setViewName("redirect: /member/login.cls");
			return mv;
		}
		
		mv.setViewName("member/chatting");
		return mv;
	}
}
