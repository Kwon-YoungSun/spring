$(document).ready(function(){
/* 게시물 메인 페이지 이벤트 처리 */
	// 페이징 처리
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
		
		// 파라미터 셋팅 부터 하고
		$('#nowPage').val(sPage);
		$('#bfrm').attr('action', '/cls/board/boardList.cls');
		$('#bfrm').submit();
	});
	
	// 홈
	$('#hbtn').click(function(){
		$(location).attr('href', '/cls/main.cls');
	});
	// 로그아웃
	$('#obtn').click(function(){
		$(location).attr('href', '/cls/member/logout.cls');
	});
	// 로그인
	$('#ibtn').click(function(){
		$(location).attr('href', '/cls/member/login.cls');
	});
	// 회원가입
	$('#jbtn').click(function(){
		$(location).attr('href', '/cls/member/join.cls');
	});
	// 글쓰기
	$('#rbtn').click(function(){
		$(location).attr('href', '/cls/board/boardWrite.cls');		
	})
	
	// 게시글 상세보기 처리
	$('.brow').click(function(){
		// 게시물 번호 아이디값 알아내고
		var sid = $(this).attr('id');
		
		// input 태그에 실어 보낸다.
//		alert(sid);
		$('#bno').val(sid);
		$('#bfrm').attr('action', '/cls/board/boardDetail.cls');
		$('#bfrm').submit();
	});
	
	
/* 게시글 작성 페이지 이벤트 처리 */
	$('#wbtn').click(function(){
		var shead = $('#title').val();
		var sbody = $('#body').val();
		
		// 데이터가 입력되었는지 확인
		if((!shead) || (!sbody)){
			alert('게시글이 입력되었는지 확인하세요.')
			return;
		}
		
		// 이곳을 실행하는 경우는 모든 입력태그에 데이터를 입력하는 경우
		$('#wfrm').submit();
	});
	
	// 파일 선택 시 파일 선택 창 추가 이벤트 처리
	var i = 1;
	$('.upfile').change(function(){
		i = i + 1;
		$(this).parent().append('<input type="file" name="file'+ i +'" class="w3-col w3-input w3-border pdl10 upfile" placeholder="파일을 선택하세요!">');
	});
	
	$('#cbtn').click(function(){
		$(location).attr('href', '/cls/board/boardList.cls');
	});

/* BOARD EDIT EVENT */
	// 문서가 완성이 되면 태그에 입력된 값을 기억을 해 놓는다.

	var stitle = $('#title').val();
	var body = $('#body').val();
	
	$('#edit').click(function(){
		var tTitle = $('#title').val();
		var tBody = $('#body').val();
		
		if(stitle == tTitle && tBody == body){
			return;
		}
		
		if(stitle == tTitle){
			// readonly 속성이 true일 경우 파라미터 전송이 안됨
			$('#title').prop('readonly', true);
		}
		
		if(tBody == body){
			$('#body').prop('readonly', true);
		}
		
		$('#efrm').submit();
	});
});