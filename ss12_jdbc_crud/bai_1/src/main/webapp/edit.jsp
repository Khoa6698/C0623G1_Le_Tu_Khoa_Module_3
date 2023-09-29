<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 9/29/2023
  Time: 4:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EDIT USER</title>
</head>
<body>
<form action="/?action=edit&id=${user.getId()}" method="post">
  NAME: <input type="text" name="name" value="${user.getName()}"><br>
  EMAIL: <input type="text" name="email" value="${user.getEmail()}"><br>
  COUNTRY: <input type="text" name="country" value="${user.getCountry()}">
  <input type="submit" value="edit">
</form>
</body>
</html>
