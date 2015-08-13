<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">

function onload() {
	$("#password").keyup(checkPasswordsMatch);
	$("#confirmpass").keyup(checkPasswordsMatch);
	
	$("#details").submit(canSubmit);
}

function checkPasswordsMatch() {
	var password = $("#password").val();
	var confirmpass = $("#confirmpass").val();
	if(password.length < 3)
		return;
	
	if(password == confirmpass) {
		$("#passmatch").text("<fmt:message key='MatchedPasswords.user.password' />");
		$("#passmatch").addClass("valid");
		$("#passmatch").removeClass("error");
	} else {
		$("#passmatch").text("<fmt:message key='UnmatchedPasswords.user.password' />");
		$("#passmatch").removeClass("valid");
		$("#passmatch").addClass("error");
	}
}

function canSubmit() {
	var password = $("#password").val();
	var confirmpass = $("#confirmpass").val();
	
	if(password != confirmpass) {
		alert("<fmt:message key='UnmatchedPasswords.user.password' />");
		return false;
	} else return true;
}

$(document).ready(onload);
</script>