<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/cls/css/w3.css">
<script type="text/javascript" src="/cls/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="/cls/js/member/chatting.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div id="one">
		별명 : <input type="text" id="nickname" /> <input type="button" id="enter" value="입장" />
	</div>
	
	<div id="two" style="display: none">
		<input type="button" id="exit" value="퇴장" /><br/>
		<div id="chatarea" style="width:400px; height:600px; border:1px solid;"></div>
		<input type="text" id="message" /> <input type="button" id="send" value="보내기" />
	</div>
</body>
</html>