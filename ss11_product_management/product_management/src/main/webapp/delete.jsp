<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 9/28/2023
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delete Product</title>
</head>
<body>
<h1>Delete Product</h1>
<p>
    <a href="/products">Back to product list</a>
</p>
<form>
    <h3>Are you sure?</h3>
    <fieldset>
        <legend>Product information</legend>
        <table>
            <tr>
                <td>nameProduct: </td>
                <td>${sessionScope["productManagement"].getNameProduct}</td>
            </tr>
            <tr>
                <td>Price: </td>
                <td>${sessionScope["productManagement"].getPrice}</td>
            </tr>
            <tr>
                <td>Describe: </td>
                <td>${sessionScope["productManagement"].getDescribe}</td>
            </tr>
            <tr>
                <td><input type="submit" value="Delete product"></td>
                <td><a href="/products">Back to product list</a></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
