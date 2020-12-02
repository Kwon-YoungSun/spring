<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 보기</title>
<link rel="stylesheet" type="text/css" href="/cls/css/w3.css">
<link rel="stylesheet" type="text/css" href="/cls/css/cls.css">
<script type="text/javascript" src="/cls/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="/cls/js/member/memberInfo.js"></script>
<script>
	$(document).ready(function(){
		$('#prevB').click(function(){
			$(location).attr('href', '/cls/member/nameList.cls');
		});
	});
</script>
</head>
<body>
	<div class="w3-content w3-center mw750">
		<h1 class="w3-indigo w3-padding">${DATA.name} 님 회원 정보</h1>
		
		<div class="w3-row">
			<div class="w3-button w3-right w3-small w3-blue" id="homeB">HOME</div>
			<div class="w3-button w3-left w3-blue w3-small" id="prevB">PREV</div>
		</div>
		<div class="w3-row w3-card-4 w3-text-gray" style="padding: 20px; margin-top: 25px;">
			<div class="w3-row">
				<div class="w3-card-4 w3-col m5">
					<img src="/cls/img/avatar/${DATA.avatar}" class="w3-border" id="avtimg" style="width: 100%; height: auto;">	
				</div>
				<div class="w3-col m7 w3-padding" style="font-size: 14pt;">
					<div class="w3-row w3-margin-top">
						<div class="w3-col m4">회원번호 : </div>
						<div class="w3-col m7" id="no">${DATA.mno}</div>
					</div>
					<div class="w3-row w3-margin-top">
						<div class="w3-col m4">아 이 디 : </div>
						<div class="w3-col m7">${DATA.id}</div>
					</div>
					<div class="w3-row w3-margin-top">
						<div class="w3-col m4">회원이름 : </div>
						<div class="w3-col m7">${DATA.name}</div>
					</div>
					<div class="w3-row w3-margin-top">
						<div class="w3-col m4">이 메 일 : </div>
						<div class="w3-col m7" id="cMail">${DATA.mail}</div>
					</div>
					<div class="w3-row w3-margin-top">
						<div class="w3-col m4">회원성별 : </div>
						<div class="w3-col m7">
							<c:if test="${DATA.gen == 'M'}">
								남 자
							</c:if>
							<c:if test="${DATA.gen == 'F'}">
								여 자
							</c:if>
						</div>
					</div>
					<div class="w3-row w3-margin-top">
						<div class="w3-col m4">가 입 일 : </div>
						<div class="w3-col m7">${DATA.sdate}</div>
					</div>
				</div>
			</div>
			
			
		</div>
	</div>
</body>
</html>