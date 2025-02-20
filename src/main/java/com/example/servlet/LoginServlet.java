package com.example.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.UserDao;
import com.example.model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        UserDao userDao = new UserDao();
        User user = userDao.authenticate(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user.getUsername());
            session.setAttribute("role", user.getRole());
            
            // 管理者ならadminDashboard.jspへ、それ以外はproductへ
            if ("admin".equals(user.getRole())) {
            	response.sendRedirect("adminDashboard.jsp");
            } else {
            	response.sendRedirect("product");            	
            }
        } else {
        	request.setAttribute("errorMessage", "ユーザー名またはパスワードが違います。");
        	request.getRequestDispatcher("login.jsp").forward(request, response);
        }
	}
}
