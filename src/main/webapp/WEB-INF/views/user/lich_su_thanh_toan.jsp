<%@page import="com.Tinhtiendien.Models.ChiTietHoaDon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lịch sử thanh toán</title>
</head>
<style>

</style>
<body>
<%-- 	<%@include file="/WEB-INF/views/layouts/user/navbar.jsp" %>	 --%>
<%-- 	<%@include file="/WEB-INF/views/layouts/user/sidebar.jsp" %> --%>
	<div class="content">
  		<div class="container" style="width:1000px" >
  		<h2 style="color:#f6621c; margin-bottom: 20px;">Lịch sử thanh toán</h2>
	        
	        <table class="table table-hover">
	          <thead>
	            <tr>
	              <th>Tháng</th>
	              <th>Năm</th>
	              <th>Tiền thanh toán</th>
	              <th>Ngày thanh toán</th>
	            </tr>
	          </thead>
	          <tbody>
				<c:forEach var="lstt" items="${list_lstt}">
				    <tr>
				        <td>${lstt.thang_tao}</td>
				        <td>${lstt.nam_tao}</td>
				        <td>${lstt.tong_tien}</td>
				        <td>${lstt.ngay_thanhtoan}</td>
				    </tr>
				</c:forEach>

	          </tbody>
	        </table>
  		</div>
  	</div>
</body>
</html>