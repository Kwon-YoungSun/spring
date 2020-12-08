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
<script type="text/javascript" src="/cls/js/member/memberInfo.js"></script>
</head>
<body>
	<div class="w3-content w3-center mw750">
		<h1 class="w3-indigo w3-padding">${DATA.name} 님 회원 정보</h1>
		
		<div class="w3-row">
			<div class="w3-button w3-left w3-blue" id="homeB">HOME</div>
			<div class="w3-button w3-right w3-blue" id="outB">탈퇴</div>
			<div class="w3-button w3-right w3-blue" id="editB">edit</div>
			<form method="POST" action="/cls/member/memberDel.cls" 
					id="dfrm" name="dfrm" style="margin-top: 20px; font-size: 14pt; display: none;"
					class="w3-col w3-card-4 w3-padding">
				<label for="pw" class="w3-col m3 w3-text-grey ft18px w3-padding">비밀번호 : </label>
				<input type="hidden" name="mno" id="mno" value="${DATA.mno}">
				<input type="password" id="pw" name="pw" 
						class="w3-col m7 w3-input w3-border">
				<div class="w3-col m2 pdh10">
					<div class="w3-col w3-button w3-medium w3-red w3-hover-orange w3-left mt0" id="del">탈퇴처리</div>
				</div>
			</form>
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
			
			<!-- 정보 수정 태그 -->
			<form class="w3-col w3-border-top" id="frm" name="frm" style="display: none; margin-top: 25px;">
				<input type="hidden" name="id" value="${ID}">
				
				<div class="w3-row w3-margin-top">
					<div class="w3-button w3-right w3-blue w3-hover-aqua" id="ebtn">수정</div>
				</div>
				
				<div class="w3-col w3-margin-top w3-margin-bottom" style="font-size: 14pt;">
					<label for="mail" class="w3-col l3 m3 w3-right-align w3-padding">회원 이메일 : </label>
					<div class="w3-col l9 m9">
						<input type="text" class="w3-input w3-border" id="mail" name="mail" value="${DATA.mail}">
					</div>
				</div>
				
				<div class="w3-col w3-margin-bottom" style="font-size: 14pt;">
					<label for="gen" class="w3-col l3 m3 w3-right-align w3-padding">아바타 선택 : </label>
					<div class="w3-col l9 m9 w3-padding">
						
						<div class="w3-col" id="avtfr">
							<c:forEach var="data" items="${LIST}">
								<c:if test="${data.gen == DATA.gen}">
									<div class="w3-third w3-center w3-padding avt${data.gen}fr">
										<input type="radio" class="w3-col w3-radio-small avt" name="avt" value="${data.ano}">
										<div class="w3-col w3-border imgbox" style="margin-left : 10px;">
											<img src="/cls/img/avatar/${data.savename}" class="imgsrc">
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
	
					</div>
				</div>
			</form>
			
		</div>
		
		<!-- 메세지 확인 모달 -->
		<c:if test="${not empty MSG}">
		<div id="id01" class="w3-modal" style="display: block;">
		    <div class="w3-modal-content">
		   <c:if test="${MSG ne '수정에 실패했습니다.' }">
		      <header class="w3-container w3-teal">
		        <span onclick="document.getElementById('id01').style.display='none'" 
		        class="w3-button w3-display-topright">&times;</span>
		        <h2>Modal Header</h2>
		      </header>
		   </c:if>
		   <c:if test="${MSG eq '수정에 실패했습니다.' }">
		      <header class="w3-container w3-red">
		        <span onclick="document.getElementById('id01').style.display='none'" 
		        class="w3-button w3-display-topright">&times;</span>
		        <h2>Modal Header</h2>
		      </header>
		   </c:if>
		      <div class="w3-container w3-margin-top w3-margin-bottom" style="padding-bottom: 16px;">
		        <h4 class="w3-center w3-text-grey w3-margin-top w3-margin-bottom">${MSG}</h4>
		      </div>
		    </div>
		</div>
		</c:if>
	</div>
</body>
</html>