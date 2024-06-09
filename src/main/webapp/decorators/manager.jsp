<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dec" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<html>
<head>
	<dec:head/>
	<title><dec:title/></title>
</head>
<body>
	<%@include file="/WEB-INF/views/layouts/manager/navbar.jsp" %>	
	<%@include file="/WEB-INF/views/layouts/manager/sidebar.jsp" %>
	
	<div style="margin-left:260px; margin-top:50px">
		<div style="display: flex; justify-content: center">
			<dec:body/>		
		</div>
	</div>
</body>
</html>