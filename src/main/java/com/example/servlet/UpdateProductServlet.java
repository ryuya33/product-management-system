package com.example.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.example.dao.ProductDao;
import com.example.model.Product;

@WebServlet("/updateProduct")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UpdateProductServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			int id = 0;
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
						case "id":
							id = Integer.parseInt(fieldValue);
							break;
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
			    	if (item.getSize() > 0) { // 新しい画像がアップロードされた場合のみ処理
			    		String fileName = new File(item.getName()).getName();
			    		String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
			    		File uploadDir = new File(uploadPath);
			    		if (!uploadDir.exists() && !uploadDir.mkdirs()) {
			    		    throw new IOException("アップロードディレクトリの作成に失敗しました: " + uploadPath);
			    		}
			    		File storeFile = new File(uploadPath + File.separator + fileName);
			    		item.write(storeFile); // ファイル保存	
			    		imageName = fileName;
			    	}
				}
			}
				
			// 画像がアップロードされていない場合、元の画像を保持する
			ProductDao productDao = new ProductDao();
			Product existingProduct = productDao.getProductById(id);
            if (existingProduct != null && imageName == null) {
                imageName = existingProduct.getImage(); // 元の画像を使用
            }
			
			// データベースの更新
			Product product = new Product(id, name, price, category, stock, description, imageName);
			productDao.updateProduct(product); // 更新処理
			
			// 商品一覧ページにリダイレクト
			response.sendRedirect("product");
			
			
		} catch (Exception e) {
			e.printStackTrace();
            response.getWriter().println("エラー: " + e.getMessage());
		}
	}
}
