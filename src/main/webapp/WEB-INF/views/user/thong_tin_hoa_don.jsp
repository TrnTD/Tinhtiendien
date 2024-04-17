<%@page import="com.Tinhtiendien.Models.ChiTietHoaDon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Thông tin hóa đơn</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<style>

</style>
<body>
	<div class="content">
  		<div class="container" style="width:1000px">
			<h2 style="color:#f6621c; margin-bottom: 20px;">Thông tin hóa đơn</h2>
	        
	        <table class="table table-hover">
	          <thead>
	            <tr>
	              <th>Tháng</th>
	              <th>Năm</th>
	              <th>Chỉ số cũ</th>
	              <th>Chỉ số mới</th>
	              <th>Kwh tiêu thụ</th>
	              <th>Thành tiền</th>
	            </tr>
	          </thead>
	          <tbody>
				<c:forEach var="cthd" items="${list_cthd}">
				    <tr>
				        <td>${cthd.thang_tao}</td>
				        <td>${cthd.nam_tao}</td>
				        <td>${cthd.chiso_cu}</td>
				        <td>${cthd.chiso_moi}</td>
				        <td>${cthd.dien_tieu_thu}</td>
				        <td>${cthd.tong_tien}</td>
				    </tr>
				</c:forEach>

	          </tbody>
	        </table>
		</div>
	</div>
</body>

</html>