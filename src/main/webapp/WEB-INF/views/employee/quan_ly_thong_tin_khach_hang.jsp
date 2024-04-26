<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
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

	<div class="container mt-5">
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
                        <h5 class="modal-title" id="editEmployeeModalLabel">Chỉnh sửa khách hàng</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Edit employee form -->
                        <form action = "quan_ly_thong_tin_nguoi_dung/sua" method="POST" id ="editCustomer">
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
				                		<c:forEach var = "i" begin = "1900" end = "2024">
				                    		<option value="${i}">${i}</option>
				                    	</c:forEach>
		                				</select>
								    </div>
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
</script>
</body>
</html>