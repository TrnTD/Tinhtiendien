<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý giá điên</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap Icons -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.1/font/bootstrap-icons.css"
	rel="stylesheet">

</head>
<body>
<style><%@include file="/WEB-INF/resource/assets/css/style-quanly.css"%></style>
    <div class="container">
        <c:if test="${not empty message}">
            <div id="alertMessage" class="alert alert-success" role="alert" style="margin-top: 10px; display: none;">${message}</div>
        </c:if>
        <div class="container mt-5">
            <div class="table-wrapper" style="box-shadow: rgba(0, 0, 0, 0.5) 0px 5px 15px;">
                <div class="table-title">
                    <div class="row">
                        <div class="col-md-6">
                            <h2>Quản lý <b>Giá Điện</b></h2>
                        </div>
                        <div class="col-sm-6 text-sm-end">
                            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addEmployeeModal" onclick="setSelectForm('addForm', '')" style="background-color: #5cb85c;">
                                <i class="material-icons">&#xE147;</i> <span>Thêm giá điện mới</span>
                            </button>
                        </div>
                    </div>
                </div>
                <table class="table table-hover table-sm">
                    <thead>
                        <tr>
                            <th style="width: 200px;">Bậc Điện</th>
                            <th style="width: 200px;">Giá Điện</th>
                            <th style="width: 300px;"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="TriSo" varStatus="i" items="${list_giaDien}">
                            <tr>
                                <td>${TriSo.bacDien}</td>
                                <td>${TriSo.giaDien}</td>
                                <td style="display: flex; justify-content: center; gap: 20px;">
                                    <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#editEmployeeModal" onclick="setSelectForm('editForm', '${TriSo.bacDien}')">
                                        <i class="bi bi-pencil-fill"></i> Chỉnh sửa
                                    </button>
                              
                                    <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteEmployeeModal" onclick="setSelectForm('deleteForm', '${TriSo.bacDien}')">
                                        <i class="bi bi-trash-fill"></i> Xoá
                                    </button>
                                </td>
                                
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <form method="POST">
                <input type="hidden" id="actionForm" name="action" value="">
                <input type="hidden" id ="bacDien_key" name="bacDien_key" value = "${TriSo.bacDien}">
                
                <!-- Add Employee Modal -->
                <div class="modal fade" id="addEmployeeModal" tabindex="-1" aria-labelledby="addEmployeeModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="addEmployeeModalLabel">Thêm</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <!-- Add employee form -->
                                <div class="mb-3">
                                    <label class="form-label">Bậc Điện</label>
                                    <input type="text" class="form-control" name="add_bacDien" id = "add_bacDien_id">
										<h6 style="color:red; padding-left:5px;padding-top:5px" id="add_bacDien_id">${add_bacDien_id}</h6>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Giá Điện</label>
                                    <input type="text" class="form-control" name="add_giaDien" id = "add_giaDien_id">
                                	<h6 style="color:red; padding-left:5px;padding-top:5px" id="add_giaDien_id">${add_giaDien_id}</h6>
                                
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                                <button type="submit" class="btn btn-primary">Thêm</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Edit Employee Modal -->
                <div class="modal fade" id="editEmployeeModal" tabindex="-1" aria-labelledby="editEmployeeModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="editEmployeeModalLabel">Chỉnh Sửa</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <!-- Edit employee form -->
                                <div class="mb-3">
                                    <label class="form-label">Giá Tiền</label>
                                    <input type="text" class="form-control" name="edit_giaDien" id = "edit_giaDien_id">
        							<h6 style="color:red; padding-left:5px;padding-top:5px" id="edit_giaDien_id">${edit_giaDien_id}</h6>
                               		
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                                <button type="submit" class="btn btn-primary">Lưu Thay Đổi</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Delete Employee Modal -->
                <div class="modal fade" id="deleteEmployeeModal" tabindex="-1" aria-labelledby="deleteEmployeeModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="deleteEmployeeModalLabel">Xoá</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <p>Bạn có chắc muốn xoá không?</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                                <button type="submit" class="btn btn-danger">Xác nhận</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    
    <script>
        function setSelectForm(form, bacDien) {
            document.getElementById("bacDien_key").value = bacDien;
            sessionStorage.setItem("formType", form);
            console.log(sessionStorage.getItem("formType"));
            var formType = sessionStorage.getItem("formType");
            if (formType === "editForm") {
                document.getElementById("actionForm").value = 'edit';
            } else if (formType === "deleteForm") {
                document.getElementById("actionForm").value = 'delete';
            } else if (formType === "addForm") {
                document.getElementById("actionForm").value = 'add';
            }

        }

        window.addEventListener('beforeunload', function() {
            sessionStorage.setItem("add_bacDien", document.querySelector('input[name="add_bacDien"]').value);
            sessionStorage.setItem("add_giaDien", document.querySelector('input[name="add_giaDien"]').value);
            sessionStorage.setItem("edit_giaDien", document.querySelector('input[name="edit_giaDien"]').value);
            sessionStorage.setItem("bacDien_key", document.getElementById("bacDien_key").value);
        });
		
       
        document.addEventListener("DOMContentLoaded", function() {
            var message = "${message}";
            console.log(message);
            
            var bacDien_keyValue = sessionStorage.getItem("bacDien_key");
            document.getElementById("bacDien_key").value = bacDien_keyValue;
            
            if (message && message.trim() !== "") {
                var tb = document.getElementById('alertMessage');
                tb.style.display = 'block'; // Hiển thị thông báo

                // Ẩn thông báo sau 3 giây
                setTimeout(function() {
                    tb.style.display = 'none';
                }, 2500);

                // Xoá session
                sessionStorage.removeItem("formType");
                sessionStorage.removeItem("add_bacDien");
                sessionStorage.removeItem("add_giaDien");
                sessionStorage.removeItem("edit_giaDien");
            } else {
                const formType = sessionStorage.getItem("formType");
                if (formType === "editForm") {
                    document.getElementById("actionForm").value = 'edit';
                    
                } else if (formType === "deleteForm") {
                    document.getElementById("actionForm").value = 'delete';
                } else if (formType === "addForm") {
                    document.getElementById("actionForm").value = 'add';
                }

                var addBacDienValue = sessionStorage.getItem("add_bacDien");
                var addGiaDienValue = sessionStorage.getItem("add_giaDien");
                var editGiaDienValue = sessionStorage.getItem("edit_giaDien");

                document.querySelector('input[name="add_bacDien"]').value = addBacDienValue;
                document.querySelector('input[name="add_giaDien"]').value = addGiaDienValue;
                document.querySelector('input[name="edit_giaDien"]').value = editGiaDienValue;

                
             
                if (formType === "editForm") {
                    // Hiển thị form chỉnh sửa khi session storage có giá trị "editForm"
                    $('#editEmployeeModal').modal('show');
                } else if (formType === "deleteForm") {
                    // Hiển thị form xoá khi session storage có giá trị "deleteForm"
                    $('#deleteEmployeeModal').modal('show');
                } else if (formType === "addForm") {
                    $('#addEmployeeModal').modal('show');
                }
            }
            
            // Gắn sự kiện hidden.bs.modal cho tất cả các modal
            $('.modal').on('hidden.bs.modal', function () {
                sessionStorage.removeItem("formType");
                sessionStorage.removeItem("add_bacDien");
                sessionStorage.removeItem("add_giaDien");
                sessionStorage.removeItem("edit_giaDien");

                let headers = document.querySelectorAll('h6');
                headers.forEach(function(header) {
                    header.textContent = '';
                    var inputId = header.id;
                    // Tìm ô input tương ứng bằng id của thẻ h6
                    var inputElement = document.getElementById(inputId);
                    inputElement.classList.remove('error-input');
                    inputElement.value = "";
                });
            });

            /* ============================ THÔNG BÁO NHẬP DỮ LIỆU ===================================== */
            function iterateErrorMessages() {
                var errorMessages = document.querySelectorAll('h6');
                errorMessages.forEach(function(errorMessage) {
                    // Trim để loại bỏ các khoảng trắng và kiểm tra nếu thông báo không rỗng
                    if (errorMessage.textContent.trim() !== "") {
                        // Lấy id của thẻ h6
                        var inputId = errorMessage.id;
                        // Tìm ô input tương ứng bằng id của thẻ h6
                        var inputElement = document.getElementById(inputId);
                        // Kiểm tra xem ô input có tồn tại không
                        if (inputElement) {
                            // Thêm lớp CSS 'error-input' cho ô input
                            inputElement.classList.add('error-input');
                        }
                    }
                });
            }
            // gọi hàm
            iterateErrorMessages();
            // gọi llại hàm và giữ các thông báo ở 1 giây
            setInterval(iterateErrorMessages, 1000);
            
        })
	

    </script>
    
    
</body>
</html>