$(document).ready(function(){
	
	function removeInfo(){
		$('#avtimg').attr('src', '');
		$('#no').text('');
		$('#id').text('');
		$('#name').text('');
		$('#cMail').text('');
		$('#gen').text('');
		$('#jdate').text('');
	}
	
	$('.namelist').click(function(){
		var sno = $(this).attr('id');
		$('#infobox').stop().slideUp(300);
		removeInfo();
//		alert(sno);
		$('#outB').css('visibility', 'hidden');
		$('#editB').css('visibility', 'hidden');
		$.ajax({
			url: '/cls/member/memberInfoAjax.cls',
			type: 'POST',
			dataType: 'json',
			data: {
				mno: sno
			},
			success: function(obj){
				var sid = $('#sid').val();
				$('#avtimg').attr('src', '/cls/img/avatar/' + obj.avatar);
				$('#no').text(obj.mno);
				$('#id').text(obj.id);
				$('#name').text(obj.name);
				$('#cMail').text(obj.mail);
				var gen = obj.gen;
				var sgen = '';
				if(gen == 'M'){
					sgen = '남자';
				} else {
					sgen = '여자';
				}
				$('#gen').text(sgen);
				$('#jdate').text(obj.sdate);
				// 회원 아이디와 같은 정보일 경우에만 버튼이 보여지도록 한다
				if(sid == obj.id){
					$('#outB').css('visibility', 'visible');
					$('#editB').css('visibility', 'visible');
				}
				$('#infobox').stop().slideDown(300);
			},
			error: function(){
				alert('*** 통신 실패 ***');
			}
		});
		
	});
});