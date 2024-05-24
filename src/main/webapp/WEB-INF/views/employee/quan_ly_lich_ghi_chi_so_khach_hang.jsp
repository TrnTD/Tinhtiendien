<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Quản lý lịch ghi chỉ số khách hàng</title>
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.1/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
		<style><%@include file="/WEB-INF/resource/assets/css/style-quanly.css"%></style>
<!-- 	================================================= -->

	<div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-md-6">
                        <h2>Quản lý <b>Lịch Ghi Chỉ Số</b></h2>
                    </div>
                    <div class="col-md-6 text-md-end">
                        <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addEmployeeModal">
                            <i class="material-icons">&#xE147;</i> <span>Thêm lịch ghi chỉ số mới</span>
                        </button>
                        <!-- <a href="#deleteEmployeeModal" class="btn btn-danger" data-bs-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a> -->
                    </div>
                </div>            
            </div>
            
            <div class="row">
		      <div class="col-2">
		      <form id="form_makh" method="GET">
		        <select id="select_makh" name="selected_makh" class="form-select" aria-label="Default select example">
		        <c:forEach var="info" items="${list_info}">
		          <option value="${info.khachhang_id}">${info.khachhang_id}</option>
		        </c:forEach>
		        </select>		      
		      </form>
		      </div>
		    </div>
            
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Mã lịch ghi chỉ số</th>
                        <th>Mã khách hàng</th>
                        <th>Ngày bắt đầu</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                  <c:forEach var="lgcs" varStatus="i" items="${list_lgcs}">
			      <tr>
			      	<td>${lgcs.lichghi_id}</th>
			        <td>${lgcs.khachhang_id}</td>
			        <td>${lgcs.ngay_thang_nam}</td>
			        <td>
			        	<button type="button" class="btn btn-primary btn-sm btn-edit" data-bs-toggle="modal" data-bs-target="#editEmployeeModal"><i class="bi bi-pencil-fill"></i> Sửa</button>

                       	<input type="hidden" class="lsd_id" name="lsd_id" value="${lsd.lichsu_do_id}">
                       	<button type="button" class="btn btn-danger btn-sm btn-delete" data-bs-toggle="modal" data-bs-target="#deleteEmployeeModal"><i class="bi bi-trash-fill"></i> Xóa</button>
			        </td>
			      </tr>
			      </c:forEach>      
                </tbody>
            </table>
        </div>

        <!-- Add Employee Modal -->
        <div class="modal fade" id="addEmployeeModal" tabindex="-1" aria-labelledby="addEmployeeModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addEmployeeModalLabel">Thêm khách hàng</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Add employee form -->
                        <form action = "quan_ly_thong_tin_khach_hang/them" method="POST" id ="addCustomer">
                            <div class="mb-3">
                                <label for="addName" class="form-label">Họ Tên</label>
                                <input type="text" class="form-control" id="addName" name="hoten" required>
                            </div>
                            <div class="mb-3">
                                <label for="addGender" class="form-label">Giới Tính</label>
                                <select class="form-select" id="addGender" aria-label="Default select example" name="gioitinh">
								  <option value="nam">Nam</option>
								  <option value="nu">Nữ</option>
								</select>
                            </div>
                            <div class="mb-3">
                                <label for="addDOB" class="form-label">Ngày tháng năm sinh</label>
                                <div class="container">
								  <div class="row">
								    <div class="col">
								    	<select class="form-select" id="addDay" name="ngay" required>
                                		<option value="0" disabled selected hidden>Ngày</option>
					                	<c:forEach var = "i" begin = "1" end = "31">
					                    	<option value="${i}">${i}</option>
					                    </c:forEach>
									      </select>
								    </div>
								    <div class="col">
								      	<select class="form-select" id="addMonth" name="thang" required>
		                				<option value="0" disabled selected hidden>Tháng</option>
				                		<c:forEach var = "i" begin = "1" end = "12">
				                    		<option value="${i}">${i}</option>
				                    	</c:forEach>
				                		</select>
								    </div>
								    <div class="col">
								      	<select class="form-select" id="addYear" name="nam" required>
		                				<option value="0" disabled selected hidden>Năm</option>
				                		<c:forEach var = "i" begin = "1900" end = "2024">
				                    		<option value="${i}">${i}</option>
				                    	</c:forEach>
		                				</select>
								    </div>
								  </div>
								</div>
                            </div>
                            <div class="mb-3">
                                <label for="addEmail" class="form-label">Email</label>
                                <input type="email" class="form-control" id="addEmail" name ="email" required>
                            </div>
                            <div class="mb-3">
                                <label for="addPhone" class="form-label">Số điện thoại</label>
                                <input type="text" class="form-control" id="addPhone" pattern="^0\d{9}$" title="Vui lòng nhập đúng định dạng số điện thoại VD: 0123456789" name ="sdt" required>
                            </div>
                            <div class="mb-3">
                                <label for="addCCCD" class="form-label">Căn cước công dân</label>
                                <input type="text" class="form-control" id="addCCCD" pattern="^\d{12}$" title="Vui lòng nhập đúng định dạng CCCD gồm 12 chữ số" name ="cccd" required>
                            </div>
                            <div class="mb-3">
                                <label for="addAddress" class="form-label">Địa chỉ</label>
                                <input type="text" class="form-control" id="addAddress" name="diachi" required>
                            </div>
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
                        <h5 class="modal-title" id="editEmployeeModalLabel">Chỉnh sửa lịch ghi chỉ số</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Edit employee form -->
                        <form action = "quan_ly_lich_ghi_chi_so_khach_hang/sua" method="POST" id ="editCustomer" onsubmit="return validateDate()">
                            <div class="mb-3">
                                <label for="editDOB" class="form-label">Ngày bắt đầu</label>
                                <div class="container">
								  <div class="row">
								    <div class="col">
								    	<select class="form-select" id="editDay" name="ngay">
	                                		<option value="0" disabled selected hidden>Ngày</option>
						                	<c:forEach var = "i" begin = "1" end = "31">
						                    	<option value="${i}">${i}</option>
						                    </c:forEach>
									    </select>
										<input type="hidden" class="pre_day" id="pre_day" name="pre_day" value="">
								    </div>
								    <div class="col">
								      	<select class="form-select" id="editMonth" name="thang">
			                				<option value="0" disabled selected hidden>Tháng</option>
					                		<c:forEach var = "i" begin = "1" end = "12">
					                    		<option value="${i}">${i}</option>
					                    	</c:forEach>
				                		</select>
				                		<input type="hidden" class="pre_month" id="pre_month" name="pre_month" value="">
								    </div>
								    <div class="col">
								      	<select class="form-select" id="editYear" name="nam">
			                				<option value="0" disabled selected hidden>Năm</option>
					                		<%-- Sử dụng vòng lặp từ 0 đến 124 (2024 - 1900) --%>
									        <% for (int i = 0; i <= 124; i++) { %>
									            <%-- Tính giá trị năm dựa trên biến i --%>
									            <% int year = 2024 - i; %>
									            <%-- Tạo một tùy chọn cho mỗi năm --%>
									            <option value="<%= year %>"><%= year %></option>
									        <% } %>
		                				</select>
		                				<input type="hidden" class="pre_year" id="pre_year" name="pre_year" value="">
								    </div>
								  </div>
								</div>
                            </div>
                            <input type="hidden" class="lgcs_id-edit" name="lgcs_id" value="">
                        </form>
                        <div id="mess-error-edit" style="color: red; font-size: 15px"></div>
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
                        <p>Bạn có chắc muốn xóa khách hàng này không?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                        <form id="formToDelete" action="quan_ly_thong_tin_khach_hang/xoa" method="POST">
                        	<input type="hidden" class="khachhang_id-delete" name="kh_id" value="">
                        	<button id="confirmDeleteButton" type="submit" class="btn btn-danger" >Xóa</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>


<script>
	// Lấy tất cả các nút "Edit"
	const editButtons = document.querySelectorAll('.btn-edit');
	
	// Thêm sự kiện click cho mỗi nút "Edit"
	editButtons.forEach((button) => {
	    button.addEventListener('click', (event) => {
	
	    	// current row
	        const row = event.target.closest('tr'); // Lấy hàng bảng chứa nút "Edit"
	        const lgcs_id = row.querySelector('td:nth-child(1)').textContent; 
	        const khachhang_id = row.querySelector('td:nth-child(2)').textContent;
	        const dob = row.querySelector('td:nth-child(3)').textContent; 
			
	        // next row
	        const nextRow = row.nextElementSibling;
	        const pre_dob = nextRow.querySelector('td:nth-child(3)').textContent;
	        
	        // Điền giá trị vào các trường input trong modal

	        var parts = dob.split("-");
	        const day = parseInt(parts[0],10);
	        const month = parseInt(parts[1],10);
	        const year = parseInt(parts[2],10);
	        
	        var pre_parts = pre_dob.split("-");
	        const pre_day = parseInt(pre_parts[0],10);
	        const pre_month = parseInt(pre_parts[1],10);
	        const pre_year = parseInt(pre_parts[2],10);
	        
	        
	        document.getElementById('editDay').value = day;
	        document.getElementById('editMonth').value = month;
	        document.getElementById('editYear').value = year;
	        
	        document.getElementById('pre_day').value = pre_day;
	        document.getElementById('pre_month').value = pre_month;
	        document.getElementById('pre_year').value = pre_year;    
	        
	        document.querySelector('.lgcs_id-edit').value = lgcs_id
	        
// 	        document.querySelector('.khachhang_id').value = khachhang_id;
// 	        console.log("lich su do id: " + document.querySelector('.lsd_id-edit').value);
	    });
	});
	
	
	const deleteButtons = document.querySelectorAll('.btn-delete');
	
	deleteButtons.forEach((button) => {
	    button.addEventListener('click', (event) => {
	    	const row = event.target.closest('tr');
	
	        const khachhang_id = row.querySelector('.khachhang_id').value
	        console.log(khachhang_id)
	
	        document.querySelector('.lsd_id-delete').value = khachhang_id
	
	    });
	});
	
	window.onload = function() {
		var selectedValue = sessionStorage.getItem('selectedValue');
		if (selectedValue) {
			document.getElementById('select_makh').value = selectedValue;
		}
	};
	
	
	const select_makh = document.querySelector('#select_makh');
	select_makh.addEventListener('change', function() {
		
		var selectedValue = this.value;
		sessionStorage.setItem('selectedValue', selectedValue);
		
		document.querySelector('#form_makh').submit()
	});
	
	function isValidateDate(day, month, year) {
		if (month < 1 || month > 12) {
			return false;
		}
		
		var daysInMonth = new Date(year, month, 0).getDate();
		
		if (day < 1 || day > daysInMonth) {
			return false;
		}
		
		return true;
	}
	
	function validateDate() {
		var selectedDay = parseInt(document.getElementById("editDay").value);
        var selectedMonth = parseInt(document.getElementById("editMonth").value);
        var selectedYear = parseInt(document.getElementById("editYear").value);
        var mess_error_edit = document.getElementById("mess-error-edit");
        
        var pre_day = parseInt(document.getElementById("pre_day").value);
        var pre_month = parseInt(document.getElementById("pre_month").value);
        var pre_year = parseInt(document.getElementById("pre_year").value);
        
        if (!isValidateDate(selectedDay, selectedMonth, selectedYear)) {
        	mess_error_edit.innerHTML = "Ngày không hợp lệ!!";
        	mess_error_edit.style.color = "red";
            return false;
        }
        
        if (selectedYear < pre_year) {
        	mess_error_edit.innerHTML = "Ngày bắt đầu không được nhỏ hơn hoặc bằng ngày của tháng trước!!";
            return false;
        } else if (selectedYear == pre_year) {
        	if (selectedMonth < pre_month) {
        		mess_error_edit.innerHTML = "Ngày bắt đầu không được nhỏ hơn hoặc bằng ngày của tháng trước!!";
                return false;
        	} else {
        		if (selectedDay <= pre_day) {
        			mess_error_edit.innerHTML = "Ngày bắt đầu không được nhỏ hơn hoặc bằng ngày của tháng trước!!";
                    return false;
        		}
        	}
        }
        
        return true;
	}
	
</script>
</body>
</html>