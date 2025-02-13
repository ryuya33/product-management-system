package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.model.User;
import com.example.utils.DatabaseConnection;

public class UserDao {
	
	public User authenticate(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password); // 本来はハッシュ化されたパスワードを比較すべき

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
