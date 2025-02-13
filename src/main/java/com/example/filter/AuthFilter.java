package com.example.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({"/productList.jsp", "/productForm.jsp", "/editProduct.jsp", "/ProductServlet"})
public class AuthFilter implements Filter {

    public AuthFilter() {
        super();
    }
    
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
        // ユーザーが未ログインならログインページへリダイレクト
        if (session == null || session.getAttribute("user") == null) {
            httpResponse.sendRedirect("login.jsp");
            return;
        }

        // 次のフィルターまたはサーブレットへ処理を渡す
        chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {}

	@Override
    public void destroy() {}
}