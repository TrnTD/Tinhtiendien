<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdn-script.com/ajax/libs/jquery/3.7.1/jquery.js"></script>
</head>
<style><%@include file="/WEB-INF/resource/assets/css/style-navbar.css"%></style>
<body>
	<div class="navbar-container">
        <div class="navbar">
            <div class="item">
                <a href="/Tinhtiendien">Về trang chủ</a>
            </div>

            <div class="item nabar-notification-bell">
                <i class="fa-regular fa-bell"></i>
            </div>
    
            <div class="item navbar-user-info">
                <div class="btn-user-info">
                    <i class="fa-regular fa-circle-user"></i>
                    <p>${hoten}</p>
                    <i class="fa-solid fa-angle-down"></i>
                </div>

                <div class="sub-user-info">
                    <div class="sub-item sub-username">
                        <p class="title">Họ và tên</p>
                        <p>${hoten}</p>
                    </div>

                    <div class="sub-item sub-id">
                        <p class="title">Mã khách hàng</p>
                        <p>${makh}</p>
                    </div>

                    <div class="sub-item sub-address">
                        <p class="title">Địa chỉ</p>
                        <p>${diachi}</p>
                    </div>

                    <div class="sub-item sub-email">
                        <p class="title">Email</p>
                        <p>${email}</p>
                    </div>

                    <div class="sub-item sub-sdt">
                        <p class="title">Số điện thoại</p>
                        <p>${sdt}</p>
                    </div>

                    <div class="btn-logout">
                        <form action="logout">
                            <button type="submit"><i class="fa-solid fa-arrow-right-from-bracket"></i>Đăng xuất</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>
</body>
<script>
    $(document).ready(() => {
        $('.navbar-user-info').click(() => {
            $('.sub-user-info').slideToggle();
        })
    })
</script>
</html>