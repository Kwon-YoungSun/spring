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
<style>
	form {
		color: black;
	}
</style>
</head>
<body>
	<div class="w3-content w3-margin-bottom w3-blue w3-center mw700">
		<h1 class="w3-padding w3-blue">설문 문항</h1>
		<div class="w3-col w3-padding w3-card-4">
			<h4 class="w3-col w3-text-blue">${LIST[0].sbody}</h4>
			<form class="w3-col w3-padding w3-left-align w3-border-bottom" method="POST" action="/cls/survey/surveyProc.cls" id="frm">
				<input type="hidden" name="sno" value="${SNO}">
				<input type="hidden" name="id" value="${SID}">
				<!-- 문항추가 -->
			<c:forEach var="data" items="${LIST}" varStatus="st">
	<!-- 상위 번호가 Null인 경우(소제목) -->
	<c:if test="${data.upno == 0}">
			<c:set var="no" value="${no + 1}"/>
		<!-- 첫번째 소제목인 경우 -->
		<c:if test="${st.index == 0}">
				<div class="w3-col w3-margin-bottom">
					<div class="w3-col txt14">${no}. ${data.qbody}</div>
		</c:if>
		<!-- 첫번째 소제목이 아닌 경우 -->
		<c:if test="${st.index != 0}">
				<!-- div 태그를 닫아준 후 소제목을 출력한다. -->
				</div>
				<div class="w3-col w3-margin-bottom">
					<div class="w3-col txt14">${no}. ${data.qbody}</div>
		</c:if>
	</c:if>
	<!-- 상위 번호가 있는 경우(문항) -->
	<c:if test="${data.upno != 0}">
					<div class="w3-col pdl30">
						<div class="txt11"><input type="radio" name="${data.upno}" value="${data.qno}"> ${data.qbody}</div>
					</div>
	</c:if>	
			</c:forEach>
				</div>

				<input type="hidden" id="cnt" name="cnt" value="${no}">
			</form>
			<div class="w3-col">
				<div class="w3-col m2 w3-left w3-button w3-green" id="hbtn">HOME</div>
				<div class="w3-col m2 w3-right w3-button w3-blue" id="sbtn">제출</div>
			</div>
		</div>	
	</div>
</body>
</html>