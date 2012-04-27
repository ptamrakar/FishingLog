package com.fishinglog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbHelper {

	private String username;
	private String password;
	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	private static final String url = "jdbc:mysql://localhost/fishinglog";
	private static final String dbUserId = "root";
	private static final String dbPassword = "";

	public boolean login(String username, String password) {
		openConnection();
		String query = "SELECT * from `fishingLog`.`userlist` where userid='"
				+ username + "' and password='"+password+"'";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				if (rs.getString(1).equals(username)) {
					System.out.println("Found");
					return true;
				}
			}
		} catch (SQLException e) {
			System.err.println("\t" + e.getMessage());
		} finally {
			closeConnection();
		}
		return false;
	}

	private void openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, dbUserId, dbPassword);
			stmt = con.createStatement();
			System.out.println("Connection successful...");
		} catch (ClassNotFoundException e) {
			System.out
					.println("Connection failed due to ClassNotFoundException");
			System.err.println("\t" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Connection failed due to SQLException");
			System.err.println("\t" + e.getMessage());
		}
	}

	private void closeConnection() {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
			System.out.println("Connection Closed...");
		} catch (SQLException e) {
			System.err.println("\t" + e.getMessage());
		}

	}
}
