<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<style>
.sidebar {
      position: fixed;
      top: 0;
      left: 0;
      height: 100%;
      width: 250px;
      padding-top: 15px;
      background-color: #ffff;
      border-right: 1px solid #ccc; /* Thêm viền bên phải */
      z-index: 9999999;
      box-shadow: 1px 0 10px rgba(0, 0, 0, 0.5);

      img {
        height: 80px;
        width: 100%;
        margin-bottom: 20px;
      }

      .nav-item {
        margin-bottom: 20px;
        a {
          position: relative;
          color: black;
          padding: 20px;
        }
      }

      i {
        color: rgb(245, 90, 90);
        font-size: 20px;
        margin-right: 10px;
      }

      .nav-link:hover {
        color: black; /* Màu chữ khi hover */
        background-color: #dcdcdc; /* Màu nền khi hover */
      }

    }

    .fa-chevron-down {
        position: absolute;
        top: 50%;
        right: 10px;
        transform: translateY(-50%);
    }
    
    .nav-link.active-page {
	    color: black; /* Màu chữ khi thẻ được chọn */
	    font-weight: bold; /* Độ đậm của chữ khi thẻ được chọn */
	    background-color: #dcdcdc;
	}
	
	.sidebar::-webkit-scrollbar {
        width: 5px; /* Chiều rộng của thanh trượt */
    }

    /* Thanh trượt */
    .sidebar::-webkit-scrollbar-thumb {
        background-color: #888; /* Màu của thanh trượt */
        border-radius: 5px; /* Bo tròn các cạnh của thanh trượt */
    }
</style>
<body>
<div class="sidebar" style="overflow-y: auto; height: 100vh;">
    <div class="sidebar-logo">
      <img src="https://www.thuvienvector.com/upload/images/items/vector-logo-evn-logo-tap-doan-dien-luc-viet-nam-file-cdr-coreldraw-ai-151.webp" alt="">
    </div>
    <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link active" href="#"><i class="fa-solid fa-chart-simple"></i> Quản lý chung</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="#lichsuhoadon" data-bs-toggle="collapse" aria-expanded="false"><i class="fa-regular fa-newspaper"></i> Lịch sử hóa đơn<i class="fa-solid fa-chevron-down"></i></a>
            <ul class="collapse list-unstyled" id="lichsuhoadon" style="padding-left: 20px;">
                <li><a class="nav-link" href="/Tinhtiendien/nguoi_dung/tra_cuu_hoa_don">Tra cứu hóa đơn điện tử</a></li>
                <li><a class="nav-link" href="/Tinhtiendien/nguoi_dung/thong_tin_hoa_don">Thông tin hóa đơn</a></li>
                <li><a class="nav-link" href="/Tinhtiendien/nguoi_dung/lich_su_thanh_toan">Lịch sử thanh toán</a></li>
            </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="#tracuu" data-bs-toggle="collapse" aria-expanded="false"><i class="fa-solid fa-magnifying-glass"></i> Tra cứu<i class="fa-solid fa-chevron-down"></i></a>
          <ul class="collapse list-unstyled" id="tracuu" style="padding-left: 20px;">
              <li><a class="nav-link" href="#">Lịch tạm ngưng cấp điện</a></li>
              <li><a class="nav-link" href="/Tinhtiendien/nguoi_dung/lich_ghi_chi_so">Lịch ghi chỉ số</a></li>
              <li><a class="nav-link" href="/Tinhtiendien/nguoi_dung/lich_su_do">Lịch sử đo</a></li>
          </ul>
      </li>
      <li class="nav-item">
          <a class="nav-link" href="#"><i class="fa-regular fa-comments"></i> Lịch sử liên hệ</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#"><i class="fa-regular fa-comment-dots"></i> Lịch sử yêu cầu</a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link" href="/Tinhtiendien/nguoi_dung/quan_ly_tai_khoan"><i class="fa-solid fa-id-badge"></i> Quản lý tài khoản</a>
      </li>
    </ul>
</div>
</body>


<script type="text/javascript">
document.addEventListener("DOMContentLoaded", function() {
    // Lấy đường dẫn của trang hiện tại
    var currentPath = window.location.pathname;
    
    // Lặp qua tất cả các thẻ a có thuộc tính href
    var navLinks = document.querySelectorAll('a[href]');
    navLinks.forEach(function(navLink) {
        // Kiểm tra xem đường dẫn của thẻ a có trùng khớp với đường dẫn hiện tại hay không
        if (navLink.getAttribute('href') === currentPath) {
            // Nếu trùng khớp, thêm class active-page vào thẻ a đó
            navLink.classList.add('active-page');
        }
    });

});
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</html>











