package com.increpas.cls.aop;


import javax.servlet.http.HttpSession;

import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/*
	참고 ]
	
		@Controller		:	컨트롤러로 사용할 클래스에 붙여주는 어노테이션
			==> 붙여주는 순간 스프링에서 자동으로 빈처리를 해준다.
		
		@Component, @Service, @Repository	: 빈등록해주는 어노테이션
 		<== 이 셋 중 아무거나 써도 상관이 없다.
 			
 			예 ]
 				@Service("memberSrvc")
 	
 	***
 	AOP 로 사용할 클래스는 빈처리가 되어야 한다.
 	추가적으로 반드시
 		@Aspect
 	어노테이션을 붙여줘야 한다.
 */

@Service
public class LogginAop {
	
	private static final Logger log1 = LoggerFactory.getLogger(LogginAop.class);
	@Pointcut("execution(* com.increpas.cls.controller.Member.loginProc(..))")
	public void loginAspect() {}
	
	@After("loginAspect()")
	public void recordLog(JoinPoint join) {
		HttpSession session = (HttpSession) join.getArgs()[2];
		String sid = (String) session.getAttribute("SID");
		System.out.println(sid);
		if(sid != null || sid.length() != 0) {
			log1.info(sid + " ] ***** Login *****");
		}
	}
	
	/*
	 	참고 ]
	 		Pointcut	: AOP 프로그램이 작동할 시점
	 		
	 		하나의 AOP 클래스내에 Pointcut은 반드시 하나만 있어야 되는 것이 아니고
	 		필요한 만큼 추가해주면 된다.
	 */
	
	@Pointcut("execution(* com.increpas.cls.controller.Member.logout(..))")
	public void logoutAspect() {}
	
	@Before("logoutAspect()")
	public void recordOut(JoinPoint join) {
		HttpSession session = (HttpSession) join.getArgs()[0];
		String sid = (String) session.getAttribute("SID");
		
		if(sid != null || sid.length() != 0) {
			log1.info(sid + " ] - ##### Logout #####");		
		}
	}
}
