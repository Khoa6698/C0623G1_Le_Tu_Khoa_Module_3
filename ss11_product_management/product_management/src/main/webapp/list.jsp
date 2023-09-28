<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<h1>Products</h1>
<br/>
<p><a href="/products?action=create">Thêm sản phẩm</a></p>
<table style="border: 1px; ">
    <tr>
        <th>STT</th>
        <th>Name</th>
        <th>Price</th>
        <th>Describe</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>

    <c:forEach items='${products}' var="products" varStatus="loop">
        <tr>
            <td>${loop.count}</td>
            <td>${products.getNameProduct}</td>
            <td>${products.getPrice}</td>
            <td>${products.getDescribe}</td>
            <td><a href="/products?action=edit&id=${products.getId()}">Edit</a></td>
            <td><a href="/products?action=delete&id=${products.getId()}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>