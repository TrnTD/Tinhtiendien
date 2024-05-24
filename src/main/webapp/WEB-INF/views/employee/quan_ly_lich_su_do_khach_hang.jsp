<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Quản lý lịch sử đo khách hàng</title>
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.1/font/bootstrap-icons.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<style><%@include file="/WEB-INF/resource/assets/css/style-quanly.css"%></style>
<style>
	.error-input {
	    border: 1px solid red !important; /* Đường viền màu đỏ */
	}
</style>
<!-- 	================================================= -->

<div class="container">

<div class="row">
	<div style="margin-left: 0px;width:1300px" class="col-md-8 offset-md-2">
		<form action="/Tinhtiendien/nhan_vien/quan_ly_lich_su_do_khach_hang/tim_kiem" class="custom-form" method="GET">
		    <div class="row mb-3">
		        <div class="col">
		            <label for="field1" class="form-label">Mã lịch sử đo</label>
		            <input type="text" class="form-control" id="field1" placeholder="1" name="search_lsdid">
		        </div>
		        <div class="col">
		            <label for="field2" class="form-label">Mã khách hàng</label>
		            <input type="text" class="form-control" id="field2" placeholder="KH001" name="search_khachhangid">
		        </div>
		        <div class="col">
		            <label for="field3" class="form-label">Mã đồng hồ điện</label>
		            <input type="text" class="form-control" id="field3" placeholder="1" name="search_dhdid">
		        </div>
		        <div class="col">
		            <label for="field4" class="form-label">Ngày đo</label>
		            <input type="date" class="form-control" id="field4"  min="01-01-1990" max="31-12-2024" name="search_ngaydo">
		        </div>
		    </div>
			<button style="float: right;" type="submit" class="btn btn-primary submit-btn">Tìm kiếm</button>
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
		sessionStorage.removeItem("chiso")
	// Sau khi tải xong trang, tự động ẩn thông báo sau 3 giây
	setTimeout(function() {
		var successMessage = document.getElementById("successMessage");
		if (successMessage) {
			successMessage.style.display = "none";
		}
	}, 3000);
		
		sessionStorage.removeItem("formType");
		sessionStorage.removeItem("lsd_id");
		sessionStorage.removeItem("khachhang_id");
		sessionStorage.removeItem("chiso");
		sessionStorage.removeItem("ngay");
		sessionStorage.removeItem("thang");
		sessionStorage.removeItem("nam");
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
		sessionStorage.removeItem("lsd_id");
		sessionStorage.removeItem("khachhang_id");
		sessionStorage.removeItem("chiso");
		sessionStorage.removeItem("ngay");
		sessionStorage.removeItem("thang");
		sessionStorage.removeItem("nam");
)
	</script>
	<%
			session.removeAttribute("message");
			session.removeAttribute("isError");
		}
	%>
        <div class="table-wrapper" style="box-shadow: rgba(0, 0, 0, 0.5) 0px 5px 15px;">
            <div class="table-title">
                <div class="row">
                    <div class="col-md-6">
                        <h2>Quản lý <b>Lịch Sử Đo</b></h2>
                    </div>
                    <div class="col-md-6 text-md-end">
                        <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addEmployeeModal" onclick="setSelectForm('addForm')">
                            <i class="material-icons">&#xE147;</i> <span>Thêm lịch sử đo mới</span>
                        </button>
                        <!-- <a href="#deleteEmployeeModal" class="btn btn-danger" data-bs-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a> -->
                    </div>
                </div>            
            </div>
            
<!--             <div class="row"> -->
<!-- 		      <div class="col-2"> -->
<!-- 		      <form id="form_makh" method="GET"> -->
<!-- 		        <select id="select_makh" name="selected_makh" class="form-select" aria-label="Default select example"> -->
<%-- 		        <c:forEach var="info" items="${list_info}"> --%>
<%-- 		          <option value="${info.khachhang_id}">${info.khachhang_id}</option> --%>
<%-- 		        </c:forEach> --%>
<!-- 		        </select>		       -->
<!-- 		      </form> -->
<!-- 		      </div> -->
<!-- 		    </div> -->
            
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Mã lịch sử đo</th>
                        <th>Mã khách hàng</th>
                        <th>Mã đồng hồ điện</th>
                        <th>Ngày đo</th>
                        <th>Chỉ số</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                  <c:forEach var="lsd" varStatus="i" items="${list_lsd}">
			      <tr>
			      	<td>${lsd.lichsu_do_id}</th>
			        <td>${lsd.khachhang_id}</td>
			        <td>${lsd.dongho_id}</td>
			        <td><fmt:formatDate value="${lsd.ngay_do}" pattern="dd-MM-yyyy"/></td>
			        <td>${lsd.chiso}</td>
			        <td>
			        	<button type="button" class="btn btn-primary btn-sm btn-edit" data-bs-toggle="modal" data-bs-target="#editEmployeeModal" onclick="setSelectForm('editForm')"><i class="bi bi-pencil-fill"></i> Sửa</button>

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
                        <h5 class="modal-title" id="addEmployeeModalLabel">Thêm lịch sử đo</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Add employee form -->
                        <form action = "/Tinhtiendien/nhan_vien/quan_ly_lich_su_do_khach_hang/them" method="POST" id ="addCustomer">
                            <div class="mb-3">
                                <label for="add_khachhang_id" class="form-label">Mã khách hàng</label>
                                <input type="text" class="form-control" id="add_khachhang_id" name="add_khachhangid" required>
                                <h6 style="color:red; padding-left:5px; padding-top:5px" id="add_khachhang_id">${err_mess_addKhachhangid}</h6>
                            </div>
                            
                            <div class="mb-3">
                                <label for="addDOB" class="form-label">Ngày đo</label>
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
								    <h6 style="color:red; padding-left:5px; padding-top:5px" id="add_khachhang_id">${err_mess_addNgaydo}</h6>
								  </div>
								</div>
                            </div>
                            <div class="mb-3">
                                <label for="add_chiso" class="form-label">Chỉ số</label>
                                <input type="number" class="form-control" id="add_chiso" name ="add_chiso" min="0" required>
                                <h6 style="color:red; padding-left:5px; padding-top:5px" id="add_khachhang_id">${err_mess_addChiso}</h6>
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
                        <h5 class="modal-title" id="editEmployeeModalLabel">Chỉnh sửa lịch sử đo</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Edit employee form -->
                        <form action = "/Tinhtiendien/nhan_vien/quan_ly_lich_su_do_khach_hang/sua" method="POST" id ="editCustomer" onsubmit="return validateDate()">
                        	<div class="mb-3">
								<label for="editDOB" class="form-label">Ngày đo</label>
								<div class="container">
								  <div class="row">
									<div class="col">
										<select class="form-select editDay" id="editngaydo" name="ngay">
											<option value="0" disabled selected hidden>Ngày</option>
											<c:forEach var = "i" begin = "1" end = "31">
												<option value="${i}">${i}</option>
											</c:forEach>
										</select>
									</div>
									<div class="col">
										  <select class="form-select editMonth" id="editngaydo" name="thang">
											<option value="0" disabled selected hidden>Tháng</option>
											<c:forEach var = "i" begin = "1" end = "12">
												<option value="${i}">${i}</option>
											</c:forEach>
										</select>
									</div>
									<div class="col">
										  <select class="form-select editYear" id="editngaydo" name="nam">
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
									<h6 style="color:red; padding-left: 5px; padding-top: 5px;" id="editngaydo">${err_mess_editNgaydo}</h6>
								  </div>
								</div>
							</div>
                        
                            <div class="mb-3">
                                <label for="editChiso" class="form-label">Chỉ số</label>
                                <input type="number" class="form-control" id="editChiso" name="chiso" required min="0">
                            </div>
                            <input type="hidden" class="lsd_id-edit" name="lsd_id" value="">
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
                        <form id="formToDelete" action="/Tinhtiendien/nhan_vien/quan_ly_lich_su_do_khach_hang/xoa" method="POST">
                        	<input type="hidden" class="lsd_id-delete" name="lsd_id" value="">
                        	<button id="confirmDeleteButton" type="submit" class="btn btn-danger" >Xóa</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
	// Lấy tất cả các nút "Edit"
	const editButtons = document.querySelectorAll('.btn-edit');
	
	// Thêm sự kiện click cho mỗi nút "Edit"
	editButtons.forEach((button) => {
	    button.addEventListener('click', (event) => {
	
	        const row = event.target.closest('tr'); // Lấy hàng bảng chứa nút "Edit"
	        const lsd_id = row.querySelector('td:nth-child(1)').textContent; 
	        const khachhang_id = row.querySelector('td:nth-child(2)').textContent;
	        const dongho_id = row.querySelector('td:nth-child(3)').textContent; 
	        const dob = row.querySelector('td:nth-child(4)').textContent; 
	        const chiso = row.querySelector('td:nth-child(5)').textContent;
	
	        // Điền giá trị vào các trường input trong modal
			
	        document.getElementById('editChiso').value = chiso;
	        
	        sessionStorage.setItem("lsd_id", lsd_id);
	        sessionStorage.setItem("khachhang_id", khachhang_id);
	        sessionStorage.setItem("chiso", chiso);

        
	        var parts = dob.split("-");
	        const day = parseInt(parts[0],10);
	        const month = parseInt(parts[1],10);
	        const year = parseInt(parts[2],10);
	        
	        sessionStorage.setItem("ngay", day);
	        sessionStorage.setItem("thang", month);
	        sessionStorage.setItem("nam", year);
	        
	        document.querySelector('.editDay').value = day;
	        document.querySelector('.editMonth').value = month;
	        document.querySelector('.editYear').value = year;

	        document.querySelector('.lsd_id-edit').value = lsd_id
	        document.querySelector('.khachhang_id-edit').value = khachhang_id
	        
	        
// 	        document.querySelector('.khachhang_id').value = khachhang_id;
// 	        console.log("lich su do id: " + document.querySelector('.lsd_id-edit').value);
	    });
	});
	
// 	const editForm = document.getElementById('editCustomer')
// 	editForm.addEventListener("submit", function(e) {
// 		var err_mess_element = document.querySelector('h6#editChiso')
// 		var editInputElement = document.querySelector('input#editChiso')
		
// 		var chiso_moi = document.querySelector('input[name="chiso"]').value
// 		var chiso_cu = sessionStorage.getItem("chiso_cu")
// 		var chiso_thangsau = sessionStorage.getItem("chiso_thangsau")
		
// 		if (chiso_thangsau == null) {
// 			console.log("null cmnr")
// 		} else {
// 			if (chiso_moi < chiso_cu) {
// 				e.preventDefault();
// 				err_mess_element.textContent = "Chỉ số phải lớn hơn hoặc bằng chỉ số tháng trước!!"
// 				editInputElement.classList.add('error-input')
// 			} else if (chiso_moi > chiso_thangsau && chiso_thangsau != null) {
// 				e.preventDefault();
// 				err_mess_element.textContent = "Chỉ số phải bé hơn hoặc bằng chỉ số tháng sau!!"
// 				editInputElement.classList.add('error-input')
// 			}			
// 		}
// 	})
	
	
	const deleteButtons = document.querySelectorAll('.btn-delete');
	
	deleteButtons.forEach((button) => {
	    button.addEventListener('click', (event) => {
	    	const row = event.target.closest('tr');
	
	        const lsd_id = row.querySelector('.lsd_id').value
	        console.log(lsd_id)
	
	        document.querySelector('.lsd_id-delete').value = lsd_id
	
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
        sessionStorage.setItem("chiso", document.querySelector('input[name="chiso"]').value);
        sessionStorage.setItem("ngay", document.querySelector('.editDay').value);
        sessionStorage.setItem("thang", document.querySelector('.editMonth').value);
        sessionStorage.setItem("nam", document.querySelector('.editYear').value);
        
	});
	
	document.addEventListener("DOMContentLoaded", function() {
			
		var chiso = sessionStorage.getItem("chiso");
		var lsd_id = sessionStorage.getItem("lsd_id");
		var khachhang_id = sessionStorage.getItem("khachhang_id");
		var ngay = sessionStorage.getItem("ngay");
		var thang = sessionStorage.getItem("thang");
		var nam = sessionStorage.getItem("nam");
		
		document.querySelector('input[name="chiso"]').value = chiso;
		document.querySelector('.lsd_id-edit').value = lsd_id;
		document.querySelector('.khachhang_id-edit').value = khachhang_id;
		document.querySelector('.editDay').value = ngay;
		document.querySelector('.editMonth').value = thang;
		document.querySelector('.editYear').value = nam;
		
		var formType = sessionStorage.getItem("formType");
		if (formType == "addForm") {
			$('#addEmployeeModal').modal('show');
		} else if (formType == "editForm") {
			$('#editEmployeeModal').modal('show');
		}
		
		$('.modal').on('hidden.bs.modal', function () {
			sessionStorage.removeItem("formType");
			sessionStorage.removeItem("lsd_id");
			sessionStorage.removeItem("khachhang_id");
			sessionStorage.removeItem("chiso");
			sessionStorage.removeItem("ngay");
			sessionStorage.removeItem("thang");
			sessionStorage.removeItem("nam");
			
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
		var selectedDay = parseInt(document.querySelector(".editDay").value);
        var selectedMonth = parseInt(document.querySelector(".editMonth").value);
        var selectedYear = parseInt(document.querySelector(".editYear").value);
        
        console.log(selectedDay)
        console.log(selectedMonth)
        console.log(selectedYear)
		
		if (!isValidateDate(selectedDay, selectedMonth, selectedYear)) {
			
			var err_mess_ngaydo = document.querySelector('h6#editngaydo');
			
			err_mess_ngaydo.textContent = "Ngày không hợp lệ!!";
			
			var inputElements = document.querySelectorAll('#editngaydo:not(h6)');

            if (inputElements) {
            	inputElements.forEach(inputElement => {
            		inputElement.classList.add('error-input');
            	})
            }

            return false;
        }
		
		return true;
	}
	
</script>
</body>
</html>