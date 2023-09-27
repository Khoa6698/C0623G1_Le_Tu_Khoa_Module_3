<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Java Web</title>
</head>
<body>
<h1>Simple Calculator</h1>
<form action="/Calculator-servlet" method="post">
    <div>
        num1: <input type="number" name="num1" id="num1"><br>
        option:
        <select name="option" id="option">
            <option value="sum">Cộng</option>
            <option value="minus">Trừ</option>
            <option value="core">Nhân</option>
            <option value="divide">Chia</option>
        </select><br>
        num2: <input type="number" name="num2" id="num2"><br>
        <input type="submit" value="Tính">
    </div>
</form>
</body>
</html>