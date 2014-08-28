package com.cl.search.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.cl.search.util.DBConnectionManager;



public class CommodityDao {

	private DBConnectionManager connManager = DBConnectionManager.getInstance();
	protected Connection conn;
	
	public HashMap<String,HashMap<String,String>> getCommodityList()
	{
		HashMap<String,HashMap<String,String>> commodityMap = new HashMap<String,HashMap<String,String>>();
		String strSql = "select * from c_commodity";
		String no = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		HashMap<String,String> commodity = null;
		try{
			conn = connManager.getConnection("commodity");
			ps = conn.prepareStatement(strSql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				no = rs.getString("no");
				commodity = new HashMap<String,String>();
				commodity.put("no", rs.getString("no"));
				commodity.put("name", rs.getString("name"));
				commodity.put("style_no", rs.getString("style_no"));
				commodity.put("sale_price", Integer.toString(rs.getInt("sale_price")));
				commodityMap.put(no, commodity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connManager.closeCommodityConnection(rs, ps, conn);
		
		return commodityMap;
	}
}