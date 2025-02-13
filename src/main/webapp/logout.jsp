<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    // セッションがあれば破棄（ログアウト）
    HttpSession session = request.getSession(false);
    if (session != null) {
        session.invalidate();
    }
    // ログインページへリダイレクト
    response.sendRedirect("login.jsp");
%>