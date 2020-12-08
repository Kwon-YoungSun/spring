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
<script type="text/javascript" src="/cls/js/reBoardComment.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<div class="w3-content w3-center mw700">
		<h1 class="w3-col w3-padding w3-purple">댓 글 작 성</h1>
			<div class="w3-col">
				<div class="w3-col inblock avtbox100 pdr10">
					<img src="/cls/img/avatar/${AVTIMG}" class="avtimg100 w3-border">
				</div>
				<form method="POST" action="/cls/reBoard/reBoardCommentProc.cls" class="w3-rest" id="frm" name="frm">
					<input type="hidden" id="upno" name="upno" value="${bno}">
					<input type="hidden" name="nowPage" value="${nowPage}">

					<textarea class="w3-input w3-border h72" style="resize: none;" id="tbody" name="tbody" placeholder="메세지를 작성하세요!"></textarea>
					<div class="w3-col pdh1 mt5">
						<span class="w3-col m2 w3-left w3-button w3-small w3-lime w3-hover-light-green btn" id="rbtn">reset</span>
						<span class="w3-col m2 w3-right w3-button w3-small w3-pink w3-hover-purple btn" id="wrbtn">댓 글 등 록</span>
					</div>
				</form>
			</div>
	</div>
</body>
</html>