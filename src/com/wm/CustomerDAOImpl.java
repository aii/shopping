package com.wm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements ICustomerDAO {
	Connection conn;
	PreparedStatement pstmt;

	public CustomerDAOImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Customer findByIdAndPwd(String customerId, String customerPwd) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT customerId,customerPwd,customerName,customerAddress,customerTelephone FROM customer WHERE customerId = ? AND customerPwd = ?";
		Customer customer = null;
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, customerId);
		this.pstmt.setString(2, customerPwd);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			customer = new Customer();
			customer.setCustomerId(rs.getString(1));
			customer.setCustomerPwd(rs.getString(2));
			customer.setCustomerName(rs.getString(3));
			customer.setCustomerAddress(rs.getString(4));
			customer.setCustomerTelephone(rs.getString(5));
		}
		this.pstmt.close();
		return customer;
	}

}
