<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/tarefa.js"></script>
<link href="resources/css/jquery-ui.css" rel="stylesheet">
<link href="resources/css/template.css" rel="stylesheet">
<title>Gerenciador de Tarefas</title>
</head>
<body>
	<div id="header"><jsp:include page="/WEB-INF/views/jstl-templates/header.jsp" /></div>
	<c:if test="${usuario != null}">
		<div id="div_menu"><jsp:include page="/WEB-INF/views/jstl-templates/menu.jsp" /></div>
	</c:if>
	<div id="content"><jsp:include page="/WEB-INF/views/${partial}" /></div> 
	<div id="footer"><jsp:include page="/WEB-INF/views/jstl-templates/footer.jsp" /></div>
</body>
</html>