<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">

    <!--====== Title ======-->
    <title>Giới thiệu</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<style><%@include file="/WEB-INF/resource/index_css/assets/css/animate.css"%></style>
<style><%@include file="/WEB-INF/resource/index_css/assets/css/bootstrap.min.css"%></style>
<style><%@include file="/WEB-INF/resource/index_css/assets/css/default.css"%></style>
<style><%@include file="/WEB-INF/resource/index_css/assets/css/style.css"%></style>
<style>
	
    #test
    {
        
        transform: skewX(-15deg);
    }
    #sigupbtn
    {
        background-color: rgba(255,255,255,0);
        color: black;
        border: 2px solid black;
    }

    #sigupbtn:hover
    {
       opacity: 0.5;
    }
    .logo-name{
        display: inline-block;
        position: relative;
    }
    .logo-name h2{
       
        position: absolute;
        top: 30%; /* Đưa phần tử lên trên 50% của container */
        left: 100%;
     }
    .logo{
        height: 90px;
        
    }
    
</style>

<body>
    
    <!--====== HEADER PART START ======-->

    <section class="header_area ">
        <div class="header_navbar">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <nav class="navbar navbar-expand-lg" style="height: 90px;">
                            <div class="logo-name">
                                <img src="https://downloadlogomienphi.com/sites/default/files/logos/download-logo-vector-hoc-vien-cong-nghe-buu-chinh-vien-thong-mien-phi.jpg" alt="logo Ptit" class="logo"> 
                                <h2>PTITHCM</h2>
                            </div>
                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                                <span class="toggler-icon"></span>
                                <span class="toggler-icon"></span>
                                <span class="toggler-icon"></span>
                            </button>

                            <div class="collapse navbar-collapse sub-menu-bar" id="navbarSupportedContent">
                                <ul id="nav" class="navbar-nav ml-auto">
                                    <li class="nav-item">
                                        <a class="page-scroll" href="/Tinhtiendien/nguoi_dung/tra_cuu_hoa_don">Tra cứu hoá đơn điện tử</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="page-scroll" href="/Tinhtiendien/nguoi_dung/thong_tin_hoa_don">Thông tin hoá đơn</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="page-scroll" href="/Tinhtiendien/nguoi_dung/lich_su_thanh_toan">Lịch sử thanh toán</a>
                                    </li>
                                </ul>
                            </div> <!-- navbar collapse -->
                        </nav> <!-- navbar -->
                    </div>
                </div> <!-- row -->
            </div> <!-- container -->
        </div> <!-- header navbar -->

        <div id="home" class="header_hero d-lg-flex align-items-center">
            <div class="hero_shape shape_1">
                <img src="https://raw.githubusercontent.com/TrnTD/Sources/8b2d1361bdbc56ebaff532cb267f6877cc79c3ef/shape-1.svg" alt="shape"> 
            </div>
            <div class="hero_shape shape_2">
                <img src="https://raw.githubusercontent.com/TrnTD/Sources/8b2d1361bdbc56ebaff532cb267f6877cc79c3ef/shape-2.svg" alt="shape"> 
            </div>
            <div class="hero_shape shape_3">
                <img src="https://raw.githubusercontent.com/TrnTD/Sources/8b2d1361bdbc56ebaff532cb267f6877cc79c3ef/shape-3.svg" alt="shape">
            </div>
            <div class="hero_shape shape_4">
                <img src="https://raw.githubusercontent.com/TrnTD/Sources/8b2d1361bdbc56ebaff532cb267f6877cc79c3ef/shape-4.svg" alt="shape">
            </div>
            <div class="hero_shape shape_6">
                <img src="https://raw.githubusercontent.com/TrnTD/Sources/8b2d1361bdbc56ebaff532cb267f6877cc79c3ef/shape-1.svg" alt="shape">
            </div>
            <div class="hero_shape shape_7">
                <img src="https://raw.githubusercontent.com/TrnTD/Sources/8b2d1361bdbc56ebaff532cb267f6877cc79c3ef/shape-4.svg" alt="shape">
            </div>
            <div class="hero_shape shape_8">
                <img src="https://raw.githubusercontent.com/TrnTD/Sources/8b2d1361bdbc56ebaff532cb267f6877cc79c3ef/shape-3.svg" alt="shape">
            </div>
            <div class="hero_shape shape_9">
                <img src="https://raw.githubusercontent.com/TrnTD/Sources/8b2d1361bdbc56ebaff532cb267f6877cc79c3ef/shape-2.svg" alt="shape">
            </div>
            <div class="hero_shape shape_10">
                <img src="https://raw.githubusercontent.com/TrnTD/Sources/8b2d1361bdbc56ebaff532cb267f6877cc79c3ef/shape-4.svg" alt="shape">
            </div>
            <div class="hero_shape shape_11">
                <img src="https://raw.githubusercontent.com/TrnTD/Sources/8b2d1361bdbc56ebaff532cb267f6877cc79c3ef/shape-1.svg" alt="shape">
            </div>
            <div class="hero_shape shape_12">
                <img src="https://raw.githubusercontent.com/TrnTD/Sources/8b2d1361bdbc56ebaff532cb267f6877cc79c3ef/shape-2.svg" alt="shape">
            </div>

            <div class="container" style="margin-left: 100px;">
                <div class="row">
                    <div class="col-lg-7" >
                        <div class="header_hero_content">
                            <h2 class="hero_title wow fadeInUp" data-wow-duration="2s" data-wow-delay="0.2s">NHÓM 4 - D21CQAT01-N <span style="color: blue;">Phần mềm tính tiền điện cho công ty điện lực</span></h2>
                            <ul>
                                <li class="wow fadeInUp" data-wow-duration="2s" data-wow-delay="0.9s"><a class="main-btn" rel="nofollow" href="login" style="width:160px">Đăng nhập</a></li>
                                <li class="wow fadeInUp" data-wow-duration="2s" data-wow-delay="0.9s"><a class="main-btn" id="sigupbtn" rel="nofollow" href="register" style="width:160px">Đăng kí</a></li>
                            </ul>
                        </div> <!-- header hero content -->
                    </div>
                </div> <!-- row -->
            </div> <!-- container -->
            <div class="header_shape d-none d-lg-block">
            </div>
            
            <div class="header_image d-flex align-items-center">
                <div class="image">
                 
                    <img  id ="test" data-wow-duration="2s" data-wow-delay="1.6s" src="https://raw.githubusercontent.com/TrnTD/Sources/main/z5522902852778_ab804b0cef631109edace87aee7561a4.jpg" alt="Header Image">
                    
                </div>
          
            </div> <!-- header image -->
        </div> <!-- header hero -->
    </section>

   
    <!--====== PART ENDS ======-->

    
    
    <script><%@include file="/WEB-INF/resource/index_css/assets/js/vendor/jquery-1.12.4.min.js"%></script>
    <script><%@include file="/WEB-INF/resource/index_css/assets/js/vendor/modernizr-3.7.1.min.js"%></script>
    <script><%@include file="/WEB-INF/resource/index_css/assets/js/wow.min.js"%></script>
    <script><%@include file="/WEB-INF/resource/index_css/assets/js/main.js"%></script>

</body>

</html>
