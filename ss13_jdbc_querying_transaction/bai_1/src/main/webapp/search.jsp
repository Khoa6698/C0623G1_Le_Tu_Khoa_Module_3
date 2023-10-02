<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 9/29/2023
  Time: 6:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SEARCH USER</title>
</head>
<body>
<h1>Kết quả mà bạn tìm</h1>
<table>
  <thead>
  <tr>
    <th>STT</th>
    <th>NAME</th>
    <th>EMAIL</th>
    <th>COUNTRY</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${userList}" var="user" varStatus="loop">
  <tr>
    <td>${loop.count}</td>
    <td>${user.getName}</td>
    <td>${user.getEmail}</td>
    <td>${user.getCountry}</td>
  </tr>
  </c:forEach>;
  </tbody>
</table>
</body>
</html>
