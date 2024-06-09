<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style>
    body{
        font: 600 16px / 18px 'Open Sans', sans-serif;
    }
    .container{
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    
    .forgot-form input,
    .forgot-form button,
    .forgot-form a
    {
        margin: 10px 10px 10px 0;
        height: 42px;
    }

    .forgot-form input
    {
        height: 15px;
        padding: 15px 20px;
        border-radius: 5px;
    }

    .forgot-form a
    {
       text-decoration: none;
       color: white;
    }

    .forgot-form button
    {
        font-size: 16px;
        font-weight: 600;
        border: none;
    }

    .forgot-form button,
    .forgot-form a
    {
        background-color: blue;
        color: white;
        padding: 10px;
        border-radius: 6px;
    }
    
    .loi {
    	color: red;
    }
    
    .thanh-cong {
    	color: green;
    }



</style>

<body>
    <div class="container">
        <div class="forgot-form" style="margin:10px 0">
            <form action="/Tinhtiendien/forgotpass" class="custom-form" method="POST">
                <h1>Hãy nhập email đã liên kết với tài khoản</h1>
                <label>Email</label>
                <input type="text" class="form-control" name="email"  style="display:inline-block; width:100%">
                <h3 class="loi">${tbloi}</h3>
                <h3 class="thanh-cong">${tbthanhcong}</h3>
                <button type="submit" class="btn btn-primary submit-btn">Lấy mật khẩu mới</button>  
                <a href="login">Quay về đăng nhập</a>
            </form>
              
            </div>
    </div>
    
</body>
</html>