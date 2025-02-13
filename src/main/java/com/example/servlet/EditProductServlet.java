package com.example.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.ProductDao;
import com.example.model.Product;

@WebServlet("/editProduct")
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditProductServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 商品IDを取得
        String id = request.getParameter("id");
        
        // データベースから該当商品を取得
        ProductDao productDao = new ProductDao();
        Product product = productDao.getProductById(Integer.parseInt(id)); // IDで商品を取得するメソッドを作成済みと仮定
        
        // 商品をリクエストスコープに保存
        request.setAttribute("product", product);

        // JSPにフォワード
        request.getRequestDispatcher("editProduct.jsp").forward(request, response);
    
    }
}
