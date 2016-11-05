<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/tarefa.js"></script>
<link href="resources/css/jquery-ui.css" rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet">
<title><tiles:getAsString name="titulo" /></title>
</head>

<body>
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="login" />
	<tiles:insertAttribute name="menu" />
	<div id="content">
		<tiles:insertAttribute name="content" />
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>