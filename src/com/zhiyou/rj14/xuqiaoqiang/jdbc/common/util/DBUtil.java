package com.zhiyou.rj14.xuqiaoqiang.jdbc.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	
	private static final String driver = "com.mysql.jdbc.Driver";
	//private static final String url = "jdbc:mysql://172.21.42.132:4408/xqq";
	private static final String url = "jdbc:mysql://localhost:3306/xqq";
	private static final String account = "root";
	private static final String password = "11937";
	/**
	 * 得到数据库连接 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Class.forName(driver);
		conn =  DriverManager.getConnection(url, account, password);
		return conn;
	}
	
	/**
	 * 关闭数据库连接
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public static void closeConn(Connection conn, PreparedStatement ps, ResultSet rs) {
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps != null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
