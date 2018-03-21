package com.tourism.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JDBCUtil {

	public static String dbNameFilter(String driver, String ip, String port, String username, String password,
			String ruleName) {
		String url = "jdbc:mysql://" + ip + ":" + port + "/information_schema";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			st = (Statement) con.createStatement();
			rs = st.executeQuery("SELECT SCHEMA_NAME FROM information_schema.SCHEMATA");
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}
		}
		for (String dbname : list) {
			if (dbname.contains(ruleName))
				return dbname;
		}
		return null;

	}

	public static String tbNameFilter(String driver, String ip, String port, String username, String password,
			String ruleName, String dbName) {
		String url = "jdbc:mysql://" + ip + ":" + port + "/" + dbName;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			st = (Statement) con.createStatement();
			rs = st.executeQuery(
					"SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '" + dbName + "'");
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}
		}
		for (String tbName : list) {
			if (tbName.contains(ruleName))
				return tbName;
		}
		return null;
	}

	public static JSONArray getField(String driver, String ip, String port, String username, String password,
			String tbName, String dbName) {
		String url = "jdbc:mysql://" + ip + ":" + port + "/" + dbName;
		Connection con = null;
		ResultSet rs = null;
		DatabaseMetaData metaData = null;
		List<String> list = new ArrayList<String>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			metaData = con.getMetaData();

			// 4. 提取表内的字段的名字和类型
			String columnName;
			String columnDis;
			String columntype;
			rs = metaData.getColumns(null, "%", tbName, "%");
			JSONArray jsonArray=new JSONArray();
			while(rs.next()) { 
				JSONObject jb=new JSONObject();
	 			columnName = rs.getString("COLUMN_NAME"); 
	 			columnDis=rs.getString("REMARKS");
	 			columntype=rs.getString("DATA_TYPE");
	 			jb.put("fdcMetaDataName", columnName);
	 			jb.put("fdcMemo", columnDis);
	 			jb.put("fdlMetaDataType", columntype);
	 			jsonArray.add(jb);
			}
			return jsonArray;
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}
		}
	
		return null;

	}
}
