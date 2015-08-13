<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

hello world

<%-- Session: <%=session.getAttribute("name") %>

<p>Request: <%=request.getAttribute("name") %></p> --%>
<%-- <p>Request(using EL):${name}</p>

<p><c:out value="${name }"></c:out></p> --%>
<table class="offers">
	<tr>
		<td>Name
		<td>
		<td>Email
		<td>
		<td>Offer
		<td>
	</tr>
	<c:forEach var="offer" items="${offers }">
		<tr>
			<td><c:out value="${offer.user.name }"></c:out></td>
			<td><a href="<c:url value='/message?uid=${offer.user.username }' />">Contact</a></td>
			<td><c:out value="${offer.text }"></c:out></td>
		</tr>
	</c:forEach>
</table>



<c:choose>
	<c:when test="${hasOffer }">
		<p>
			<a href="${pageContext.request.contextPath}/createoffer">Edit ur
				current offer</a>
		</p>
	</c:when>
	<c:otherwise>
		<p>
			<a href="${pageContext.request.contextPath}/createoffer">create
				offer</a>
		</p>
	</c:otherwise>
</c:choose>
<%-- <p><a href="${pageContext.request.contextPath}/createoffer">create offer</a></p> --%>

<%-- <sec:authorize access="!isAuthenticated()">
<p><a href="<c:url value='/login'/>">log in</a></p>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
  <input type="submit" value="Log out" />
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</sec:authorize> --%>

<sec:authorize access="hasRole('ADMIN')">
	<p>
		<a href="<c:url value='/admin'/>">admin</a>
	</p>
</sec:authorize>

