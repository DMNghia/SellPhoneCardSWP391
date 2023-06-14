<%--
  Created by IntelliJ IDEA.
  User: nghia
  Date: 6/13/23
  Time: 11:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/storage.css">
</head>
<body>
<div class="root">
    <header>
        <div class="menu">
            <nav>
                <div class="header1">
                    <ul>
                        <li><a href="home">Trang chủ</a></li>
                        <li><a href="#">Mua hàng</a>
                            <ul>
                                <li><a href="storage">Sản phẩm</a></li>
                                <li><a href="#">Đơn hàng</a></li>
                            </ul>
                        </li>
                        <li><a href="#">Giao dịch</a>
                            <ul>
                                <li><a href="#">Lịch sử</a></li>
                            </ul>
                        </li>
                        <li><a href="#">Liên hệ</a></li>
                        <li><a href="#">Số dư</a></li>
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

    </div>
</div>
</body>
</html>
