$(document).ready(function(){
	var one = document.getElementById('one');
	var two = document.getElementById('two');
	
	// '입장' 버튼 클릭했을 때의 이벤트 처리
	document.getElementById('enter').addEventListener('click', function(){
		// 웹 소켓 연결해주는 함수 호출
		connect();
	});
	
	// '퇴장' 버튼 클릭했을 때의 이벤트 처리
	document.getElementById('exit').addEventListener('click', function(){
		// 연결을 해제해주는 함수 호출
		disconnect();
	});
	
	// '보내기' 버튼 클릭했을 때의 이벤트 처리
	document.getElementById('send').addEventListener('click', function(){
		// 메세지를 보내는 함수 호출
		send();
	});
	
	var websocket;
	// 입장 버튼을 눌렀을 때 호출되는 함수
	function connect(){
		websocket = new WebSocket("ws://localhost:80/cls/member/chatting.cls");
		
		// 웹 소켓에 이벤트가 발생했을 때 호출될 함수 등록
		websocket.onopen = onOpen;
		websocket.onmessage = onMessage;
		websocket.onclose = onClose;
	}
	// 퇴장 버튼을 눌렀을 때 호출되는 함수
	function disconnect(){
		msg = document.getElementById('nickname').value;
		websocket.send(msg + '님이 퇴장하셨습니다.');
		websocket.close();
	}
	
	// 보내기 버튼을 눌렀을 때 호출될 함수
	function send(){
		nickname = document.getElementById('nickname').value;
		msg = document.getElementById('message').value;
		websocket.send(nickname + " : " + msg);
		document.getElementById('message').value = "";
	}
	/*
		연결을 맺는것은 비동기 작업이고 실패하기 쉬운 작업이기 때문에, 
		WebSocket 오브젝트를 생성하자마자  send() 로 데이터 전송을 시도하는것은 
		성공하지 않을 가능성이 있습니다. 
		
		우리는 연결이 수립된 이후에만 데이터를 전송하도록 하기 위해 
		onopen 핸들러를 정의하고, 이 위에서 작업합니다.
	*/
	// 웹 소켓에 연결되었을 때 호출될 함수
	function onOpen() {
		nickname = document.getElementById('nickname').value;
		two = document.getElementById('two');
		two.style.display='block';
		websocket.send(nickname + "님 입장하셨습니다.");
	}
	/*
		WebSockets는 event-driven API 입니다;
		메세지가 수신되면 "message" 이벤트가 onmessage 함수로 전달되게 됩니다.
		아래와 같은 코드를 작성하여 수신되는 데이터를 받아볼 수 있습니다.:
	*/
	// 웹소켓에서 연결이 해제되었을 때 호출될 함수
	function onMessage(evt) {
		data = evt.data;
		chatarea = document.getElementById('chatarea');
		chatarea.innerHTML = data + "<br/>" + chatarea.innerHTML;
	}
	
	function onClose(){
		
	}
});