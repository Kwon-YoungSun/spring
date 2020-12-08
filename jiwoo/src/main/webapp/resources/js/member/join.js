$(document).ready(function(){
	// 아이디 체크버튼 처리
	$('#idck').click(function(){
		// 할 일
		// 1. 아이디 입력값을 알아낸다.
		var sid = $('#id').val();
		
		if(!sid){
			return;
		}
		
		// 데이터를 서버에 보내서 응답을 받는다. 비동기통신으로
		$.ajax({
			url: '/cls/member/idCheck.cls',
			type: 'POST',
			dataType: 'json',
			data: {
				id: sid
			},
			success: function(data){
				if(data.result == 'OK'){
					$('#idmsg').html('***** 사용 가능한 아이디 입니다! *****');
					$('#idmsg').removeClass('w3-text-red');
					$('#idmsg').addClass('w3-text-blue');
					$('#idmsg').stop().slideDown(500);
				} else {
					$('#idmsg').html('***** 사용 할 수 없는 아이디 입니다! *****');
					$('#idmsg').removeClass('w3-text-blue');
					$('#idmsg').addClass('w3-text-red');
					$('#idmsg').stop().slideDown(500);
				}
			},
			error: function(){
				alert('### 통신 에러 ###');
			}
		});
		
	});
	// 성별 선택하면 성별에 맞는 아바타 선택창 보여주는 기능 처리
	$('.gen').click(function(){
		// 할 일
		// 1. 무슨 성별이 선택되었는지 알아낸다.
		var sgen = $(this).val();
		
		if(sgen == 'M'){
			$('.avtFfr').stop().hide();
			$('.avtMfr').stop().slideDown(500);
		} else if(sgen == 'F'){
			$('.avtMfr').stop().hide();
			$('.avtFfr').stop().slideDown(500);
		}
	});
	
	// 비밀번호 확인 기능
	$('#repw').keyup(function(){
		// 할일
		// 1. 비밀번호 읽어오고
		var spw = $('#pw').val();
		// 2. 확인번호 읽어오고
		var srepw = $(this).val();	// this : 이 함수를 호출하고 있는 객체
		// 3. 비교하고
		if(spw == srepw){
			// 4. 결과 처리하고
			$('#pwmsg').css('color', 'green');
			$('#pwmsg').html('*** 비밀번호가 일치합니다! ***');
			$('#pwmsg').stop().show();
//			setTimeout(hidePwTag, 1500);
		} else {
			$('#pwmsg').css('color', 'red');			
			$('#pwmsg').html('*** 비밀번호가 다릅니다! ***');
			$('#pwmsg').stop().show();
		}
	});
/*	
	function hidePwTag(){
		$('#repw').parent().parent().stop().slideUp(300);
	}*/
	
	// 회원이름 정규식 검사
	$('#name').keyup(function(){
		var sname = $(this).val();
		
		var pattern = /^[가-힣]{2,10}$/;
		
		var result = pattern.test(sname);
//		alert('result : ' + result);
		if(!result){
			$('#namemsg').css('color', 'red');
			$('#namemsg').html('이름은 2자에서 10자 사이 한글이어야 합니다.');			
			$('#namemsg').stop().show();
		} else {
			$('#namemsg').stop().hide();
		}
	});
	
	// 회원 아이디 정규식 검사
	$('#id').keyup(function(){
		var sid = $(this).val();
		var pattern = /^[a-z]{1}[a-z0-9_-]{4,18}$/g;
		
		var result = pattern.test(sid);
		if(result){
			$(this).removeClass('w3-pale-red');
			$(this).addClass('w3-pale-green');
		} else {
			$(this).removeClass('w3-pale-green');
			$(this).addClass('w3-pale-red');		
		}
	});
	
	// 회원 비밀번호 정규식 검사
	$('#pw').keyup(function(){
		// 비밀번호는 영문 대소문자 각 한 개씩 숫자 한개 특수문자(#@!$%&*-_) 1개
		// 가 반드시 한개씩 포함되는 형식을 사용하기로 한다.
		
		// 입력 내용 알아내고
		var spw = $(this).val();
		
		// 정규식 패턴 만들고
/*		var pattern = /^(?=^[a-zA-Z0-9#@!$%&*-_]{8,})(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[#@!$%&*-_]).*$/; */
		var pattern = /^[0-9]{3,}$/;
		// 검사하고
		var result = pattern.test(spw);
		// 결과로 처리하고
		if(result){
			$(this).removeClass('w3-pale-red');
			$(this).addClass('w3-pale-green');
			$('#pwconf').stop().hide();						
		} else {
			$(this).removeClass('w3-pale-green');
			$(this).addClass('w3-pale-red');
			$('#pwconf').css('color', 'red');
			$('#pwconf').html('8자리 이상, 숫자와 영문 대소문자를 반드시 넣어야 합니다.');			
			$('#pwconf').stop().show();
		}
	});
	
	// 이메일 정규식 검사
	$('#mail').keyup(function(){
		var smail = $(this).val();
		
		var pattern = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	
		var result = pattern.test(smail);
		
		if(result){
			$(this).removeClass('w3-pale-red');
			$(this).addClass('w3-pale-green');
			$('#mailmsg').stop().hide();	
		} else {
			$(this).removeClass('w3-pale-green');
			$(this).addClass('w3-pale-red');
			$('#mailmsg').css('color', 'red');
			$('#mailmsg').html('형식에 맞지 않는 이메일 입니다.');			
			$('#mailmsg').stop().show();		
		}
	});
	
	$('#tel').keyup(function(){
		var stel = $(this).val();
		
		var pattern = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;
		
		var result = pattern.test(stel);
		
		if(result){
			$(this).removeClass('w3-pale-red');
			$(this).addClass('w3-pale-green');
			$('#telmsg').stop().hide();	
		} else {
			$(this).removeClass('w3-pale-green');
			$(this).addClass('w3-pale-red');
			$('#telmsg').css('color', 'red');
			$('#telmsg').html('전화번호 형식에 맞지 않습니다.');			
			$('#telmsg').stop().show();		
		}
	});
	
	// 버튼 이벤트 처리
	// 홈버튼
	$('#hbtn').click(function(){
		$(location).attr('href', '/cls/main.cls');
	});
	
	// 회원가입 처리 버튼
	$('#jbtn').click(function(){
		// 할 일
		// 데이터 유효성 검사하고
		var sid = $('#id').val();
		var spw = $('#pw').val();
		var srepw = $('#repw').val();
		var sname = $('#name').val();
		var smail = $('#mail').val();
		var tel = $('#tel').val();
		var sgen = $('.gen:checked').val();
		var savt = $('.avt:checked').val();
		
		if(!(sid && spw && sname && smail && tel && sgen && savt)){
			// 입력하지 않은 태그가 적어도 한 개가 존재하므로
			alert('입력하지 않은 정보를 확인하세요.');
			return;
		}
		
		if(spw != srepw){
			alert('비밀번호 확인이 일치하지 않습니다.');
			$('#repw').focus();
			return;
		}
		
		/*
		// 1. 포워드 방식으로 회원가입
		$('#frm').attr('method', 'POST');
		$('#frm').attr('action', '/cls/member/joinProc.cls');
		$('#frm').submit();
		*/
		
		
		// 2. 비동기 통신으로 회원가입
		// 먼저 formData 객체를 생성하고, 폼태그 전체를 가져온다.
		// 비동기 통신에서 form 태그를 전송할 경우
		// 해당 form 태그의 encType 속성이 반드시 기술되어야 한다.
		$('#frm').attr('encType', 'multipart/form-data');
		var el = $('#frm');
		var formData = new FormData($(el)[0]);

		// 비동기 통신을 하자.
		$.ajax({
			url: '/cls/member/joinAjaxProc.cls',
			type: 'POST',
			dataType: 'text',
			processData: false,
			contentType: false,
			data: formData,
			success: function(obj){
				if(obj == 'OK'){
					alert('회원가입 성공!');
					$(location).attr('href', '/cls/main.cls');
				} else {
					alert('*** 회원가입 실패 ***');
				}
			},
			error: function(){
				alert('### 통신 에러 ###');
			}
		});
		
	});
});








