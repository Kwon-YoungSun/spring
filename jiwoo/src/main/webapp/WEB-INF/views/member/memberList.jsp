<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberList</title>
<link rel="stylesheet" type="text/css" href="/cls/css/w3.css">
<link rel="stylesheet" type="text/css" href="/cls/css/cls.css">
<script type="text/javascript" src="/cls/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="/cls/js/member/memberList.js"></script>
<style>
	.namelist{
		line-height: 220%;
	}
	
	#editB, #outB{
		visibility: hidden;
	}
</style>
<script type="text/javascript">
	$(function(){
		/*
		$('.namelist').click(function(){
			var sno = $(this).attr('id');
			
			$('#mno').val(sno);
			$('#frm').attr('action', '/cls/member/memberInfo2.cls');
			$('#frm').submit();
		});
		*/
		$('.namelist').css('height', '50px');
		$('.namelist').removeClass('w3-margin-top');
		$('.namelist').addClass('w3-input');
		
		$('#homeB').click(function(){
			$(location).attr('href', '/cls/main.cls');
		});
	});
	
</script>
</head>
<body>
	<form id="frm" name="frm" method="POST">
		<input type="hidden" id="mno" name="mno">
		<input type="hidden" id="sid" name="sid" value="${SID}">
	</form>
	<div class="w3-content w3-center mw750">
		<h1 class="w3-col w3-center w3-padding w3-deep-purple">회원 리스트</h1>
		<div class="w3-row">
			<div class="w3-button w3-left w3-small w3-blue w3-margin-bottom" id="homeB">HOME</div>
		</div>
		<div class="w3-col">
<c:set var="i" value="${0}" />
<c:forEach var="list" items="${LIST}">
	<c:set var="i" value="${i + 1}" />
			<div id="${list.mno}" class="w3-col m2 w3-button ${COLORS[i]} w3-margin-bottom w3-margin-top namelist">${list.name}</div>
</c:forEach>
		</div>
		<div class="w3-button w3-right w3-red w3-small w3-round" id="outB">탈퇴</div>
		<div class="w3-button w3-right w3-green w3-small w3-round" id="editB">edit</div>
		<div id="infobox" class="w3-col w3-card-4 w3-border w3-text-gray w3-margin-top w3-margin-bottom" style="padding: 20px; margin-top: 25px; display: none;">
			<div class="w3-row">
				<div class="w3-card-4 w3-col m5">
					<img class="w3-border" id="avtimg" style="width: 100%; height: auto;">	
				</div>
				<div class="w3-col m7 w3-padding" style="font-size: 14pt;">
					<div class="w3-row w3-margin-top">
						<div class="w3-col m4">회원번호 : </div>
						<div class="w3-col m7" id="no"></div>
					</div>
					<div class="w3-row w3-margin-top">
						<div class="w3-col m4">아 이 디 : </div>
						<div class="w3-col m7" id="id"></div>
					</div>
					<div class="w3-row w3-margin-top">
						<div class="w3-col m4">회원이름 : </div>
						<div class="w3-col m7" id="name"></div>
					</div>
					<div class="w3-row w3-margin-top">
						<div class="w3-col m4">이 메 일 : </div>
						<div class="w3-col m7" id="cMail"></div>
					</div>
					<div class="w3-row w3-margin-top">
						<div class="w3-col m4">회원성별 : </div>
						<div class="w3-col m7" id="gen">
							
						</div>
					</div>
					<div class="w3-row w3-margin-top">
						<div class="w3-col m4">가 입 일 : </div>
						<div class="w3-col m7" id="jdate"></div>
					</div>
				</div>
			</div>
			
		</div>
		
	</div>
	
	
	
	
</body>
</html>