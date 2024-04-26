<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý tài khoản</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap Icons -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.1/font/bootstrap-icons.css"
	rel="stylesheet">
<style>
body {
	color: #566787;
	background: #f5f5f5;
	font-family: 'Varela Round', sans-serif;
	font-size: 13px;
}

.table-wrapper {
	background: #fff;
	padding: 20px 25px;
	margin: 30px 0;
	border-radius: 3px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
}

.table-title {
	padding-bottom: 15px;
	background: #435d7d;
	color: #fff;
	padding: 10px 30px;
	margin: -20px -25px 10px;
	border-radius: 3px 3px 0 0;
}

.table-title h2 {
	margin: 5px 0 0;
	font-size: 24px;
}

.table-title .btn-group {
	float: right;
}

.table-title .btn {
	color: #fff;
	float: right;
	font-size: 13px;
	border: none;
	min-width: 50px;
	border-radius: 2px;
	border: none;
	outline: none !important;
	margin-left: 10px;
}

.table-title .btn i {
	float: left;
	font-size: 21px;
	margin-right: 5px;
}

.table-title .btn span {
	float: left;
	margin-top: 2px;
}

table.table tr th:last-child {
	width: 180px;
}

table.table-striped tbody tr:nth-of-type(odd) {
	background-color: #fafafa;
}

table.table-striped.table-hover tbody tr:hover {
	background: #f5f5f5;
}

table.table tr {
	text-align: center;
}

table.table td button {
	display: inline-block;
}

.btn-success {
    background-color: #5cb85c;
}

/* .table-striped>tbody>tr:nth-of-type(odd) {
    --bs-table-accent-bg: none;
    color: var(--bs-table-striped-color);
} */
</style>
</head>


<body>
	<div class="container mt-5">
		<div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-md-6">
						<h2>
							Quản lý <b>Tài khoản</b>
						</h2>
					</div>
					<div class="col-md-6 text-md-end">
						<button type="button" class="btn btn-success"
							data-bs-toggle="modal" data-bs-target="#addEmployeeModal" style="background-color: #5cb85c;">
							<i class="material-icons">&#xE147;</i> <span>Thêm tài
								khoản</span>
						</button>
					</div>
				</div>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th style="width: 150px;">STT</th>
						<th style="width: 200px;">Mã khách hàng</th>
						<th style="width: 200px;">Tên đăng nhập</th>
						<th>Mật khẩu</th>
						<th style="width: 300px;">Chức năng</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="acc" varStatus="i" items="${list_acc}">
						<tr>
							<th scope="row">${i.index + 1}</th>
							<td>${acc.khachhang_id}</td>
							<td>${acc.username}</td>
							<td>${acc.password}</td>
							<td style="display: flex; justify-content: center; gap: 20px;">
								<button type="button" class="btn btn-primary btn-sm"
									data-bs-toggle="modal" data-bs-target="#editEmployeeModal"
									onclick="selectRow('${acc.username}')">
									<i class="bi bi-pencil-fill"></i> Chỉnh sửa
								</button>
								<button type="button" class="btn btn-danger btn-sm"
									data-bs-toggle="modal" data-bs-target="#deleteEmployeeModal"
									onclick="selectRow('${acc.username}')">
									<i class="bi bi-trash-fill"></i> Xoá
								</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<form method="POST">
				<input type="hidden" id="selectedUsername" name="selectedUsername"
					value="">
					
				<!-- Add Employee Modal -->
				<div class="modal fade" id="addEmployeeModal" tabindex="-1"
					aria-labelledby="addEmployeeModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="addEmployeeModalLabel">Thêm tài
									khoản</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<!-- Add employee form -->
						   			<div class="mb-3">
										<label class="form-label">Mã khách hàng</label>
										<input type="text" class="form-control" name="addUsernameId">
									</div>
									<div class="mb-3">
										<label  class="form-label">Tên đăng nhập</label>
										<input type="text" class="form-control" name="addUsername">
									</div>
									<div class="mb-3">
										<label class="form-label">Mật khẩu </label> <input
											type="text" class="form-control" name="addPassWord">
									</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Huỷ</button>
								<button type="submit" class="btn btn-primary" name="action" value="add">Thêm</button>
							</div>
						</div>
					</div>
				</div>

				<!-- Edit Employee Modal -->
				<div class="modal fade" id="editEmployeeModal" tabindex="-1"
					aria-labelledby="editEmployeeModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="editEmployeeModalLabel">Thay
									đổi mật khẩu</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<!-- Edit employee form -->

								<div class="mb-3">
									<label  class="form-label">Mật khẩu</label> <input
										type="text" class="form-control" name="newPass">
								</div>

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Huỷ</button>
								<button type="submit" name="action" value="edit"
									class="btn btn-primary">Đổi mật khẩu</button>
							</div>
						</div>
					</div>
				</div>

				<!-- Delete Employee Modal -->
				<div class="modal fade" id="deleteEmployeeModal" tabindex="-1"
					aria-labelledby="deleteEmployeeModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="deleteEmployeeModalLabel">Xoá
									tài khoản</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<p>Bạn có chắc muốn xoá tài khoản này?</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Huỷ</button>
								<button type="submit" name="action" value="delete"
									class="btn btn-danger">Xác nhận</button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>


	</div>



	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		function selectRow(username) {
			document.getElementById("selectedUsername").value = username;
			console.log(username);
		}
	</script>
</body>
</html>