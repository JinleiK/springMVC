<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>authorized users only</h2>
<table class="formtable">
<tr><td>username<td><td>authority<td><td>enabled<td></tr>
<c:forEach var="user" items="${users }">
<tr><td><c:out value="${user.username }"></c:out><td>
<td><c:out value="${user.authority }"></c:out><td>
<td><c:out value="${user.enabled }"></c:out><td></tr>
</c:forEach>
</table>
