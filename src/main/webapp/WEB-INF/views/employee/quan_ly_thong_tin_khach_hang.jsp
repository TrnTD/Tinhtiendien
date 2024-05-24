<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<%@ page import="java.time.Year" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Quản lý thông tin người dùng</title>
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.1/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
	<style><%@include file="/WEB-INF/resource/assets/css/style-quanly.css"%></style>
<!-- 	================================================= -->
<style>
	th {
		vertical-align: middle;
	}
</style>
<div class="container">
<div class="row">
	<div style="margin-left: 0px;width:1300px" class="col-md-8 offset-md-2">
		<form class="custom-form">
		    <div class="row mb-3">
		        <div class="col">
		            <label for="field1" class="form-label">Field 1</label>
		            <input type="text" class="form-control" id="field1" placeholder="Enter Field 1">
		        </div>
		        <div class="col">
		            <label for="field2" class="form-label">Field 2</label>
		            <input type="text" class="form-control" id="field2" placeholder="Enter Field 2">
		        </div>
		        <div class="col">
		            <label for="field3" class="form-label">Field 3</label>
		            <input type="text" class="form-control" id="field3" placeholder="Enter Field 3">
		        </div>
		        <div class="col">
		            <label for="field4" class="form-label">Ngày sinh</label>
		            <input type="date" class="form-control" id="field4"  min="01-01-1990" max="31-12-2024" name="ngaysinh">
		        </div>
		        
		    </div>
		    <div class="row mb-3">
		        <div class="col">
		            <label for="field5" class="form-label">Field 5</label>
		            <input type="text" class="form-control" id="field5" placeholder="Enter Field 5">
		        </div>
		        <div class="col">
		            <label for="field6" class="form-label">Field 6</label>
		            <input type="text" class="form-control" id="field6" placeholder="Enter Field 6">
		        </div>
		        <div class="col">
		            <label for="field7" class="form-label">Field 7</label>
		            <input type="text" class="form-control" id="field7" placeholder="Enter Field 7">
		        </div>
		        <div class="col">
		            <label for="field8" class="form-label">Field 8</label>
		            <input type="text" class="form-control" id="field8" placeholder="Enter Field 8">
		        </div>
		    </div>
    		<!-- Thêm các hàng input khác nếu cần -->
			<button style="float: right;" type="submit" class="btn btn-primary submit-btn">Tìm kiếm</button>
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
                        <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addEmployeeModal">
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
                        <th></th>
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
			        	<button type="button" class="btn btn-primary btn-sm btn-edit" data-bs-toggle="modal" data-bs-target="#editEmployeeModal"><i class="bi bi-pencil-fill"></i> Sửa</button>

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
                        <form action = "quan_ly_thong_tin_khach_hang/them" method="POST" id ="addCustomer" onsubmit="return validateDateAdd()">
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
                                		<option value="" disabled selected hidden>Ngày</option>
					                	<c:forEach var = "i" begin = "1" end = "31">
					                    	<option value="${i}">${i}</option>
					                    </c:forEach>
									      </select>
								    </div>
								    <div class="col">
								      	<select class="form-select" id="addMonth" name="thang" required>
		                				<option value="" disabled selected hidden>Tháng</option>
				                		<c:forEach var = "i" begin = "1" end = "12">
				                    		<option value="${i}">${i}</option>
				                    	</c:forEach>
				                		</select>
								    </div>
								    <div class="col">
								      	<select class="form-select" id="addYear" name="nam" required>
		                				<option value="" disabled selected hidden>Năm</option>
										<%
										int currentYear = Year.now().getValue();
										for (int year=currentYear; year>1923; year--){
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
                        <h5 class="modal-title" id="editEmployeeModalLabel">Chỉnh sửa khách hàng</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Edit employee form -->
                        <form action = "quan_ly_thong_tin_khach_hang/sua" method="POST" id ="editCustomer" onsubmit="return validateDateEdit()">
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
                                <input type="email" class="form-control" id="editEmail" name ="email" required>
                            </div>
                            <div class="mb-3">
                                <label for="editPhone" class="form-label">Số điện thoại</label>
                                <input type="text" class="form-control" id="editPhone" pattern="^0\d{9}$" title="Vui lòng nhập đúng định dạng số điện thoại VD: 0123456789" name="sdt" required>
                            </div>
                             <div class="mb-3">
                                <label for="editCCCD" class="form-label">Căn cước công dân</label>
                                <input type="text" class="form-control" id="editCCCD" pattern="^\d{12}$" title="Vui lòng nhập đúng định dạng CCCD gồm 12 chữ số" name ="cccd" required>
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

        console.log(123)
        
        // Điền giá trị vào các trường input trong modal
        document.getElementById('editName').value = name;
        document.getElementById('editEmail').value = email;
        if (gender === "Nam"){
        	document.getElementById("editGender").selectedIndex = 0
        } else {
        	document.getElementById("editGender").selectedIndex = 1
        }
        var parts = dob.split("-");
        const day = parseInt(parts[0],10);
        const month = parseInt(parts[1],10);
        const year = parseInt(parts[2],10);
        
        document.getElementById('editDay').value = day;
        document.getElementById('editMonth').value = month;
        document.getElementById('editYear').value = year;
        document.getElementById('editCCCD').value = cccd;
        document.getElementById('editAddress').value = address;
        document.getElementById('editPhone').value = phone;
        document.querySelector('.khachhang_id-edit').value = khachhang_id
        
        document.querySelector('.khachhang_id').value = khachhang_id;
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

const select_makh = document.querySelector('#select_makh');
select_makh.addEventListener('change', function() {
	document.querySelector('#form_makh').submit()
});


</script>
</body>
</html>