<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="register-container">
		<h1>新規登録</h1>
		<form action="RegisterServlet" method="post">
			<label>ユーザー名</label>
			<input type="text" name="username" required>
			<label>パスワード</label>
			<input type="password" name="password" required>
			<button type="submit">登録</button>
		</form>
		<p><a href="login.jsp">ログインはこちら</a></p>
		<!-- 登録エラーメッセージ  -->
		<c:if test="${not empty errorMessage}">
			<p class="error-message">${errorMessage}</p>
		</c:if>
	</div>
</body>
</html>