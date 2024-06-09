<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Quản lý hóa đơn khách hàng</title>
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.1/font/bootstrap-icons.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script> <%@include file="/paging/jquery.twbsPagination.js" %></script>
</head>
<body>
<style><%@include file="/WEB-INF/resource/assets/css/style-quanly.css"%></style>
<style>
	.error-input {
	    border: 1px solid red !important; /* Đường viền màu đỏ */
	}
	

	
 	.status_hoadon_green { 
 		color: #09a706; 
 		background-color: rgba(9, 167, 6, .1); 
 		padding: 8px 0px; 
 		border-radius: 34px;
 		font-weight: bold;
 		text-align: center;
 		line-height: 14px;
 		
 	}
 	
 	.status_hoadon_red { 
 		color: #DC1414; 
 		background-color: rgba(255, 0, 0, 0.1); 
 		padding: 8px 0px; 
 		border-radius: 34px;
 		font-weight: bold;
 		text-align: center;
 		line-height: 14px;
 	}
 	
 	tr th,
 	tr td {
 		font-size: 15px;
 	}
</style>
<!-- 	================================================= -->

<div class="container">
	<div class="row">
		<div style="margin-left: 0px;width:1300px" class="col-md-8 offset-md-2">
			<form action="/Tinhtiendien/nhan_vien/quan_ly_lich_su_thanh_toan_khach_hang/tim_kiem" class="custom-form" method="GET">
			    <div class="row mb-3">
			        <div class="col">
			            <label for="field1" class="form-label">Mã hóa đơn</label>
			            <input type="text" class="form-control" id="field1" name="search_hoadonid">
			        </div>
			        <div class="col">
			            <label for="field2" class="form-label">Mã khách hàng</label>
			            <input type="text" class="form-control" id="field2" name="search_khachhangid">
			        </div>
			        <div class="col">
			            <label for="field4" class="form-label">Từ ngày (Ngày thanh toán)</label>
			            <input type="date" class="form-control" id="field4"  min="01-01-1990" max="31-12-2024" name="search_tungay">
			        </div>
			        <div class="col">
			            <label for="field4" class="form-label">Đến ngày (Ngày thanh toán)</label>
			            <input type="date" class="form-control" id="field4"  min="01-01-1990" max="31-12-2024" name="search_denngay">
			        </div>
			    </div>
			    <div class="row mb-3">

	    	   		<div class="col">
			            <label for="field1" class="form-label">Tháng</label>
			            <select name="search_month" class="form-select" aria-label="Default select example">
				        	<option value="-1" selected hidden></option>
					        <c:forEach var="i" begin="1" end="12">
						    	<option value="${i}">${i}</option>
					        </c:forEach>
				        </select>
		            </div>
		            
		             <div class="col">
			        	<label for="field1" class="form-label">Năm</label>
			            <select name="search_year" class="form-select" aria-label="Default select example">
				        	<option value="-1" selected hidden></option>
					        <%
					        	java.util.Calendar calendar = java.util.Calendar.getInstance();
			        			int currentYear = calendar.get(java.util.Calendar.YEAR);
								for (int i = currentYear; i >= 1900; i--) {
							%>
								<option value="<%= i %>"><%= i %></option>
							<% 
								}
							%>
				        </select>
			        </div>
			        
			        <div class="col">
			        	<label for="field1" class="form-label">Phương thức</label>
			            <select name="search_pttt" class="form-select" aria-label="Default select example"> -->
						<option value="-1" selected hidden></option>
						<c:forEach var="pttt" varStatus="i" items="${list_pttt}">
							<c:if test="${pttt.ten_phuongthuc != 'Chưa xác định'}">
								<option value="${pttt.ten_phuongthuc}">${pttt.ten_phuongthuc}</option>
							</c:if>
						</c:forEach>
				        </select>		        	
			        </div>
			        
			        
			        <div class="col">
			        </div>
			        
			        <div class="col" style="display: flex; justify-content: flex-end; align-items: flex-end;">
						<button name="action" value="search" style="float: right; margin-right: 10px;" type="submit" class="btn btn-primary submit-btn">Tìm kiếm</button>
						<button name="action" value="reset" style="float: right;" type="submit" class="btn btn-primary submit-btn">Tất cả</button>
			        </div>
			    </div>
			</form>
		</div>
	</div>
	<%
		String mess = (String) session.getAttribute("message");
		Boolean isError = (Boolean) session.getAttribute("isError");
	
		if (mess != null && isError != null && !isError) {
	%>
	<div id="successMessage" class="alert alert-success" role="alert" style="margin-top: 20px;"><%= mess %></div>
	<script>
    	sessionStorage.removeItem("formType");
	// Sau khi tải xong trang, tự động ẩn thông báo sau 3 giây
	setTimeout(function() {
		var successMessage = document.getElementById("successMessage");
		if (successMessage) {
			successMessage.style.display = "none";
		}
	}, 3000);
		sessionStorage.removeItem("formType");
		sessionStorage.removeItem("khachhang_id");
		sessionStorage.setItem("thang", "");
		sessionStorage.setItem("nam", "");
		sessionStorage.removeItem("thue");
	</script>
	<%
			session.removeAttribute("message");
			session.removeAttribute("isError");
		} else if (mess != null && isError != null && isError) {
	%>
	<div id="errorMessage" class="alert alert-danger" role="alert" style="margin-top: 20px;"><%= mess %></div>
	<script>
	// Sau khi tải xong trang, tự động ẩn thông báo sau 3 giây
	setTimeout(function() {
		var errorMessage = document.getElementById("errorMessage");
		if (errorMessage) {
			errorMessage.style.display = "none";
		}
	}, 3000);

		sessionStorage.removeItem("formType");
		sessionStorage.removeItem("khachhang_id");
		sessionStorage.setItem("thang", "");
		sessionStorage.setItem("nam", "");
		sessionStorage.removeItem("thue");
	</script>
	<%
			session.removeAttribute("message");
			session.removeAttribute("isError");
		}
	%>
		<div style="display: flex; justify-content: center; margin-bottom: -30px;">

			<form action="/Tinhtiendien/nhan_vien/quan_ly_lich_su_thanh_toan_khach_hang" class="custom-form" id="submitPage" method="GET">
				 <nav aria-label="Page navigation">
		        	<ul class="pagination" id="pagination"></ul>
		    	</nav>
		    	<input type="hidden" id="cur_page" name="cur_page" value="${curr_page}">
		    	<input type="hidden" id="limit" name="limit" value="${total_page}">
	    	</form>
		</div>
        <div class="table-wrapper" style="box-shadow: rgba(0, 0, 0, 0.5) 0px 5px 15px;">
            <div class="table-title">
                <div class="row">
                    <div class="col-md-6">
                        <h2>Quản lý <b>Lịch sử thanh toán</b></h2>
                    </div>
                    <div class="col-md-6 text-md-end">
<!--                         <button type="button" class="btn btn-success btn-add" data-bs-toggle="modal" data-bs-target="#addEmployeeModal" onclick="setSelectForm('addForm')"> -->
<!--                             <i class="material-icons">&#xE147;</i> <span>Thêm lịch sử thanh toán mới</span> -->
<!--                         </button> -->
                        <!-- <a href="#deleteEmployeeModal" class="btn btn-danger" data-bs-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a> -->
                    </div>
                </div>            
            </div>
            
<!--             <div class="row"> -->
<!-- 		      <div class="col-1"> -->
<%-- 		      <c:forEach var="hoadon" items="${list_hoadon}"> --%>
<%-- 		      	<input disabled value="${hoadon.khachhang_id}" style="width: 100px; text-align: center; font-weight: bold;"> --%>
<%-- 		      </c:forEach> --%>
<!-- 		      </div> -->
<!-- 		    </div> -->
				
<%-- 				<fmt:setTimeZone value="America/New_York" />  --%>
<%-- 				<c:set var = "now" value = "<%= new java.util.Date()%>" /> --%>
<!-- 			    <h2>Date and Time Example</h2>  -->
<%-- 			    <p>Current Date and Time: ${now} : <fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" /></p>  --%>
            
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>MÃ HÓA ĐƠN</th>
                        <th>Mã KHÁCH HÀNG</th>
                        <th>THÁNG</th>
                        <th>NĂM</th>
                        <th>TỔNG TIỀN (VNĐ)</th>
                        <th>NGÀY THANH TOÁN</th>
                        <th>PHƯƠNG THỨC</th>
                    </tr>
                </thead>
                <tbody>
                  <c:forEach var="hoadon" varStatus="i" items="${list_hoadon}">
			      <tr>
			      	<td>${hoadon.hoadon_id}</th>
			        <td>${hoadon.khachhang_id}</td>
<%-- 			        <td><fmt:formatDate value="${hoadon.ngay_tao}" pattern="dd-MM-yyyy HH:mm:ss"/></td> --%>
			        <td>${hoadon.month_bill}</td>
			        <td>${hoadon.year_bill}</td>
			        <td><fmt:formatNumber value="${hoadon.tong_tien}" pattern="#,###"/></td>
				    <td>${hoadon.ngay_thanhtoan}</td>
				    <td>${hoadon.ten_phuongthuc}</td>
<!-- 			        <td> -->
<!-- 			        	<button type="button" class="btn btn-primary btn-sm btn-edit" data-bs-toggle="modal" data-bs-target="#editEmployeeModal" onclick="setSelectForm('editForm')"><i class="bi bi-pencil-fill"></i> Sửa</button> -->

<%--                        	<input type="hidden" class="hoadon_id" name="hoadon_id" value="${hoadon.hoadon_id}"> --%>
<!--                        	<button type="button" class="btn btn-danger btn-sm btn-delete" data-bs-toggle="modal" data-bs-target="#deleteEmployeeModal"><i class="bi bi-trash-fill"></i> Xóa</button> -->
<!-- 			        </td> -->
			      </tr>
			      </c:forEach>      
                </tbody>
            </table>
            <c:if test="${empty list_hoadon}">
		     	<div style="display: flex; justify-content: center; align-items: center;">
			     	<%@include file="/WEB-INF/resource/assets/imgs/nodata.svg"%>
		     	</div>
		     	<div style="display: flex; justify-content: center; align-items: center; margin-top: 10px;">
					<p>Không có dữ liệu</p>				     	
		     	</div>
			</c:if>     
        </div>

        <!-- Add Employee Modal -->
        <div class="modal fade" id="addEmployeeModal" tabindex="-1" aria-labelledby="addEmployeeModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addEmployeeModalLabel">Thêm hóa đơn</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Add employee form -->
                        <form action = "/Tinhtiendien/nhan_vien/quan_ly_hoa_don_khach_hang/them" method="POST" id ="addCustomer">
                            <div class="mb-3">
                                <label for="add_khachhang_id" class="form-label">Mã khách hàng</label>
                                <input type="text" class="form-control" id="add_khachhang_id" name="add_khachhangid" required>
                                <h6 style="color:red; padding-left:5px; padding-top:5px" id="add_khachhang_id">${err_mess_addKhachhangid}</h6>
                            </div>
                            
                            <div class="mb-3">
                                <label for="addDOB" class="form-label">Thời gian hóa đơn</label>
                                <div class="container">
								  <div class="row">
								    <div class="col">
								      	<select class="form-select addMonth" id="add_thoigian_hoadon" name="thang" required>
		                				<option value="0" selected hidden>Tháng</option>
				                		<c:forEach var = "i" begin = "1" end = "12">
				                    		<option value="${i}">${i}</option>
				                    	</c:forEach>
				                		</select>
								    </div>
								    <div class="col">
								      	<select class="form-select addYear" id="add_thoigian_hoadon" name="nam" required>
		                				<option value="0" selected hidden>Năm</option>
				                		<c:forEach var = "i" begin = "1900" end = "2024">
				                    		<option value="${i}">${i}</option>
				                    	</c:forEach>
		                				</select>
								    </div>
								    <h6 style="color:red; padding-left:5px; padding-top:5px" id="add_thoigian_hoadon">${err_mess_addThoigianhoadon}</h6>
								  </div>
								</div>
                            </div>
                            
                            <div class="mb-3">
                                <label for="add_thue" class="form-label">Thuế VAT (%)</label>
                                <input type="number" class="form-control" id="add_thue" name ="add_thue" min="0" max="100" required>
                                <h6 style="color:red; padding-left:5px; padding-top:5px" id="add_thue">${err_mess_addThue}</h6>
                            </div>
                            
<!--                             <div class="mb-3"> -->
<!--                                 <label for="add_status" class="form-label">Trạng thái</label> -->
<!--                                 <select name="add_status" class="form-select" aria-label="Default select example"> -->
<!-- 						          <option value="0"selected>Chưa thanh toán</option> -->
<!-- 						          <option value="1">Đã thanh toán</option> -->
<!-- 						        </select>		         -->
<%--                                 <h6 style="color:red; padding-left:5px; padding-top:5px" id="add_status">${err_mess_addStatus}</h6> --%>
<!--                             </div> -->
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                        <button type="submit" form="addCustomer" class="btn btn-primary">Lưu</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Edit Employee Modal -->
        <div class="modal fade" id="editEmployeeModal" tabindex="-1" aria-labelledby="editEmployeeModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editEmployeeModalLabel">Chỉnh sửa lịch sử thanh toán</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Edit employee form -->
                        <form action = "/Tinhtiendien/nhan_vien/quan_ly_hoa_don_khach_hang/sua" method="POST" id ="editCustomer">
                        	<div class="mb-3">
                        		<div class="container">
                        		  <div class="row">
								  	<div class="col">
		                                <label for="editHoadon_id" class="form-label">Mã hóa đơn</label>
		                                <input type="text" class="form-control editHoadon_id" id="editHoadon_id" name="hoadon_id" disabled>								  	
								  	</div>
								  	<div class="col">
		                                <label for="editKhachhang_id" class="form-label">Mã khách hàng</label>
		                                <input type="text" class="form-control editKhachhang_id" id="editKhachhang_id" name="khachhang_id" disabled>								  	
								  	</div>
								  </div>
                            	</div>
                            </div>
                        
                        	<div class="mb-3">
								<label for="editDOB" class="form-label">Thời gian hóa đơn</label>
								<div class="container">
								  <div class="row">
									<div class="col">
										  <select class="form-select editMonth" id="editthoigian" name="thang">
											<option value="0" disabled selected hidden>Tháng</option>
											<c:forEach var = "i" begin = "1" end = "12">
												<option value="${i}">${i}</option>
											</c:forEach>
										</select>
									</div>
									<div class="col">
										  <select class="form-select editYear" id="editthoigian" name="nam">
											<option value="0" disabled selected hidden>Năm</option>
											Sử dụng vòng lặp từ 0 đến 124 (2024 - 1900)
											<% for (int i = 0; i <= 124; i++) { %>
												Tính giá trị năm dựa trên biến i
												<% int year = 2024 - i; %>
												Tạo một tùy chọn cho mỗi năm
												<option value="<%= year %>"><%= year %></option>
											<% } %>
										</select>
									</div>
									<h6 style="color:red; padding-left: 5px; padding-top: 5px;" id="editthoigian">${err_mess_editThoigian}</h6>
								  </div>
								</div>
							</div>
                            <div class="mb-3">
                        		<div class="container">
                        		  <div class="row">
								  	<div class="col">
		                                <label for="editThue" class="form-label">Thuế VAT (%)</label>
                                		<input type="number" class="form-control editTax" id="editThue" name="thue" required min="0" max="100">								  	
								  	</div>
								  	<div class="col">
		                                <label for="field1" class="form-label">Trạng thái</label>
							            <select name="edit_status" class="form-select editStatus" aria-label="Default select example"> -->
								          <option value="-1" disabled selected hidden></option>
								          <option value="0">Chưa thanh toán</option>
								          <option value="1">Đã thanh toán</option>
								        </select>		     								  	
								  	</div>
								  </div>
                            	</div>
                            </div>
                            <input type="hidden" class="hoadon_id-edit" name="hoadon_id" value="">
                            <input type="hidden" class="khachhang_id-edit" name="khachhang_id" value="">
	                        <h6 style="color:red; padding-left:5px; padding-top:5px" id="editChiso">${err_mess_editChiso}</h6>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                        <button type="submit" form="editCustomer" class="btn btn-primary">Lưu thay đổi</button>
                    </div>
                </div>
            </div>
        </div>

<!--         Delete Employee Modal -->
       	<div class="modal fade" id="deleteEmployeeModal" tabindex="-1" aria-labelledby="deleteEmployeeModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteEmployeeModalLabel">Xóa khách hàng</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p>Bạn có chắc muốn xóa lịch sử đo này không?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                        <form id="formToDelete" action="/Tinhtiendien/nhan_vien/quan_ly_hoa_don_khach_hang/xoa" method="POST">
                        	<input type="hidden" class="hoadon_id-delete" name="hoadon_id" value="">
                        	<button id="confirmDeleteButton" type="submit" class="btn btn-danger" >Xóa</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script>

	// Lấy nút "Add"
	const addButton = document.querySelector('.btn-add');
	addButton.addEventListener('click', (event) => {
		var khachhang_id = sessionStorage.getItem("khachhang_id");
		var ngay = sessionStorage.getItem("ngay");
		var thang = sessionStorage.getItem("thang");
		var nam = sessionStorage.getItem("nam");
		
		if (thang == '') {
			document.querySelector('.addMonth').selectedIndex = 0;
		} else {
			document.querySelector('.addMonth').value = thang;			
		}
		
		if (nam == '') {
			document.querySelector('.addYear').selectedIndex = 0;
		} else {
			document.querySelector('.addYear').value = nam;			
		}
	})
	


	// Lấy tất cả các nút "Edit"
	const editButtons = document.querySelectorAll('.btn-edit');
	
	// Thêm sự kiện click cho mỗi nút "Edit"
	editButtons.forEach((button) => {
	    button.addEventListener('click', (event) => {
	
	        const row = event.target.closest('tr'); // Lấy hàng bảng chứa nút "Edit"
	        const hoadon_id = (row.querySelector('td:nth-child(1)').textContent).trim(); 
	        const khachhang_id = row.querySelector('td:nth-child(2)').textContent;
	        const ngaytao = row.querySelector('td:nth-child(3)').textContent; 
	        const thang = row.querySelector('td:nth-child(4)').textContent; 
	        const nam = row.querySelector('td:nth-child(5)').textContent;
	        const thue = row.querySelector('td:nth-child(6)').textContent;
	        const tongtien = row.querySelector('td:nth-child(7)').textContent;
			const trangthai = (row.querySelector('td:nth-child(8)').textContent).trim();
	        // Điền giá trị vào các trường input trong modal
	        
	        console.log("hoadon_id: " + hoadon_id)
	        console.log("khachhang_id: " + khachhang_id)
	        console.log("ngaytao: " + ngaytao)
	        console.log("trangthai: " + trangthai)
	        
			
	        
	        sessionStorage.setItem("hoadon_id", hoadon_id);
	        sessionStorage.setItem("khachhang_id", khachhang_id);
	        sessionStorage.setItem("thang", thang);
	        sessionStorage.setItem("nam", nam);
	        sessionStorage.setItem("thue", thue);
	        sessionStorage.setItem("trangthai", trangthai);
	        
	        document.querySelector('.editHoadon_id').value = hoadon_id;
	        document.querySelector('.editKhachhang_id').value = khachhang_id;
	        document.querySelector('.editMonth').value = thang;
	        document.querySelector('.editYear').value = nam;
	        document.querySelector('.editTax').value = thue;
	        
	        if (trangthai == "Chưa thanh toán") {
		        document.querySelector('.editStatus').value = 0	        	
	        } else {
	        	document.querySelector('.editStatus').value = 1	    
	        }

	        document.querySelector('.hoadon_id-edit').value = hoadon_id
	        document.querySelector('.khachhang_id-edit').value = khachhang_id
	        
	        
// 	        document.querySelector('.khachhang_id').value = khachhang_id;
// 	        console.log("lich su do id: " + document.querySelector('.hoadon_id-edit').value);
	    });
	});
	
	
	// Lấy nút Delete
	const deleteButtons = document.querySelectorAll('.btn-delete');
	
	deleteButtons.forEach((button) => {
	    button.addEventListener('click', (event) => {
	    	const row = event.target.closest('tr');
	
	        const hoadon_id = row.querySelector('.hoadon_id').value
	        console.log(hoadon_id)
	
	        document.querySelector('.hoadon_id-delete').value = hoadon_id
	
	    });
	});
	
	window.onload = function() {
		var selectedValue = sessionStorage.getItem('selectedValue');
		if (selectedValue) {
			document.getElementById('select_makh').value = selectedValue;
		}
	};
	
	
// 	const select_makh = document.querySelector('#select_makh');
// 	select_makh.addEventListener('change', function() {
		
// 		var selectedValue = this.value;
// 		sessionStorage.setItem('selectedValue', selectedValue);
		
// 		document.querySelector('#form_makh').submit()
// 	});
	
	function setSelectForm(form) {
		sessionStorage.setItem("formType", form)
		console.log(form)
	}
	
	window.addEventListener('beforeunload', function() {
		var formType = sessionStorage.getItem("formType")
		
		// edit form
		if (formType == "editForm") {
	        sessionStorage.setItem("thang", document.querySelector('.editMonth').value);
	        sessionStorage.setItem("nam", document.querySelector('.editYear').value);			
		}
        
        // add form
        if (formType == "addForm") {
	        sessionStorage.setItem("khachhang_id", document.querySelector('input[name="add_khachhangid"]').value);
	        sessionStorage.setItem("thang", document.querySelector('.addMonth').value);
	        sessionStorage.setItem("nam", document.querySelector('.addYear').value);
	        sessionStorage.setItem("thue", document.querySelector('input[name="add_thue"]').value);
        }
        
	});
	
	document.addEventListener("DOMContentLoaded", function() {
			
		var thue = sessionStorage.getItem("thue");
		var hoadon_id = sessionStorage.getItem("hoadon_id");
		var khachhang_id = sessionStorage.getItem("khachhang_id");
		var ngay = sessionStorage.getItem("ngay");
		var thang = sessionStorage.getItem("thang");
		var nam = sessionStorage.getItem("nam");
		var status = sessionStorage.getItem("trangthai");
		
		
		// binding data khi tải lại trang
		// edit form
		document.querySelector('.editHoadon_id').value = hoadon_id;
		document.querySelector('.editKhachhang_id').value = khachhang_id;
		document.querySelector('.hoadon_id-edit').value = hoadon_id;
		document.querySelector('.khachhang_id-edit').value = khachhang_id;
		document.querySelector('.editMonth').value = thang;
		document.querySelector('.editYear').value = nam;
		document.querySelector('.editTax').value = thue;
		if (status == "Chưa thanh toán") {
			document.querySelector('.editStatus').value = 0;			
		} else {
			document.querySelector('.editStatus').value = 1;
		}
		
		
		
		// add form
		document.querySelector('#add_khachhang_id').value = khachhang_id;
		if (thang == '') {
			document.querySelector('.addMonth').selectedIndex = 0;
		} else {
			document.querySelector('.addMonth').value = thang;			
		}
		
		if (nam == '') {
			document.querySelector('.addYear').selectedIndex = 0;
		} else {
			document.querySelector('.addYear').value = nam;			
		}

		
		var formType = sessionStorage.getItem("formType");
		if (formType == "addForm") {
			$('#addEmployeeModal').modal('show');
		} else if (formType == "editForm") {
			$('#editEmployeeModal').modal('show');
		}
		
		$('.modal').on('hidden.bs.modal', function () {
			sessionStorage.removeItem("formType");
			sessionStorage.removeItem("khachhang_id");
			sessionStorage.removeItem("thue");
			sessionStorage.setItem("thang", '');
			sessionStorage.setItem("nam", '');
			
			var error_mess_list = document.querySelectorAll('h6');
			error_mess_list.forEach(function(error_mess) {
				error_mess.textContent = "";
				
				var inputElements = document.querySelectorAll('#' + error_mess.id + ':not(h6)')
				
				inputElements.forEach(inputElement => {
					inputElement.classList.remove('error-input');	
		            inputElement.value = "";
				})
				
			})
			
		})
		
	})
	
	
	var errorMessages = document.querySelectorAll('h6');
	errorMessages.forEach(errorMessage => {
		if (errorMessage.textContent.trim() !== "") {
            
            var inputId = errorMessage.id;

            var inputElements = document.querySelectorAll('#' + inputId + ':not(h6)');

            if (inputElements) {
            	inputElements.forEach(inputElement => {
            		inputElement.classList.add('error-input');
            	})
            }
        }
	})
	
// 	------------------------------------ phan trang ----------------------------

	let isPageClicked = false;
	var curPage = parseInt($('#cur_page').val())
	$(function() {
		window.pagObj = $('#pagination').twbsPagination({
			totalPages : ${total_page},
			visiblePages : 3,
			first : '<<',
			prev : '<',
	   		next:'>',
			last : '>>',
			startPage: curPage,
			onPageClick : function(event, page) {
				if (isPageClicked) {
					if ((window.location.pathname).includes("tim_kiem")) {
						var searchParams = new URLSearchParams(window.location.search);
						searchParams.delete('cur_page');
			            searchParams.delete('limit');
			            
						var url = window.location.origin + window.location.pathname + "?" + searchParams.toString();
						url += "&cur_page=" + page + "&limit=3";
						window.location.href = url
						
					} else {
	                    $('#cur_page').val(page);
	                    $('#submitPage').submit();							
					}
	
	               }
	               isPageClicked = true;
		
			}
		})
	});
	
</script>
</body>
</html>