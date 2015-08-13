<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h1>Create new account</h1>
 <sf:form method="post" action="${pageContext.request.contextPath}/createaccount" commandName="user" id="details">
 	<table class="formtable">
 		<tr><td class="label">Username: </td><td><sf:input class="control" path="username" name="username" type="text" /><br/><sf:errors path="username" cssClass="error"></sf:errors></td></tr>
 		<tr><td class="label">Password: </td><td><sf:input id="password" class="control" path="password" name="password" type="password" /><br/><sf:errors path="password" cssClass="error"></sf:errors></td></tr>
 		<tr><td class="label">Confirm Password: </td><td><input id="confirmpass" class="control" name="confirmpassword" type="password" /><br/><div id="passmatch"></div></td></tr>
 		<tr><td class="label">Name: </td><td><sf:input class="control" path="name" name="name" type="text" /><br/><sf:errors path="name" cssClass="error"></sf:errors></td></tr>
 		<tr><td class="label"></td><td><input class="control" value="Create account" type="submit" /></td></tr>
 	</table>
 	
 </sf:form>
