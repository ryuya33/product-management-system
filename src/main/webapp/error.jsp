<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラー</title>
</head>
<body>
	<h1>エラーが発生しました</h1>
    <p>申し訳ありません。現在、処理中に問題が発生しました。</p>
    <p>以下のエラー内容をご確認ください:</p>
    <pre><%= request.getAttribute("javax.servlet.error.message") %></pre>
    <p>再度お試しいただくか、システム管理者にお問い合わせください。</p>
    <a href="productForm.jsp">トップページに戻る</a>
</body>
</html>