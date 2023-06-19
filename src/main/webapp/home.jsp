<%-- 
    Document   : home
    Created on : May 26, 2023, 4:43:42 PM
    Author     : hp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList" %>
<%@page import="model.Supplier" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">

</head>

<body>
<header>
    <div class="menu">
        <nav>
            <div class="header1">
                <ul>
                    <li><a href="home">Trang chủ</a></li>
                    <li><a href="#">Mua hàng</a>
                        <ul>
                            <li><a href="#">Đơn hàng</a></li>
                        </ul>
                    </li>
                    <li><a href="#">Giao dịch</a>
                        <ul>
                            <li><a href="#">Lịch sử</a></li>
                        </ul>
                    </li>
                    <li><a href="#">Liên hệ</a></li>
                    <c:if test="${user != null}">
                        <li><span href="#">Số dư: ${user.getBalance()}</span></li>
                    </c:if>
                    <li><a href="#"><i class="fa-solid fa-cart-shopping"></i></a></li>
                    <c:if test="${user != null}">
                        <li><a href="#"><i class="fa-solid fa-circle-user"></i></a>
                            <ul>
                                <li><a href="changeProfile">Thông tin người dùng</a></li>
                                <li><a href="logout">Đăng xuất</a></li>
                            </ul>
                        </li>
                    </c:if>
                    <c:if test="${user == null}">
                        <li><a href="login">Đăng nhập</a></li>
                    </c:if>
                </ul>
            </div>
        </nav>
    </div>
</header>

<div class="main">
    <form action="">
        <div class="content-header container-fluid text-center" style="margin-bottom: 50px;">
            <p style="color: #8a8a8a">[ DỊCH VỤ ]</p>
            <h1 style="color: #000000">MUA THẺ ĐIỆN THOẠI ONLINE</h1>
            <h2 style="color: #000000">MUA THẺ ĐIỆN THOẠI, MUA THẺ CÀO ONLINE GIÁ ĐÚNG</h2>
        </div>
        <div class="container">
            <div class="radio-inputs container justify-content-between" style="margin-bottom: 100px;">
                <c:forEach var="s" items="${imgList}">
                    <label style="height: 130px;width: 24%" class="d-flex justify-content-center">
                        <input class="radio-input" type="radio" name="supplier" value="${s.getId()}">
                        <span class="radio-tile w-100" style="border-radius: 10px;">
                                <span class="radio-icon">
                                    <img style="max-width: 98%;height: auto;object-fit: cover" src="${s.getImage()}"
                                         alt="${s.getName()}"/>
                                </span>
                            </span>
                    </label>
                </c:forEach>
            </div>
            <div class="row justify-content-around" style="margin-bottom: 50px;">
                <div class="price-div col-5">
                    <div class="row justify-content-between" style="height: fit-content;margin-bottom: 50px">
                        <label style="height: 50px;width: 45%" class="d-flex justify-content-center">
                            <input class="radio-input" type="radio" name="price">
                            <span class="radio-tile w-100" style="border-radius: 10px;">
                                <span class="radio-icon">
                                    20.000đ
                                </span>
                            </span>
                        </label>
                        <label style="height: 50px;width: 45%" class="d-flex justify-content-center">
                            <input class="radio-input" type="radio" name="price">
                            <span class="radio-tile w-100" style="border-radius: 10px;">
                                <span class="radio-icon">
                                    10.000đ
                                </span>
                            </span>
                        </label>
                    </div>
                    <div class="row justify-content-between" style="height: fit-content;margin-bottom: 50px">
                        <label style="height: 50px;width: 45%" class="d-flex justify-content-center">
                            <input class="radio-input" type="radio" name="price">
                            <span class="radio-tile w-100" style="border-radius: 10px;">
                                <span class="radio-icon">
                                    30.000đ
                                </span>
                            </span>
                        </label>
                        <label style="height: 50px;width: 45%" class="d-flex justify-content-center">
                            <input class="radio-input" type="radio" name="price">
                            <span class="radio-tile w-100" style="border-radius: 10px;">
                                <span class="radio-icon">
                                    50.000đ
                                </span>
                            </span>
                        </label>
                    </div>
                    <div class="row justify-content-between" style="height: fit-content;margin-bottom: 50px">
                        <label style="height: 50px;width: 45%" class="d-flex justify-content-center">
                            <input class="radio-input" type="radio" name="price">
                            <span class="radio-tile w-100" style="border-radius: 10px;">
                                <span class="radio-icon">
                                    100.000đ
                                </span>
                            </span>
                        </label>
                        <label style="height: 50px;width: 45%" class="d-flex justify-content-center">
                            <input class="radio-input" type="radio" name="price">
                            <span class="radio-tile w-100" style="border-radius: 10px;">
                                <span class="radio-icon">
                                    200.000đ
                                </span>
                            </span>
                        </label>
                    </div>
                    <div class="row justify-content-between" style="height: fit-content;margin-bottom: 50px">
                        <label style="height: 50px;width: 45%" class="d-flex justify-content-center">
                            <input class="radio-input" type="radio" name="price">
                            <span class="radio-tile w-100" style="border-radius: 10px;">
                                <span class="radio-icon">
                                    300.000đ
                                </span>
                            </span>
                        </label>
                        <label style="height: 50px;width: 45%" class="d-flex justify-content-center">
                            <input class="radio-input" type="radio" name="price">
                            <span class="radio-tile w-100" style="border-radius: 10px;">
                                <span class="radio-icon">
                                    500.000đ
                                </span>
                            </span>
                        </label>
                    </div>
                </div>
                <div class="content-product col-5">

                </div>
            </div>
            <div class="row">
                <div class="quantity-div d-flex flex-wrap" style="margin-left: 10%;width: 90%;">
                    <label style="font-size: 18px;font-weight: 500;margin-right: 25px;line-height: 30px;">Số lượng thẻ
                        cần mua</label>
                    <span class="down_number" style="cursor: s-resize;">
                        <svg width="40" height="40" viewBox="0 0 40 40"
                             fill="none"
                             xmlns="http://www.w3.org/2000/svg">
                                                <circle cx="20" cy="20" r="20" fill="#1CA799"></circle>
                                                <line x1="10" y1="19" x2="31" y2="19" stroke="white"
                                                      stroke-width="2"></line>
                        </svg>
                    </span>
                    <input style="width: 80px;margin: 0 20px;height: 40px;border-radius: 5px;border: 0.5px solid #000000;"
                           type="text" maxlength="40" id="ctrlsoluongthe" name="ctrlsoluongthe" value="1"
                           class="input_card text-center" placeholder="Số lượng thẻ cần mua" title="Số lượng thẻ"
                           oninput="this.value = this.value.replace(/[^0-9.]/g, ''); this.value = this.value.replace(/(\..*)\./g, '$1');">
                    <span class="up_number" style="cursor: n-resize;">
                        <svg width="40" height="40" viewBox="0 0 40 40" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                <circle cx="20" cy="20" r="20" fill="#1CA799"></circle>
                                                <line x1="10" y1="20" x2="31" y2="20" stroke="white"
                                                      stroke-width="2"></line>
                                                <line x1="20" y1="30" x2="20" y2="9" stroke="white"
                                                      stroke-width="2"></line>
                        </svg>
                    </span>
                </div>
            </div>
        </div>
        <div>
            <button class="btn">Thêm vào giỏ hàng</button>
            <button class="btn">Mua ngay</button>
        </div>

    </form>
</div>
<footer class="footer">
    <div>
        Powered by: SWP391-Group5 ©2023
    </div>
</footer>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/core/bootstrap.min.js"></script>
<script>
    function click() {
        document.getElementById("frm").submit();
    }
</script>

</body>

</html>
