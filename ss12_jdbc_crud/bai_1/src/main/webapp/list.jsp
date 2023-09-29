<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Table User</title>
</head>
<body>
<h1>Table User</h1>
<br/>
<table border="1px">
    <thead>
    <tr>
        <th>STT</th>
        <th>NAME</th>
        <th>EMAIL</th>
        <th>COUNTRY</th>
        <th>UPDATE</th>
        <th>DELETE</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userList}" var="user" varStatus="loop">
    <tr>
        <td>${loop.count}</td>
        <td>${user.name}</td>
        <td>${user.email}</td>
        <td>${user.country}</td>
        <td><a href="/?action=edit&id=${user.getId()}">edit</a></td>
        <td><a href="">delete</a></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<a href="/?action=create">Thêm mới</a>
<form action="?action=search" method="post">
    <input type="text" id="country" name="country"><br><br>
    <input type="submit" value="Search">
</form>
</body>
</html>