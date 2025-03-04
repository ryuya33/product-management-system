<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTLタグライブラリの宣言 -->

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>商品一覧</title>
	<link rel="stylesheet" href="css/style.css">
	<script>
		function confirmDelete() {
			return confirm("本当に削除しますか？");
		}
	</script>
</head>
<body>
	<div class="header">
		<p class="welcome-message">ようこそ、${sessionScope.user} さん</p>
		<a href="LogoutServlet" class="logout-link">ログアウト</a>	
	</div>
	<h1>商品一覧</h1>
	
	<!-- エラーメッセージ表示 -->
	<div class="productList-container">
		<c:if test="${not empty errorMessage}">
		    <p class="error-message">削除するには管理者権限が必要です。</p>
		</c:if>	
	</div>
	
	<!-- 検索フォーム -->
    <form class="form-container" action="product" method="get">
        <input type="text" name="query" placeholder="商品名を入力" value="${param.query}">
        <button type="submit">検索</button>
        
        <!-- ソート機能 -->
    	<select name="sort" onchange="this.form.submit()">
	        <option value="">並び替え</option>
	        <option value="price_asc" ${param.sort == 'price_asc' ? 'selected' : ''}>価格が安い順</option>
	        <option value="price_desc" ${param.sort == 'price_desc' ? 'selected' : ''}>価格が高い順</option>
	        <option value="name_asc" ${param.sort == 'name_asc' ? 'selected' : ''}>名前順（A→Z）</option>
	        <option value="name_desc" ${param.sort == 'name_desc' ? 'selected' : ''}>名前順（Z→A）</option>
    	</select>
    </form>
	
	<div class="productList-container">
		<c:choose>
		    <c:when test="${empty products}">
		        <p>商品がありません。</p>
		    </c:when>
			<c:otherwise>	
	
				<!-- 商品表示テーブル -->
		    	<table>
		    		<thead>
		    			<tr>
		    				<th>商品名</th>
		    				<th>価格</th>
		    				<th>カテゴリ</th>
		    				<th>在庫数</th>
		    				<th>画像</th>
		    				<th>操作</th>
		    			</tr>
		    		</thead>
		    		<tbody>
						<!-- 商品リストを表示 -->
						<c:forEach var="product" items="${products}">
							<tr>
								<td>${product.name}</td>
								<td>${product.price} 円</td>
								<td>${product.category}</td>
								<td>${product.stock}</td>
								<td>
									<c:if test="${not empty product.image}">
										<img src="uploads/${product.image}" alt="${product.name}">
									</c:if>
								</td>
								<td>
									<a href="productDetail?id=${product.id}" class="btn">詳細</a>
									<c:if test="${sessionScope.role == 'admin'}">
										<!-- 編集リンク（管理者のみ表示）-->
										<a href="editProduct?id=${product.id}" class="btn">編集</a>
										<!-- 削除リンク（管理者のみ表示）-->
										<a href="product?deleteId=${product.id}" class="btn btn-danger" onclick="return confirmDelete();">削除</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
		    		</tbody>
		    	</table>
		    	
		    	<!-- ページネーション -->
	            <div class="pagination">
				    <c:if test="${currentPage > 1}">
				    	<a href="product?page=1&query=${param.query}&sort=${param.sort}">最初</a>
				        <a href="product?page=${currentPage - 1}&query=${param.query}&sort=${sort}">前へ</a>
				    </c:if>
				
				    <c:forEach begin="1" end="${totalPages}" var="i">
				        <c:choose>
				            <c:when test="${i == currentPage}">
				                <span><strong>${i}</strong></span> <!-- 現在のページは強調 -->
				            </c:when>
				            <c:otherwise>
				                <a href="product?page=${i}&query=${param.query}&sort=${sort}">${i}</a>
				            </c:otherwise>
				        </c:choose>
				    </c:forEach>
				
				    <c:if test="${currentPage < totalPages}">
				        <a href="product?page=${currentPage + 1}&query=${param.query}&sort=${sort}">次へ</a>
				        <a href="product?page=${totalPages}&query=${param.query}&sort=${param.sort}">最後</a>
				    </c:if>
				</div>
	    	</c:otherwise>
		</c:choose>
		
	</div>
	
	<!-- 商品追加ボタン -->
	<div class="add-product-container">
		<a href="productForm.jsp" class="btn">商品を追加する</a>
	</div>
</body>
</html>