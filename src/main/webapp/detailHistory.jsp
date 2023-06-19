<%-- 
    Document   : detailHistory
    Created on : Jun 19, 2023, 6:55:55 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <tr>
                <td>Số tiền</td>
                <td>Loại giao dịch</td>
                <td>Đã xử lí</td>
                <td>Ghi chú</td>
                <td>Trạng thái giao dịch</td>
                <td>Người tạo</td>
            </tr>
            <c:forEach var="at" items="${transactionsList}">
                <tr>
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
                    <th><a href="detailHistory?id=${at.getId()}&mod=1">Thông tin</a></th>
                </tr>  
            </c:forEach>
<!--            <c:forEach  items="${data}" var="item">
                <tr>
                    <td><a href="detailProduct?id=${item.getProductId()}&mod=1">${item.getProductId()}</a></td>
                    <td>${item.getProductName()}</td>
                    <td>${item.getUnitPrice()}</td>
                    <td>${item.getUnitslnStock()}</td>
                    <td>${item.getImage()}</td>
                    <td>${item.getCategoryId()}</td>
                    <td>${item.getDiscontinued()}</td>
                    <td><a href="product?id=${item.getProductId()}&mod=1">Delete</a></td>
                </tr>
            </c:forEach>-->
        </table>
        
    </body>
</html>
