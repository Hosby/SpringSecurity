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
    <title>Security Login</title>
</head>

<body>
<h1>${message}</h1>
<form action="${pageContext.request.contextPath}/login/success">
    <div>
        아이디: <input type="text" name="userName"/>
    </div>
    <div>
        비밀번호: <input type="password" name="password"/>
        <input type="submit" value="로그인"/>
    </div>
</form>

<form action="${pageContext.request.contextPath}/joinForm">
    <input type="submit" value="회원가입">
</form>
</body>
</html>