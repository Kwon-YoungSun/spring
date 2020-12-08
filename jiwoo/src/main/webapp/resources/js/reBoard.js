$(document).ready(function(){
	$('.pagebtn').click(function(){
		var str = $(this).text();
//		var sno = $(this).attr('id');
		var pcode = str.charCodeAt(str);
		
		var sPage = '';
		
		if(pcode == 171){
			sPage = $(this).attr('id');
//			if(sPage == 0) return;
		} else if(pcode == 187){
			sPage = $(this).attr('id');
		} else {
			sPage = str;
		}
		
		/*
		// 1. GET 방식 전송
		$(location).attr('href', '/cls/reBoard/reBoardList.cls?nowPage=' + sPage);
		*/
		
		// 2. POST 방식 전송
		// 파라미터 셋팅 부터 하고
		$('#nowPage').val(sPage);
		$('#pfrm').submit();
	});
	
	$('.btnBox').click(function(){
		var tid = $(this).attr('id');
		
		switch(tid){
		case 'hbtn':
			url = '/cls/main.cls';
			break;
		case 'lbtn':
			url = '/cls/member/login.cls';
			break;
		case 'jbtn':
			url = '/cls/member/join.cls';
			break;
		case 'obtn':
			url = '/cls/member/logout.cls';
			break;
		}
		
		$(location).attr('href', url);
	});
	
	$('#mcbtn').click(function(){
		$('#wmodal').css('display', 'none');
	});
	
	// 원글 쓰기
	$('#wbtn').click(function(){
		$('#body').val('');
		$('#mtitle').html('<b>글 작 성</b>');
		$('#wrbtn').html('글등록');
		$('#wmodal').css('display', 'block');
	});
	// 리셋 버튼
	$('#rbtn').click(function(){
		$('#body').val('');
	});
	
	// 글쓰기 버튼(모달창) 이벤트 처리	
	$('#wrbtn').click(function(){
		var str = $(this).text();
		// 수정인지 등록인지 구분해주고
		
		var txt = $('#body').val();
		var url = '/cls/reBoard/reBoardWriteProc.cls';
		
		// 새로운 글을 쓰는 상황이면
		if(str != 'edit'){
			$('#tno').val(0);
			if(!txt){
				alert('메세지를 입력하세요!');
				return;
			}			
		} else { // 글을 수정하는 상황이면
			url = '/cls/reBoard/reBoardEditProc.cls';	
		}
		
		alert($('#body').val());
		$('#frm').attr('method', 'POST');
		$('#frm').attr('action', url);
		$('#frm').submit();
	});
	
	
	// 게시글 삭제 이벤트 처리
	$('.dbtn').click(function(){
		var str = $(this).attr('id');
		var tno = str.substring(1);
		
		$('#dbno').val(tno);
		$('#frm1').attr('action', '/cls/reBoard/reBoardDelProc.cls');
		$('#frm1').submit();
	});
	
	// 게시글 수정 이벤트 처리
	$('.ebtn').click(function(){
		// 버튼 내용 변경
		
		// 모달 창을 이용해서 처리하는 방법
		$('#wrbtn').html('edit');
		$('#mtitle').html('<b>글 수 정</b>');
		
		// 수정할 글번호 기억시켜놓고
		var tno = $(this).attr('id').substring(1);
		$('#tno').val(tno);
		
		// 수정할 내용 읽어오고
		var tbody = $(this).parent().siblings().eq(0).html().replaceAll('<br>', '\r\n');
		
		// 내용 입력태그에 입력하고
		$('#body').val(tbody);
		$('#wmodal').css('display', 'block');
		
		
		/*
		// 새로운 뷰를 요청해서 처리하는 방법
		// 데이터 입력태그에 입력하고
		var tno = $(this).attr('id').substring(1);
		$('#tno').val(tno);
		
		var tbody = $(this).parent().siblings().eq(0).html().replaceAll('<br>', '\r\n');
		$('#tbody').val(tbody);
		$('#frm').attr('method', 'POST');
		$('#frm').attr('action', '/cls/reBoard/reBoardEditView.cls');
		$('#frm').submit();
		*/
	});
	
	// 게시글 답글 이벤트 처리
	$('.rebtn').click(function(){
		var tno = $(this).attr('id');
		$('#tno').val(tno);
		$('#frm').attr('method', 'POST');
		$('#frm').attr('action', '/cls/reBoard/reBoardComment.cls');
		$('#frm').submit();
	});
	
});