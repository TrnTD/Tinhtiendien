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
body {
	background-color: #e5e5e5;
}

.sidebar {
      position: fixed;
      top: 0;
      left: 0;
      height: 100%;
      width: 250px;
/*       padding-top: 15px; */
      background-color: #ffff;
      border-right: 1px solid #ccc; /* Thêm viền bên phải */
      z-index: 9999999;
      box-shadow: 1px 0 10px rgba(0, 0, 0, 0.5);

      img {
        height: 150px;
/*         width: 100%; */
/*         margin-bottom: 20px; */
      }

      .nav-item {
        margin-bottom: 20px;
        a {
          position: relative;
          color: black;
          padding: 20px;
          border-radius: 5px 0 0 5px;
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
      
      .menu-item.active {
	    background-color: #dcdcdc; /* Màu nền khi thẻ được chọn */
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
    <div class="sidebar-logo" style="display: flex; justify-content: center">
      <img src="https://downloadlogomienphi.com/sites/default/files/logos/download-logo-vector-hoc-vien-cong-nghe-buu-chinh-vien-thong-mien-phi.jpg" alt="">
    </div>
    <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link menu-item" href="/Tinhtiendien/nguoi_dung/quan_ly_chung"><i class="fa-solid fa-chart-simple"></i> Quản lý chung</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#lichsuhoadon" data-bs-toggle="collapse" aria-expanded="false"><i class="fa-regular fa-newspaper"></i> Lịch sử hóa đơn<i class="fa-solid fa-chevron-down"></i></a>
            <ul class="collapse list-unstyled" id="lichsuhoadon" style="padding-left: 20px;">
                <li><a class="nav-link menu-item" href="/Tinhtiendien/nguoi_dung/tra_cuu_hoa_don">Tra cứu hóa đơn điện tử</a></li>
                <li><a class="nav-link menu-item" href="/Tinhtiendien/nguoi_dung/thong_tin_hoa_don">Thông tin hóa đơn</a></li>
                <li><a class="nav-link menu-item" href="/Tinhtiendien/nguoi_dung/lich_su_thanh_toan">Lịch sử thanh toán</a></li>
            </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#tracuu" data-bs-toggle="collapse" aria-expanded="false"><i class="fa-solid fa-magnifying-glass"></i> Tra cứu<i class="fa-solid fa-chevron-down"></i></a>
          <ul class="collapse list-unstyled" id="tracuu" style="padding-left: 20px;">
              <li><a class="nav-link menu-item" href="#">Lịch tạm ngưng cấp điện</a></li>
<!--               <li><a class="nav-link menu-item" href="/Tinhtiendien/nguoi_dung/lich_ghi_chi_so">Lịch ghi chỉ số</a></li> -->
              <li><a class="nav-link menu-item" href="/Tinhtiendien/nguoi_dung/lich_su_do">Lịch sử đo</a></li>
          </ul>
      </li>
      <li class="nav-item">
          <a class="nav-link menu-item" href="#"><i class="fa-regular fa-comments"></i> Lịch sử liên hệ</a>
      </li>
      <li class="nav-item">
        <a class="nav-link menu-item" href="#"><i class="fa-regular fa-comment-dots"></i> Lịch sử yêu cầu</a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link menu-item" href="/Tinhtiendien/nguoi_dung/quan_ly_tai_khoan"><i class="fa-solid fa-id-badge"></i> Quản lý tài khoản</a>
      </li>
    </ul>
</div>
</body>


<script type="text/javascript">
document.addEventListener("DOMContentLoaded", function() {
    var menuItems = document.querySelectorAll(".menu-item");

    menuItems.forEach(function(item) {
        item.addEventListener("click", function() {
            // Xóa lớp active từ tất cả các menu items
            menuItems.forEach(function(item) {
                item.classList.remove("active");
            });

            // Thêm lớp active cho menu item được nhấp vào
            this.classList.add("active");

            // Kiểm tra xem menu item được nhấp có phải là "Tra cứu hóa đơn điện tử" không
            var parentMenuItem = document.querySelector('a[href="#lichsuhoadon"]');
            var collapseMenu = document.querySelector('#lichsuhoadon');
            
            var parentMenuItem1 = document.querySelector('a[href="#tracuu"]');
            var collapseMenu1 = document.querySelector('#tracuu');

            if (this.getAttribute("href") === "/Tinhtiendien/nguoi_dung/tra_cuu_hoa_don" || this.getAttribute("href") === "/Tinhtiendien/nguoi_dung/thong_tin_hoa_don" || this.getAttribute("href") === "/Tinhtiendien/nguoi_dung/lich_su_thanh_toan") 
            {
                parentMenuItem1.setAttribute("aria-expanded", "false");
                collapseMenu1.classList.remove("show");
                sessionStorage.setItem("menuState1", "collapsed");

                // Lưu trạng thái vào sessionStorage
                sessionStorage.setItem("menuState", "expanded");
            }
            else if (this.getAttribute("href") === "/Tinhtiendien/nguoi_dung/lich_tam_ngung_cap_dien"|| this.getAttribute("href") === "/Tinhtiendien/nguoi_dung/lich_ghi_chi_so" || this.getAttribute("href") === "/Tinhtiendien/nguoi_dung/lich_su_do" ) 
            {
                parentMenuItem.setAttribute("aria-expanded", "false");
                collapseMenu.classList.remove("show");
                sessionStorage.setItem("menuState", "collapsed");
                
                	
                sessionStorage.setItem("menuState1", "expanded");
            } 
            else {
                // Nếu không phải, đặt thuộc tính aria-expanded là false
                parentMenuItem.setAttribute("aria-expanded", "false");
                parentMenuItem1.setAttribute("aria-expanded", "false");
                
                // Xóa class "show" để ẩn ul
                collapseMenu.classList.remove("show");
                collapseMenu1.classList.remove("show");


                // Lưu trạng thái vào sessionStorage
                sessionStorage.setItem("menuState", "collapsed");
                sessionStorage.setItem("menuState1", "collapsed");

            }

            // Lưu trạng thái của menu vào local storage hoặc session storage
            var selectedMenuItem = this.getAttribute("href");
            sessionStorage.setItem("selectedMenuItem", selectedMenuItem);
        });
    });

    // Phục hồi trạng thái của menu từ local storage hoặc session storage khi trang được tải
    var selectedMenuItem = sessionStorage.getItem("selectedMenuItem");
    if (selectedMenuItem) {
        var activeMenuItem = document.querySelector('a[href="' + selectedMenuItem + '"]');
        if (activeMenuItem) {
            activeMenuItem.classList.add("active");
        }
    }

    // Phục hồi trạng thái của menu từ sessionStorage khi trang được tải
    var menuState = sessionStorage.getItem("menuState");
    if (menuState === "expanded") {
        var parentMenuItem = document.querySelector('a[href="#lichsuhoadon"]');
        var collapseMenu = document.querySelector('#lichsuhoadon');
        if (parentMenuItem && collapseMenu) {
            parentMenuItem.setAttribute("aria-expanded", "true");
            collapseMenu.classList.add("show");
        }
    }
    
    var menuState1 = sessionStorage.getItem("menuState1");
    if (menuState1 === "expanded") {
        var parentMenuItem1 = document.querySelector('a[href="#tracuu"]');
        var collapseMenu1 = document.querySelector('#tracuu');
        if (parentMenuItem1 && collapseMenu1) {
            parentMenuItem1.setAttribute("aria-expanded", "true");
            collapseMenu1.classList.add("show");
        }
    }
});
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</html>











