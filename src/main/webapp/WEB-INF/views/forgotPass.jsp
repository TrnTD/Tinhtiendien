<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	 <form action="/Tinhtiendien/forgotpass" class="custom-form" method="POST">
        <div style="margin:10px 0">
          <h1>Hãy nhập email đã liên kết với tài khoản</h1>
            <input type="text" class="form-control" name="email"  style="display:inline-block; width:40%">
          <button type="submit" class="btn btn-primary submit-btn">Lấy lại mật khẩu</button>  
          
        </div>
        <a href="login">Quay về đăng nhập</a>
</body>
</html>