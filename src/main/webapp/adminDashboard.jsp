<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者ダッシュボード</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="header">
		<p class="welcome-message">ようこそ、管理者 ${sessionScope.user} さん</p>
		<a href="LogoutServlet" class="logout-link">ログアウト</a>	
	</div>
	<h1>管理者ダッシュボード</h1>
	<ul>
        <li><a href="manageUsers.jsp">ユーザー管理</a></li>
        <li><a href="product">商品管理</a></li>
    </ul>
</body>
</html>