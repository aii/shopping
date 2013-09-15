package com.wm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAOProxy implements IGoodsDAO{
	private IGoodsDAO dao = null;
	private DatabaseConnection conn = null;
	
	public GoodsDAOProxy() throws ClassNotFoundException, SQLException{
		this.conn = new DatabaseConnection();
		this.dao = new GoodsDAOImpl(this.conn.getConnection());
	}
	@Override
	public List<Good> getAllGoods() throws SQLException {
		// TODO Auto-generated method stub
		List<Good> goods = new ArrayList<Good>();
		try{
			goods = this.dao.getAllGoods();
		}catch(SQLException e){
			throw e;
		}finally{
			this.conn.close();
		}
		return goods;
	}
	@Override
	public Good findGoodByPid(String pid) throws SQLException {
		// TODO Auto-generated method stub
		Good go = null;
		try{
			go = this.dao.findGoodByPid(pid);
		}catch(SQLException e){
			throw e;
		}finally{
			this.conn.close();
		}
		return go;
	}
}
