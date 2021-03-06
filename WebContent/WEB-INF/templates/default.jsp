<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link href="${pageContext.request.contextPath }/static/css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/static/script/jquery.js"></script>

<title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>

<tiles:insertAttribute name="includes"></tiles:insertAttribute>
</head>
<body>
<script src="https://code.angularjs.org/1.4.4/angular.js"></script>

<div class="header">
<tiles:insertAttribute name="header"></tiles:insertAttribute>
</div>
<div class="toolbar">
<tiles:insertAttribute name="toolbar"></tiles:insertAttribute>
</div>
<div class="content">
<tiles:insertAttribute name="content"></tiles:insertAttribute>
</div>
<div class="footer">
<hr/>
<tiles:insertAttribute name="footer"></tiles:insertAttribute>
</div>
</body>
</html>