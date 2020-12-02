$(document).ready(function(){
	// 홈
	$('#hbtn').click(function(){
		$(location).attr('href', '/cls/main.cls');
	});
	// 로그아웃
	$('#obtn').click(function(){
		$(location).attr('href', '/cls/member/logout.cls');
	});
	// 로그인
	$('#hbtn').click(function(){
		$(location).attr('href', '/cls/member/login.cls');
	});
	// 회원가입
	$('#jbtn').click(function(){
		$(location).attr('href', '/cls/member/join.cls');
	});
	// 목록
	$('#pbtn').click(function(){
		$(location).attr('href', '/cls/board/boardList.cls');
	});
	// 수정
	$('#ebtn').click(function(){
		$('#frm').submit();
	});
	

});