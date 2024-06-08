<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý đồng hồ điện khách hàng</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
	integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<!-- Bootstrap Icons -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.1/font/bootstrap-icons.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script src="https://npmcdn.com/flatpickr/dist/l10n/vn.js"></script>
<script> <%@include file="/paging/jquery.twbsPagination.js" %></script>

</head>

<body>
<style><%@include file="/WEB-INF/resource/assets/css/style-quanly.css"%></style>


	<div class="container mt-5" style="margin-top: 0px !important">
		<form action="/Tinhtiendien/nhan_vien/quan_ly_dong_ho_dien_khach_hang/tim_kiem" class="custom-form" method="GET">
		<div style="margin:10px 0">
			<div style="display:inline-block; width:30%; margin:10px 10px 10px 0">
				<label for="field1" class="form-label"  style="display:block">Mã khách hàng</label>
			    <input type="text" class="form-control" name="kh_id" value="${search_id_khachhang}">
			</div>
			<div style="display:inline-block; width:30%; margin:10px">
			    <label for="field1" class="form-label"  style="display:block">Mã đồng hồ</label>
		    	<input type="text" class="form-control" name="dh_id" value="${search_id_dongho}" >
			</div>
			
			<button type="submit" class="btn btn-primary submit-btn" style="margin:10px; width:91px">Tìm kiếm</button>
			<button type="submit" class="btn btn-primary submit-btn" name ="all" value="search_all" style="margin:10px; width:91px">Tất cả</button>
		</div>
		</form>
		<div id="thong_bao" class="alert alert-success" role="alert" style="margin-top: 20px; display: none">${tb}</div>
		<div id="thong_bao_rong" class="alert alert-danger" role="alert" style="margin-top: 20px; display: none">${tb_rong}</div>
		
		<div style="display: flex; justify-content: center">
			<form
				action="/Tinhtiendien/nhan_vien/quan_ly_dong_ho_dien_khach_hang"
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
							Quản lý <b>Đồng hồ điện</b>
						</h2>
					</div>
				</div>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>STT</th>
						<th>Mã đồng hồ</th>
						<th>Mã khách hàng</th>
						<th>Ngày đăng kí</th>
						<th style="width: 400px;"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dh" varStatus="i" items="${list_dh}">
						<tr style="background-color: white">
							<th scope="row">${i.index + 1}</th>
							<td>${dh.dongho_id}</td>
							<td>${dh.khachhang_id}</td>
							<td><fmt:formatDate value="${dh.ngay_dangky}"
									pattern="dd-MM-yyyy" /></td>
							<td style="display: flex; justify-content: center; gap: 20px;">
								<button type="button" class="btn btn-primary btn-sm"
									data-bs-toggle="modal" data-bs-target="#editEmployeeModal"
									onclick="setSelectForm('editForm','${dh.khachhang_id}')">
									<i class="bi bi-pencil-fill"></i> Chỉnh sửa
								</button>
								<button type="button" class="btn btn-success btn-sm"
									data-bs-toggle="modal" data-bs-target="#deleteEmployeeModal"
									onclick="setSelectForm('renewForm','${dh.khachhang_id}')">
									<i class="bi bi-pencil-fill"></i> Cập nhập đồng hồ
								</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<form method="POST">
				<input type="hidden" id="khachHangId" name="khachHangId" value="">
				<input type="hidden" id="actionForm" name="action" value="">


				<!-- Edit Employee Modal -->
				<div class="modal fade" id="editEmployeeModal" tabindex="-1"
					aria-labelledby="editEmployeeModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="editEmployeeModalLabel">Thay
									đổi ngày đăng kí</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<div class="mb-3">
									<h5 style="margin-bottom:15px; font-weight:600" id="khachHangId_dang_dung"></h5> 
									<!-- <label class="form-label">Lựa chọn chỉnh sửa</label>  -->
								<!-- 	<select
										id="editOption" class="form-select mb-3">
										<option value="both">Chỉnh sửa cả ngày và mã khách
											hàng</option>
										<option value="ngay_dangky">Chỉnh sửa ngày đăng kí</option>
										<option value="khachhang_id">Chỉnh sửa mã khách hàng</option>
									</select> -->
								</div>
								<!-- Edit employee form -->

								<%-- <div class="mb-3" id="doiMa">
									<label class="form-label">Mã khách hàng</label> <input
										type="text" class="form-control" name="doiMKH" id="doiMKH">
									<h6 style="color: red; padding-left: 5px; padding-top: 5px"
										id="doiMKH">${tbDoiMKH}</h6>
								</div> --%>
								<div class="mb-3" id="doiNgay">
									<label class="form-label">Ngày đăng kí</label>
									<!-- <input type="text"
										class="form-control" name="newPass" id="doiNDK"> -->
									<div class="date">
										<i class="fa-solid fa-calendar-days calendar-icon"
											id="icon_lich"></i> <input type="text"
											class="form-control doi_ngayDK" name="doiNDK" id="doiNDK">
									</div>
									<h6 style="color: red; padding-left: 5px; padding-top: 5px"
										id="doiNDK">${tbDoiNDK}</h6>
								</div>

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Huỷ</button>
								<button type="submit" class="btn btn-primary">Chỉnh sửa</button>
							</div>
						</div>
					</div>
				</div>

				<!-- Renew Employee Modal -->
				<div class="modal fade" id="deleteEmployeeModal" tabindex="-1"
					aria-labelledby="deleteEmployeeModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="deleteEmployeeModalLabel">Cập nhập đồng hồ</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<p id="renewDH"></p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Huỷ</button>
								<button type="submit" class="btn btn-success">Xác nhận</button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div style="display: flex; justify-content: center">
			<c:choose>
				<c:when test="${not empty search_id_khachhang || not empty search_id_dongho}">
					<form
						action="/Tinhtiendien/nhan_vien/quan_ly_dong_ho_dien_khach_hang/tim_kiem"
						class="custom-form" id="submitPage" method="GET">
						<nav aria-label="Page navigation">
							<ul class="pagination" id="pagination"></ul>
						</nav>
						<input type="hidden" id="cur_page" name="cur_page"
							value="${curr_page}"> <input type="hidden" id="limit"
							name="limit" value="${total_page}">
						<input type="hidden"
							name="kh_id" value="${search_id_khachhang}">
						<input type="hidden"
							name="dh_id" value="${search_id_dongho}">
					</form>
				</c:when>
				<c:otherwise>
					<form
						action="/Tinhtiendien/nhan_vien/quan_ly_dong_ho_dien_khach_hang"
						class="custom-form" id="submitPage" method="GET">
						<nav aria-label="Page navigation">
							<ul class="pagination" id="pagination"></ul>
						</nav>
						<input type="hidden" id="cur_page" name="cur_page"
							value="${curr_page}"> <input type="hidden" id="limit"
							name="limit" value="${total_page}">
						 
					</form>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
<script>

		
/* 	$('#editOption').change(function() {
		var selectedOption = $(this).val();
		if (selectedOption === 'both') {
			$('#doiMa').show();
			$('#doiNgay').show();
		} else if (selectedOption === 'ngay_dangky') {
			$('#doiMa').hide();
			$('#doiNgay').show();
		} else if (selectedOption === 'khachhang_id') {
			$('#doiMa').show();
			$('#doiNgay').hide();
		}
	}); */

	var datepickerSua = flatpickr(".doi_ngayDK", {
		dateFormat : "d-m-Y",
		locale : "vn",
		onClose : function(selectedDates, dateStr, instance) {
			console.log(dateStr);
			document.getElementById('doiNDK').value = dateStr;
		}
	});
	
	// Lấy đối tượng icon lịch
	var calendarIconSua = document.getElementById('icon_lich');
	// Thêm event listener cho icon lịch
	calendarIconSua.addEventListener('click', function() {
		// Mở Flatpickr khi icon lịch được click
		datepickerSua.open();
	});
	


	function setSelectForm(form, khachHangId) {
		document.getElementById("khachHangId").value = khachHangId;
		document.getElementById("khachHangId_dang_dung").textContent = "Chỉnh sửa ngày đăng kí đồng hồ của khách hàng: "+khachHangId;
		document.getElementById("renewDH").textContent = "Bạn có muốn cập nhập đồng hồ của khách hàng: "+khachHangId+"?";
		sessionStorage.setItem("formTypeDH", form);
		sessionStorage.setItem("khachHangId", khachHangId);
		console.log(sessionStorage.getItem("formTypeDH"));
		console.log(sessionStorage.getItem("khachHangId"));
		var formTypeDH = sessionStorage.getItem("formTypeDH");
		if (formTypeDH === "editForm") {
			document.getElementById("actionForm").value = 'edit';
		} else if (formTypeDH === "renewForm") {
			document.getElementById("actionForm").value = 'renew';
		}
	
	}
	

	document.addEventListener("DOMContentLoaded", function() {
		var message = "${tb}";
		var message_rong = "${tb_rong}";
		console.log(message);
	 /*    var doiMKHValue = sessionStorage.getItem("doiMKH"); */
	    var doiNDKValue = sessionStorage.getItem("doiNDK");
	        
		
		/* document.querySelector('input[name="doiMKH"]').value = doiMKHValue; */
		document.querySelector('input[name="doiNDK"]').value = doiNDKValue;
		if (message_rong && message_rong.trim() !== "") {
			
			var tb = document.getElementById('thong_bao_rong');
			tb.style.display = 'block'; // Hiển thị thông báo

	        // Ẩn thông báo sau 3 giây
	        setTimeout(function() {
	        	tb.style.display = 'none';
	        }, 3000);
			
	
		}
		
		if (message && message.trim() !== "") {
			// Hiển thị thông báo
			var tb = document.getElementById('thong_bao');
				tb.style.display = 'block'; // Hiển thị thông báo

		        // Ẩn thông báo sau 3 giây
		        setTimeout(function() {
		        	tb.style.display = 'none';
		        }, 3000);
			
			sessionStorage.removeItem("formTypeDH");
		
		/* 	sessionStorage.removeItem("doiMKH"); */
			sessionStorage.removeItem("doiNDK");
		} else {
			var formTypeDH = sessionStorage.getItem("formTypeDH");
			var khachHangId_dang_chon = sessionStorage.getItem("khachHangId");
			console.log(sessionStorage.getItem("formTypeDH"));
			console.log(sessionStorage.getItem("khachHangId"));
			document.getElementById("khachHangId_dang_dung").textContent = "Chỉnh sửa ngày đăng kí của khách hàng: "+ khachHangId_dang_chon;
			document.getElementById("renewDH").textContent = "Bạn có muốn cập nhập đồng hồ của khách hàng: "+khachHangId_dang_chon+"?";
			if (formTypeDH === "editForm") {
				document.getElementById("actionForm").value = 'edit';
			} else if (formTypeDH === "renewForm") {
				document.getElementById("actionForm").value = 'renew';
			}
	       
		 	
	        

			if (formTypeDH === "editForm") {
				// Hiển thị form chỉnh sửa khi session storage có giá trị "editForm"
				$('#editEmployeeModal').modal('show');
			} else if (formTypeDH === "renewForm") {
				// Hiển thị form xoá khi session storage có giá trị "deleteForm"
				$('#deleteEmployeeModal').modal('show');
			} 
		}

		// Gắn sự kiện hidden.bs.modal cho tất cả các modal
		$('.modal').on('hidden.bs.modal', function() {
			sessionStorage.removeItem("formTypeDH");
			sessionStorage.removeItem("doiNDK");
			
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

		/*============================ THÔNG BÁO NHẬP DỮ LIỆU ====================================*/

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
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>


</html>