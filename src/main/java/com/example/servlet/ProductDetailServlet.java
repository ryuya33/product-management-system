package com.example.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.Product;
import com.example.service.ProductService;

@WebServlet("/productDetail")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	
    public ProductDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter("id");
		if (idParam == null || idParam.isEmpty()) {
			response.sendRedirect("product");
			return;
		}
		
		int productId = Integer.parseInt(idParam);
		Product product = productService.getProductById(productId);
		
		if (product == null) {
			response.sendRedirect("product");
			return;
		}
		
		request.setAttribute("product", product);
		request.getRequestDispatcher("productDetail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
