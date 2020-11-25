<%--
  User: AmirS
  Date: 2020-11-24
  Time: 11:42 p.m.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Week 11 Lab</title>
</head>
<body>
<h1>Forgot Password</h1>
<form action="${pageContext.request.contextPath}/forgotPassword" method="post">
    <label for="email">Email Address:
        <input type="text" id="email" name="email">
    </label>
    <input type="submit" name="action" value="Submit">
    <br>
    <p>${requestScope.response}</p>
</form>
</body>
</html>
