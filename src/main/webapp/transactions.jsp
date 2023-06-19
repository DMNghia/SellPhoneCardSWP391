<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList" %>
<%@page import="model.Supplier" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <link href="${pageContext.request.contextPath}/admin/assets/css/pagination.css" rel="stylesheet">
        <link rel="apple-touch-icon" sizes="76x76"
              href="${pageContext.request.contextPath}/admin/assets/img/apple-icon.png">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/admin/assets/img/favicon.ico">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
        <title>Light Bootstrap Dashboard - Free Bootstrap 4 Admin Dashboard by Creative Tim</title>
        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no'
              name='viewport'/>
        <!--     Fonts and icons     -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"/>
        <!-- CSS Files -->
        <link href="${pageContext.request.contextPath}/user/assets/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/user/assets/css/light-bootstrap-dashboard.css?v=2.0.0 "
              rel="stylesheet"/>
        <!-- CSS Just for demo purpose, don't include it in your project -->

        <link href="${pageContext.request.contextPath}/user/assets/css/demo.css" rel="stylesheet"/>
        <!--        <style>
                    table {
                        border-collapse: collapse;
                        width: 100%;
                    }
        
                    th, td {
                        border: 1px solid black;
                        padding: 8px;
                        text-align: left;
                    }
                </style>-->
        <style>
            table {
                        border-collapse: collapse;
                        width: 100%;
                    }
        
                    th, td {
                        border: 1px solid black;
                        padding: 8px;
                        text-align: left;
                    }
            #updateDiv {
                display: none;
                position: fixed;
                top: -100%;
                left: 50%;
                transform: translateX(-50%);
                background-color: #ebc8fa;
                padding: 20px;
                transition: top 0.3s;
                z-index: 9999;
                border-radius: 10px;
                box-shadow: #464646 0 0 7px;
            }
            #deleteDiv{
                display: none;
                position: fixed;
                top: -100%;
                left: 50%;
                transform: translateX(-50%);
                background-color: #ebc8fa;
                padding: 20px;
                transition: top 0.3s;
                z-index: 9999;
                border-radius: 10px;
                box-shadow: #464646 0 0 7px;
            }

        </style>
    </head>
    <body>
        <h1>Lịch sử giao dịch</h1>

        <table>
            <tr>
                <th>Mã giao dich</th>
                <th>Số tiền</th>
                <th>Loại giao dịch</th>
                <th>Xử lí</th>
                <th>Ghi chú giao dịch</th>                
                <th>Người tạo giao dịch</th>                               
                <th>Thời gian tạo</th>
                <th>Cập nhật cuối</th>
                <th>Hành động</th>   
            </tr>

            <c:forEach var="at" items="${transactionsList}">
                <tr>
                    <th>${at.getId()}</th>
                    <th>${at.getMoney()}</th>
                        <c:if test="${at.isType()==true}">
                        <th>+</th>
                        </c:if>
                        <c:if test="${at.isType()==false}">
                        <th>-</th>
                        </c:if>
                        <c:if test="${at.isStatus()==true}">
                        <th>Đã xử lí</th>
                        </c:if>
                        <c:if test="${at.isStatus()==false}">
                        <th>Chưa xử lí</th>
                        </c:if>
                    <th>${at.getNote()}</th>
                    <th>${at.getUser().getAccount()}</th>
                    <th>${at.getCreateAt()}</th>
                    <th>${at.getUpdatedAt()}</th>
                    <td>
                        <button class="btn"
                                style="background-color: #01b901;color: #ffffff;cursor: pointer;"
                                onclick='showUpdateDiv(JSON.stringify(${storage.toJson()}))'>Thông tin
                        </button>
                    </td>
                </tr>  
            </c:forEach>

            <c:forEach begin="${1}" end="${soTrang}" var="i">
                <a class="${i==page?"active":""}" href="transactions?page=${i}">${i}</a>
            </c:forEach>
        </table>
        <!--    <center>
        
                <div id="pagination">
                </div>
            </center>-->
        <div class="main-panel">
            <!-- Navbar -->
            <nav class="navbar navbar-expand-lg " color-on-scroll="500">
                <div class="container-fluid">
                    <a class="navbar-brand" href="storage"> Sản phẩm </a>
                    <div class="collapse navbar-collapse justify-content-end" id="navigation">
                        <form method="get" action="storage">
                            <ul class="nav navbar-nav mr-auto">
                                <li class="dropdown nav-item" style="margin-left: 10px">
                                    <select name="price" class="h-100 border-0" style="background-color: transparent;color: #5e5e5e;cursor: pointer">
                                        <option value="all">Loại giao dịch</option>
                                        <c:forEach var="product" items="${listProduct}">
                                            <option class="dropdown-item" ${String.valueOf(product.getPrice()).equals(param.price) ? "selected" : ""} value="${product.getPrice()}">${product.getPrice()}</option>
                                        </c:forEach>
                                    </select>
                                </li>
                                <li class="nav-item dropdown" style="margin-left: 10px">
                                    <select name="supplier" class="h-100 border-0" style="background-color: transparent;color: #5e5e5e;cursor: pointer">
                                        <option value="all">Xử lí</option>
                                        <c:forEach var="product" items="${listProduct}">
                                            <option class="dropdown-item" ${String.valueOf(product.getId()).equals(param.supplier) ? "selected" : ""} value="${product.getId()}">${product.getSupplier().getName()}</option>
                                        </c:forEach>
                                    </select>
                                </li>
                                <li class="nav-item" style="margin-left: 10px">
                                    <input type="text" name="search" placeholder="Tìm giao dịch" class="h-50 border-0" value="${param.search}">
                                    <button type="submit" class="nav-link border-0" style="cursor: pointer">
                                        <i class="nc-icon nc-zoom-split"></i>
                                        <span class="d-lg-block">&nbsp;Search</span>
                                    </button>
                                </li>
                            </ul>
                        </form>
                    </div>
                </div>
                </body>
                <!--<script>
                    let pages = ${totalPageNumbers};
                
                //    document.getElementById('pagination').innerHTML = createPagination(pages,${1});
                    document.getElementById('pagination').innerHTML = 1;
                
                    function createPagination(pages, page) {
                        let str = '<ul class="page">';
                        let active;
                        let pageCutLow = page - 1;
                        let pageCutHigh = page + 1;
                        // Show the Previous button only if you are on a page other than the first
                        if (page > 1) {
                            str += '<li onclick="createPagination(pages, ' + (page - 1) + ')" class="page__btn"><a href="storage?page=' + (page - 1) + '" class="w-100 h-100 d-flex text-justify justify-content-center"><span>&laquo;</span></a></li>';
                        }
                        // Show all the pagination elements if there are less than 6 pages total
                        if (pages < 6) {
                            for (let p = 1; p <= pages; p++) {
                                active = page == p ? "active" : "";
                                str += '<li onclick="createPagination(pages, ' + p + ')" class="page__numbers ' + active + '"><a href="storage?page=' + p + '" class="w-100 h-100 d-flex text-justify justify-content-center"><span>' + p + '</span></a></li>';
                            }
                        }
                        // Use "..." to collapse pages outside of a certain range
                        else {
                            // Show the very first page followed by a "..." at the beginning of the
                            // pagination section (after the Previous button)
                            if (page > 2) {
                                str += `<li onclick="createPagination(pages, 1)" class="page__numbers"><a href="storage?page=1" class="w-100 h-100 d-flex text-justify justify-content-center"><span>1</span></a></li>`;
                                if (page > 3) {
                                    str += `<li class="page__dots"><span>...</span></li>`;
                                }
                            }
                            // Determine how many pages to show after the current page index
                            if (page === 1) {
                                pageCutHigh += 2;
                            } else if (page === 2) {
                                pageCutHigh += 1;
                            }
                            // Determine how many pages to show before the current page index
                            if (page === pages) {
                                pageCutLow -= 2;
                            } else if (page === pages - 1) {
                                pageCutLow -= 1;
                            }
                            // Output the indexes for pages that fall inside the range of pageCutLow
                            // and pageCutHigh
                            for (let p = pageCutLow; p <= pageCutHigh; p++) {
                                if (p === 0) {
                                    p += 1;
                                }
                                if (p > pages) {
                                    continue
                                }
                                active = page == p ? "active" : "";
                                str += '<li onclick="createPagination(pages, ' + p + ')" class="page__numbers ' + active + '"><a href="storage?page=' + p + '" class="w-100 h-100 d-flex text-justify justify-content-center"><span>' + p + '</span></a></li>';
                            }
                            // Show the very last page preceded by a "..." at the end of the pagination
                            // section (before the Next button)
                            if (page < pages - 1) {
                                if (page < pages - 2) {
                                    str += '<li class="page__dots"><span>...</span></li>';
                                }
                                str += '<li onclick="createPagination(pages, pages)" class="page__numbers"><a href="storage?page=' + pages + '" class="w-100 h-100 d-flex text-justify justify-content-center"><span>' + pages + '</span></a></li>';
                            }
                        }
                        // Show the Next button only if you are on a page other than the last
                        if (page < pages) {
                            str += '<li onclick="createPagination(pages, ' + (page + 1) + ')" class="page__btn"><a href="storage?page=' + (page + 1) + '" class="w-100 h-100 d-flex text-justify justify-content-center"><span>&raquo;</span></a></li>';
                        }
                        str += '</ul>';
                        // Return the pagination string to be outputted in the pug templates
                        document.getElementById('pagination').innerHTML = str;
                        return str;
                    }
                </script>-->
                </html>