<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTLタグライブラリの宣言 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="login-container">
		<h1>ログイン</h1>
	    <form action="LoginServlet" method="post">
	        <label>ユーザー名</label>
	        <input type="text" name="username" required>
	        <label>パスワード</label>
	        <input type="password" name="password" required>
	        <button type="submit">ログイン</button>
	    </form>
	    <p><a href="register.jsp">新規登録はこちら</a></p>
	    <!-- ログインエラーメッセージ -->
	    <c:if test="${not empty errorMessage}">
	        <p class="error-message">${errorMessage}</p>
	    </c:if>	    	
	</div>
</body>
</html>