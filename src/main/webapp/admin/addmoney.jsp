<!-- admin_page.jsp -->
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Trang admin</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<h1>Trang admin</h1>

<form action="addmoney" method="post">
    <label for="userId">ID Người dùng:</label>
    <input type="text" id="userId" name="userId" value="${user.getId()}" readonly>
    <br>

    <label for="userAccount">ID Người dùng:</label>
    <input type="text" id="userAccount" name="userAccount" value="${user.getAccount()}" readonly>
    <br>

    <label for="balance">Số tiền cần cấp:</label>
    <input type="number" id="balance" name="balance" required>
    <br>

    <label for="note">Ghi chú:</label>
    <input type="text" id="note" name="note" required>
    <br>



    <button type="submit">Cấp tiền</button>
</form>

<div id="result"></div>

<script>
    $(document).ready(function () {
        $("#transactionForm").submit(function (event) {
            // Ngăn chặn gửi yêu cầu trực tiếp (refresh trang)
            event.preventDefault();

            // Lấy dữ liệu từ form
            var formData = {
                userId: $("#userId").val(),
                amount: $("#balance").val()
            };

            // Gửi yêu cầu POST thông qua AJAX đến Servlet
            $.ajax({
                type: "POST",
                url: "AdminAddMoneyController",
                data: formData,
                success: function (data) {
                    // Hiển thị kết quả từ Servlet lên trang
                    $("#result").html("<p>" + data + "</p>");
                },
                error: function (xhr, status, error) {
                    // Xử lý lỗi nếu có
                    console.log("Lỗi: " + error);
                }
            });
        });
    });
</script>
</body>
</html>
