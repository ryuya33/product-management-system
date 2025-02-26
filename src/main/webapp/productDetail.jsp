<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTLタグライブラリの宣言 -->
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    // ユーザーのセッションを取得
    HttpSession userSession = request.getSession(false);
    if (userSession == null || userSession.getAttribute("user") == null) {
        // 未ログインならログインページへリダイレクト
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>商品詳細</title>
	<link rel="stylesheet" href="css/style.css"> 
</head>
<body>
	<h1>商品詳細</h1>
	
	<div class="product-info">
    	<!-- 商品画像 -->
		<div class="product-image">
			<c:if test="${not empty product.image}">
				<img src="uploads/${product.image}" alt="${product.name}">
			</c:if>
		</div>
	
		<!-- 商品情報 -->
        <div class="product-text">
			<p>商品名: ${product.name}</p>
			<p>価格: ${product.price} 円</p>
			<p>カテゴリ: ${product.category}</p>
			<p>在庫数: ${product.stock}</p>
			<p>説明: ${product.description}</p>
		</div>
	</div>
	
	<a href="product">商品一覧へ戻る</a>
</body>
</html>