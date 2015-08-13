<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> --%>


<%-- <sql:query var="rs" dataSource="jdbc/spring">
select id, name, email from offer
</sql:query> --%>

<c:forEach var="offer" items="${offers}">
    <p><c:out value="${offer }"></c:out></p>
</c:forEach>
