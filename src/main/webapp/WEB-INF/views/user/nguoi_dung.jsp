<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
	<%@include file="/WEB-INF/views/layouts/user/navbar.jsp" %>	
	<%@include file="/WEB-INF/views/layouts/user/sidebar.jsp" %>	
	
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
// 	$(document).ready(function() {
// 		$('a').click(function(e) {
// 			e.preventDefault();
// 			var href = $(this).attr('href');
// 			if (href.includes('#')) {
// 				return;
// 			} else {
// 				alert("Href: " + href);
// 				$.ajax({
// 					url: href,
// 					method: "GET",
// 					success: function(data) {
// 						$('.container').html(data);
// 					},
// 					error: function(xhr, status, error) {
// 						console.error("Co loi hoac " + href + " khong ton tai")
// 					}
// 				})
// 			}	
// 		});
// 	});
</script>

</html>