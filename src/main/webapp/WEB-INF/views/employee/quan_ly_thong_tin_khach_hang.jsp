<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<%@ page import="java.time.Year" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Quản lý thông tin khách hàng</title>
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
	<div class="container mt-5">
	<div class="row">
	<div style="margin-left: 0px;width:1300px" class="col-md-8 offset-md-2">
		<form class="custom-form" action = "/Tinhtiendien/nhan_vien/quan_ly_thong_tin_khach_hang/tim_kiem" method="GET">
		    <div class="row mb-3">
		        <div class="col">
		            <label for="field1" class="form-label">Mã khách hàng</label>
		            <input type="text" class="form-control" id="field1" name="search_khachhang_id">
		        </div>
		        <div class="col">
		            <label for="field2" class="form-label">Họ tên</label>
		            <input type="text" class="form-control" id="field2" name="search_hoten">
		        </div>
		        <div class="col">
		            <label for="field3" class="form-label">Giới tính</label>
		             <select class="form-select" id="field3" aria-label="Default select example" name="search_gioitinh">
								  <option value="" selected>Không</option>
								  <option value="nam">Nam</option>
								  <option value="nu">Nữ</option>
					</select>
		        </div>
		        <div class="col">
		            <label for="field4" class="form-label">Ngày sinh</label>
		            <input type="date" class="form-control" id="field4"  min="01-01-1990" max="31-12-2024" name="search_ngaysinh">
		        </div>
		        
		    </div>
		    <div class="row mb-3">
		        <div class="col">
		            <label for="field5" class="form-label">Email</label>
		            <input type="text" class="form-control" id="field5" name="search_email">
		        	<label class="form-label">Nhập "." nếu bạn muốn tìm khách hàng không có email</label>
		        </div>
		        <div class="col">
		            <label for="field6" class="form-label">Số điện thoại</label>
		            <input type="text" class="form-control" id="field6" pattern="^0\d{9}$" title="Vui lòng nhập đúng định dạng số điện thoại VD: 0123456789" name="search_sdt">
		        </div>
		        <div class="col">
		            <label for="field7" class="form-label">Căn cước công dân</label>
		            <input type="text" class="form-control" id="field7" pattern="^\d{12}$" title="Vui lòng nhập đúng định dạng CCCD gồm 12 chữ số" name="search_cccd">
		        </div>
		        <div class="col">
		            <label for="field8" class="form-label">Địa chỉ</label>
		            <input type="text" class="form-control" id="field8" name="search_diachi">
		        </div>
		    </div>
    		<!-- Thêm các hàng input khác nếu cần -->
			<button style="float: right;" type="submit" class="btn btn-primary submit-btn">Tìm kiếm</button>
		</form>
			<form action="/Tinhtiendien/nhan_vien/quan_ly_thong_tin_khach_hang" method="GET">
				<button style="float: right; margin-right: 5px;" type="submit" class="btn btn-primary submit-btn">Tất Cả</button>
			</form>
	</div>
</div>
	<%
		String mess = (String) session.getAttribute("message");
		Boolean isError = (Boolean) session.getAttribute("isError");
	
		if (mess != null && !isError) {
	%>
	<div id="successMessage" class="alert alert-success" role="alert" style="margin-top: 20px;"><%= mess %></div>
	<script>
	// Sau khi tải xong trang, tự động ẩn thông báo sau 3 giây
	setTimeout(function() {
		var successMessage = document.getElementById("successMessage");
		if (successMessage) {
			successMessage.style.display = "none";
		}
	}, 3000);
	sessionStorage.removeItem("formType");
	sessionStorage.removeItem("hoten");
	sessionStorage.removeItem("gioitinh");
	sessionStorage.removeItem("email");
	sessionStorage.removeItem("sdt");
	sessionStorage.removeItem("cccd");
	sessionStorage.removeItem("diachi");
	sessionStorage.setItem("ngay", '');
	sessionStorage.setItem("thang", '');
	sessionStorage.setItem("nam", '');
	</script>
	<%
			session.removeAttribute("message");
			session.removeAttribute("isError");
		} else if (mess != null && isError) {
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
	sessionStorage.removeItem("hoten");
	sessionStorage.removeItem("gioitinh");
	sessionStorage.removeItem("email");
	sessionStorage.removeItem("sdt");
	sessionStorage.removeItem("cccd");
	sessionStorage.removeItem("diachi");
	sessionStorage.setItem("ngay", '');
	sessionStorage.setItem("thang", '');
	sessionStorage.setItem("nam", '');
	</script>
	<%
			session.removeAttribute("message");
			session.removeAttribute("isError");
		}
	%>
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-md-6">
                        <h2>Quản lý <b>Khách Hàng</b></h2>
                    </div>
                    <div class="col-md-6 text-md-end">
                        <button type="button" class="btn btn-success btn-add" data-bs-toggle="modal" data-bs-target="#addEmployeeModal" onclick="setSelectForm('addForm')">
                            <i class="material-icons">&#xE147;</i> <span>Thêm khách hàng mới</span>
                        </button>
                        <!-- <a href="#deleteEmployeeModal" class="btn btn-danger" data-bs-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a> -->
                    </div>
                </div>
                
            </div>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Mã Khách Hàng</th>
                        <th>Họ Tên</th>
                        <th>Giới tính</th>
                        <th>Ngày sinh</th>
                        <th>Email</th>
                        <th>Số điện thoại</th>
                        <th>Căn cước công dân</th>
                        <th>Địa chỉ</th>
                        <th>Hoạt động</th>
                    </tr>
                </thead>
                <tbody>
                  <c:forEach var="kh" varStatus="i" items="${listKH}">
			      <tr>
			      	<td>${kh.khachhang_id}</th>
			        <td>${kh.hovaten}</td>
			        <td>${kh.gioitinh}</td>
			        <td><fmt:formatDate value="${kh.ngaythangnam_sinh}" pattern="dd-MM-yyyy"/></td>
			        <td>${kh.email}</td>
			        <td>${kh.sdt}</td>
			        <td>${kh.cccd}</td>
			        <td>${kh.diachi}</td>
			        <td>
			        	<button type="button" class="btn btn-primary btn-sm btn-edit" data-bs-toggle="modal" data-bs-target="#editEmployeeModal" onclick="setSelectForm('editForm')"><i class="bi bi-pencil-fill"></i> Sửa</button>

                       	<input type="hidden" class="khachhang_id" name="kh_id" value="${kh.khachhang_id}">
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
                        <form action = "/Tinhtiendien/nhan_vien/quan_ly_thong_tin_khach_hang/them" method="POST" id ="addCustomer" onsubmit="return validateDateAdd()" >
                            <div class="mb-3">
                                <label for="addName" class="form-label">Họ Tên</label>
                                <input type="text" class="form-control" id="addName" name="hoten" required>
                            </div>
                            <div class="mb-3">
                                <label for="addGender" class="form-label addGender">Giới Tính</label>
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
								    	<select class="form-select addDay" id="addDay" name="ngay" required>
                                		<option value="" disabled selected hidden>Ngày</option>
					                	<c:forEach var = "i" begin = "1" end = "31">
					                    	<option value="${i}">${i}</option>
					                    </c:forEach>
									      </select>
								    </div>
								    <div class="col">
								      	<select class="form-select addMonth" id="addMonth" name="thang" required>
		                				<option value="" disabled selected hidden>Tháng</option>
				                		<c:forEach var = "i" begin = "1" end = "12">
				                    		<option value="${i}">${i}</option>
				                    	</c:forEach>
				                		</select>
								    </div>
								    <div class="col">
								      	<select class="form-select addYear" id="addYear" name="nam" required>
		                				<option value="" disabled selected hidden>Năm</option>
										<%
										int currentYear = Year.now().getValue();
										for (int year=currentYear-16; year>(currentYear-96); year--){
										%>	
											<option value="<%= year %>"><%= year %></option>
										<%
										}
										%>
		                				</select>
								    </div>
								    <div id="mess-error-date-add"></div>
								  </div>
								</div>
                            </div>
                            <div class="mb-3">
                                <label for="addEmail" class="form-label">Email</label>
                                <input type="email" class="form-control" id="addEmail" name ="email">
                                <h6 style="color:red; padding-left: 5px; padding-top: 5px;" id="addEmail">${err_mess_addEmail}</h6>
                            </div>
                            <div class="mb-3">
                                <label for="addPhone" class="form-label">Số điện thoại</label>
                                <input type="text" class="form-control" id="addPhone" pattern="^0\d{9}$" title="Vui lòng nhập đúng định dạng số điện thoại VD: 0123456789" name ="sdt" required>
                            	<h6 style="color:red; padding-left: 5px; padding-top: 5px;" id="addPhone">${err_mess_addPhone}</h6>
                            </div>
                            <div class="mb-3">
                                <label for="addCCCD" class="form-label">Căn cước công dân</label>
                                <input type="text" class="form-control" id="addCCCD" pattern="^\d{12}$" title="Vui lòng nhập đúng định dạng CCCD gồm 12 chữ số" name ="cccd" required>
                            	<h6 style="color:red; padding-left: 5px; padding-top: 5px;" id="addCCCD">${err_mess_addCCCD}</h6>
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
                        <h5 class="modal-title" id="editEmployeeModalLabel">Chỉnh sửa khách hàng</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Edit employee form -->
                        <form action = "/Tinhtiendien/nhan_vien/quan_ly_thong_tin_khach_hang/sua" method="POST" id ="editCustomer" onsubmit="return validateDateEdit()">
                            <div class="mb-3">
                                <label for="editName" class="form-label">Họ Tên</label>
                                <input type="text" class="form-control" id="editName" name ="hoten" required>
                            </div>
                            <div class="mb-3">
                                <label for="editGender" class="form-label">Giới Tính</label>
                                <select id="editGender" class="form-select" aria-label="Default select example" name="gioitinh">
								  <option value="nam">Nam</option>
								  <option value="nu">Nữ</option>
								</select>
                            </div>
                            <div class="mb-3">
                                <label for="editDOB" class="form-label">Ngày tháng năm sinh</label>
                                <div class="container">
								  <div class="row">
								    <div class="col">
								    	<select class="form-select" id="editDay" name="ngay">
                                		<option value="0" disabled selected hidden>Ngày</option>
					                	<c:forEach var = "i" begin = "1" end = "31">
					                    	<option value="${i}">${i}</option>
					                    </c:forEach>
									      </select>
								    </div>
								    <div class="col">
								      	<select class="form-select" id="editMonth" name="thang">
		                				<option value="0" disabled selected hidden>Tháng</option>
				                		<c:forEach var = "i" begin = "1" end = "12">
				                    		<option value="${i}">${i}</option>
				                    	</c:forEach>
				                		</select>
								    </div>
								    <div class="col">
								      	<select class="form-select" id="editYear" name="nam">
		                				<option value="0" disabled selected hidden>Năm</option>
				                    	<%
										for (int year=currentYear; year>1923; year--){
										%>	
											<option value="<%= year %>"><%= year %></option>
										<%
										}
										%>
		                				</select>
								    </div>
								     <div id="mess-error-date-edit"></div>
								  </div>
								</div>
                            </div>
                            <div class="mb-3">
                                <label for="editEmail" class="form-label">Email</label>
                                <input type="email" class="form-control" id="editEmail" name ="email">
                                <h6 style="color:red; padding-left: 5px; padding-top: 5px;" id="editEmail">${err_mess_editEmail}</h6>
                            </div>
                            <div class="mb-3">
                                <label for="editPhone" class="form-label">Số điện thoại</label>
                                <input type="text" class="form-control" id="editPhone" pattern="^0\d{9}$" title="Vui lòng nhập đúng định dạng số điện thoại VD: 0123456789" name="sdt" required>
                            	<h6 style="color:red; padding-left: 5px; padding-top: 5px;" id="editPhone">${err_mess_editPhone}</h6>
                            </div>
                             <div class="mb-3">
                                <label for="editCCCD" class="form-label">Căn cước công dân</label>
                                <input type="text" class="form-control" id="editCCCD" pattern="^\d{12}$" title="Vui lòng nhập đúng định dạng CCCD gồm 12 chữ số" name ="cccd" required>
                            	<h6 style="color:red; padding-left: 5px; padding-top: 5px;" id="editCCCD">${err_mess_editCCCD}</h6>
                            </div>
                            <div class="mb-3">
                                <label for="editAddress" class="form-label">Địa chỉ</label>
                                <input type="text" class="form-control" id="editAddress" name="diachi" required>
                            </div>
                            <input type="hidden" class="khachhang_id-edit" name="khachhang_id" value="">
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
                        <p>Bạn có chắc muốn xóa khách hàng này không?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                        <form id="formToDelete" action="/Tinhtiendien/nhan_vien/quan_ly_thong_tin_khach_hang/xoa" method="POST">
                        	<input type="hidden" class="khachhang_id-delete" name="kh_id" value="">
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
		var hoten = sessionStorage.getItem("hoten");
		var gioitinh = sessionStorage.getItem("gioitinh");
		var ngay = sessionStorage.getItem("ngay");
		var thang = sessionStorage.getItem("thang");
		var nam = sessionStorage.getItem("nam");
		var email = sessionStorage.getItem("email");
		var sdt = sessionStorage.getItem("sdt");
		var cccd = sessionStorage.getItem("cccd");
		var diachi = sessionStorage.getItem("diachi");
		
		document.getElementById("addName").value = hoten
		if (gioitinh == "" || gioitinh == "Nam"){
			document.getElementById("addGender").selectedIndex = 0;
		} else {
			document.getElementById("addGender").selectedIndex = 1;
		}
		if (ngay == '') {
			console.log("ngay null")
			document.querySelector('.addDay').selectedIndex = 0;
		} else {
			document.querySelector('.addDay').value = ngay;			
		}
		
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
		document.getElementById("addEmail").value = email
		document.getElementById("addPhone").value = sdt
		document.getElementById("addCCCD").value = cccd
		document.getElementById("addAddress").value = diachi
	})
	
	
// Lấy tất cả các nút "Edit"
const editButtons = document.querySelectorAll('.btn-edit');

// Thêm sự kiện click cho mỗi nút "Edit"
editButtons.forEach((button) => {
    button.addEventListener('click', (event) => {

        const row = event.target.closest('tr'); // Lấy hàng bảng chứa nút "Edit"
        const id = row.querySelector('td:nth-child(1)').textContent; // Lấy giá trị id
        const name = row.querySelector('td:nth-child(2)').textContent; // Lấy giá trị tên
        const gender = row.querySelector('td:nth-child(3)').textContent; // Lấy giá trị giới tính
        const dob = row.querySelector('td:nth-child(4)').textContent; // Lấy giá trị ngày sinh
        const email = row.querySelector('td:nth-child(5)').textContent;// Lấy giá trị email
        const phone = row.querySelector('td:nth-child(6)').textContent;// Lấy giá trị số điện thoại
        const cccd = row.querySelector('td:nth-child(7)').textContent;// Lấy giá trị CCCD
        const address = row.querySelector('td:nth-child(8)').textContent;// Lấy giá trị địa chỉ
        
        const khachhang_id = row.querySelector('.khachhang_id').value
        sessionStorage.setItem("khachhang_id", id);
        sessionStorage.setItem("hoten", name);
        sessionStorage.setItem("gioitinh", gender);
        
        sessionStorage.setItem("email", email);
        sessionStorage.setItem("sdt", phone);
        sessionStorage.setItem("cccd", cccd);
        sessionStorage.setItem("diachi", address);
        
        // Điền giá trị vào các trường input trong modal
        document.getElementById('editName').value = name;
        document.getElementById('editEmail').value = email;
        if (gender === "Nam" || gender == ""){
        	document.getElementById("editGender").selectedIndex = 0
        } else {
        	document.getElementById("editGender").selectedIndex = 1
        }
        var parts = dob.split("-");
        const day = parseInt(parts[0],10);
        const month = parseInt(parts[1],10);
        const year = parseInt(parts[2],10);
     
        sessionStorage.setItem("ngay", day);
        sessionStorage.setItem("thang", month);
        sessionStorage.setItem("nam", year);
        
        document.getElementById('editDay').value = day;
        document.getElementById('editMonth').value = month;
        document.getElementById('editYear').value = year;
        document.getElementById('editCCCD').value = cccd;
        document.getElementById('editAddress').value = address;
        document.getElementById('editPhone').value = phone;
        document.querySelector('.khachhang_id-edit').value = khachhang_id
        
        console.log("khach hang id: " + document.querySelector('.khachhang_id-edit').value);
    });
});


const deleteButtons = document.querySelectorAll('.btn-delete');

deleteButtons.forEach((button) => {
    button.addEventListener('click', (event) => {
    	const row = event.target.closest('tr');

        const khachhang_id = row.querySelector('.khachhang_id').value
        console.log(khachhang_id)

        document.querySelector('.khachhang_id-delete').value = khachhang_id

    });
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


function validateDateAdd() {
	var selectedDay = parseInt(document.getElementById("addDay").value);
    var selectedMonth = parseInt(document.getElementById("addMonth").value);
    var selectedYear = parseInt(document.getElementById("addYear").value);
    var thongbao = document.getElementById("mess-error-date-add");
    
    if (!isValidateDate(selectedDay, selectedMonth, selectedYear)) {
		thongbao.innerHTML = "Ngày không tồn tại!!";
		thongbao.style.color = "red";
        return false;
    }
    
    return true;
}

function validateDateEdit() {
	var selectedDay = parseInt(document.getElementById("editDay").value);
    var selectedMonth = parseInt(document.getElementById("editMonth").value);
    var selectedYear = parseInt(document.getElementById("editYear").value);
    var thongbao = document.getElementById("mess-error-date-edit");
    
    if (!isValidateDate(selectedDay, selectedMonth, selectedYear)) {
		thongbao.innerHTML = "Ngày không tồn tại!!";
		thongbao.style.color = "red";
        return false;
    }
    
    return true;
}

function setSelectForm(form) {
	sessionStorage.setItem("formType", form)
	console.log(form)
}

window.addEventListener('beforeunload', function() {
	var formType = sessionStorage.getItem("formType")
	
	// edit form
	if (formType == "editForm") {
		sessionStorage.setItem("khachhang_id", document.querySelector('.khachhang_id-edit').value);
		sessionStorage.setItem("hoten", document.getElementById("editName").value);
        sessionStorage.setItem("gioitinh", document.getElementById("editGender").value);
        sessionStorage.setItem("ngay", document.getElementById("editDay").value);
        sessionStorage.setItem("thang", document.getElementById("editMonth").value);
        sessionStorage.setItem("nam", document.getElementById("editYear").value);
        sessionStorage.setItem("email", document.getElementById("editEmail").value);
        sessionStorage.setItem("sdt", document.getElementById("editPhone").value);
        sessionStorage.setItem("cccd", document.getElementById("editCCCD").value);
        sessionStorage.setItem("diachi", document.getElementById("editAddress").value);
	}
    
    // add form
    if (formType == "addForm") {
    	sessionStorage.setItem("hoten", document.getElementById("addName").value);
        sessionStorage.setItem("gioitinh", document.getElementById("addGender").value);
        sessionStorage.setItem("ngay", document.getElementById("addDay").value);
        sessionStorage.setItem("thang", document.getElementById("addMonth").value);
        sessionStorage.setItem("nam", document.getElementById("addYear").value);
        sessionStorage.setItem("email", document.getElementById("addEmail").value);
        sessionStorage.setItem("sdt", document.getElementById("addPhone").value);
        sessionStorage.setItem("cccd", document.getElementById("addCCCD").value);
        sessionStorage.setItem("diachi", document.getElementById("addAddress").value);
    }
});

document.addEventListener("DOMContentLoaded", function() {
	
	var khachhang_id = sessionStorage.getItem("khachhang_id");
	var hoten = sessionStorage.getItem("hoten");
	var gioitinh = sessionStorage.getItem("gioitinh");
	var ngay = sessionStorage.getItem("ngay");
	var thang = sessionStorage.getItem("thang");
	var nam = sessionStorage.getItem("nam");
	var email = sessionStorage.getItem("email");
	var sdt = sessionStorage.getItem("sdt");
	var cccd = sessionStorage.getItem("cccd");
	var diachi = sessionStorage.getItem("diachi");
	
	// binding data khi tải lại trang
	// edit form
	document.getElementById("editName").value = hoten
	document.getElementById("editGender").value = gioitinh
	document.getElementById("editDay").value = ngay
	document.getElementById("editMonth").value = thang
	document.getElementById("editYear").value = nam
	document.getElementById("editEmail").value = email
	document.getElementById("editPhone").value = sdt
	document.getElementById("editCCCD").value = cccd
	document.getElementById("editAddress").value = diachi
	document.querySelector('.khachhang_id-edit').value = khachhang_id

	// add form
	document.getElementById("addName").value = hoten
	if (gioitinh == "Nam"){
		document.getElementById("addGender").selectedIndex = 0;
	} else {
		document.getElementById("addGender").selectedIndex = 1;
	}
	if (ngay == '') {
		console.log("ngay null")
		document.getElementById("addDay").selectedIndex = 0;
	} else {
		document.getElementById("addDay").value = ngay;			
	}
	
	if (thang == '') {
		document.getElementById("addMonth").selectedIndex = 0;
	} else {
		document.getElementById("addMonth").value = thang;			
	}
	
	if (nam == '') {
		document.getElementById("addYear").selectedIndex = 0;
	} else {
		document.getElementById("addYear").value = nam;			
	}
	
	document.getElementById("addEmail").value = email
	document.getElementById("addPhone").value = sdt
	document.getElementById("addCCCD").value = cccd
	document.getElementById("addAddress").value = diachi
	
	var formType = sessionStorage.getItem("formType");
	if (formType == "addForm") {
		$('#addEmployeeModal').modal('show');
	} else if (formType == "editForm") {
		$('#editEmployeeModal').modal('show');
	}
	
	$('.modal').on('hidden.bs.modal', function () {
		sessionStorage.removeItem("khachhang_id");
		sessionStorage.removeItem("formType");
		sessionStorage.removeItem("hoten");
		sessionStorage.removeItem("gioitinh");
		sessionStorage.removeItem("email");
		sessionStorage.removeItem("sdt");
		sessionStorage.removeItem("cccd");
		sessionStorage.removeItem("diachi");
		sessionStorage.setItem("ngay", '');
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

</script>
</body>
</html>