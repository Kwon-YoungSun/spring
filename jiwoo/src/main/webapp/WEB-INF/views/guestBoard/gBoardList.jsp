<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cls 방명록 리스트</title>
<link rel="stylesheet" type="text/css" href="/cls/css/w3.css">
<link rel="stylesheet" type="text/css" href="/cls/css/cls.css">
<script type="text/javascript" src="/cls/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="/cls/js/guestBoard.js"></script>
</head>
<body>
	<div class="w3-content w3-center mw650">
		<h1 class="w3-orange w3-padding w3-card-4">Cls 방명록 리스트</h1>
		<div class="w3-col w3-border-bottom pdb10">
			<div class="w3-col m2 w3-left pdh1">
				<span class="w3-col w3-button w3-small w3-green w3-hover-lime w3-left mt0 btnBox" id="hbtn">Home</span>
			</div>
			<c:if test="${empty SID}">
				<div class="w3-col m2 w3-right pdh1">
					<span class="w3-col w3-button w3-small w3-orange w3-hover-deep-orange mt0 btnBox" id="lbtn">로그인</span>
				</div>
				<div class="w3-col m2 w3-right pdh1">
					<span class="w3-col w3-button w3-small w3-blue w3-hover-aqua w3-right mt0 btnBox" id="jbtn">회원가입</span>
				</div>
			</c:if>
			<c:if test="${not empty SID}">
				<div class="w3-col m2 w3-right pdh1">
					<span class="w3-col w3-button w3-small w3-red w3-hover-orange w3-right mt0 btnBox" id="obtn">로그아웃</span>
				</div>
			</c:if>
		</div>
		
		<!-- 글 입력창 -->
		<c:if test="${(not empty SID) and (CNT eq 0)}">
			
			<div class="w3-col pb10 w3-border-bottom w3-border-lightgrey pb20" >
				<div class="w3-col w3-card-4 pd10">
					<div class="w3-col w3-border-bottom w3-left-align w3-text-grey mb10">글 작 성</div>
					<div class="w3-col">
						<div class="w3-col inblock avtbox100 pdr10">
							<img src="/cls/img/avatar/${AVATAR}" class="avtimg100 w3-border">
						</div>
						<form method="POST" class="w3-rest" id="frm" name="frm">
							<textarea class="w3-input w3-border h72" style="resize: none;" placeholder="인삿말을 작성하세요!" id="body" name="body"></textarea>
							<div class="w3-col pdh1 mt5">
								<span class="w3-col m2 w3-left w3-button w3-small w3-orange w3-hover-deep-orange btnBox" id="rbtn">reset</span>
								<span class="w3-col m2 w3-right w3-button w3-small w3-orange w3-hover-deep-orange btnBox" id="wbtn">글 등 록</span>
							</div>
						</form>
					</div>
				</div>
			</div>
			
		</c:if>
		<!-- 글 목 록 창 -->
		<!-- 글 목록은 데이터베이스에 입력한 만큼 반복되어서 만들어져야 한다. -->
		<c:forEach var="data" items="${LIST}">
		
			<div class="w3-col w3-card-4 pd10 w3-margin-top">
				<div class="w3-col">
					<div class="w3-col inblock avtbox100 pdr10">
						<img src="/cls/img/avatar/${data.avatar}" class="avtimg100 w3-border">
					</div>
					<div class="w3-rest">
						<div class="w3-col w3-border-bottom w3-border-blue w3-leftw3-text-grey mb5 pb3">
							<div class="w3-cell w3-left">작성자 : ${data.id}</div>
							<div class="w3-cell w3-right"><small>${data.sdate}</small></div>
						</div>
						<div class="w3-col w3-padding h70 w3-left-align">${data.body}</div>
					</div>
				</div>
			</div>
		</c:forEach>
		
		<!-- 페이징 버튼 -->
		<div class="w3-col w3-margin-top">
			<form method="POST" action="/cls/guestBoard/gBoardList.cls" name="gfrm" id="gfrm" class="w3-bar w3-border w3-round">
				<input type="hidden" id="nowPage" name="nowPage">
				<!-- 이전 버튼 처리 -->
			  	<c:if test="${PAGE.startPage != 1}">
			  		<span class="w3-bar-item w3-button pagebtn" id="${PAGE.startPage - 1}">&laquo;</span>
			  	</c:if>
			  
			  	<c:forEach var="page" begin="${PAGE.startPage}" end="${PAGE.endPage}">
			  		<span class="w3-bar-item w3-button pagebtn" id="${page}">${page}</span>
			  	</c:forEach>
			  
				<!-- 다음 버튼 처리 -->
			  	<c:if test="${PAGE.endPage != PAGE.totalPage}">
			  		<span class="w3-bar-item w3-button pagebtn" id="${PAGE.endPage + 1}">&raquo;</span>
			  	</c:if>
			</form>
		</div>
	</div>
</body>
</html>