<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 9/28/2023
  Time: 2:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View product</title>
</head>
<body>
<h1>Product details</h1>
<p>
  <a href="/products">Back to product list</a>
</p>
<table>
  <tr>
    <td>nameProduct: </td>
    <td>${requestScope["productManagement"].getNameProduct}</td>
  </tr>
  <tr>
    <td>Price: </td>
    <td>${requestScope["productManagement"].getPrice}</td>
  </tr>
  <tr>
    <td>Describe: </td>
    <td>${requestScope["productManagement"].getDescribe}</td>
  </tr>
</table>
</body>
</html>
