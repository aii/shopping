package com.wm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	Connection conn;

	public DatabaseConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/test", "root", "198666");// 创建数据连接
	}
	public Connection getConnection(){
		return this.conn;
	}
	public void close() throws SQLException{
		this.conn.close();
	}
}
