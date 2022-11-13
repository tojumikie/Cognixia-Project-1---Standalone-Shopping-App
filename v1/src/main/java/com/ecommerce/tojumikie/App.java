package com.ecommerce.tojumikie;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class App {
	static Scanner kb = new Scanner(System.in);
	static String choice = null;
	static ItemDAO itemDAO = new ItemDAO();
	static String sessionUser = null;
	static int sessionUserNo = 0;
	static String jdbc = "jdbc:mysql://localhost:3306/shopping_database";
	static String user = "root";
	static String pass = "root";
	static String invoiceNo = null;
	
  public static void main(String[] args) {
	  SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYY.HHmmss");
	  String timeStamp = sdf.format(new Date());
	  System.out.println(timeStamp);
	  invoiceNo = timeStamp;
	  
	  try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(jdbc, user, pass);
		String sql = "select account_id from customer_accounts where username = 'root'";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
//	  System.out.println(timeStamp);
//	  try {
//		itemDAO.displayItems();
//	} catch (ClassNotFoundException | SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	  ItemDAO itemDAO = new ItemDAO();
//	  try {
//		itemDAO.displayItems();
//	} catch (ClassNotFoundException | SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	  menu();
  }

	public static void menu() {
		do {
			System.out.println("Standalone E-Commerce app");
			System.out.println("+===============================================+");
			System.out.println("+   1. REGISTER                                 +");                                   
			System.out.println("+   2. LOG IN                                   +");                                 
			System.out.println("+   3. EXIT                                     +");
			System.out.println("+===============================================+");
			
			
			choice = kb.nextLine();
			switch (choice) {
				case "1" -> registerUser();
				case "2" -> logIn();
//				case "3" -> showItems();
//				case "4" -> replaceItems();
//				case "5" -> showInvoice();
				case "3" -> {
					System.out.println("Closed the application.");
					System.exit(0);
				}
				default -> System.out.println("you have to select a choice (type the number)");
			}
		} while(!choice.equals("5"));
	}
	public static void registerUser() {
		String username = null;
		String password = null;
		
		System.out.println("register");
		System.out.println("Enter username");
		username = kb.nextLine();
		System.out.println("Enter password");
		password = kb.nextLine();
		AccountDAO accountDAO = new AccountDAO();
		try {
			accountDAO.registerAccount(username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(username + " " + password);
	}
	public static void logIn() {
		String username = null;
		String password = null;
		System.out.println("log in");
		username = kb.nextLine();
		System.out.println("Enter password");
		password = kb.nextLine();
		AccountDAO accountDAO = new AccountDAO();
		try {
			boolean found = accountDAO.verifyLogin(username, password);
			if(found == true) {
				System.out.println("true");
				sessionUser = username;
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(jdbc, user, pass);
				String sql = "select account_id from customer_accounts where username = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, username);
				
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println(rs.getString("account_id"));
				}
				
				showItems();
			}
			else System.out.println("false");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void showItems() {
		int quantity = 0;
		try {
			itemDAO.displayItems();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("select the # of the item you want to purchase or press L to log out");
		choice = kb.nextLine();
		if(choice.equalsIgnoreCase("L")) {
			sessionUser = null;
			sessionUserNo = 0;
			menu();
		}
		else {
			int intChoice = Integer.parseInt(choice);
			System.out.println("you chose item " + intChoice);
			System.out.println("enter the quantity you want to purchase");
			quantity = kb.nextInt();
			kb.nextLine();
			try {
				packToInvoice(intChoice, quantity);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void packToInvoice(int choice, int quantity) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(jdbc, user, pass);
		String sql = "insert into orders(invoice_no, customer_id, item_code, quantity) values (?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, invoiceNo);
//		stmt.setString(2, )
	}
	public static void displayInvoice() {
		
	}
}