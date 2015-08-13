<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h1>Send message</h1>

<sf:form method="POST" commandName="message">

<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey }" />
<input type="hidden" name="_eventId" value="send" />

<input type='hidden' name='username' value='aaaaa'/>

	<table class="formtable">
		<tr>
			<td><label class="label">Your name:</label></td>
			<td><sf:input class="control" path="name" type="text" value="${fromName }" /><br />
			<div class="error">
					<sf:errors path="name"></sf:errors>
				</div></td>
		</tr>
		
		<tr>
			<td><label class="label">Your email:</label></td>
			<td><sf:input class="control" path="email" type="text" /><br />
			<div class="error">
					<sf:errors path="email"></sf:errors>
				</div></td>
		</tr>
		
		<tr>
			<td><label class="label">Subject:</label></td>
			<td><sf:input class="control" path="subject" type="text" /><br />
			<div class="error">
					<sf:errors path="subject"></sf:errors>
				</div></td>
		</tr>

		<tr>
			<td><label class="label">Your message:</label></td>
			<td><sf:textarea class="control" path="content" type="text" /><br />
			<div class="error">
					<sf:errors path="content"></sf:errors>
				</div></td>
		</tr>
		
		<tr>
			<td><label class="label"></label></td>
			<td><input class="control" value="Send message" type="submit" />
			</td>
		</tr>
		
	</table>

</sf:form>