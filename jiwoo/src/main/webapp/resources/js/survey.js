$(document).ready(function(){
/* 설문 정보 페이지(SurveyInfo) 이벤트 처리 */
	$('.sibtn').click(function(){
		// 버튼의 아이디값 읽어오고
		var tid = $(this).attr('id');
		// 입력태그에 입력하고
		$('#sno').val(tid);
		// 폼 태그 전송하고
		$('#frm').submit();
	});
	
	$('.srbtn').click(function(){
		var tno = $(this).attr('id').substring(1);
		$('#sno').val(tno);
		$('#frm').attr('action', '/cls/survey/surveyResult.cls');
		$('#frm').submit();
//		$(location).attr('href', '/cls/survey/surveyResult.cls');
	});

/* 설문 페이지(Survey) 이벤트 처리 */
	$('#hbtn').click(function(){
		$(location).attr('href', '/cls/main.cls');
	});
	
	$('#sbtn').click(function(){
		var sno = $('#cnt').val();
		var tno = $('input:checked');
		if(sno != tno.length){
			return;
		}
		
		$('#frm').submit();
	});

/**/
	$('#sibtn').click(function(){
		$(location).attr('href', '/cls/survey/surveyInfo.cls');
	});
});