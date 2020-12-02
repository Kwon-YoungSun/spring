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
<script type="text/javascript">
	$(function(){
		$('.mw600 > div').css('height', '50px');
		$('.mw600 > div').removeClass('w3-margin-top');
		$('.mw600 > div').addClass('w3-input');
		
		$('.namelist').click(function(){
			var sno = $(this).attr('id');
			
			$('#mno').val(sno);
			$('#frm').attr('action', '/cls/member/memberInfo2.cls');
			$('#frm').submit();
		});
	});
</script>
</head>
<body>
	<form id="frm" name="frm" method="POST">
		<input type="hidden" id="mno" name="mno">
	</form>
	<div class="w3-content w3-center mw600">
		<h1 class="w3-center w3-padding w3-deep-purple">회원 리스트</h1>
<c:forEach var="list" items="${LIST}">
		<div id="${list.mno}" class="w3-col m2 w3-button w3-hover-indigo w3-purple w3-margin-bottom w3-margin-top namelist">${list.name}</div>
</c:forEach>	
	</div>
</body>
</html>