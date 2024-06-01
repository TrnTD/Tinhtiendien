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
<%-- <canvas id="myChart" width="700px" height="200px" style="background-color: white;"></canvas> --%>
<%
	ArrayList<Integer> list_2YearNearest = (ArrayList) request.getAttribute("list_2YearNearest");

	if (list_2YearNearest.size() == 1) {
		ArrayList<Integer> list_chiso_currentyear = (ArrayList) request.getAttribute("list_chiso_currentyear");
		ArrayList<Integer> list_tien_currentyear = (ArrayList) request.getAttribute("list_tien_currentyear");
%>
<div class="content">
	<div class="container" style="width: 1200px">	
		<canvas id="myChart_dien" style="background-color: white; max-width: 1200px; max-height: 400px; box-shadow: rgba(0, 0, 0, 0.5) 0px 5px 15px;"></canvas>
		<br>
		<br>
		<canvas id="myChart_tien" style="background-color: white; max-width: 1200px; max-height: 400px; box-shadow: rgba(0, 0, 0, 0.5) 0px 5px 15px;"></canvas>
	</div>
</div>

	<script>
		$(document).ready(function() {
			var list_chiso_currentyear = <%= list_chiso_currentyear %>; 
			var list_tien_currentyear = <%= list_tien_currentyear %>;
			var list_2YearNearest = <%= list_2YearNearest %>;
			var monthArray = ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'];
		
			console.log(list_chiso_currentyear)
			console.log(list_tien_currentyear)
		
	        let delayed;
			
			//Lấy thẻ canvas
	        var ctx_dien = document.getElementById('myChart_dien').getContext('2d');
			
			var chartData_dien = {
		            labels: monthArray,
		            datasets: [{
		                label: list_2YearNearest,
		                backgroundColor: "rgba(73, 159, 250, 0.7)",
		                borderColor: "rgba(0, 67, 139, 1)",
		                borderWidth: 1,
		                data: list_chiso_currentyear, // Dữ liệu từ cơ sở dữ liệu
		            }]
		        };
	
		        // Tạo biểu đồ dạng cột
		        var myChart_dien = new Chart(ctx_dien, {
		            type: 'bar',
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
		                label: list_2YearNearest,
		                backgroundColor: "rgba(254, 109, 109, 0.7)",
		                borderColor: "rgba(159, 10, 10, 1)",
		                borderWidth: 1,
		                data: list_tien_currentyear, // Dữ liệu từ cơ sở dữ liệu
		            }]
		        };
		
			        // Tạo biểu đồ dạng cột
		        var myChart_tien = new Chart(ctx_tien, {
		            type: 'bar',
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
		                            text: 'Tiền điện (VMD)' // Tên trục dọc
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
		                      text: 'Tiền điện tiêu thụ',
		                    }
		                }
		            }
		        });
			        
		})
	</script>
<% 
	} else {
		ArrayList<Integer> list_chiso_lastyear = (ArrayList) request.getAttribute("list_chiso_lastyear");
		ArrayList<Integer> list_tien_lastyear = (ArrayList) request.getAttribute("list_tien_lastyear");
		
		
		ArrayList<Integer> list_chiso_currentyear = (ArrayList) request.getAttribute("list_chiso_currentyear");
		ArrayList<Integer> list_tien_currentyear = (ArrayList) request.getAttribute("list_tien_currentyear");
		
%>
<div class="content">
	<div class="container" style="width: 1200px">	
		<canvas id="myChart_dien" style="background-color: white; max-width: 1200px; max-height: 400px; box-shadow: rgba(0, 0, 0, 0.5) 0px 5px 15px;"></canvas>
		<br>
		<br>
		<canvas id="myChart_tien" style="background-color: white; max-width: 1200px; max-height: 400px; box-shadow: rgba(0, 0, 0, 0.5) 0px 5px 15px;"></canvas>
	</div>
</div>

	<script>
		$(document).ready(function() {
			var list_chiso_lastyear = <%= list_chiso_lastyear %>; 
			var list_tien_lastyear = <%= list_tien_lastyear %>;
			
			var list_chiso_currentyear = <%= list_chiso_currentyear %>; 
			var list_tien_currentyear = <%= list_tien_currentyear %>;
			
			var list_2YearNearest = <%= list_2YearNearest %>;
			var monthArray = ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'];
		
			console.log(list_chiso_currentyear)
		
	        let delayed;
			
			//Lấy thẻ canvas
	        var ctx_dien = document.getElementById('myChart_dien').getContext('2d');
			
			var chartData_dien = {
		            labels: monthArray,
		            datasets: [{
		                label: list_2YearNearest[0],
		                backgroundColor: "rgba(73, 159, 250, 0.7)",
		                borderColor: "rgba(0, 67, 139, 1)",
		                borderWidth: 1,
		                data: list_chiso_lastyear, // Dữ liệu từ cơ sở dữ liệu
		            },
		            {
		            	label: list_2YearNearest[1],
		                backgroundColor: "rgba(90, 255, 126, 0.7)",
		                borderColor: "rgba(16, 97, 34, 1)",
		                borderWidth: 1,
		                data: list_chiso_currentyear, // Dữ liệu từ cơ sở dữ liệu
		            }]
		        };
	
		        // Tạo biểu đồ dạng cột
		        var myChart_dien = new Chart(ctx_dien, {
		            type: 'bar',
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
		                            text: 'KWH' // Tên trục dọc
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
			                label: list_2YearNearest[0],
			                backgroundColor: "rgba(254, 109, 109, 0.7)",
			                borderColor: "rgba(159, 10, 10, 1)",
			                borderWidth: 1,
			                data: list_tien_lastyear, // Dữ liệu từ cơ sở dữ liệu
			            },
			            {
			            	label: list_2YearNearest[1],
			                backgroundColor: "rgba(255, 231, 115, 0.7)",
			                borderColor: "rgba(153, 107, 0, 1)",
			                borderWidth: 1,
			                data: list_tien_currentyear, // Dữ liệu từ cơ sở dữ liệu
			            }]
			        };
		
			        // Tạo biểu đồ dạng cột
			        var myChart_tien = new Chart(ctx_tien, {
			            type: 'bar',
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
			                            text: 'VND' // Tên trục dọc
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
			                      text: 'Tiền điện tiêu thụ',
			                    }
			                }
			            }
			        });
		})
	</script>
<%
	}
%>

</body>
</body>
</html>