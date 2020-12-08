<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/cls/css/w3.css">
<link rel="stylesheet" type="text/css" href="/cls/css/cls.css">
<script type="text/javascript" src="/cls/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="/cls/js/survey.js"></script>
</head>
<body>
	<div class="w3-content w3-margin-bottom w3-blue w3-center mw700">
		<h1 class="w3-padding w3-blue">진행중인 설문 조사</h1>
		<div class="w3-col w3-padding w3-card-4">
			<form class="w3-col" method="post" action="/cls/survey/survey.cls" id="frm" name="frm">
				<input type="hidden" name="sno" id="sno">
				<c:forEach var="data" items="${LIST}">
					<div class="w3-col m9 w3-text-blue txt14"><b>${data.sbody}</b></div>
					<c:if test="${data.cnt eq 0}">
						<div class="w3-col m2 w3-button w3-small w3-blue sibtn" id="${data.sno}">참여</div>
					</c:if>
					
					<c:if test="${data.cnt ne 0}">
						<div class="w3-col m2 w3-button w3-small w3-blue srbtn" id="r${data.sno}">결과보기</div>
					</c:if>
				</c:forEach>
			</form>
		</div>	
	</div>
</body>
</html>