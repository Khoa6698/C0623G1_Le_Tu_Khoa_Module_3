<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 9/27/2023
  Time: 11:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Simple Dictionary</title>
</head>
<body>
<%!
  Map<String, String> dic = new HashMap<>();
%>

<%
  dic.put("hello", "Xin chào");
  dic.put("how", "Thế nào");
  dic.put("book", "Quyển vở");
  dic.put("computer", "Máy tính");
  String search = request.getParameter("search");
  String result = dic.get(search);
  if (result != null) {
    System.out.println("Word: " + search);
    System.out.println("Result: " + result);
  } else {
    System.out.println("Not found");
  }
%>
</body>
</html>
