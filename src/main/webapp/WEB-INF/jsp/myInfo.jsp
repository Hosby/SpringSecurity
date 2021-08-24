<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2021-08-13 0013
  Time: 오후 1:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Security Home</title>
</head>

<body>
<h1>아이디 : <input type="text" value="${userName}" /></h1>
<h1>비밀번호 : <input type="text" value="${password}" /></h1>

<form action="${pageContext.request.contextPath}/logout">
    <input type="submit" value="로그아웃">
</form>

</body>
</html>