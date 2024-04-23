<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Đăng ký</title>
</head>
<style><%@include file="/WEB-INF/resource/assets/css/style-register.css"%></style>
<body>
	<div class="signup-wrap">
        <div class="signup">
        <div class="tag">
        	<h1 style="color: red;">${message_success}</h1>
         </div>
                <div class="tag">
                    <h1>Đăng kí tài khoản</h1>
                </div>
                <div class="signup-form">
                    <div class="sign-up-user">
                   <form action="register" method="POST">
                        <div class="group">
                            <label for="id" class="label">Mã khách hàng</label>
                            <input id="id" type="text" class="input" name ="khachhangid" required>
                        	<h4 style="color: red;">${messageMKH}</h4>
                        </div>
                        <div class="group">
                            <label for="user" class="label">Tên đăng nhập</label>
                            <input id="user" type="text" class="input" name = "username" required>
                            <h4 style="color: red;">${messageUN}</h4>
                            <i>Tên đăng nhập hợp lệ chỉ chứa chữ in hoa (A-Z), chữ in thường (a-z) và chữ số (0-9), không bao gồm tiếng Việt có dấu. Ví dụ: User12</i>
                        </div>
                        <div class="group">
                            <label for="pass" class="label">Mật khẩu</label>
                            <input id="pass" type="password" class="input" data-type="password" name = "password" required>
  							<h4 style="color: red;">${messageMK}</h4>
                        </div>
                        <div class="group">
                            <label for="pass" class="label">Nhập lại mật khẩu</label>
                            <input id="pass" type="password" class="input" data-type="password" name = "repassword" required>
                            <h4 style="color: red;">${messagePW}</h4>
                            <i>Mật khẩu bao gồm tối thiểu 06 ký tự, có ít nhất một chữ in hoa (A-Z), một chữ in thường (a-z), một chữ số (0-9) và một ký tự đặc biệt (#?.!@$%^&*-). Ví dụ: Matkhau@123</i>
                        </div>
                        <div class="group">
                            <input type="submit" class="button" value="ĐĂNG KÝ">
                        </div>
                        </form>
                    </div> 
                </div>
                <div class="footer"><span>Quý khách đã có tài khoản? </span><a href="/Tinhtiendien/login">Đăng nhập </a></div>
            
        </div>
    </div>
</body>
</html>