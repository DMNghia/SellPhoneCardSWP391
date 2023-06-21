<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

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
    <link href="${pageContext.request.contextPath}/admin/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/admin/assets/css/light-bootstrap-dashboard.css?v=2.0.0 "
          rel="stylesheet"/>
    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link href="${pageContext.request.contextPath}/admin/assets/css/demo.css" rel="stylesheet"/>
    <style>
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
<div class="row col-6 text-center" id="deleteDiv">
    <h4 style="text-align: center"><b>Bạn có chắc muốn xóa chứ</b></h4>
    <form action="storage" method="post">
        <input name="id" id="idInputDeleteDiv" class="d-none">
        <input name="page" value='${request.getParameter("page")}' class="d-none">
        <button class="btn" type="submit" name="option" value="delete" style="background-color: #cc2127;color: #ffffff;cursor: pointer;">
            Xóa
        </button>
        <button type="button" class="btn" id="closeButtonDelete" style="cursor: pointer;background-color: #01b901;color: #ffffff;">
            Hủy
        </button>
    </form>
</div>
<div class="row col-6" id="updateDiv">
    <button type="button" aria-hidden="true" class="close" data-dismiss="alert" id="closeButton"
            style="cursor: pointer">
        <i class="nc-icon nc-simple-remove"></i>
    </button>
    <form action="storage" method="post" class="">
        <div class="row">
            <div class="col-md-8 pr-1">
                <div class="form-group">
                    <input name="page" value='${param.page}' class="d-none">
                    <label>Id</label>
                    <input type="text" class="form-control" id="idInputUpdateDiv"
                           readonly name="id">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-8 pr-1">
                <div class="form-group">
                    <label>Số seri</label>
                    <input type="text" class="form-control" id="seriInputUpdateDiv"
                           name="seri">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-8 pr-1">
                <div class="form-group">
                    <label>Số thẻ</label>
                    <input type="text" class="form-control" id="cardNumberInputUpdateDiv"
                           name="cardNumber">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-8 pr-1">
                <div class="form-group">
                    <label>Hạn sử dụng</label>
                    <input type="text" class="form-control" id="expiredAtInputUpdateDiv"
                           name="expiredAt">
                </div>
            </div>
        </div>
        <div class="row">
            <button type="submit" name="option" value="update" class="btn pr-1"
                    style="cursor: pointer;background-color: #01b901;color: #ffffff;">
                Cập nhật
            </button>
        </div>
    </form>
</div>
<div class="wrapper">
    <div class="sidebar" data-image="${pageContext.request.contextPath}/admin/assets/img/sidebar-5.jpg">
        -->
        <div class="sidebar-wrapper">
            <div class="logo">
                <a href="/" class="simple-text">
                    SWP391 GROUP5
                </a>
            </div>
            <ul class="nav">
                <li>
                    <a class="nav-link" href="/">
                        <i class="nc-icon nc-chart-pie-35"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                <li>
                    <a class="nav-link" href="changeProfile">
                        <i class="nc-icon nc-circle-09"></i>
                        <p>Thông tin</p>
                    </a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="storage">
                        <i class="nc-icon nc-notes"></i>
                        <p>Sản phẩm</p>
                    </a>
                </li>
                <li>
                    <a class="nav-link" href="order">
                        <i class="nc-icon nc-paper-2"></i>
                        <p>Mua hàng</p>
                    </a>
                </li>
                <li>
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/icons.jsp">
                        <i class="nc-icon nc-atom"></i>
                        <p>Icons</p>
                    </a>
                </li>
                <li>
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/maps.jsp">
                        <i class="nc-icon nc-pin-3"></i>
                        <p>Maps</p>
                    </a>
                </li>
                <li>
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/notifications.jsp">
                        <i class="nc-icon nc-bell-55"></i>
                        <p>Notifications</p>
                    </a>
                </li>
                <li class="nav-item active active-pro">
                    <a class="nav-link active" href="${pageContext.request.contextPath}/admin/upgrade.jsp">
                        <i class="nc-icon nc-alien-33"></i>
                        <p>Upgrade to PRO</p>
                    </a>
                </li>
            </ul>
        </div>
    </div>
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
                                    <option value="all">Mệnh giá</option>
                                    <c:forEach var="product" items="${listProduct}">
                                        <option class="dropdown-item" ${String.valueOf(product.getPrice()).equals(param.price) ? "selected" : ""} value="${product.getPrice()}">${product.getPrice()}</option>
                                    </c:forEach>
                                </select>
                            </li>
                            <li class="nav-item dropdown" style="margin-left: 10px">
                                <select name="supplier" class="h-100 border-0" style="background-color: transparent;color: #5e5e5e;cursor: pointer">
                                    <option value="all">Nhà phát hành</option>
                                    <c:forEach var="product" items="${listProduct}">
                                        <option class="dropdown-item" ${String.valueOf(product.getId()).equals(param.supplier) ? "selected" : ""} value="${product.getId()}">${product.getSupplier().getName()}</option>
                                    </c:forEach>
                                </select>
                            </li>
                            <li class="nav-item" style="margin-left: 10px">
                                <input type="text" name="search" placeholder="Tìm tên sản phẩm" class="h-50 border-0" value="${param.search}">
                                <button type="submit" class="nav-link border-0" style="cursor: pointer">
                                    <i class="nc-icon nc-zoom-split"></i>
                                    <span class="d-lg-block">&nbsp;Search</span>
                                </button>
                            </li>
                        </ul>
                    </form>
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="logout">
                                <span class="no-icon">Log out</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- End Navbar -->
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card strpied-tabled-with-hover">
                            <div class="card-header ">
                                <h4 class="card-title">Striped Table with Hover</h4>
                                <p class="card-category">Here is a subtitle for this table</p>
                            </div>
                            <div class="card-body table-full-width table-responsive">
                                <table class="table table-hover table-striped">
                                    <thead>
                                    <th>ID</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Giá</th>
                                    <th>Tạo lúc</th>
                                    <th>Tạo bởi</th>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="storage" items="${listStorage}">
                                        <tr>
                                            <td>${storage.getId()}</td>
                                            <td>${storage.getProduct().getName()}</td>
                                            <td class="price_storage">${storage.getProduct().getPrice()}</td>
                                            <td>${storage.getCreatedAt()}</td>
                                            <td>${storage.getCreatedBy().getAccount()}</td>
                                            <td>
                                                <button class="btn"
                                                        style="background-color: #01b901;color: #ffffff;cursor: pointer;"
                                                        onclick='showUpdateDiv(JSON.stringify(${storage.toJson()}))'>Thông tin
                                                </button>
                                            </td>
                                            <td>
                                                <button class="btn"
                                                        style="background-color: #cc2127;color: #ffffff;cursor: pointer;"
                                                        onclick="showDeleteAlert(${storage.getId()})">
                                                    Xóa
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="pagination">
            </div>
        </div>
        <footer class="footer">
            <div class="container-fluid">
                <nav>
                    <ul class="footer-menu">
                        <li>
                            <a href="home">
                                Home
                            </a>
                        </li>
                    </ul>
                    <p class="copyright text-center">
                        ©
                        <script>
                            document.write(new Date().getFullYear())
                        </script>
                        SWP391 group5, made with love for a better web
                    </p>
                </nav>
            </div>
        </footer>
    </div>
</div>
<!--   Core JS Files   -->
<script src="${pageContext.request.contextPath}/admin/assets/js/core/jquery.3.2.1.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/admin/assets/js/core/popper.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/admin/assets/js/core/bootstrap.min.js" type="text/javascript"></script>
<!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
<script src="${pageContext.request.contextPath}/admin/assets/js/plugins/bootstrap-switch.js"></script>
<!--  Google Maps Plugin    -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
<!--  Chartist Plugin  -->
<script src="${pageContext.request.contextPath}/admin/assets/js/plugins/chartist.min.js"></script>
<!--  Notifications Plugin    -->
<script src="${pageContext.request.contextPath}/admin/assets/js/plugins/bootstrap-notify.js"></script>
<!-- Control Center for Light Bootstrap Dashboard: scripts for the example pages etc -->
<script src="${pageContext.request.contextPath}/admin/assets/js/light-bootstrap-dashboard.js?v=2.0.0 "
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/admin/assets/js/demo.js"></script>
<c:if test="${message != null}">
    <script type="text/javascript">
        setTimeout(demo.showNotify("${message}"), 100);
    </script>
</c:if>
</body>


<script type="text/javascript">
    var priceStorage = document.querySelectorAll(".price_storage");
    priceStorage.forEach( p => {
        p.innerText = parseInt(p.innerText).toLocaleString();
    });

    function showDeleteAlert(id) {
        const div = document.getElementById("deleteDiv");
        const closeButton = document.getElementById("closeButtonDelete");
        document.getElementById("idInputDeleteDiv").value = id;
        const body = document.querySelector(".wrapper");

        if (div.style.top === "-100%") {
            div.style.display = "block";
            body.style.overflow = "hidden"; // Prevent scrolling while the div is open
            setTimeout(function () {
                div.style.top = "0";
                body.classList.add("blur"); // Add class to blur the background
            }, 10);
        } else {
            div.style.top = "-100%";
            setTimeout(function () {
                div.style.display = "none";
                body.style.overflow = ""; // Re-enable scrolling when the div is closed
                body.classList.remove("blur"); // Remove class to remove the background blur
            }, 500);
        }

        closeButton.addEventListener("click", function () {
            div.style.top = "-100%";
            setTimeout(function () {
                div.style.display = "none";
                body.style.overflow = ""; // Re-enable scrolling when the div is closed
                body.classList.remove("blur"); // Remove class to remove the background blur
            }, 500);
        });
    }

    function showUpdateDiv(storage) {
        console.log(storage);
        const div = document.getElementById("updateDiv");
        const closeButton = document.getElementById("closeButton");
        const body = document.querySelector(".wrapper");
        var json = JSON.parse(storage);
        document.getElementById("idInputUpdateDiv").value = json.id;
        document.getElementById("seriInputUpdateDiv").value = json.serialNumber;
        document.getElementById("cardNumberInputUpdateDiv").value = json.cardNumber;
        document.getElementById("expiredAtInputUpdateDiv").value = json.expiredAt;

        if (div.style.top === "-100%") {
            div.style.display = "block";
            body.style.overflow = "hidden"; // Prevent scrolling while the div is open
            setTimeout(function () {
                div.style.top = "0";
                body.classList.add("blur"); // Add class to blur the background
            }, 10);
        } else {
            div.style.top = "-100%";
            setTimeout(function () {
                div.style.display = "none";
                body.style.overflow = ""; // Re-enable scrolling when the div is closed
                body.classList.remove("blur"); // Remove class to remove the background blur
            }, 500);
        }

        closeButton.addEventListener("click", function () {
            div.style.top = "-100%";
            setTimeout(function () {
                div.style.display = "none";
                body.style.overflow = ""; // Re-enable scrolling when the div is closed
                body.classList.remove("blur"); // Remove class to remove the background blur
            }, 500);
        });
    }

    let pages = ${totalPageNumbers};

    document.getElementById('pagination').innerHTML = createPagination(pages, ${pageNumber});

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

</script>
</html>
