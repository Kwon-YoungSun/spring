<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1st JSP 파일</title>
<link rel="stylesheet" type="text/css" href="/cls/css/w3.css">
<link rel="stylesheet" type="text/css" href="/cls/css/cls.css">
<script type="text/javascript" src="/cls/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
<script type="text/javascript" src="/cls/js/main.js"></script>
<style>
	h5 {
		height: 20px;
		line-height: 30%;
	}
	
	.pdh10 > h5 {
		font-size: 10.5pt;
	} 
</style>
<script type="text/javascript">
</script>
</head>
<body>
	<form method="POST" action="/cls/member/memberInfo.cls" id="frm" name="frm">
		<input type="hidden" id="id" name="id" value="${SID}">
	</form>
	<div class="w3-content w3-center mw700">
		<h1 class="w3-purple w3-padding">CLS Project</h1>
		<div class="w3-col w3-padding">
			<c:if test="${empty SID}">
				<h5 class="w3-col m2 w3-button w3-small w3-indigo w3-hover-light-blue w3-left" id="jbtn">Join</h5>
				<h5 class="w3-col m2 w3-button w3-small w3-indigo w3-hover-light-blue w3-right" id="lbtn">Login</h5>
			</c:if>
			<!-- 굳이 범위를 주지 않아도 된다. -->
			<c:if test="${not empty SID}">
				<div class="w3-col" id="btnfr">
					<div class="w3-col w3-border-bottom">
						<h5 class="w3-cell m2 w3-button w3-blue w3-hover-aqua w3-right w3-small" id="obtn">LogOut</h5>				
						<h5 class="w3-cell m2 w3-button w3-blue w3-hover-aqua w3-left w3-small" id="ibtn">회원정보</h5>										
					</div>
					
					<div class="w3-col w3-margin-top">
						<div class="w3-col m3 pdh10">
							<h5 class="w3-col w3-button w3-red w3-hover-pink" id="gbtn">방명록</h5>				
						</div>
						<div class="w3-col m3 pdh10">
							<h5 class="w3-col w3-button w3-orange w3-hover-amber" id="sbtn">설문조사</h5>
						</div>
						<c:if test="${RCNT == 0}">
							<div class="w3-col m3 pdh10">			
								<h5 class="w3-col w3-button w3-yellow w3-hover-orange" id="irbtn">댓글쓰기</h5>
							</div>
						</c:if>
						<div class="w3-col m3 pdh10">			
							<h5 class="w3-col w3-button w3-pink w3-hover-orange" id="rbtn">댓글게시판</h5>
						</div>
						<div class="w3-col m3 pdh10">			
							<h5 class="w3-col w3-button w3-lime w3-hover-green" id="fbtn">파일게시판</h5>
						</div>
						<div class="w3-col m3 pdh10">			
							<h5 class="w3-col w3-button w3-blue w3-hover-aqua" id="searchbtn">회원검색</h5>
						</div>				
					</div>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>