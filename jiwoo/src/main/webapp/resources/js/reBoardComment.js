$(document).ready(function(){
	$('#rbtn').click(function(){
		$('#tbody').val('');
	});
	
	$('#wrbtn').click(function(){
		// 폼 전송하고
		$('#frm').submit();
	});
});