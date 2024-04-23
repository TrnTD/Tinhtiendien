<%@page import="com.Tinhtiendien.Models.ChiTietHoaDon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="content">
  		<div class="container" style="width:1000px" >
	  		<h2 style="color:#f6621c">Quản lý tài khoản</h2>
		    <div class="row">
		            <!-- Cột 1 -->
		            <div class="col-2">
		                <p><strong>Họ và tên:</strong></p>
		            </div>
		            <!-- Cột 2 -->
		            <div class="col-md-3">
		                <p><span class="left-align" id="hoten">${info_khachhang.hovaten}</span></p>
		            </div>
		            <!-- Cột 3 -->
		            <div class="col-2">
		                <p><strong>Mã khách hàng:</strong></p>
		            </div>
		            <!-- Cột 4 -->
		            <div class="col-md-3">
		                <p><span class="left-align" id="diachi">${info_khachhang.khachhang_id}</span></p>
		            </div>
		        </div>
		        <div class="row">
		            <!-- Cột 5 -->
		            <div class="col-2">
		                <p><strong>Số điện thoại:</strong></p>
		            </div>
		            <!-- Cột 6 -->
		            <div class="col-md-3">
		                <p><span class="left-align" id="mahoadon">${info_khachhang.sdt}</span></p>
		            </div>
		            <!-- Cột 7 -->
		            <div class="col-2">
		                <p><strong>Mã đồng hồ điện:</strong></p>
		            </div>
		            <!-- Cột 8 -->
		            <div class="col-md-3">
		                <p><span class="left-align" id="makh">${ma_dongho}</span></p>
		            </div>
		             
		        </div>
		        <div class="row">
		        	<div class="col-2">
		                <p><strong>Email:</strong></p>
		            </div>
		            <!-- Cột 8 -->
		            <div class="col-md-3">
		                <p><span class="left-align" id="makh">${info_khachhang.email}</span></p>
		            </div>
		        </div>
		        <div class="row">
		        	<div class="col-2">
		                <p><strong>Địa chỉ:</strong></p>
		            </div>
		            <!-- Cột 8 -->
		            <div class="col-md-3">
		                <p><span class="left-align" id="makh">${info_khachhang.diachi}</span></p>
		            </div>
		        </div>
		        
   <div class="container mt-5">
    <div class="row">
      <div class="col-md-9">
            <h4 style="color:#f6621c; margin-bottom: 20px;" class="card-title">Thay đổi mật khẩu</h4>
            <form action="quan_ly_tai_khoan" method="POST">
              <div class="form-group" style="margin-bottom: 20px;">
                <label for="inputOldPassword">Mật khẩu cũ</label>
                <input type="password" name="oldpassword" class="form-control" id="inputOldPassword" placeholder="Nhập mật khẩu cũ" required>
              	<h6 style="color: red;">${oldpassmessage}</h4>
              </div>
              <div class="form-group" style="margin-bottom: 20px;">
                <label for="inputNewPassword">Mật khẩu mới</label>
                <input type="password" name = "newpassword" class="form-control" id="inputNewPassword" placeholder="Nhập mật khẩu mới" required>
              	<h6 style="color: red;">${newpassmessage}</h4>
              </div>
              <div class="form-group" style="margin-bottom: 20px;">
                <label for="inputConfirmPassword">Nhập lại mật khẩu mới</label>
                <input type="password" name = "renewpassword" class="form-control" id="inputConfirmPassword" placeholder="Nhập lại mật khẩu mới" required>
              	<h6 style="color: red;">${renewpassmessage}</h4>
              </div>
              <p>Mật khẩu bao gồm tối thiểu 06 ký tự, có ít nhất một chữ in hoa (A-Z), một chữ in thường (a-z), một chữ số (0-9) và một ký tự đặc biệt (#?.!@$%^&*-). Ví dụ: Matkhau@123</p>
              <button type="submit" class="btn btn-primary">Cập nhật</button>
            </form>
          </div>
    </div>
  </div>
  		</div>  
</div>


</body>
</html>