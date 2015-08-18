<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sec:csrfMetaTags />	
	
<div id="showMessages"></div>


<script type="text/javascript">

var timer;
var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");
var csrfToken = $("meta[name='_csrf']").attr("content");

function success(data) {
	alert("success");
}

function error(data) {
	alert("error");
}

function sendmessage(i, name, email) {
	var text = $("#replyContent" + i).val();
	var headers = {};
    headers[csrfHeader] = csrfToken;
	$.ajax({
		"type": 'POST',
		"url": '<c:url value="/sendmessage" />',
		"headers": headers,
		"data": JSON.stringify({"text": text, "name": name, "email": email}),
		"success": success,
		"error": error,
		contentType: "application/json",
		dataType: "json"
	});
}

function showMessages(data) {
	$("#showMessages").html("");
	
	for(var i = 0; i < data.messages.length; i ++) {
		var message = data.messages[i];
		
		var messageDiv = document.createElement("div");
		messageDiv.setAttribute("class", "message");
		
		var subjectSpan = document.createElement("span");
		subjectSpan.setAttribute("class", "subject");
		subjectSpan.appendChild(document.createTextNode(message.subject));
		
		var contentSpan = document.createElement("span");
		contentSpan.setAttribute("class", "messageContent");
		contentSpan.appendChild(document.createTextNode(message.content));
		
		var nameSpan = document.createElement("span");
		nameSpan.setAttribute("class", "name")
		nameSpan.appendChild(document.createTextNode(message.name + " (" + message.email + ")"));
		
		var replyForm = document.createElement("form");
		replyForm.setAttribute("class", "replyForm");
		var textArea = document.createElement("textArea");
		textArea.setAttribute("class", "replyArea");
		textArea.setAttribute("id", "replyContent" + i);
		textArea.setAttribute("onInput", "OnInput(event)");
		var replyButton = document.createElement("input");
		replyButton.setAttribute("type", "button");
		replyButton.setAttribute("value", "reply");
		replyButton.onclick = function(j, name, email) {
			return function() {
				sendmessage(j, name, message.email);
			}
		}(i, message.name, message.email);
		/* replyButton.setAttribute("class", "replybutton"); */
		replyForm.appendChild(textArea);
		replyForm.appendChild(replyButton);
		
		messageDiv.appendChild(subjectSpan);
		messageDiv.appendChild(contentSpan);
		messageDiv.appendChild(nameSpan);
		messageDiv.appendChild(replyForm);
		
		$("#showMessages").append(messageDiv);
	}
}

function updatePage() {
	$.getJSON("<c:url value='/getmessages' />", showMessages);
}

function startTimer() {
	timer = window.setInterval(updatePage, 5000);
}

function stopTimer() {
	window.clearInterval(timer);
}

function OnInput (event) {
	stopTimer();
}

function onLoad() {
	updatePage();
	startTimer();
}

$(document).ready(onLoad);

</script>