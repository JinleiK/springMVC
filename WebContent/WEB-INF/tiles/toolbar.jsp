<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
	
<sec:authorize access="hasRole('ADMIN')">
		<a href="<c:url value='/admin'/>">admin</a>
</sec:authorize>
	&nbsp;
<c:choose>
	<c:when test="${hasOffer }">
			<a href="${pageContext.request.contextPath}/createoffer">Edit ur
				current offer</a>
	</c:when>
	<c:otherwise>
			<a href="${pageContext.request.contextPath}/createoffer">create
				offer</a>
	</c:otherwise>
</c:choose>
&nbsp;

<sec:authorize access="isAuthenticated()">
		<a href="<c:url value='/messages'/>">messages(<span id="numMessages">0</span>)</a>
</sec:authorize>



<script type="text/javascript">
<!--

function updateMessagesLink(data) {
	$("#numMessages").text(data.number);
}

function updateMessages() {
	$.getJSON("<c:url value="/getmessages" />", updateMessagesLink);
}

function onLoad() {
	updateMessages();
	window.setInterval(updateMessages, 5000);
}

$(document).ready(onLoad);

//-->
</script>