package com.zhiyou.rj14.xuqiaoqiang.jdbc.business.user.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Statement;
import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.role.entries.Role;
import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.user.entries.User;
import com.zhiyou.rj14.xuqiaoqiang.jdbc.common.util.DBUtil;

public class UserDao {
	
	private Connection conn = null;
	private PreparedStatement ps  = null;
	private ResultSet rs =  null;
	/**
	 * ɾ��һ����¼
	 * @param roleId
	 */
	public void delete(int userId) {
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("delete from user where user_id = ?");
			ps.setInt(1, userId);
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(conn, ps, rs);
		}
	}
	
	/**
	 * ����һ�����
	 * @param user
	 */
	public void insert(User user) {
		try {
			user.setUpdateTime(new Date(System.currentTimeMillis()));
			user.setCreateDate(new Date(System.currentTimeMillis()));
			conn = DBUtil.getConnection();
			//���ò������ �����󷵻ز���ɹ�������
			ps = conn.prepareStatement("insert into user(user_name,create_time,create_by,update_time,update_by,account,password) value(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, user.getUserName());
			ps.setDate(2, user.getCreateDate());
			ps.setInt(3, user.getCreateBy());
			ps.setDate(4, user.getUpdateTime());
			ps.setInt(5, user.getUpdateBy());
			ps.setString(6, user.getAccount());
			ps.setString(7, user.getPassword());

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			while(rs.next()) {
				int id = rs.getInt(1);
				System.out.println(id);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(conn, ps, rs);
		}
	}
	/**
	 * ���id����һ����¼
	 * @param user
	 */
	public void Update(User user){
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("update user set user_name = ?,update_time = ?,update_by = ?,account = ?,password = ? where user_id = ?");
			ps.setString(1, user.getUserName());
			ps.setDate(2, user.getUpdateTime());
			ps.setInt(3, user.getUpdateBy());
			ps.setString(4, user.getAccount());
			ps.setString(5, user.getPassword());
			ps.setInt(6, user.getUserId());
			int result = ps.executeUpdate();
			System.out.println(result);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		
	}
	
	public User finByUserId(int userid){
		List<User> list= new ArrayList<User>();
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select user_id,user_name,create_time,account,password from user where user_id = ?");
			ps.setInt(1, userid);
			rs =  ps.executeQuery();
			
			list = dealResultSet(rs);
			if(list.isEmpty())
				return null;
			return list.get(0);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBUtil.closeConn(conn, ps, rs);
		}
		return null;
	}
	public User finByAccount(String account){
		List<User> list= new ArrayList<User>();
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select user_id,user_name,create_time,account,password from user where account = ?");
			ps.setString(1, account);
			rs =  ps.executeQuery();
			
			list = dealResultSet(rs);
			if(list.isEmpty())
				return null;
			return list.get(0);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBUtil.closeConn(conn, ps, rs);
		}
		return null;
	}
	public List<User> finByMap(Map<String, Object> map,int start,int size){
		List<User> list= new ArrayList<User>();
		
		try {
			conn = DBUtil.getConnection();
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append("select user_id,user_name,create_time,account,password from user where 1 = 1 ");
			int index = 0;
			if(map.get("userName") != null && map.get("userName").toString().length() != 0) {
				sBuffer.append(" and user_name like ? ");
			}
			if(map.get("account") != null && map.get("account").toString().length() != 0) {
				sBuffer.append(" and account like ?");
			}
			sBuffer.append(" order by user_id");
			if(start != -1 && size != -1)
				sBuffer.append(" limit ?,?");
			ps = conn.prepareStatement(sBuffer.toString());
			if(map.get("userName") != null && map.get("userName").toString().length() != 0) {
				ps.setString(++index, "%" +map.get("userName").toString()+"%");
			}
			if(map.get("account") != null && map.get("account").toString().length() != 0) {
				ps.setString(++index, "%" +map.get("account").toString()+"%");
			}
			if(start != -1 && size != -1) {
				ps.setInt(++index, start);
				ps.setInt(++index, size);
			}
				
			rs =  ps.executeQuery();
			
			list = dealResultSet(rs);
			return list;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBUtil.closeConn(conn, ps, rs);
		}
		return null;
	}
	public int finCountByMap(Map<String, Object> map){
		
		try {
			conn = DBUtil.getConnection();
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append("select count(1) from user where 1 = 1");
			int index = 0;
			if(map.get("userName") != null && map.get("userName").toString().length() != 0) {
				sBuffer.append(" and user_name like ? ");
			}
			if(map.get("account") != null && map.get("account").toString().length() != 0) {
				sBuffer.append(" and account like ?");
			}
			ps = conn.prepareStatement(sBuffer.toString());
			if(map.get("userName") != null && map.get("userName").toString().length() != 0) {
				ps.setString(++index, "%" +map.get("userName").toString()+"%");
			}
			if(map.get("account") != null && map.get("account").toString().length() != 0) {
				ps.setString(++index, "%" +map.get("account").toString()+"%");
			}

			rs =  ps.executeQuery();
			int count = 0;
			if(rs.next())
				count = rs.getInt(1);
			return count;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBUtil.closeConn(conn, ps, rs);
		}
		return 0;
	}
	
	public List<User> queryAll() {
		List<User> list= new ArrayList<User>();
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select user_id,user_name,create_time,account,password from user");
			rs =  ps.executeQuery();
			
			list = dealResultSet(rs);
			return list;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBUtil.closeConn(conn, ps, rs);
		}
		return null;
	}
	public List dealResultSet(ResultSet rs) throws SQLException{
		List<User> list= new ArrayList<User>();
		while(rs.next()) {
			User user = new User();
			int userId = rs.getInt(1);
			String userName = rs.getString(2);
			Date create_date = rs.getDate(3);
			user.setAccount(rs.getString(4));
			user.setPassword(rs.getString(5));
			user.setUserId(userId);
			user.setUserName(userName);
			user.setCreateDate(create_date);
			list.add(user);
		}
		return list;
	}
}
