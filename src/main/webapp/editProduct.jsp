<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTLタグライブラリの宣言 -->

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>商品を編集する</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<h1>商品を編集する</h1>
	<form action="updateProduct" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${product.id }"> <!-- 商品ID（隠しフィールド） -->
		
		<label for="name">商品名:</label>
		<input type="text" id="name" name="name" value="${product.name}" required>
		
		<label for="price">価格:</label>
		<input type="number" id="price" name="price" value="${product.price}" required>
		
		<label for="category">カテゴリ:</label>
		<input type="text" id="category" name="category" value="${product.category}" required>
		
		<label for="stock">在庫数:</label>
		<input type="number" id="stock" name="stock" value="${product.stock}" required> 
		
		<label for="description">説明:</label>
		<textarea id="description" name="description" required>${product.description}</textarea>
		
		<label for="image">画像:</label>
		<input type="file" id="image" name="image">
		
		<input type="submit" class="btn" value="保存">
	</form>
</body>
</html>