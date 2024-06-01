<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý giá điên</title>
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
<style><%@include file="/WEB-INF/resource/assets/css/style-quanly.css"%></style>
</head>


<body>
	<div class="container">
		<div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-md-6">
						<h2>
							Quản lý <b>Giá Điện</b>
						</h2>
					</div>
					<div class="col-sm-6 text-sm-end">
						<button type="button" class="btn btn-success"
							data-bs-toggle="modal" data-bs-target="#addEmployeeModal"
							style="background-color: #5cb85c;">
							<i class="material-icons">&#xE147;</i> <span>Thêm giá điện</span>
						</button>
					</div>
				</div>
			</div>
			<table class="table table-hover table-sm">
				<thead>
					<tr>
						<th style="width: 200px;">Bậc Điện</th>
						<th style="width: 200px;">Giá Điện</th>
						<th style="width: 300px;">Chức năng</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="TriSo" varStatus="i" items="${list_giaDien}">
						<tr>
							<td>${TriSo.bacDien}</td>
							<td>${TriSo.giaDien}</td>
							<td style="display: flex; justify-content: center; gap: 20px;">
								<button type="button" class="btn btn-primary btn-sm"
									data-bs-toggle="modal" data-bs-target="#editEmployeeModal"
									onclick="selectRow('${TriSo.bacDien}')">
									<i class="bi bi-pencil-fill"></i> Chỉnh sửa
								</button>
								<button type="button" class="btn btn-danger btn-sm"
									data-bs-toggle="modal" data-bs-target="#deleteEmployeeModal"
									onclick="selectRow('${TriSo.bacDien}')">
									<i class="bi bi-trash-fill"></i> Xoá
								</button>

							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	
	</div>

			<form method="POST">
				<input type="hidden" id="bacDien_key" name="bacDien_key" value="">

				<!-- Add Employee Modal -->
				<div class="modal fade" id="addEmployeeModal" tabindex="-1"
					aria-labelledby="addEmployeeModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="addEmployeeModalLabel">Thêm</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<!-- Add employee form -->
								<div class="mb-3">
									<label class="form-label">Bậc Điện</label> <input type="text"
										class="form-control" name="add_bacDien">
								</div>
								<div class="mb-3">
									<label class="form-label">Giá Điện</label> <input type="text"
										class="form-control" name="add_giaDien">
								</div>

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Huỷ</button>
								<button type="submit" class="btn btn-primary" name="action"
									value="add">Thêm</button>
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
								<h5 class="modal-title" id="editEmployeeModalLabel">Chỉnh
									Sửa</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<!-- Edit employee form -->

								<div class="mb-3">
									<label class="form-label">Giá Tiền</label> <input type="text"
										class="form-control" name="edit_giaDien">
								</div>

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Huỷ</button>
								<button type="submit" name="action" value="edit"
									class="btn btn-primary">Lưu Thay Đổi</button>
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
								<h5 class="modal-title" id="deleteEmployeeModalLabel">Xoá</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<p>Bạn có chắc muốn xoá không?</p>
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

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		function selectRow(bacDien) {
			document.getElementById("bacDien_key").value = bacDien;
			console.log(bacDien);
		}
	</script>
</body>
</html>