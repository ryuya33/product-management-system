<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
	<meta charset="UTF-8">
	<title>商品情報入力</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<h1>商品情報入力フォーム</h1>
	<form class="form-container" action="product" method="post" enctype="multipart/form-data">
		<div id="error-messages"></div> <!-- エラーメッセージ表示エリア -->
		
		<label for="name">商品名:</label>
		<input type="text" id="name" name="name" required>
		
		<label for="price">価格:</label>
		<input type="number" id="price" name="price" required>
		
		<label for="category">カテゴリ:</label>
		<input type="text" id="category" name="category" required>
		
		<label for="stock">在庫数:</label>
		<input type="number" id="stock" name="stock" required>
		
		<label for="description">説明:</label>
		<textarea id="description" name="description" rows="4" cols="50"></textarea>
		
		<label for="image">画像:</label>
		<input type="file" id="image" name="image" accept="image/*">
		
		<input type="submit" class="btn" value="送信">
	</form>
	<script src="js/form-validation.js"></script>
</body>
</html>