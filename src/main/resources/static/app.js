var stompClient = null;

function sendData(form) {
	var XHR = new XMLHttpRequest();

	// Bind the FormData object and the form element
	var FD = new FormData(form);
	var FDobject = {};
	FD.forEach(function(value, key) {
		FDobject[key] = value;
	});
	FDobject['id'] = Math.floor(Math.random() * Number.MAX_SAFE_INTEGER);
	var json = JSON.stringify(FDobject);

	// Define what happens on successful data submission
	XHR.addEventListener("load", function(event) {
		console.log(event.target.responseText);
	});

	// Define what happens in case of error
	XHR.addEventListener("error", function(event) {
		alert('Oops! Something goes wrong.');
	});

	// Set up our request
	XHR.open("POST", "/msg");
	XHR.setRequestHeader('Content-type', 'application/json');
	XHR.setRequestHeader('Accept', 'application/json');

	// The data sent is what the user provided in the form
	XHR.send(json);
}

function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	if (connected) {
		$("#conversation").show();
	} else {
		$("#conversation").hide();
	}
	$("#chat").html("");
}

function connect() {
	var socket = new SockJS('/broadcast-websocket');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		setConnected(true);
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/chat', function(greeting) {
			console.log(greeting);
			console.log(JSON.stringify(greeting));
			showChat(JSON.parse(greeting.body).message);
		});
	});
}

function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function sendHistory() {
	stompClient.send("/app/getmessage", {}, JSON.stringify({
		'history' : $("#history").val()
	}));
}

function showChat(message) {
	$("#chat").prepend("<tr><td>" + message + "</td></tr>");
}

window.addEventListener("load", function() {
	// Access the form element...
	var form = document.getElementById("sendMessage");

	// ...and take over its submit event.
	form.addEventListener("submit", function(event) {
		event.preventDefault();

		sendData(form);
	});
});

$(function() {
	$("form").on('submit', function(e) {
		e.preventDefault();
	});
	$("#connect").click(function() {
		connect();
	});
	$("#disconnect").click(function() {
		disconnect();
	});
	$("#send").click(function() {
		sendHistory();
	});
});