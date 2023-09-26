<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Discount Calculator</title>
</head>
<body>
<form action="/display-discount" method="post">
    <label for="num1" >Product Description :</label>
    <input type="text" name="product" id="num1"> <br>
    <label for="num2" >List Price :</label>
    <input type="number" name="price" id="num2"> <br>
    <label for="num3">Discount Percent :</label>
    <input type="number" name="percent" id="num3"> <br>
    <input type="submit" value="Discount Amount">
</form>
<%--Discount Amount: ${discountAmount}--%>
<%--Discount Price: ${discountPrice}--%>
<%--<a href="display-discount">Calculate Discount</a>--%>
</body>
</html>