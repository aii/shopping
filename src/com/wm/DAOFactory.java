package com.wm;

import java.sql.SQLException;

public class DAOFactory {
	public static ICustomerDAO getICustomerInstance() throws ClassNotFoundException, SQLException {
		return new CustomerDAOProxy();
	}

	public static IGoodsDAO getIGoodsDAOInstance() throws ClassNotFoundException, SQLException {
		return new GoodsDAOProxy();
	}
}
