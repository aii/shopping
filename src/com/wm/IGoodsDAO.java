package com.wm;

import java.sql.SQLException;
import java.util.List;

public interface IGoodsDAO {
	List<Good> getAllGoods() throws SQLException;

	Good findGoodByPid(String pid) throws SQLException;
}
