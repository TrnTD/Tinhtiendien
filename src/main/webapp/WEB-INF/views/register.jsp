
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style><%@include file="/WEB-INF/resource/assets/css/style-register.css"%></style>
<body>
	<div class="signup-wrap">
		<div class="signup">
			<div class="tag">
				<h1>Đăng kí tài khoản</h1>
				<a href="#">Hướng dẫn đăng kí</a> <a href="#"
					style="color: rgba(0, 0, 0, .7);">Tìm thông tin khách hàng?</a>
			</div>
			<div class="signup-form">
				<div class="sign-up-user">
					<div class="group">
						<label for="user" class="label">Mã khách hàng/ Tên đăng
							nhập</label> <input id="user" type="text" class="input"> <i>Tên
							đăng nhập hợp lệ chỉ chứa chữ in hoa (A-Z), chữ in thường (a-z)
							và chữ số (0-9), không bao gồm tiếng Việt có dấu. Ví dụ: User12</i>
					</div>
					<div class="group">
						<label for="pass" class="label">Mật khẩu</label> <input id="pass"
							type="password" class="input" data-type="password">
					</div>
					<div class="group">
						<label for="pass" class="label">Nhập lại mật khẩu</label> <input
							id="pass" type="password" class="input" data-type="password">
						<i>Mật khẩu bao gồm tối thiểu 06 ký tự, có ít nhất một chữ in
							hoa (A-Z), một chữ in thường (a-z), một chữ số (0-9) và một ký tự
							đặc biệt (#?.!@$%^&*-). Ví dụ: Matkhau@123</i>
					</div>
					<div class="group">
						<input type="submit" class="button" value="Đăng ký">
					</div>
				</div>
			</div>
			<div class="footer">
				<span>Quý khách đã có tài khoản? </span><a href="#">Đăng nhập </a>
			</div>
		</div>
	</div>
</body>
</html>