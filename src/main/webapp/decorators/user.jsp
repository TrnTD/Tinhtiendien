<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dec" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><dec:title/></title>
</head>

<style>


</style>
<body>
	<%@include file="/WEB-INF/views/layouts/user/navbar.jsp" %>	
	<%@include file="/WEB-INF/views/layouts/user/sidebar.jsp" %>
	
	<div style="margin-left:260px; margin-top:35px;">
		<div style="display: flex; justify-content: center; gap: 0px;">
			<dec:body/>		
		</div>
	</div>
</body>
</html>