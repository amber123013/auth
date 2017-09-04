package com.zhiyou.rj14.zhouxin.auth.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/zhouxin";
	private static final String account = "root";
	private static final String password = "root";
	/**
	 * 得到连接
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Connection conn = null;
		Class.forName(driver);
		conn = DriverManager.getConnection(url, account, password);
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
