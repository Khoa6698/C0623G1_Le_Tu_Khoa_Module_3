<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 9/29/2023
  Time: 3:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CREATE USER</title>
</head>
<body>
<form action="/?action=create" method="post">
    ID: <input type="number" name="id"><br>
    NAME: <input type="text" name="name"><br>
    EMAIL: <input type="text" name="email"><br>
    COUNTRY: <input type="text" name="country">
    <input type="submit" value="create">
</form>
</body>
</html>
