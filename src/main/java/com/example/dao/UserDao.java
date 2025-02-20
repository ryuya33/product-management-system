package com.example.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.model.User;
import com.example.utils.DatabaseConnection;

public class UserDao {
	
	/**
     * ユーザー認証処理（パスワードハッシュ化対応）
     */
	public User authenticate(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	String storedHashedPassword = rs.getString("password");
                // 入力パスワードをハッシュ化して比較
            	if (storedHashedPassword.equals(hashPassword(password))) {
            		return new User(
            			rs.getInt("id"),
            			rs.getString("username"),
            			storedHashedPassword,
            			rs.getString("role")
            		);
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	/**
     * パスワードをSHA-256でハッシュ化するメソッド
     */
	public static String hashPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = md.digest(password.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : hashedBytes) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("ハッシュ化エラー", e);
		}
	}
	
	/**
     * 新規ユーザー登録時にハッシュ化して保存
     */
	public boolean registerUser(String username, String password, String role) {
		String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, username);
			stmt.setString(2, hashPassword(password)); // ハッシュ化して保存
			stmt.setString(3, role);
			return stmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
