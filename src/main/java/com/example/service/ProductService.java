package com.example.service;

import java.util.Comparator;
import java.util.List;

import com.example.dao.ProductDao;
import com.example.model.Product;

public class ProductService {
	private ProductDao productDao = new ProductDao();
	
	// 商品一覧を取得
	public List<Product> getAllProducts() {
		return productDao.getProducts();
	}
	
	// 商品を追加
	public void addProduct(Product product) {
		try {
			productDao.addProduct(product);
		} catch (Exception e) {
			throw new RuntimeException("商品追加に失敗しました", e);
		}
	}
	
	// 商品をIDで取得	
	public Product getProductById(int id) {
		return productDao.getProductById(id);
	}
	
	// 商品を削除
	public void removeProductById(int id) {
		try {
			productDao.removeProductById(id);
		} catch (Exception e) {
			throw new RuntimeException("商品削除に失敗しました", e);
		}
	}
	
	// 商品情報を更新
	public void updateProduct(Product product) {
		try {
			productDao.updateProduct(product);
		} catch (Exception e) {
			throw new RuntimeException("商品更新に失敗しました", e);
		}
	}
	
	// 商品を検索
    public List<Product> searchProducts(String query) {
        return productDao.searchProducts(query);
    }
    
    // DBで商品を並び替え
    public List<Product> getSortedProducts(String sort) {
        return productDao.getSortedProducts(sort);
    }
    
    // メモリ内で商品を並び替え
    public List<Product> sortProducts(List<Product> products, String sort) {
        if ("price_asc".equals(sort)) {
            products.sort(Comparator.comparingInt(Product::getPrice)); // 価格の昇順
        } else if ("price_desc".equals(sort)) {
            products.sort(Comparator.comparingInt(Product::getPrice).reversed()); // 価格の降順
        } else if ("name_asc".equals(sort)) {
            products.sort(Comparator.comparing(Product::getName)); // 名前の昇順
        } else if ("name_desc".equals(sort)) {
            products.sort(Comparator.comparing(Product::getName).reversed()); // 名前の降順
        }
        return products;
    }
}
