package com.zhiyou.rj14.zhouxin.auth.business.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.zhiyou.rj14.zhouxin.auth.business.user.entries.User;
import com.zhiyou.rj14.zhouxin.auth.common.util.DBUtil;

public class UserDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	/**
	 * 更新user部分信息
	 */
	public void update(User user){
		StringBuilder sqlSb = new StringBuilder();//StringBuilder线程不安全,StringBuffer线程安全
		try {
			//得到连接
			conn = DBUtil.getConnection();
			
			sqlSb.append("UPDATE sys_user set username=?,password=?,email=?,phone=?,last_update_by=?,last_update_time=?");
			sqlSb.append(" WHERE user_id=?");
			
			ps = conn.prepareStatement(sqlSb.toString());
			//设置参数
			ps.setString(1,user.getUsername());
			ps.setString(2,user.getPassword());
			ps.setString(3,user.getEmail());
			ps.setString(4,user.getPhone());
			ps.setLong(5,user.getLastUpdateBy());
			ps.setDate(6, new java.sql.Date(System.currentTimeMillis()));
			ps.setLong(7, user.getUserId());
			//执行sql
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//关闭连接
			DBUtil.closeConn(conn, ps, rs);
		}
	}
	
	/**
	 * 创建新的user
	 */
	public void create(User user){
		StringBuilder sqlSb = new StringBuilder();//线程不安全
		try {
			conn = DBUtil.getConnection();
			
			sqlSb.append("INSERT INTO sys_user(account,username,password,email,phone,create_by,last_update_by,create_time,last_update_time)");
			sqlSb.append(" VALUES(?,?,?,?,?,?,?,?,?)");
			//设置statement参数，要求返回主键
			ps = conn.prepareStatement(sqlSb.toString(), Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1,user.getAccount());
			ps.setString(2,user.getUsername());
			ps.setString(3,user.getPassword());
			ps.setString(4,user.getEmail());
			ps.setString(5,user.getPhone());
			ps.setLong(6,user.getCreateBy());
			ps.setLong(7,user.getLastUpdateBy());
			ps.setDate(8, new java.sql.Date(System.currentTimeMillis()));
			ps.setDate(9, new java.sql.Date(System.currentTimeMillis()));
			
			ps.executeUpdate();
			//得到数据库自动生成的主键
			rs = ps.getGeneratedKeys();
			while(rs.next()){
				Long userId = rs.getLong(1);
				user.setUserId(userId);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeConn(conn, ps, rs);
		}
	}
	
	public void delete(Long userId){
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM sys_user WHERE user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, userId);
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeConn(conn, ps, rs);
		}
	}
	
	/**
	 * 根据用户ID查询用户
	 */
	
	public User findByUserId(Long userId){
		User user = null;
		
		try {
			//1.得到连接
			conn = DBUtil.getConnection();
			//2.准备SQL
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("SELECT user_id,account,password,username,email,phone,create_by,create_time,");
			sqlSb.append(" last_update_by,last_update_time");
			sqlSb.append(" FROM sys_user");
			sqlSb.append(" WHERE user_id=?");
			ps = conn.prepareStatement(sqlSb.toString());
			ps.setLong(1, userId);
			//3.发送给数据库服务器执行
			//4.收到数据库服务器返回的结果
			rs = ps.executeQuery();
			//5.处理结果
			List<User> userList = dealResultSet(rs);
			if(userList != null && userList.size() > 0){
				user = userList.get(0);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//6.关闭连接
			DBUtil.closeConn(conn, ps, rs);
		}
		return user;
	}

	/**
	 * 根据用户ID查询用户
	 */
	
	public User findByAccount(String account){
		User user = null;
		try {
			//1.得到连接
			conn = DBUtil.getConnection();
			//2.准备SQL
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("SELECT user_id,account,password,username,email,phone,create_by,create_time,");
			sqlSb.append(" last_update_by,last_update_time");
			sqlSb.append(" FROM sys_user");
			sqlSb.append(" WHERE account=?");
			ps = conn.prepareStatement(sqlSb.toString());
			ps.setString(1, account);
			//3.发送给数据库服务器执行
			//4.收到数据库服务器返回的结果
			rs = ps.executeQuery();
			//5.处理结果
			List<User> userList = dealResultSet(rs);
			if(userList != null && userList.size() > 0){
				user = userList.get(0);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//6.关闭连接
			DBUtil.closeConn(conn, ps, rs);
		}
		return user;
	}
	
	public int countByQueryMap(Map<String, Object> map){
		int total = 0;
		try {
			//1.得到连接
			conn = DBUtil.getConnection();
			//2.准备SQL
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("SELECT COUNT(1)");
			sqlSb.append(" FROM sys_user");
			sqlSb.append(" WHERE 1=1");
			
			if(map.get("account") != null && map.get("account").toString().trim().length() != 0){
				sqlSb.append(" AND account LIKE ?");
			}
			if(map.get("username") != null && map.get("username").toString().trim().length() != 0){
				sqlSb.append(" AND username LIKE ?");
			}
			
			ps = conn.prepareStatement(sqlSb.toString());
			
			int index = 1;
			if(map.get("account") != null && map.get("account").toString().trim().length() != 0){
				ps.setString(index++, "%" + map.get("account").toString().trim() + "%");
			}
			if(map.get("username") != null && map.get("username").toString().trim().length() != 0){
				ps.setString(index++, "%" + map.get("username").toString().trim() + "%");
			}
			//3.发送给数据库服务器执行
			//4.收到数据库服务器返回的结果
			rs = ps.executeQuery();
			//5.处理结果
			if(rs.next()){
				total = rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//6.关闭连接
			DBUtil.closeConn(conn, ps, rs);
		}
		return total;
	}
	
	public List<User> findByQueryMap(Map<String, Object> map, int start, int size){
		List<User> userList = new ArrayList<User>();
		try {
			//1.得到连接
			conn = DBUtil.getConnection();
			//2.准备SQL
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("SELECT u.user_id,u.account,u.password,u.username,u.email,u.phone,u.create_by,u.create_time,");
			sqlSb.append(" u.last_update_by,u.last_update_time,cu.username create_username,lu.username last_update_username");
			sqlSb.append(" FROM sys_user u");
			sqlSb.append(" LEFT JOIN sys_user cu");
			sqlSb.append(" ON u.user_id=cu.user_id");
			sqlSb.append(" LEFT JOIN sys_user lu");
			sqlSb.append(" ON u.last_update_by=lu.user_id");
			sqlSb.append(" WHERE 1=1");
			
			if(map.get("account") != null && map.get("account").toString().trim().length() != 0){
				sqlSb.append(" AND u.account LIKE ?");
			}
			if(map.get("username") != null && map.get("username").toString().trim().length() != 0){
				sqlSb.append(" AND u.username LIKE ?");
			}
			sqlSb.append(" ORDER BY user_id DESC");
			if(start != -1 && size != -1){
				sqlSb.append(" LIMIT ?,?");
			}
			
			ps = conn.prepareStatement(sqlSb.toString());
			
			int index = 1;
			if(map.get("account") != null && map.get("account").toString().trim().length() != 0){
				ps.setString(index++, "%" + map.get("account").toString().trim() + "%");
			}
			if(map.get("username") != null && map.get("username").toString().trim().length() != 0){
				ps.setString(index++, "%" + map.get("username").toString().trim() + "%");
			}
			if(start != -1 && size != -1){
				ps.setInt(index++, start);
				ps.setInt(index++, size);
			}
			//3.发送给数据库服务器执行
			//4.收到数据库服务器返回的结果
			rs = ps.executeQuery();
			//5.处理结果
			while(rs.next()){
				User user = new User();
				
				user.setUserId(rs.getLong("user_id"));
				user.setAccount(rs.getString("account"));
				user.setPassword(rs.getString("password"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setCreateBy(rs.getLong("create_by"));
				user.setCreateUsername(rs.getString("create_username"));
				user.setCreateTime(rs.getDate("create_time"));
				user.setLastUpdateBy(rs.getLong("last_update_by"));
				user.setLastUpdateUsername(rs.getString("last_update_username"));
				user.setLastUpdateTime(rs.getDate("last_update_time"));
				
				userList.add(user);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//6.关闭连接
			DBUtil.closeConn(conn, ps, rs);
		}
		return userList;
	}
	
	/**
	 * 处理查询结果
	 */
	protected List<User> dealResultSet(ResultSet rs) throws SQLException{
		List<User> userList = new ArrayList<User>();
		while(rs.next()){
			User user = new User();
			
			user.setUserId(rs.getLong("user_id"));
			user.setAccount(rs.getString("account"));
			user.setPassword(rs.getString("password"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			user.setPhone(rs.getString("phone"));
			user.setCreateBy(rs.getLong("create_by"));
			user.setCreateTime(rs.getDate("create_time"));
			user.setLastUpdateBy(rs.getLong("last_update_by"));
			user.setLastUpdateTime(rs.getDate("last_update_time"));
			
			userList.add(user);
		}
		return userList;
	}

}
