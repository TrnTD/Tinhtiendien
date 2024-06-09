<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.Tinhtiendien.Models.ChiTietHoaDon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quản lý chung</title>
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<style><%@include file="/WEB-INF/resource/assets/css/style-quanly.css"%></style>
<style>
    .thong-ke {
        display: flex;
        justify-content: space-evenly;
        align-items: flex-end;
    }

    p {
        font-size: 18px;
    }

    .so-lieu {
        padding: 10px;
        width: 400px;
        margin-right: 25px;
        background-color: white;
        box-shadow: rgba(0, 0, 0, 0.5) 0px 5px 15px;
    }

    .doanh-thu-thang {
        border: 3px solid rgb(8, 69, 200);
    }

    .khach-hang {
        border: 3px solid rgb(10, 207, 10);
    }

    .doanh-thu-nam {
        border: 3px solid rgb(236, 168, 41);
    }

</style>

<%
    // Lấy ngày tháng hiện tại
    LocalDate now = LocalDate.now();
    
    // Lấy tháng/năm
    String monthYear = now.format(DateTimeFormatter.ofPattern("MM/yyyy"));
    // Lấy năm
    int year = now.getYear();
%>

<div class="content">
	<div class="thong-ke">
        <div class="so-lieu doanh-thu-thang">
            <p>Tiền hóa đơn tháng này thu được (<%= monthYear %>)</p>
            <h4>${doanhthu_thang}</h4>
        </div>

        <div class="so-lieu khach-hang">
            <p>Tổng khách hàng</p>
            <h4>${tongKhachHang}</h4>
        </div>
        <div class="so-lieu doanh-thu-nam">
            <p>Doanh thu năm nay (<%= year %>)</p>
            <h4>${doanhthu_nam}</h4>
        </div>
    </div>
    
    
    <div class="bieu-do" style="float: left; margin-top: 40px;">
    	<div class="container" style="width: 800px;">
			<canvas id="myChart_doanhthu3nam" style="background-color: white; max-width: 1200px; max-height: 400px; box-shadow: rgba(0, 0, 0, 0.5) 0px 5px 15px;"></canvas>
		</div>	
	</div>
</div>
	<%
		List<Integer> list_3YearsNearest = (List) request.getAttribute("list_3YearsNearest");
		List<Long> list_doanhthu = (List) request.getAttribute("list_doanhthu");
	%>
	<script type="text/javascript">
		var list_3YearsNearest = <%= list_3YearsNearest %>;
		var list_doanhthu = <%= list_doanhthu %>;
		var monthArray = ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'];
		
		let delayed;
			
			//Lấy thẻ canvas
	        var ctx_doanhthu3nam = document.getElementById('myChart_doanhthu3nam').getContext('2d');
			
			var chartData_dien = {
		            labels: list_3YearsNearest,
		            datasets: [{
		                label: list_3YearsNearest[0],
		                backgroundColor: "rgba(73, 159, 250, 0.7)",
		                borderColor: "rgba(0, 67, 139, 1)",
		                borderWidth: 1,
		                data: [list_doanhthu[0]], // Dữ liệu từ cơ sở dữ liệu
		            },
		            {
		                label: list_3YearsNearest[1],
		                backgroundColor: "rgba(73, 159, 250, 0.7)",
		                borderColor: "rgba(0, 67, 139, 1)",
		                borderWidth: 1,
		                data: [list_doanhthu[1]], // Dữ liệu từ cơ sở dữ liệu
		            },
		            {
		                label: list_3YearsNearest[2],
		                backgroundColor: "rgba(73, 159, 250, 0.7)",
		                borderColor: "rgba(0, 67, 139, 1)",
		                borderWidth: 1,
		                data: [list_doanhthu[2]], // Dữ liệu từ cơ sở dữ liệu
		            }]
		        };
	
		        // Tạo biểu đồ dạng cột
		        var myChart_tiendientrongnam = new Chart(ctx_doanhthu3nam, {
				    type: 'bar',
				    data: {
				        labels: ['Tiền điện'], // Nhãn chung cho cả hai cột
				        datasets: [
				            {
				                label: list_3YearsNearest[0],
				                backgroundColor: "rgba(254, 109, 109, 0.7)",
				                borderColor: "rgba(159, 10, 10, 1)",
				                borderWidth: 1,
				                data: [list_doanhthu[0]], // Dữ liệu cho năm đầu tiên
				                barPercentage: 0.5,
				                categoryPercentage: 0.5,
				            },
				            {
				                label: list_3YearsNearest[1],
				                backgroundColor: "rgba(255, 231, 115, 0.7)",
				                borderColor: "rgba(153, 107, 0, 1)",
				                borderWidth: 1,
				                data: [list_doanhthu[1]], // Dữ liệu cho năm thứ hai
				                barPercentage: 0.5,
				                categoryPercentage: 0.5,
				            },
				            {
				                label: list_3YearsNearest[2],
				                backgroundColor: "rgba(252, 179, 107, 0.7)",
				                borderColor: "rgba(255, 160, 51, 1)",
				                borderWidth: 1,
				                data: [list_doanhthu[2]], // Dữ liệu cho năm thứ hai
				                barPercentage: 0.5,
				                categoryPercentage: 0.5,
				            }
				        ]
				    },
				    options: {
				        animation: {
				            onComplete: () => {
				                delayed = true;
				            },
				            delay: (context) => {
				                let delay = 0;
				                if (context.type === 'data' && context.mode === 'default' && !delayed) {
				                    delay = context.dataIndex * 200 + context.datasetIndex * 50;
				                }
				                return delay;
				            },
				        },
					        scales: {
				            y: {
				                beginAtZero: true,
				                title: {
				                    display: true,
				                    text: 'VND' // Tên trục dọc
				                }
				            },
				            x: {
				                title: {
		                    	display: true,
// 							    text: 'Năm' // Tên trục ngang
				                }
				            }
				        },
				        plugins: {
				            title: {
				                display: true,
				                text: 'Tiền điện thu trong 3 năm gần nhất',
				            }
				        }
				    }
				});  
	        
		     
		
	</script>
</body>
</html>