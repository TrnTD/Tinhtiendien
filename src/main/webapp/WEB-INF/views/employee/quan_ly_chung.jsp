<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<div class="content">
	<div class="container" style="width: 1200px">	
		<canvas id="myChart_dien" style="background-color: white; max-width: 1200px; max-height: 400px; box-shadow: rgba(0, 0, 0, 0.5) 0px 5px 15px;"></canvas>
		<br>
		<br>
		<canvas id="myChart_tien" style="background-color: white; max-width: 1200px; max-height: 400px; box-shadow: rgba(0, 0, 0, 0.5) 0px 5px 15px;"></canvas>
	</div>
	
</div>
	<%
		List<Integer> list_dientieuthu = (List) request.getAttribute("list_dientieuthu");
		List<Integer> list_sotien = (List) request.getAttribute("list_sotien");
	%>
	<script type="text/javascript">
		var list_dientieuthu = <%= list_dientieuthu %>;
		var list_sotien = <%= list_sotien %>;
		var monthArray = ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'];
		
		console.log(list_dientieuthu)
		console.log(list_sotien)
		
		let delayed;
			
			//Lấy thẻ canvas
	        var ctx_dien = document.getElementById('myChart_dien').getContext('2d');
			
			var chartData_dien = {
		            labels: monthArray,
		            datasets: [{
		                label: 2010,
		                backgroundColor: "rgba(73, 159, 250, 0.7)",
		                borderColor: "rgba(0, 67, 139, 1)",
		                borderWidth: 1,
		                data: list_dientieuthu, // Dữ liệu từ cơ sở dữ liệu
		            }]
		        };
	
		        // Tạo biểu đồ dạng cột
		        var myChart_dien = new Chart(ctx_dien, {
		            type: 'line',
		            data: chartData_dien,
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
		                            text: 'Điện tiêu thụ (Kwh)' // Tên trục dọc
		                        }
		                    },
		                    x: {
		                        title: {
		                            display: true,
		                            text: 'Tháng' // Tên trục ngang
		                        }
		                    }
		                },
		                plugins: {
		                    title: {
		                      display: true,
		                      text: 'Điện tiêu thụ',
		                    }
		                }
		            }
		        });
		        
		        
		        
		        var ctx_tien = document.getElementById('myChart_tien').getContext('2d');
		        
		        var chartData_tien = {
		            labels: monthArray,
		            datasets: [{
		                label: 2010,
		                backgroundColor: "rgba(254, 109, 109, 0.7)",
		                borderColor: "rgba(159, 10, 10, 1)",
		                borderWidth: 1,
		                data: list_sotien, // Dữ liệu từ cơ sở dữ liệu
		            }]
		        };
		
			        // Tạo biểu đồ dạng cột
		        var myChart_tien = new Chart(ctx_tien, {
		            type: 'line',
		            data: chartData_tien,
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
		                            text: 'Tiền điện (VND)' // Tên trục dọc
		                        }
		                    },
		                    x: {
		                        title: {
		                            display: true,
		                            text: 'Tháng' // Tên trục ngang
		                        }
		                    }
		                },
		                plugins: {
		                    title: {
		                      display: true,
		                      text: 'Tiền điện',
		                    }
		                }
		            }
		        });
		
	</script>
</body>
</html>