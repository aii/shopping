package com.wm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAOImpl implements IGoodsDAO {
	private Connection conn = null;
	PreparedStatement pstmt;
	public GoodsDAOImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Good> getAllGoods() throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM product";
		this.pstmt = this.conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery();
		List<Good> goods = new ArrayList<Good>();
		while(rs.next()){
			Good good = new Good();
			good.setPid(rs.getInt(1));
			good.setName(rs.getString(2));
			good.setNote(rs.getString(3));
			good.setPrice(rs.getFloat(4));
			good.setAmount(rs.getInt(5));
			goods.add(good);
		}
		this.pstmt.close();
		return goods;
	}

	@Override
	public Good findGoodByPid(String pid) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM product WHERE pid = ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, pid);
		ResultSet rs = this.pstmt.executeQuery();
		Good go = null;
		while(rs.next()){
			go = new Good();
			go.setPid(rs.getInt(1));
			go.setName(rs.getString(2));
			go.setNote(rs.getString(3));
			go.setPrice(rs.getFloat(4));
			go.setAmount(rs.getInt(5));
		}
		return go;
	}

}
