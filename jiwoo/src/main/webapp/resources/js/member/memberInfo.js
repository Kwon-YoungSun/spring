$(document).ready(function(){
	// 홈으로 돌아가는 버튼
	$('#homeB').click(function(){
		$(location).attr('href', '/cls/main.cls');
	});
	// 정보 수정하는 버튼
	$('#editB').click(function(){
		$('#frm').stop().slideToggle(300);
	});
	
	$('#ebtn').click(function(){
		// 할 일
		// 데이터 읽어오고
		// 원 데이터
		var omail = $('#cmail').text();
		var oavt = $('#avtimg').val();
		
		// 수정 데이터
		var tmail = $('#mail').val();
		var tavt = $('.avt:checked').val();
		
		var imgsrc = $('#avtimg').attr('src').substring($('#avtimg').attr('src').lastIndexOf('/'));
		// ==> /img_avarat1.png
		var sno = $('#no').text();
		
		if(!tavt){
			tavt = oavt;
		} else {
			var tmp = $('.avt:checked').next().children().eq(0).attr('src');
			imgsrc = tmp.substring(tmp.lastIndexOf('/'));
		}
		
		$.ajax({
			url: '/cls/member/memberEdit.cls',
			type: 'POST',
			dataType: 'text',
			data: {
		//		mno: sno,
				mail: tmail,
				avt: tavt
			},
			success: function(obj){
				// obj 는 텍스트 문서이고 우리는 이 문서에 변경된 데이터 갯수를 입력해두기로 하자.
				// 따라서 회원정보가 변경이 되면 "1" 이 문서에 기록되고 반대의 경우는 "0"이 기록될 것이다.
				if(obj == 1){
					// 이 경우는 수정에 성공한 경우
					$('#cMail').text(tmail);
					$('#avtimg').attr('src', '/cls/img/avatar/' + imgsrc);
					
					$('#frm').stop().slideUp();
				} else {
					// 문제 있는 경우
					// 함수 실행을 즉시 종료하고 수정작업을 다시 해야된다.
					return;
				}
			},
			error: function(){
				alert('### 통신 실패 ###');
			}
		});
	});
	
	// 회원 탈퇴 처리
	$('#outB').click(function(){
		// 비밀번호 입력창 보이게 하고
		$('#dfrm').stop().slideDown(300);
	});
	
	$('#del').click(function(){
		var spw = $('#pw').val();
		
		if(!spw){
			$('#pw').focus();
			return;
		}
		
		// 동기 방식 처리
		$('#dfrm').submit();
		
		
		/*
		// 비동기 방식 처리
		var sno = $('#no').val();
		$.ajax({
			url: '/cls/member/ajaxDel.cls',
			type: 'POST',
			dataType: 'json',
			data: {
				no: sno,
				pw: spw
			},
			success: function(obj){
				// obj = { 'result': 'OK'};
				if(obj.result == 'OK'){
					// 탈퇴처리 완료된 경우
					$(location).attr('href', '/cls/main.cls');
				} else {
					// 탈퇴처리가 안된 경우
					$(location).attr('href', '/cls/member/memberInfo.cls');
				}
			},
			error: function(){
				alert('*** 통신 실패 ***');
			}
		});
		*/
	});
});