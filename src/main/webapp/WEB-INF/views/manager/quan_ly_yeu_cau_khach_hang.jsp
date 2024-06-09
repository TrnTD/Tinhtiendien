<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Quản lý yêu cầu</title>
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.1/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
<style><%@include file="/WEB-INF/resource/assets/css/style-quanly.css"%></style>
<!-- 	================================================= -->

	<div class="container">
		<h2 style="color:#f6621c; margin-bottom: 20px;">Tìm Yêu Cầu</h2>
		<form action = "/Tinhtiendien/quan_ly/quan_ly_yeu_cau_khach_hang/tim_kiem" method="GET">
	  <div class="row mb-3">
		        <div class="col">
		            <label for="field1" class="form-label">Mã khách hàng</label>
		            <input type="text" class="form-control" id="field1" name="search_khachhang_id">
		        </div>
		        <div class="col">
		            <label for="field2" class="form-label">Ngày gửi</label>
		            <input type="date" class="form-control" id="field2"  min="01-01-1990" max="31-12-2024" name="search_ngay_gui">
		        </div>
		        <div class="col">
		            <label for="field3" class="form-label">Tựa đề</label>
		            <input type="text" class="form-control" id="field3" name="search_tua_de">
		        </div>
		        <div class="col">
		            <label for="field4" class="form-label">Trạng thái</label>
		            <select class="form-select" id="field4" aria-label="Default select example" name="search_trang_thai">
								  <option value="" selected>Không</option>
								  <option value="1">Đã gửi</option>
								  <option value="2">Đã tiếp nhận</option>
								  <option value="3">Đang xử lý</option>
								  <option value="4">Hoàn thành</option>
								  <option value="5">Từ chối</option>
								</select>
		        </div>
		        </div>
				<button style="float: right;" type="submit" class="btn btn-primary submit-btn">Tìm kiếm</button>
		</form>
			<form action="/Tinhtiendien/quan_ly/quan_ly_yeu_cau_khach_hang" method="GET">
				<button style="float: right; margin-right: 5px;" type="submit" class="btn btn-primary submit-btn">Tất Cả</button>
			</form>
	<br>
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
                        <h2>Quản lý <b>Yêu Cầu</b></h2>
                    </div>
                </div>            
            </div>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Ngày gửi</th>
                    	<th>Giờ gửi</th>
                    	<th>Mã yêu cầu </th>
                    	<th>Mã khách hàng gửi</th>
                        <th>Tựa đề</th>
                        <th>Nội dung</th>
                        <th>Trạng thái</th>
                        <th>Sửa trạng thái</th>
                        <th>Chi tiết </th>
                    </tr>
                </thead>
                <tbody>
                  <c:forEach var="yc" varStatus="i" items="${listYC}">
			      <tr>
			      	<td><fmt:formatDate value="${yc.ngay_gui}" pattern="dd-MM-yyyy"/></th>
			      	<td>${yc.gio_gui}</th>
			      	<td>${yc.yeucau_id}</td>
			      	<td>${yc.khachhang_id}</td>
			      	<td>${yc.tua_de}</td>
			        <td>${yc.noi_dung_rut_gon}</td>
			        <th>${yc.trang_thai} </th>
			        <td><button type="button" class="btn btn-primary btn-sm btn-edit" data-bs-toggle="modal" data-bs-target="#editEmployeeModal"><i class="bi bi-pencil-fill"></i> Sửa</button></td>
			        <td>
			        <input type="hidden" class="content" name="content" value="${yc.noi_dung}">
			        <button type="button" class="btn btn-info btn-sm btn-view" data-bs-toggle="modal" data-bs-target="#viewEmployeeModal"><i class="bi bi-info-circle-fill"></i> Xem</button></td>
			      </tr>
			      </c:forEach>    
                </tbody>
            </table>
        </div>
        </div>
        
        <!-- Edit Employee Modal -->
        <div class="modal fade" id="editEmployeeModal" tabindex="-1" aria-labelledby="editEmployeeModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editEmployeeModalLabel">Chỉnh sửa trạng thái yêu cầu</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Edit employee form -->
                        <form action = "/Tinhtiendien/quan_ly/quan_ly_yeu_cau_khach_hang/sua_tt" method="POST" id ="editTT">
                            <div class="mb-3">
                                <label for="editTT" class="form-label">Trạng thái</label>
                                <select class="form-select" id="editTT" aria-label="Default select example" name="trangthai" required>
								  <option value="" disabled selected hidden>Trạng thái</option>
								  <option value="2">Đã tiếp nhận</option>
								  <option value="3">Đang xử lý</option>
								  <option value="4">Hoàn thành</option>
								  <option value="5">Từ chối</option>
								</select>
                            </div>
                             <input type="hidden" class="yeucau_id-edit" name="yeucau_id" value="">
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                        <button type="submit" form="editTT" class="btn btn-primary">Lưu thay đổi</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- View Employee Modal -->
        <div class="modal fade" id="viewEmployeeModal" tabindex="-1" aria-labelledby="viewEmployeeModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="viewEmployeeModalLabel">Xem chi tiết yêu cầu</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- View employee form -->
                      
                            <div class="mb-3">
                                <label for="editTittle" class="form-label">Tựa đề</label>
                                <input type="text" class="form-control" id="viewTittle" name ="tittle" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="editContent" class="form-label">Nội dung</label>
                                 <textarea class="form-control" id="viewContent" rows="6" name = "content" readonly></textarea>
                                <h6 style="color:red; padding-left: 5px; padding-top: 5px;" id="editEmail">${err_mess_editEmail}</h6>
                            </div>
                        
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    </div>
                </div>
            </div>
        </div>
<script>
//Lấy tất cả các nút "Edit"
const editButtons = document.querySelectorAll('.btn-edit');
const viewButtons = document.querySelectorAll('.btn-view');
// Thêm sự kiện click cho mỗi nút "Edit"
editButtons.forEach((button) => {
    button.addEventListener('click', (event) => {

        const row = event.target.closest('tr'); // Lấy hàng bảng chứa nút "Edit"
        const yeucau_id = row.querySelector('td:nth-child(3)').textContent; // Lấy giá trị yêu cầu id
     
        // Điền giá trị vào các trường input trong modal
        document.querySelector('.yeucau_id-edit').value = yeucau_id
        
        console.log("yeu cau id: " + document.querySelector('.yeucau_id-edit').value);
    });
});

viewButtons.forEach((button) => {
    button.addEventListener('click', (event) => {

        const row = event.target.closest('tr'); // Lấy hàng bảng chứa nút "Edit"
        const tittle = row.querySelector('td:nth-child(5)').textContent; // Lấy giá trị tựa đề
        const content_short = row.querySelector('td:nth-child(6)').textContent; // Lấy giá trị nội dung
        const content = row.querySelector('.content').value
     
        // Điền giá trị vào các trường input trong modal
        document.getElementById('viewTittle').value = tittle;
        document.getElementById('viewContent').value = content;
    });
});
</script>
</body>
</html>