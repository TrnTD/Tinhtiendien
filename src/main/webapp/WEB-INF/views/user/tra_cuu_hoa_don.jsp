<%@page import="com.Tinhtiendien.Models.ChiTietHoaDon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tra cứu hóa đơn</title>
</head>

<body>
<%-- 	<%@include file="/WEB-INF/views/layouts/user/navbar2.jsp" %>	 --%>
<%-- 	<%@include file="/WEB-INF/views/layouts/user/sidebar2.jsp" %> --%>
<style>
	thead tr {
		background-color: #e8ecfc;
		width: 1000px;
		
		th {
			color: #063bc0;
		}
		
		th:first-child {
			width: 120px;
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
  		<div class="container" style="width: 1200px;">
<!-- 	        <h2 style="color:#f6621c;margin-bottom: 20px;">Thông tin khách hàng</h3> -->
<!-- 	       	<div style="background-color: white;"> -->
<!-- 	       		<div class="row"> -->
<!-- 		            Cột 1 -->
<!-- 		            <div class="col-2"> -->
<!-- 		                <p><strong>Họ và tên:</strong></p> -->
<!-- 		            </div> -->
<!-- 		            Cột 2 -->
<!-- 		            <div class="col-md-3"> -->
<%-- 		                <p><span class="left-align" id="hoten">${info_khachhang.hovaten}</span></p> --%>
<!-- 		            </div> -->
<!-- 		            Cột 3 -->
<!-- 		            <div class="col-2"> -->
<!-- 		                <p><strong>Mã khách hàng:</strong></p> -->
<!-- 		            </div> -->
<!-- 		            Cột 4 -->
<!-- 		            <div class="col-md-3"> -->
<%-- 		                <p><span class="left-align" id="diachi">${info_khachhang.khachhang_id}</span></p> --%>
<!-- 		            </div> -->
<!-- 		        </div> -->
<!-- 		        <div class="row"> -->
<!-- 		            Cột 5 -->
<!-- 		            <div class="col-2"> -->
<!-- 		                <p><strong>Địa chỉ:</strong></p> -->
<!-- 		            </div> -->
<!-- 		            Cột 6 -->
<!-- 		            <div class="col-md-3"> -->
<%-- 		                <p><span class="left-align" id="makh">${info_khachhang.diachi}</span></p> --%>
<!-- 		            </div> -->
<!-- 		            Cột 7 -->
<!-- 		            <div class="col-2"> -->
<!-- 		                <p><strong>Số điện thoại:</strong></p> -->
<!-- 		            </div> -->
<!-- 		            Cột 8 -->
<!-- 		            <div class="col-md-3"> -->
<%-- 		                <p><span class="left-align" id="mahoadon">${info_khachhang.sdt}</span></p> --%>
<!-- 		            </div> -->
<!-- 		        </div> -->
<!-- 	       	</div> -->
<!--   		</div> -->
  		
  		<div class="container">
			<h2 style="color:#f6621c; margin-bottom: 20px;">Tra cứu hóa đơn</h3>
			
		    <form method="POST">
		        <div class="row d-flex align-items-end">
		            <!-- Dropdown cho tháng -->
		            <div class="mb-3" style="width:150px;">
		                <label for="month" class="form-label">Tháng</label>
		                <select class="form-select" id="month" name="month">
		                	<c:forEach var = "i" begin = "1" end = "12">
		                    	<option value="${i}">${i}</option>
		                    </c:forEach>
		                    <!-- Thêm các tháng còn lại tương tự -->
		                </select>
		            </div>
		            <!-- Dropdown cho năm -->
		            <div class="mb-3" style="width:150px;">
		                <label for="year" class="form-label">Năm</label>
		                <select class="form-select" id="year" name="year">
		                	<c:forEach var = "i" begin = "${nam_dangky}" end = "2024">
		                		<option value="${i}">${i}</option>
		                	</c:forEach>		                  
		                    <!-- Thêm các năm còn lại tương tự -->
		                </select>
		            </div>
		            <!-- Nút submit -->
		            <div class="col-auto mb-3">
		                <button type="submit" class="btn btn-primary"><i class="fa-solid fa-magnifying-glass"></i></button>
		            </div>
		        </div>
		    </form>
		    
	        <table class="table table-hover" style="background-color: white; box-shadow: rgba(0, 0, 0, 0.5) 0px 5px 15px;">
	          <thead>
	            <tr>
	           	  <th>Mã hóa đơn</th>
	              <th>Mã khách hàng</th>
	              <th>Loại hóa đơn</th>
	              <th>Từ ngày</th>
	              <th>Đến ngày</th>
	              <th>Số tiền</th>
	              <th>Tiền thuế</th>
	              <th>Tổng tiền</th>
	            </tr>
	          </thead>
	          <tbody>
<%-- 				<c:forEach var="hoadon" items="${list_hoadon}"> --%>
					<div class="content_render">
					    <tr>
					    	<td>${hoadon.hoadon_id}</td>
					        <td>${hoadon.khachhang_id}</td>
					        <td>${hoadon.loai_hoadon}</td>
					        <td>${hoadon.ngay_batdau}</td>
					        <td>${hoadon.ngay_ketthuc}</td>
					        <td><fmt:formatNumber value="${hoadon.so_tien}" pattern="#,###"/></td>
					        <td><fmt:formatNumber value="${hoadon.tien_thue}" pattern="#,###"/></td>
					        <td><fmt:formatNumber value="${hoadon.tong_tien}" pattern="#,###"/></td>
					    </tr>
					</div>
<%-- 				</c:forEach> --%>

	          </tbody>
	        </table>
		</div>
  	</div>
</body>

</html>