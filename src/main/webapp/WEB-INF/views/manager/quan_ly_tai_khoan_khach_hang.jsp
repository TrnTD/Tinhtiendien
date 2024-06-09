
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý tài khoản khách hàng</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap Icons -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.1/font/bootstrap-icons.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script> <%@include file="/paging/jquery.twbsPagination.js" %></script>
</head>
<body>

<style><%@include file="/WEB-INF/resource/assets/css/style-quanly.css"%></style>

	<div class="container mt-5" style="margin-top: 0px !important">
		<div id="thong_bao" class="alert alert-success" role="alert" style="margin-top: 20px; display: none">${tb}</div>
		<div id="thong_bao_err" class="alert alert-danger" role="alert" style="margin-top: 20px; display: none">${tb_err}</div>
	
		<form action="/Tinhtiendien/quan_ly/quan_ly_tai_khoan_khach_hang/tim_kiem" class="custom-form" method="GET">
		<div style="margin:10px 0">
			<label for="field1" class="form-label"  style="display:block">Mã khách hàng</label>
		    <input type="text" class="form-control" name="kh_id" value="${search_id}" style="display:inline-block; width:40%">
			<button type="submit" class="btn btn-primary submit-btn" style="margin:10px; width:91px">Tìm kiếm</button>
			<button type="submit" class="btn btn-primary submit-btn" name ="all" value="search_all" style="margin:10px; width:91px">Tất cả</button>
		</div>
		</form>
		<div id="thong_bao_rong" class="alert alert-danger" role="alert" style="margin-top: 20px; display: none">${tb_rong}</div>
		
		<div style="display: flex; justify-content: center">
			<form
				action="/Tinhtiendien/quan_ly/quan_ly_tai_khoan_khach_hang/"
				class="custom-form" id="submitPage" method="GET">
				<nav aria-label="Page navigation">
					<ul class="pagination" id="pagination"></ul>
				</nav>
				<input type="hidden" id="cur_page" name="cur_page"
					value="${curr_page}"> <input type="hidden" id="limit"
					name="limit" value="${total_page}">
				 
			</form>
		</div>
		
		<div class="table-wrapper" style="margin-top: 0px !important; padding-bottom: 0px; box-shadow: rgba(0, 0, 0, 0.5) 0px 5px 15px;">
			<div class="table-title">
				<div class="row">
					<div class="col-md-6">
						<h2>
							Quản lý <b>Tài khoản</b>
						</h2>
					</div>
					<div class="col-md-6 text-md-end">
						<button type="button" class="btn btn-success"
							data-bs-toggle="modal" data-bs-target="#addEmployeeModal"
							onclick="setSelectForm('addForm','')">
							<i class="material-icons">&#xE147;</i> <span>Thêm tài
								khoản</span>
						</button>
					</div>
				</div>
			</div>
				<c:choose>
					<c:when test="${empty list_acc}">
						<div style="display: flex; justify-content: center; align-items: center;">
					     	<%@include file="/WEB-INF/resource/assets/imgs/nodata.svg"%>
				     	</div>
				     	<div style="display: flex; justify-content: center; align-items: center; margin-top: 10px;">
							<p>Không có dữ liệu</p>				     	
				     	</div>
					</c:when>
					<c:otherwise>
						<table class="table table-hover">
						<thead>
							<tr>
								<th style="width: 150px;">STT</th>
								<th style="width: 200px;">Mã khách hàng</th>
								<th style="width: 200px;">Tên đăng nhập</th>
								<th>Mật khẩu</th>
								<th style="width: 300px;"></th>
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
										onclick="setSelectForm('editForm', '${acc.username}')">
										<i class="bi bi-pencil-fill"></i> Chỉnh sửa
									</button>
									<button type="button" class="btn btn-danger btn-sm"
										data-bs-toggle="modal" data-bs-target="#deleteEmployeeModal"
										onclick="setSelectForm('deleteForm', '${acc.username}')">
										<i class="bi bi-trash-fill"></i> Xoá
									</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					</table>
					</c:otherwise>
				</c:choose>
			<form method="POST">
				<input type="hidden" id="selectedUsername" name="selectedUsername"
					value=""> 
				<input type="hidden" id="actionForm" name="action" value="">
				<input type="hidden" id="actionForm" name="action" value="">


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
									<label class="form-label">Mã khách hàng</label> <input
										type="text" class="form-control" name="addUsernameId"
										id="themMKH">
									<h6 style="color: red; padding-left: 5px; padding-top: 5px"
										id="themMKH">${tbThemMKH}</h6>
								</div>
								<div class="mb-3">
									<label class="form-label">Tên đăng nhập</label> <input
										type="text" class="form-control" name="addUsername"
										id="themTK">
									<h6 style="color: red; padding-left: 5px; padding-top: 5px"
										id="themTK">${tbThemTK}</h6>
									<i>Tên đăng nhập hợp lệ chỉ chứa chữ in hoa (A-Z), chữ in
										thường (a-z) và chữ số (0-9), không bao gồm tiếng Việt có dấu.
										Ví dụ: User12</i>
								</div>
								<div class="mb-3">
									<label class="form-label">Mật khẩu </label> <input type="text"
										class="form-control" name="addPassWord" id="themMK">
									<h6 style="color: red; padding-left: 5px; padding-top: 5px"
										id="themMK">${tbThemMK}</h6>
									<i>Mật khẩu bao gồm tối thiểu 06 ký tự, có ít nhất một chữ
										in hoa (A-Z), một chữ in thường (a-z), một chữ số (0-9) và một
										ký tự đặc biệt (#?.!@$%^&*-). Ví dụ: Matkhau@123</i>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Huỷ</button>
								<button type="submit" class="btn btn-primary">Thêm</button>
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
									<label class="form-label">Mật khẩu</label> <input type="text"
										class="form-control" name="newPass" id="doiMK">
									<h6 style="color: red; padding-left: 5px; padding-top: 5px"
										id="doiMK">${tbDoiMK}</h6>
									<i>Mật khẩu bao gồm tối thiểu 06 ký tự, có ít nhất một chữ
										in hoa (A-Z), một chữ in thường (a-z), một chữ số (0-9) và một
										ký tự đặc biệt (#?.!@$%^&*-). Ví dụ: Matkhau@123</i>
								</div>

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Huỷ</button>
								<button type="submit" class="btn btn-primary">Đổi mật
									khẩu</button>
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
								<button type="submit" class="btn btn-danger">Xác nhận</button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		
		<div style="display: flex; justify-content: center">
			<c:choose>
				<c:when test="${not empty search_id}">
					<form
						action="/Tinhtiendien/quan_ly/quan_ly_tai_khoan_khach_hang/tim_kiem"
						class="custom-form" id="submitPage" method="GET">
						<nav aria-label="Page navigation">
							<ul class="pagination" id="pagination"></ul>
						</nav>
						<input type="hidden" id="cur_page" name="cur_page"
							value="${curr_page}"> <input type="hidden" id="limit"
							name="limit" value="${total_page}">
						<input type="hidden"
							name="kh_id" value="${search_id}">
					</form>
				</c:when>
				<c:otherwise>
					
				</c:otherwise>
			</c:choose>
		</div>


	</div>

	<%-- 	<%
	String tb = (String) session.getAttribute("tbao");
	%>
 --%>
	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		function setSelectForm(form, username) {
			document.getElementById("selectedUsername").value = username;
			sessionStorage.setItem("formType", form);
			console.log(sessionStorage.getItem("formType"));
			var formType = sessionStorage.getItem("formType");
			  if (formType === "editForm") {
				  document.getElementById("actionForm").value = 'edit';
		        } else if (formType === "deleteForm") {
		        	document.getElementById("actionForm").value = 'delete';
		        } else if (formType === "addForm") {
		        	document.getElementById("actionForm").value = 'add';
		        }
		}
		
		window.addEventListener('beforeunload', function() {
            sessionStorage.setItem("addUsernameId", document.querySelector('input[name="addUsernameId"]').value);
            sessionStorage.setItem("addUsername", document.querySelector('input[name="addUsername"]').value);
            sessionStorage.setItem("addPassWord", document.querySelector('input[name="addPassWord"]').value);
            sessionStorage.setItem("newPass", document.querySelector('input[name="newPass"]').value);
            sessionStorage.setItem("selectedUsername", document.querySelector('input[name="selectedUsername"]').value);
		});

		document.addEventListener("DOMContentLoaded", function() {
			var message = "${tb}";
			var message_err = "${tb_err}";
			var message_rong = "${tb_rong}";
			console.log(message_rong);
			
			if (message_rong && message_rong.trim() !== "") {
				
				var tb = document.getElementById('thong_bao_rong');
				tb.style.display = 'block'; // Hiển thị thông báo

		        // Ẩn thông báo sau 3 giây
		        setTimeout(function() {
		        	tb.style.display = 'none';
		        }, 3000);
				

			}
			if (message && message.trim() !== "") {
				
				var tb = document.getElementById('thong_bao');
				tb.style.display = 'block'; // Hiển thị thông báo

		        // Ẩn thông báo sau 3 giây
		        setTimeout(function() {
		        	tb.style.display = 'none';
		        }, 3000);
				
				// Xoá session
				sessionStorage.removeItem("formType");
				sessionStorage.removeItem("addUsernameId");
				sessionStorage.removeItem("addUsername");
				sessionStorage.removeItem("addPassWord");
				sessionStorage.removeItem("newPass");
			}
			else if (message_err && message_err.trim() !== "") {
				
				var tb_err = document.getElementById('thong_bao_err');
				tb_err.style.display = 'block'; // Hiển thị thông báo

		        // Ẩn thông báo sau 3 giây
		        setTimeout(function() {
		        	tb_err.style.display = 'none';
		        }, 3000);
				
				// Xoá session
				sessionStorage.removeItem("formType");
				sessionStorage.removeItem("addUsernameId");
				sessionStorage.removeItem("addUsername");
				sessionStorage.removeItem("addPassWord");
				sessionStorage.removeItem("newPass");
			}
			else {
					var formType = sessionStorage.getItem("formType");
					if (formType === "editForm") {
						  document.getElementById("actionForm").value = 'edit';
				        } else if (formType === "deleteForm") {
				        	document.getElementById("actionForm").value = 'delete';
				        } else if (formType === "addForm") {
				        	document.getElementById("actionForm").value = 'add';
				        }
					
					
					var addUsernameIdValue = sessionStorage.getItem("addUsernameId");
			        var addUsernameValue = sessionStorage.getItem("addUsername");
			        var addPassWordValue = sessionStorage.getItem("addPassWord");
			        var newPassValue = sessionStorage.getItem("newPass"); 
			        var selectedUsernameValue = sessionStorage.getItem("selectedUsername"); 
			      
			        
				 	document.querySelector('input[name="addUsernameId"]').value = addUsernameIdValue;
			        document.querySelector('input[name="addUsername"]').value = addUsernameValue;
			        document.querySelector('input[name="addPassWord"]').value = addPassWordValue;
			        document.querySelector('input[name="newPass"]').value = newPassValue;
			        document.querySelector('input[name="selectedUsername"]').value = selectedUsernameValue;
				if (formType === "editForm") {
					// Hiển thị form chỉnh sửa khi session storage có giá trị "editForm"
					$('#editEmployeeModal').modal('show');
				} else if (formType === "deleteForm") {
					// Hiển thị form xoá khi session storage có giá trị "deleteForm"
					$('#deleteEmployeeModal').modal('show');
				} else if (formType === "addForm") {
					$('#addEmployeeModal').modal('show');
				}
			}
			
			// Gắn sự kiện hidden.bs.modal cho tất cả các modal
			$('.modal').on('hidden.bs.modal', function () {
			    sessionStorage.removeItem("formType");
				sessionStorage.removeItem("addUsernameId");
				sessionStorage.removeItem("addUsername");
				sessionStorage.removeItem("addPassWord");
				sessionStorage.removeItem("newPass");
				sessionStorage.removeItem("selectedUsernameValue");
				
			    let headers = document.querySelectorAll('h6');
	            headers.forEach(function(header) {
	                header.textContent = '';
					var inputId = header.id;
		            // Tìm ô input tương ứng bằng id của thẻ h6
		            var inputElement = document.getElementById(inputId); 
		            inputElement.classList.remove('error-input');
		            inputElement.value = "";

	            });
			    
			});
			
/* ============================ THÔNG BÁO NHẬP DỮ LIỆU =====================================	 */																
			var errorMessages = document.querySelectorAll('h6');
		    // Duyệt qua mỗi thông báo lỗi
		    errorMessages.forEach(function(errorMessage) {
		        // Kiểm tra nếu thông báo không rỗng
		        if (errorMessage.textContent.trim() !== "") {
		            // Lấy id của thẻ h6
		            var inputId = errorMessage.id;
		            
		            // Tìm ô input tương ứng bằng id của thẻ h6
		            var inputElement = document.getElementById(inputId); 
		            
		            // Kiểm tra xem ô input có tồn tại không
		            if (inputElement) {
		                // Thêm lớp CSS 'error-input' cho ô input
		                inputElement.classList.add('error-input');
		            }
		        }
		    });
			
		});
/* ============================ Phân trang =====================================	 */	
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
