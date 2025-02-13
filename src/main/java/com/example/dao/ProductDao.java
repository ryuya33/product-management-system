package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Product;
import com.example.utils.DatabaseConnection;

public class ProductDao {
	
	// 商品リストを取得
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        
        try (Connection conn = DatabaseConnection.getConnection();
        	 PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
             while (rs.next()) {
                Product product = new Product(
                    rs.getInt("id"),
                	rs.getString("name"),
                    rs.getInt("price"),
                    rs.getString("category"),
                    rs.getInt("stock"),
                    rs.getString("description"),
                    rs.getString("image")
                );
                products.add(product);
            }
        } catch (Exception e) {
        	throw new RuntimeException("商品リストの取得に失敗しました", e);
        }
        return products;
    }
	
    // 商品をデータベースに追加
    public void addProduct(Product product) {
        String sql = "INSERT INTO products (name, price, category, stock, description, image) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
        	 PreparedStatement stmt = conn.prepareStatement(sql)) {
            
        	stmt.setString(1, product.getName());
            stmt.setInt(2, product.getPrice());
            stmt.setString(3, product.getCategory());
            stmt.setInt(4, product.getStock());
            stmt.setString(5, product.getDescription());
            stmt.setString(6, product.getImage());
            
            stmt.executeUpdate();
        } catch (Exception e) {
        	throw new RuntimeException("商品追加に失敗しました", e);
        }
    }
    
	// 商品をIDで取得
    public Product getProductById(int id) {
        Product product = null;
        String sql = "SELECT * FROM products WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                product = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getString("category"),
                    rs.getInt("stock"),
                    rs.getString("description"),
                    rs.getString("image")
                );
            }
        } catch (Exception e) {
        	throw new RuntimeException("商品情報の取得に失敗しました", e);
        }
        return product;
    }

    // 商品をIDで削除
    public void removeProductById(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
        	 PreparedStatement stmt = conn.prepareStatement(sql)) {
            
        	stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
        	throw new RuntimeException("商品削除に失敗しました", e);
        }
    }
    
    // 商品情報を更新
	public void updateProduct(Product product) {
		String sql = "UPDATE products SET name = ?, price = ?, category = ?, stock = ?, description = ?, image = ? WHERE id = ?";
		
		try (Connection conn = DatabaseConnection.getConnection(); // 共通メソッドを使用
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			// パラメーターの設定
			stmt.setString(1, product.getName());
			stmt.setInt(2, product.getPrice());
			stmt.setString(3, product.getCategory());
			stmt.setInt(4, product.getStock());
			stmt.setString(5, product.getDescription());
			stmt.setString(6, product.getImage());
			stmt.setInt(7, product.getId());
			
			// 更新クエリを実行
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("商品更新に失敗しました", e);
		}
	}
	
	// 商品検索
	public List<Product> searchProducts(String query) {
	    List<Product> products = new ArrayList<>();
	    String sql = "SELECT * FROM products WHERE name LIKE ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, "%" + query + "%");
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            products.add(new Product(
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getInt("price"),
	                rs.getString("category"),
	                rs.getInt("stock"),
	                rs.getString("description"),
	                rs.getString("image")
	            ));
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("検索処理に失敗しました", e);
	    }
	    return products;
	}
	
	// 商品並び替え
	public List<Product> getSortedProducts(String sort) {
	    String sql = "SELECT * FROM products";
	    
	    // ソート条件に応じたクエリを追加
	    if ("price_asc".equals(sort)) {
	        sql += " ORDER BY price ASC";
	    } else if ("price_desc".equals(sort)) {
	        sql += " ORDER BY price DESC";
	    } else if ("name_asc".equals(sort)) {
	        sql += " ORDER BY name ASC";
	    } else if ("name_desc".equals(sort)) {
	        sql += " ORDER BY name DESC";
	    }

	    List<Product> products = new ArrayList<>();
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {
	        
	        while (rs.next()) {
	            products.add(new Product(
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getInt("price"),
	                rs.getString("category"),
	                rs.getInt("stock"),
	                rs.getString("description"),
	                rs.getString("image")
	            ));
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("商品取得に失敗しました", e);
	    }
	    return products;
	}

}