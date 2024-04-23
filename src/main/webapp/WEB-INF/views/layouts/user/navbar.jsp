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
	.navbar {
      position: fixed;
      top: 0;
      width: 100%;
      z-index: 1000; /* Ensure the navbar appears above other content */
      box-shadow: 0 5px 5px rgba(0, 0, 0, .1);
      background-color: #ffff;

      .nav-item {
        margin-left: 30px;
        padding: 0 15px;
      }

      .nav-item:hover {
        background-color: #f2f2f2;
        border-radius: 5px;
      }

      a {

        line-height: 35px;
      }

      i {
        display: flex;
        font-size: 20px;
        justify-content: center;
      }

      .dropdown-menu {
        display: none;
        position: absolute;
        right: 0;
        width: 300px;
        font-size: 15px;
      }

      .dropdown-menu.show {
        display: block;
      }

      .info {
        font-weight: bold;
        margin-left: 40px;
        font-size: 15px;
      }

      .dropdown-item.logout {
        display: flex;
        justify-content: center;
        align-items: center;

        i {
          margin-right: 10px;
        }
      }

      #userDropdown {
        li {
          /* margin-bottom: 15px; */
          
          a {
            color: gray;
          }
        }

        li:not(:last-child) {
          pointer-events: none;
        }

        form {
          button {
            color: red;
          }

          button:hover {
            background-color: #E3E3E3;
          }
        }
      }
      
    }
</style>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <!-- Move this content to the right -->
    <div class="ms-auto">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="#" id="userDropdownTrigger">
            <i class="fa-solid fa-circle-user"></i>
            <i class="fa-solid fa-angle-down"></i>
          </a>
          <ul class="dropdown-menu" id="userDropdown">
            <li>
              <a class="dropdown-item" href="#">Họ và tên</a>
              <div class="info">${info_khachhang.hovaten}</div>
            </li>

            <li>
              <a class="dropdown-item" href="#">Mã khách hàng</a>
              <div class="info">${info_khachhang.khachhang_id}</div>
            </li>
            <li>
              <a class="dropdown-item" href="#">Địa chỉ</a>
              <div class="info">${info_khachhang.diachi}</div>
            </li>
            <li>
              <a class="dropdown-item" href="#">Email</a>
              <div class="info">${info_khachhang.email}</div>
            </li>
            <li>
              <a class="dropdown-item" href="#">Số điện thoại</a>
              <div class="info">${info_khachhang.sdt}</div>
            </li>
            <li><hr class="dropdown-divider"></li>
            <li>
              <div class="text-center">
                <form action="../logout">
                  <!-- Nút submit -->
                  <button type="submit" class="dropdown-item logout-button"><i class="fa-solid fa-right-from-bracket"></i> Đăng xuất</button>
              </form>
              </div>
            </li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
</body>
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script> -->
<script>
  document.addEventListener("DOMContentLoaded", function() {
    var userDropdown = document.getElementById("userDropdown");
    var userDropdownTrigger = document.getElementById("userDropdownTrigger");
  
    userDropdownTrigger.addEventListener("click", function(event) {
      event.stopPropagation(); // Ngăn chặn sự kiện click lan sang các phần tử khác
      userDropdown.classList.toggle("show");
    });
  
    // Đóng dropdown nếu click bên ngoài dropdown
    document.addEventListener("click", function(event) {
      if (!userDropdown.contains(event.target)) {
        userDropdown.classList.remove("show");
      }
    });
  });
</script>
</html>
