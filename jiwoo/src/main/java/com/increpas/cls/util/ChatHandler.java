package com.increpas.cls.util;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.*;
import java.util.*;

/*
	스프링 빈 설정 XML 파일에 <bean id="..." class="..."/>나 자바 @Configuration 클래스에서 
	@Bean을 붙여 빈을 등록하던 것처럼 빈 클래스에 @Component 애노테이션을 붙여 빈을 등록할 수 있다.
	
	즉 @Component를 사용해서 빈 설정 파일이 아니라 빈 클래스에서 빈을 직접 등록할 수 있다.
 */

public class ChatHandler extends TextWebSocketHandler {
	private static List<WebSocketSession> list = new ArrayList<WebSocketSession>();
	ArrayList<WebSocketSession> arrList;
	
	// 클라이언트가 접속 했을 때 호출될 메소드
	// 클라이언트가 접속 했을 때 호출되는 메소드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		list.add(session);
		System.out.println("하나의 클라이언트가 연결됨");
	}
	
	// 클라이언트가 메시지를 보냈을 때 호출되는 메소드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 전송된 메시지를 List의 모든 세션에 전송
		String msg = message.getPayload();
		
		for(WebSocketSession s : list) {
			s.sendMessage(new TextMessage(session.getAcceptedProtocol() + " : " + msg));
		}
	}
	
	// 클라이언트의 접속이 해제 되었을 때 호출되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("클라이언트와 연결 해제됨");
		list.remove(session);
	}
}
