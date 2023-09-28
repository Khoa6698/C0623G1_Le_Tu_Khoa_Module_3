<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 9/28/2023
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Product</title>
</head>
<body>
<h1>Edit Product</h1>
<p>
  <c:if test='${requestScope["message"]}!=null'>
    <span>${requestScope["message"]}</span>
  </c:if>
</p>
<p>
  <a href="/products">Back to product list</a>
</p>
<form method="post">
  <fieldset>
    <legend>Product information</legend>
    <table>
      <tr>
        <td>Name Product: </td>
        <td><input type="text" name="nameProduct" id="nameProduct" value="${requestScope["productManagement"].getNameProduct()}"></td>
      </tr>
      <tr>
        <td>Name Product: </td>
        <td><input type="text" name="price" id="price" value="${requestScope["productManagement"].getPrice()}"></td>
      </tr>
      <tr>
        <td>Name Product: </td>
        <td><input type="text" name="describe" id="describe" value="${requestScope["productManagement"].getDescribe()}"></td>
      </tr>
      <tr>
        <td> </td>
        <td><input type="submit" value="Update product"></td>
      </tr>
    </table>
  </fieldset>
</form>
</body>
</html>
