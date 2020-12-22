package com.increpas.cls.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 이 클래스는 *.cls로 오는 모든 요청을 가로채서 로그인 여부를 검사할 목적으로 만든 클래스
 * @author		권영선
 * @since		2020/12/14
 * @version		v.1.0.0
 *
 */
public class LoginCheck implements HandlerInterceptor {

	@Override
	/*
		이 함수는 요청을 처리할 함수가 실행 되기 전에 호출되는 함수
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 할일
		// 세션에 기억된 내용이 있는지 검사한다.
		HttpSession session = request.getSession();
		String sid = (String) session.getAttribute("SID");
		// 검사 결과에 따라서 기억된 내용이 없으면
		// 로그인 페이지로 보낸다. (==> 새롭게 요청을 만든다.)
		if(sid == null || sid.length() == 0) {
			response.sendRedirect("/cls/member/login.cls");
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
