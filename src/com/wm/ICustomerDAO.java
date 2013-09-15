package com.wm;

import java.sql.SQLException;

public interface ICustomerDAO {
	public Customer findByIdAndPwd(String customerId,String customerPwd) throws SQLException;
}
