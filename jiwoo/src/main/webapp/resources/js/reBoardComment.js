$(document).ready(function(){
	$('#rbtn').click(function(){
		$('#body').val('');
	});
	
	$('#wrbtn').click(function(){
		// 폼 전송하고
		$('#frm').submit();
	});
});