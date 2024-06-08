<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lịch sử thông báo</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<style>
	thead tr {
		background-color: #e8ecfc;
		width: 1000px;
		
		th {
			color: #063bc0;
		}
		
		th:first-child {
			width: 120px;
		}
		
	}

	th {
		font-size: 16px;
		text-align: center;
	}
	
	td {
		font-weight: 500;
		text-align: center;
	}
</style>
	<div class="content">
  		<div class="container" style="width: 1200px">
			<h2 style="color:#f6621c; margin-bottom: 20px;">Lịch sử thông báo</h2>           
	        <table class="table table-hover" style="background-color: white; box-shadow: rgba(0, 0, 0, 0.5) 0px 5px 15px;">
	         <thead>
		      <tr>
			      <th>STT</th>
			      <th>Ngày gửi</th>
			      <th>Nội dung</th>
		      </tr>
		    </thead>
		    <tbody>
		      <c:forEach var="thongbao" items="${list_thongbao}" varStatus="status">
		   		<tr>
		   			<th>${status.index+1}</th>
		   			<td>${thongbao.ngay_gui}</td>
		   			<td>${thongbao.noi_dung}</td>
<%-- 		   			<td><fmt:formatDate value="${LSDo.ngay_do}" pattern="dd-MM-yyyy"/></td> --%>
		   		</tr>
  			 </c:forEach>
    		</tbody>
	        </table>
		</div>
	</div>
</body>

</html>