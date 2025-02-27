package com.example.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.UserDao;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = "user"; // 管理者登録を防ぐためにroleを固定
		
		UserDao userDao = new UserDao();
		boolean isRegistered = userDao.registerUser(username, password, role);
		
		if (isRegistered) {
			response.sendRedirect("login.jsp?registerSuccess=1");
		} else {
			request.setAttribute("errorMessage", "ユーザー登録に失敗しました。");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}
}
