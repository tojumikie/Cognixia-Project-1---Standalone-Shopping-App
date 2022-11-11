package com.ecommerce.tojumikie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAO {
	public void displayItems() throws ClassNotFoundException, SQLException {
		String jdbc = "jdbc:mysql://localhost:3306/shopping_database";
		String user = "root";
		String pass = "root";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(jdbc, user, pass);
		
		String sql = "select * from items";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery(sql);
		System.out.printf("%-8s %-15s %-15s %-15s\n", "item #", "item name", "item code", "price");
		while (rs.next()) {
			System.out.printf("%-8s %-15s %-15s %-15s\n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
//			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
		}
	}
}
