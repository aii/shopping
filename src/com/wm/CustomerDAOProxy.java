package com.wm;

import java.sql.SQLException;

public class CustomerDAOProxy implements ICustomerDAO {
	private DatabaseConnection dbc;
	private ICustomerDAO dao = null;

	public CustomerDAOProxy() throws ClassNotFoundException, SQLException {
		this.dbc = new DatabaseConnection();
		this.dao = new CustomerDAOImpl(this.dbc.getConnection());
	}

	@Override
	public Customer findByIdAndPwd(String customerId, String customerPwd) throws SQLException {
		// TODO Auto-generated method stub
		Customer customer = null;
		try {
			customer = this.dao.findByIdAndPwd(customerId, customerPwd);
		} catch (SQLException e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return customer;
	}
}
