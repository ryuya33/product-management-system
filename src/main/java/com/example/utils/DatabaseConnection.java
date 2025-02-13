package com.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/product_db?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "G8N4k27vX$";
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver"; // MySQL用ドライバ
    
    // データベース接続を取得するメソッド
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER_CLASS); // JDBCドライバをロード
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBCドライバのロードに失敗しました", e);
        }
    }
}
