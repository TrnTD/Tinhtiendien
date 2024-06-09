<%@page import="com.Tinhtiendien.Models.ChiTietHoaDon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hóa đơn chưa thanh toán</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.1/font/bootstrap-icons.css" rel="stylesheet">
</head>
<style>

}
	
</style>
<body>
<style>
	thead tr {
		background-color: #e8ecfc;
		
		th {
			color: #063bc0;
		}
	}

	th {
		font-size: 16px;
		text-align: center;
	}
	
	td {
		font-weight: 500;
		text-align: center;
	}
.payment-method {
  display: flex;
  gap: 10px; /* Khoảng cách giữa các nút, có thể tùy chỉnh */
}

.payment-method button {
  flex: 1;
  padding: 10px; /* Điều chỉnh padding nếu cần */
  font-size: 16px; /* Điều chỉnh kích thước chữ nếu cần */
  border: none;
  cursor: pointer;
}

.payment-method .bank {
  background-color: #28a745; /* Màu nền nút Ngân Hàng */
  color: white;
}

.payment-method .e-wallet {
  background-color: #ffc107; /* Màu nền nút Ví Điện Tử */
  color: white;
}
</style>
	<div class="content">
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
  		<div class="container" style="width:1000px">
			<h2 style="color:#f6621c; margin-bottom: 20px;">Thông tin hóa đơn chưa thanh toán</h2>
	        
	        <table class="table table-hover" style="background-color: white;" box-shadow: rgba(0, 0, 0, 0.5) 0px 5px 15px;>
	          <thead>
	            <tr>
	              <th>Mã hóa đơn</th>
	              <th>Tháng</th>
	              <th>Năm</th>
	              <th>Chỉ số cũ</th>
	              <th>Chỉ số mới</th>
	              <th>Kwh tiêu thụ</th>
	              <th>Thành tiền</th>
	              <th>Hoạt động</th>
	            </tr>
	          </thead>
	          <tbody>
				<c:forEach var="hoadon" items="${list_hoadon}">
				    <tr>
				        <td>${hoadon.hoadon_id}</td>
				        <td>${hoadon.month_bill}</td>
				        <td>${hoadon.year_bill}</td>
				        <td>${hoadon.chiso_cu}</td>
				        <td>${hoadon.chiso_moi}</td>
				        <td>${hoadon.dien_tieu_thu}</td>
				        <td><fmt:formatNumber value="${hoadon.tong_tien}" pattern="#,###"/></td>
						<td>
						<button type="button" class="btn btn-primary btn-sm btn-thanhtoan" data-bs-toggle="modal" data-bs-target="#editEmployeeModal"><i class="bi bi-pencil-fill"></i> Thanh toán</button>
						</td>				    
				    </tr>
				</c:forEach>
	          </tbody>
	        </table>
	        <c:if test="${empty list_hoadon}">
		     	<div style="display: flex; justify-content: center; align-items: center;">
			     	<%@include file="/WEB-INF/resource/assets/imgs/nodata.svg"%>
		     	</div>
		     	<div style="display: flex; justify-content: center; align-items: center; margin-top: 10px;">
					<p>Không có dữ liệu</p>				     	
		     	</div>
			</c:if>     
		</div>
	</div>
	
	<!-- Edit Employee Modal -->
        <div class="modal fade" id="editEmployeeModal" tabindex="-1" aria-labelledby="editEmployeeModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editEmployeeModalLabel">Thanh Toán</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Edit employee form -->
                        <form id="paymentForm" action = "/Tinhtiendien/nguoi_dung/thong_tin_hoa_don_chua_thanh_toan/thanh_toan" method="POST" id ="editYC">
                            <div class="mb-3">
                                <label for="editTittle" class="form-label">Phương thức</label>
                                <br>
                                <div class="payment-method">
                               	<button type="button" class="btn btn-outline-primary bank" onclick = "clickBank()"> Ngân Hàng</button>
                            	<button type="button" class="btn btn-outline-danger e-wallet" onclick = "clickEWallet()"> Ví Điện Tử</button>
                            </div>	
                            </div>
                            <input type="hidden" class="phuong_thuc" name="phuong_thuc" value="">
                            <input type="hidden" class="hoadon_id_thanh_toan" name="hoadon_id" value="">
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    </div>
                </div>
            </div>
        </div>
<script>
const ttButtons = document.querySelectorAll('.btn-thanhtoan');

function clickBank(){
	document.querySelector('.phuong_thuc').value = '2'
	console.log(document.querySelector('.phuong_thuc').value)
	document.getElementById('paymentForm').submit();
}

function clickEWallet(){
	document.querySelector('.phuong_thuc').value = '3'
	console.log(document.querySelector('.phuong_thuc').value)
	document.getElementById('paymentForm').submit();
}

ttButtons.forEach((button) => {
    button.addEventListener('click', (event) => {

        const row = event.target.closest('tr'); // Lấy hàng bảng chứa nút "Edit"
        const hoadon_id = row.querySelector('td:nth-child(1)').textContent;
     
        // Điền giá trị vào các trường input trong modal
        document.querySelector('.hoadon_id_thanh_toan').value = hoadon_id
        
        console.log("hoa don id: " + document.querySelector('.hoadon_id_thanh_toan').value);
    });
});
</script>
</body>
</html>