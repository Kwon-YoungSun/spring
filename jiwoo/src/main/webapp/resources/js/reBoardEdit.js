$(document).ready(function(){
	// 처음 가져온 본문글
	var body = $('#body').val();		
	$('#wrbtn').click(function(){
		// body 글 읽어오고(버튼이 클릭되었을 때 본문글)
		var tbody = $('#tbody').val();
		
		if(body == tbody){
			return;
		}
		$('#frm').submit();
	});
	$('#rbtn').click(function(){
		$('#body').val(body);
	});
});