<%@page import="com.Tinhtiendien.Models.InfoNhanVien"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thông Tin Nhân Viên</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.1/font/bootstrap-icons.css" rel="stylesheet">
    <style><%@ include file="/WEB-INF/resource/assets/css/style-quanly.css" %></style>
</head>
<body>
    <div class="content">
        <div class="container" style="width: 1050px">
            <h2 style="color: #f6621c">Quản lý tài khoản nhân viên</h2>
            <div class="row">
                <div class="col-2">
                    <p><strong>Họ và tên:</strong></p>
                </div>
                <div class="col-md-3">
                    <p><span id="hoten">${info_nhanvien.hovaten}</span></p>
                </div>
                <div class="col-2">
                    <p><strong>Mã nhân viên:</strong></p>
                </div>
                <div class="col-md-3">
                    <p><span id="manv">${info_nhanvien.nhanvien_id}</span></p>
                </div>
            </div>
            <div class="row">
                <div class="col-2">
                    <p><strong>Số điện thoại:</strong></p>
                </div>
                <div class="col-md-3">
                    <p><span id="sdt">${info_nhanvien.sdt}</span></p>
                </div>
                <div class="col-2">
                    <p><strong>Email:</strong></p>
                </div>
                <div class="col-md-3">
                    <p><span id="email">${info_nhanvien.email}</span></p>
                </div>
            </div>
            <div class="row">
                <div class="col-2">
                    <p><strong>Tên tài khoản:</strong></p>
                </div>
                <div class="col-md-3">
                    <p><span id="username">${info_nhanvien.username}</span></p>
                </div>
            </div>
        </div>

        <div class="container mt-5" style="width: 1050px">
        	 <c:if test="${not empty mess}">
                        <div id="successMessage" class="alert alert-success" role="alert" style="margin-top: 20px;">
                           			${mess}
                        </div>
                        <script>
                            setTimeout(function() {
                                var successMessage = document.getElementById("successMessage");
                                if (successMessage) {
                                    successMessage.style.display = "none";
                                }
                            }, 2500);
                        </script>
                    </c:if>
            <div class="row">
                <div class="col-md-9">
                    <h4 style="color: #f6621c; margin-bottom: 20px;" class="card-title">Thay đổi mật khẩu</h4>
                    <form action="quan_ly_tai_khoan" method="POST">
                        <div class="form-group" style="margin-bottom: 20px;">
                            <label for="inputOldPassword">Mật khẩu cũ</label> 
                            <input type="password" name="oldpassword" class="form-control" id="inputOldPassword" placeholder="Nhập mật khẩu cũ" required>
                            <h6 style="color: red;">${oldpassmessage}</h6>
                        </div>

                        <div class="form-group" style="margin-bottom: 20px;">
                            <label for="inputNewPassword">Mật khẩu mới</label>
                            <input type="password" name="newpassword" class="form-control" id="inputNewPassword" placeholder="Nhập mật khẩu mới" required>
                            <h6 style="color: red;">${newpassmessage}</h6>
                        </div>

                        <div class="form-group" style="margin-bottom: 20px;">
                            <label for="inputConfirmPassword">Nhập lại mật khẩu mới</label>
                            <input type="password" name="renewpassword" class="form-control" id="inputConfirmPassword" placeholder="Nhập lại mật khẩu mới" required>
                            <h6 style="color: red;">${renewpassmessage}</h6>
                        </div>

                        <p>Mật khẩu bao gồm tối thiểu 06 ký tự, có ít nhất một chữ in hoa (A-Z), một chữ in thường (a-z), một chữ số (0-9) và một ký tự đặc biệt (#?.!@$%^&*-). Ví dụ: Matkhau@123</p>
                        <button type="submit" class="btn btn-primary">Cập nhật</button>
                    </form>
                    
                   
                </div>
            </div>
        </div>
    </div>
</body>
</html>
