<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thông báo</title>
    <style>
        .notification_success {
            background-color: #f2f2f2;
            padding: 20px;
            margin: 20px;
            border-left: 6px solid #4CAF50; /* Green */
            color: #333;
        }
        
        .notification_fail {
            background-color: #f2f2f2;
            padding: 20px;
            margin: 20px;
            border-left: 6px solid red; /* Green */
            color: #333;
        }
        
        .back-button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
        }
    </style>
</head>
<body>

<%-- Hiển thị thông báo --%>
<div class="notification">
    <h2>Thông báo: ${message} ${khachhang_id}</h2>
</div>

<%-- Nút để quay về --%>
<form action="/Tinhtiendien/nhan_vien/quan_ly_thong_tin_khach_hang">
    <input type="submit" class="back-button" value="Quay về">
</form>
</body>
</html>
