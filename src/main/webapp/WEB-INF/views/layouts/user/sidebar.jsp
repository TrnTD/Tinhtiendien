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
<style><%@include file="/WEB-INF/resource/assets/css/style-sidebar.css"%></style>
<body>
    <div class="sidebar">
        <div class="sidebar-logo">
            <img src="https://www.thuvienvector.com/upload/images/items/vector-logo-evn-logo-tap-doan-dien-luc-viet-nam-file-cdr-coreldraw-ai-151.webp" alt="">
        </div>

        <div class="sidebar-menu">
            <div class="item">
                <a href="#"><i class="fa-solid fa-chart-simple"></i>Quản lý chung</a>
            </div>
            
            <div class="item">
                <a class="sub-btn" href="#"><i class="fa-regular fa-newspaper"></i>Lịch sử hóa đơn<i class="fa-solid fa-angle-right"></i></a>
                <div class="sub-sidebar-menu">
                    <a href="#">Tra cứu hóa đơn điện tử</a>
                    <a href="#">Lịch sử thanh toán</a>
                </div>
            </div>
            

            <div class="item">
                <a href="#"><i class="fa-regular fa-folder"></i>Quản lý hợp đồng</a>
            </div>

            <div class="item">
                <a class="sub-btn" href="#"><i class="fa-solid fa-magnifying-glass"></i>Tra cứu<i class="fa-solid fa-angle-right"></i></a>
                <div class="sub-sidebar-menu">
                    <a href="#">Lịch tạm ngưng cấp điện</a>
                    <a href="#">Lịch ghi chỉ số</a>
                    <a href="#">Thông tin đo xa</a>
                </div>
            </div>


            <div class="item"> 
                <a href="#"><i class="fa-regular fa-comments"></i>Lịch sử yêu cầu</a>
            </div>

            <div class="item">
                <a href="#"><i class="fa-regular fa-comments"></i>Lịch sử liên hệ</a>
            </div>

            <div class="item">
                <a href="#"><i class="fa-regular fa-user"></i>Quản lý tài khoản</a>
            </div>
        </div>
    </div>
</body>
<script>
    $(document).ready(function() {
        $('.sub-btn').click(function() {
            $(this).parent('.item').find('.sub-sidebar-menu').slideToggle();
            $(this).find('.fa-angle-right').toggleClass('rotate');
        })
    })
</script>
</html>