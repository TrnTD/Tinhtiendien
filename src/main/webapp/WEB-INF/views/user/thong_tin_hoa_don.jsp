<%@page import="com.Tinhtiendien.Models.ChiTietHoaDon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<style>
	thead tr {
		background-color: #e8ecfc;
		
		th {
			color: #063bc0;
		}
	}

	th {
		font-size: 16px;
		text-align: center;
	}
	
	td {
		font-weight: 500;
		text-align: center;
	}
</style>
	<div class="content">
	<h2 style="color:#f6621c; margin-bottom: 20px;">Tìm hoá đơn</h2>
	<form action = "/Tinhtiendien/nguoi_dung/thong_tin_hoa_don/tim_kiem" method="GET">
	  <div class="row mb-3">
		        <div class="col">
		            <label for="field1" class="form-label">Tháng</label>
		            <select class="form-select" id="field1" aria-label="Default select example" name="search_month">
		            <option value="" selected>Không</option>
		            <c:forEach var = "i" begin = "1" end = "12">
				               <option value="${i}">${i}</option>
				    </c:forEach>
				    </select>
		        </div>
		        <div class="col">
		            <label for="field2" class="form-label">Năm</label>
		            <select class="form-select" id="field2" aria-label="Default select example" name="search_year">
		            <option value="" selected>Không</option>
		            <c:forEach var = "i" begin = "${nam_dangky}" end = "2024">
				               <option value="${i}">${i}</option>
				    </c:forEach>
				    </select>
		        </div>
		        </div>
				<button style="float: right;" type="submit" class="btn btn-primary submit-btn">Tìm kiếm</button>
		</form>
			<form action="/Tinhtiendien/nguoi_dung/thong_tin_hoa_don" method="GET">
				<button style="float: right; margin-right: 5px;" type="submit" class="btn btn-primary submit-btn">Tất Cả</button>
			</form>
		<br>
  		<div class="container" style="width: 1200px">
			<h2 style="color:#f6621c; margin-bottom: 20px;">Thông tin hóa đơn</h2>
	        
	        <table class="table table-hover" style="background-color: white; box-shadow: rgba(0, 0, 0, 0.5) 0px 5px 15px;">
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
				<c:forEach var="hoadon" items="${list_hoadon}">
				    <tr>
				        <td>${hoadon.month_bill}</td>
				        <td>${hoadon.year_bill}</td>
				        <td>${hoadon.chiso_cu}</td>
				        <td>${hoadon.chiso_moi}</td>
				        <td>${hoadon.dien_tieu_thu}</td>
				        <td><fmt:formatNumber value="${hoadon.tong_tien}" pattern="#,###"/></td>
				    </tr>
				</c:forEach>

	          </tbody>
	        </table>
		</div>
	</div>
</body>

</html>