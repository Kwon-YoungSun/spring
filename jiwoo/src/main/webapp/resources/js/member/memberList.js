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

//		alert(sno);
		
		$.ajax({
			url: '/cls/member/memberInfoAjax.cls',
			type: 'POST',
			dataType: 'json',
			data: {
				mno: sno
			},
			success: function(obj){
				$('#infobox').stop().slideUp(300);
				removeInfo();
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
				$('#infobox').stop().slideDown(300);
			},
			error: function(){
				alert('*** 통신 실패 ***');
			}
		});
		
	});
});