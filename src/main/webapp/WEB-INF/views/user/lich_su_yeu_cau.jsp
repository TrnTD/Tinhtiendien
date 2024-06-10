<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Lịch sử yêu cầu</title>
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.1/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
<style><%@include file="/WEB-INF/resource/assets/css/style-quanly.css"%></style>
<!-- 	================================================= -->

	<div class="container">
		<h2 style="color:#f6621c; margin-bottom: 20px;">Gửi Yêu Cầu</h2>
		<form action = "/Tinhtiendien/nguoi_dung/lich_su_yeu_cau/gui" method="POST">
	  <div class="form-group">
	    <label for="exampleFormControlInput1">Tựa đề</label>
	    <input type="text" class="form-control" id="tittle" name="tittle" required>
	  </div>
	  <div class="form-group">
	    <label for="exampleFormControlTextarea1">Nội dung</label>
	    <textarea class="form-control" id="content" rows="6" name = "content" required></textarea>
	  </div>
	  <br>
	  <button style="float: right;" type="submit" class="btn btn-primary submit-btn">Gửi</button>
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
        <div class="table-wrapper" style="box-shadow: rgba(0, 0, 0, 0.5) 0px 5px 15px;">
            <div class="table-title">
                <div class="row">
                    <div class="col-md-6">
                        <h2><b>Lịch Sử Yêu Cầu</b></h2>
                    </div>
                </div>            
            </div>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Ngày gửi</th>
                    	<th>Giờ gửi</th>
                        <th>Tựa đề</th>
                        <th>Nội dung</th>
                        <th>Trạng Thái</th>
                        <th>Chi tiết </th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                  <c:forEach var="lsyc" varStatus="i" items="${listYeuCau}">
			      <tr>
			      	<td><fmt:formatDate value="${lsyc.ngay_gui}" pattern="dd-MM-yyyy"/></th>
			      	<td>${lsyc.gio_gui}</th>
			      	<td>${lsyc.tua_de}</td>
			        <td>${lsyc.noi_dung_rut_gon}</td>
			        <td>${lsyc.trang_thai}</td>
			        <td>
			        <input type="hidden" class="content" name="content" value="${lsyc.noi_dung}">
			        <button type="button" class="btn btn-info btn-sm btn-view" data-bs-toggle="modal" data-bs-target="#viewEmployeeModal"><i class="bi bi-info-circle-fill"></i> Xem</button></td>
			        <td>
			        <c:choose>
			        	<c:when test="${lsyc.trang_thai_id == 1 }">
			        	<input type="hidden" class="yeucau_id" name="yc_id" value="${lsyc.yeucau_id}">
			        	<input type="hidden" class="content" name="content" value="${lsyc.noi_dung}">
			        	<button type="button" class="btn btn-primary btn-sm btn-edit" data-bs-toggle="modal" data-bs-target="#editEmployeeModal"><i class="bi bi-pencil-fill"></i> Sửa</button>
                       	<button type="button" class="btn btn-danger btn-sm btn-delete" data-bs-toggle="modal" data-bs-target="#deleteEmployeeModal"><i class="bi bi-trash-fill"></i> Thu hồi</button>
			        	</c:when>
			        </c:choose>
			        </td>
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
                        <h5 class="modal-title" id="editEmployeeModalLabel">Chỉnh sửa yêu cầu</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Edit employee form -->
                        <form action = "/Tinhtiendien/nguoi_dung/lich_su_yeu_cau/sua" method="POST" id ="editYC">
                            <div class="mb-3">
                                <label for="editTittle" class="form-label">Tựa đề</label>
                                <input type="text" class="form-control" id="editTittle" name ="tittle" required>
                            </div>
                            <div class="mb-3">
                                <label for="editContent" class="form-label">Nội dung</label>
                                 <textarea class="form-control" id="editContent" rows="6" name = "content" required></textarea>
                            </div>
                            <input type="hidden" class="yeucau_id-edit" name="yeucau_id" value="">
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                        <button type="submit" form="editYC" class="btn btn-primary">Lưu thay đổi</button>
                    </div>
                </div>
            </div>
        </div>

<!--         Delete Employee Modal -->
       	<div class="modal fade" id="deleteEmployeeModal" tabindex="-1" aria-labelledby="deleteEmployeeModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteEmployeeModalLabel">Thu hồi yêu cầu</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p>Bạn có chắc muốn thu hồi yêu cầu này không?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                        <form id="formToDelete" action="/Tinhtiendien/nguoi_dung/lich_su_yeu_cau/xoa" method="POST">
                        	<input type="hidden" class="yeucau_id-delete" name="yeucau_id" value="">
                        	<button id="confirmDeleteButton" type="submit" class="btn btn-danger" >Thu hồi</button>
                        </form>
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
                        <form action = "/Tinhtiendien/nhan_vien/quan_ly_thong_tin_khach_hang/sua" method="POST" id ="editCustomer" onsubmit="return validateDateEdit()">
                            <div class="mb-3">
                                <label for="editTittle" class="form-label">Tựa đề</label>
                                <input type="text" class="form-control" id="viewTittle" name ="tittle" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="editContent" class="form-label">Nội dung</label>
                                 <textarea class="form-control" id="viewContent" rows="6" name = "content" readonly></textarea>
                                <h6 style="color:red; padding-left: 5px; padding-top: 5px;" id="editEmail">${err_mess_editEmail}</h6>
                            </div>
                        </form>
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
        const tittle = row.querySelector('td:nth-child(3)').textContent; // Lấy giá trị tựa đề
        const content_short = row.querySelector('td:nth-child(4)').textContent; // Lấy giá trị nội dung
        const yeucau_id = row.querySelector('.yeucau_id').value
        const content = row.querySelector('.content').value
     
        // Điền giá trị vào các trường input trong modal
        document.getElementById('editTittle').value = tittle;
        document.getElementById('editContent').value = content;
        document.querySelector('.yeucau_id-edit').value = yeucau_id
        
        console.log("yeu cau id: " + document.querySelector('.yeucau_id-edit').value);
    });
});

viewButtons.forEach((button) => {
    button.addEventListener('click', (event) => {

        const row = event.target.closest('tr'); // Lấy hàng bảng chứa nút "Edit"
        const tittle = row.querySelector('td:nth-child(3)').textContent; // Lấy giá trị tựa đề
        const content_short = row.querySelector('td:nth-child(4)').textContent; // Lấy giá trị nội dung
        const content = row.querySelector('.content').value
     
        // Điền giá trị vào các trường input trong modal
        document.getElementById('viewTittle').value = tittle;
        document.getElementById('viewContent').value = content;
    });
});

const deleteButtons = document.querySelectorAll('.btn-delete');

deleteButtons.forEach((button) => {
    button.addEventListener('click', (event) => {
    	const row = event.target.closest('tr');

        const yeucau_id = row.querySelector('.yeucau_id').value
        console.log(yeucau_id)

        document.querySelector('.yeucau_id-delete').value = yeucau_id

    });
});

</script>
</body>
</html>
