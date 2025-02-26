package com.example.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.example.model.Product;
import com.example.service.ProductService;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	private static final int PRODUCTS_PER_PAGE = 5;
	
    public ProductServlet() {
        super();
    }
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エンコーディングを設定
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		if (!ServletFileUpload.isMultipartContent(request)) {
			response.getWriter().println("フォームの enctype 属性が 'multipart/form-data' になっていません。");
	        return;
		}
		
		try {
			// アップロードされたデータを解析
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> formItems = upload.parseRequest(request);
			
			// フォームのデータを格納する変数
			String name = null;
			int price = 0;
			String category = null;
			int stock = 0;
			String description = null;
			String imageName = null;
			
			// フォームデータの処理
			for (FileItem item : formItems) {
				if (item.isFormField()) {
					// 通常のフォームフィールド
					String fieldName = item.getFieldName();
					String fieldValue = item.getString("UTF-8"); // UTF-8でデコード
					
					switch (fieldName) {
						case "name":
							name = fieldValue;
							break;
						case "price":
							price = Integer.parseInt(fieldValue);
							break;
						case "category":
							category = fieldValue;
							break;
						case "stock":
							stock = Integer.parseInt(fieldValue);
							break;
						case "description":
							description = fieldValue;
							break;
					}
				} else {
					// ファイルフィールド	
					String fileName = new File(item.getName()).getName();
					String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
					File uploadDir = new File(uploadPath);
					if (!uploadDir.exists()) {
						uploadDir.mkdir();
					}
					File storeFile = new File(uploadPath + File.separator + fileName);
					item.write(storeFile); // ファイル保存	
					imageName = fileName;
				}
			}
			
			// 商品データを作成してデータベースに保存
			Product product = new Product(name, price, category, stock, description, imageName);
			productService.addProduct(product);
			
			// 商品登録後、リダイレクトしてページネーション処理を適用
			response.sendRedirect("product?page=1");

		} catch (Exception e) {
			e.printStackTrace();
			// エラーが発生した場合はエラーページを表示
			response.sendRedirect("error.jsp");				
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); 
		
		HttpSession session = request.getSession(false);
	        String role = (session != null) ? (String) session.getAttribute("role") : null;
		
		// 削除処理（管理者のみ許可）
		String idToDelete = request.getParameter("deleteId");
		if (idToDelete != null) {
			if ("admin".equals(role)) {
				int id = Integer.parseInt(idToDelete); 
				productService.removeProductById(id);					
			} else {
				request.setAttribute("errorMessage", "削除するには管理者権限が必要です。");
			}
		}
		
		// 検索 & 並び替え処理
	    String query = request.getParameter("query");   // 検索キーワード
	    String sort = request.getParameter("sort");     // ソート条件
	    List<Product> products;

	    if (query != null && !query.trim().isEmpty()) {
	        products = productService.searchProducts(query); // 検索結果を取得
	    
	        // 検索結果に並び替えを適用
	        if (sort != null && !sort.isEmpty()) {
	            products = productService.sortProducts(products, sort);
	        }
	    } else if (sort != null && !sort.isEmpty()) {
	        products = productService.getSortedProducts(sort); // 並び替えのみ
	    } else {
	        products = productService.getAllProducts(); // デフォルト表示
	    }
        
        // ページネーション処理
        int currentPage = 1;
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        int totalProducts = products.size();
        int totalPages = (int) Math.ceil((double) totalProducts / PRODUCTS_PER_PAGE);

        // ページ内の商品を取得
        int start = (currentPage - 1) * PRODUCTS_PER_PAGE;
        int end = Math.min(start + PRODUCTS_PER_PAGE, totalProducts);
        List<Product> paginatedProducts = products.subList(start, end);

        // JSP へデータ送信
        request.setAttribute("products", paginatedProducts);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("query", query);  // 検索キーワードを保持
        request.setAttribute("sort", sort);    // ソート条件を保持
        
		request.getRequestDispatcher("productList.jsp").forward(request, response);
	}
}