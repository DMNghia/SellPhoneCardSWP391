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
        <link rel="stylesheet" href="css/home.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

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
                                    <li><a href="#">Sản phẩm</a></li>
                                    <li><a href="#">Đơn hàng</a></li>
                                </ul>
                            </li>
                            <li><a href="#">Giao dịch</a>
                                    <ul>
                                        <li><a href="transactions">Lịch sử</a></li>
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

        <div class="content">
            <form action="">
                <p
                    style=" font-size:40px;margin:0px;line-height:29px;color:#06202D;font-weight:700;text-transform: uppercase">
                    Mua thẻ điện thoại </p>

                <div class="supplier">
                    <div class="buycard-title">
                        <form action="home" method="get" id="frm">
                            1.Chọn nhà cung cấp
                            <br> <c:forEach var="s" items="${imgList}">
                                <button style="cursor: pointer;" onclick="click()" name="id" value="${s.getId()}">
                                    <img src="${s.getImage()}" alt="alt"/>
                                </button>
                            </c:forEach>
                        </form>
                    </div>
                </div>

                <div class="price">
                    <div class="buycard-title">
                        2.Chọn mệnh giá
                        <c:forEach var="a" items="${priceList}">
                            <br>
                            <button style="font-weight: 500;font-size: 18px;
                                    line-height: 30px;
                                    color: #fff;
                                    background: #23a6f7;
                                    border-radius: 10px;
                                    padding: 10px 32px;
                                    border: none;
                                    margin: 10px">
                                <ul>
                                    <li style="list-style: none">${a.getPrice()} VND</li>
                                </ul>
                            </button>
                        </c:forEach>
                    </div>


                </div>

                <div class="quantity">
                    <div class="buycard-title">
                        3.Chọn số lượng
                        <br><input type="text"
                                   style=" padding: 5px 10px;  box-sizing: border-box;margin: 10px 40px; width:30% ">
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
        <script>
            function click() {
                document.getElementById("frm").submit();
            }
        </script>

    </body>

</html>
