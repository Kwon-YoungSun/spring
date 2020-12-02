$(document).ready(function(){
	$('#lbtn').click(function(){
		$(location).attr('href', '/cls/member/login.cls');
	});
	
	$('#obtn').click(function(){
		$(location).attr('href', '/cls/member/logout.cls');
	});
	
	$('#jbtn').click(function(){
		$(location).attr('href', '/cls/member/join.cls');
	});
	
	$('#ibtn').click(function(){
		$('#frm').submit();
	})
	
	$('#gbtn').click(function(){
		$(location).attr('href', '/cls/guestBoard/gBoardList.cls');
	});
	
	$('#rbtn').click(function(){
		$(location).attr('href', '/cls/reBoard/reBoardList.cls');
	});
	
	$('#irbtn').click(function(){
		$(location).attr('href', '/cls/reBoard/initRBD.cls');
	});
	
	$('#sbtn').click(function(){
		$(location).attr('href', '/cls/survey/surveyInfo.cls');
	});
	
	$('#fbtn').click(function(){
		$(location).attr('href', '/cls/board/boardList.cls');
	});
	$('#searchbtn').click(function(){
		$(location).attr('href', '/cls/search/search.cls');
	});
	$('#membListbtn').click(function(){
		$(location).attr('href', '/cls/member/nameList.cls');
	});
});