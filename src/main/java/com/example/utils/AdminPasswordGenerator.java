package com.example.utils;

import com.example.dao.UserDao;

public class AdminPasswordGenerator {
	public static void main(String[] args) {
		String password = "admin123"; // 管理者のパスワード
		String hashedPassword = UserDao.hashPassword(password); // SHA-256でハッシュ化	
		System.out.println("ハッシュ化されたパスワード:" + hashedPassword);
	}
}
