<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<table width="80%" border="1px">
    <thead>
    <tr>
        <th>STT</th>
        <th>Tên</th>
        <th>Ngày sinh</th>
        <th>Địa chỉ</th>
        <th>Ảnh</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${customerList}" var="customer" varStatus="count">
    <tr>
        <td>${count.count}</td>
        <td>${customer.name}</td>
        <td>${customer.birthDay}</td>
        <td>${customer.address}</td>
        <td><img width="100px" height="100px" src="${customer.img}" alt="${customer.name}"></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>