<%@page import="com.Tinhtiendien.Models.ChiTietHoaDon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quản lý chung</title>
</head>
<body>
<div id="intValueContainer1" data-intvalue="${list_thang}"></div>
<div id="intValueContainer2" data-intvalue="${list_chiso}"></div>

<canvas id="myChart" width="100vh" height="50vh" style="background-color: white;"></canvas>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var list_thang = $('#intValueContainer1').data('intvalue');
    var list_chiso = $('#intValueContainer2').data('intvalue');
    
    let monthArray = list_thang.map(function(month) {
        return "Tháng " + month;
    });

    var chartData = {
            labels: monthArray,
            datasets: [{
                label: "Lượng điện tiêu thụ",
                backgroundColor: "rgba(255, 99, 132, 0.2)",
                borderColor: "rgba(255, 99, 132, 1)",
                borderWidth: 1,
                data: list_chiso, // Dữ liệu từ cơ sở dữ liệu
            }]
        };

        // Lấy thẻ canvas
        var ctx = document.getElementById('myChart').getContext('2d');

        // Tạo biểu đồ dạng cột
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: chartData,
            options: {
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            display: true,
                            text: 'Điện tiêu thụ (Kwh)' // Tên trục dọc
                        }
                    },
                    x: {
                        title: {
                            display: true,
                            text: 'Tháng' // Tên trục ngang
                        }
                    }
                }
            }
        });

});
</script>
</body>
</body>
</html>