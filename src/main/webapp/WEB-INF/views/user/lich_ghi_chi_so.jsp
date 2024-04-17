<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lịch ghi chỉ số</title>
</head>
<body>
	<div class="container" style="width:1000px" >
		  <h2 style="color:#f6621c; margin-bottom: 20px;">Lịch ghi chỉ số </h2>
		  <table class="table table-hover">
		    <thead>
		      <tr>
		      	<th>STT</th>
		        <th>Năm</th>
		        <th>Tháng</th>
		        <th>Ngày ghi chỉ số</th>
		      </tr>
		    </thead>
		    <tbody>
		    <c:forEach var="ckd" varStatus="i" items="${list_ckd}">
		      <tr>
		      	<th scope="row">${i.index + 1}</th>
		        <td>${ckd.nam}</td>
		        <td>${ckd.thang}</td>
		        <td>${ckd.ngay_thang_nam}</td>
		      </tr>
		      </c:forEach>
		    </tbody>
		  </table>
	</div>
	
	
</body>
</html>