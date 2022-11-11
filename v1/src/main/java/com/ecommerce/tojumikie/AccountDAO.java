package com.ecommerce.tojumikie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
	public boolean verifyLogin(String username, String password) throws SQLException, ClassNotFoundException {
		boolean accountFound = false;
		String jdbc = "jdbc:mysql://localhost:3306/shopping_database";
		String user = "root";
		String pass = "root";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(jdbc, user, pass);
		String sql = "select * from customer_accounts where username = ? and password = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		stmt.setString(2, password);
		
		ResultSet result = stmt.executeQuery();
		
//		Account account = null;
		
		if(result.next()) {
			Account account = new Account(user, pass);
			System.out.println("login was found");
			accountFound = true;
		}
		else {
			System.out.println("login was not found");
			accountFound = false;
		}
		conn.close();
		return accountFound;
	}
	public void registerAccount(String username, String password) throws SQLException, 
	ClassNotFoundException {
		String jdbc = "jdbc:mysql://localhost:3306/shopping_database";
		String user = "root";
		String pass = "root";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(jdbc, user, pass);
		String sql = "INSERT INTO customer_accounts (username, password) VALUES (?, ?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		stmt.setString(2, password);
		
		stmt.executeUpdate();
		conn.close();
		
		System.out.println("the account has been created");
	}
}
