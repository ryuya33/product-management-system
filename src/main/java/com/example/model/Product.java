package com.example.model;

public class Product {
	private int id;
	private String name;
	private int price;
	private String category;
	private int stock;
	private String description;
	private String image;
	
	// コンストラクタ
	public Product(int id, String name, int price, String category, int stock, String description, String image) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.stock = stock;
		this.description = description;
		this.image = image;
	}
	
	// コンストラクタのオーバーロード（ID なしの場合）
    public Product(String name, int price, String category, int stock, String description, String image) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.description = description;
        this.image = image;
    }

	
	// Getterメソッド
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getCategory() {
		return category;
	}
	
	public int getStock() {
		return stock;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getImage() {
		return image;
	}
}
