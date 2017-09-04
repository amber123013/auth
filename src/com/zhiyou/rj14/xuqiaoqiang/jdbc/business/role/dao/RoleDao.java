package com.zhiyou.rj14.xuqiaoqiang.jdbc.business.role.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
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
public class RoleDao {
	
	private Connection conn = null;
	private PreparedStatement ps  = null;
	private ResultSet rs =  null;
	
	/**
	 * 删除角色
	 * @param roleId
	 */
	public void delete(int roleId) {
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("delete from role where role_id = ?");
			ps.setInt(1, roleId);
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
	 * 插入数据
	 * @param role
	 */
	public void insert(Role role) {
		try {
			conn = DBUtil.getConnection();

			ps = conn.prepareStatement("insert into role(role_name,create_date,create_by,update_time,update_by,last_update_time) value(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, role.getRoleName());
			ps.setDate(2, role.getCrateDate());
			ps.setInt(3, role.getCreateBy());
			ps.setDate(4, role.getUpdateTime());
			ps.setInt(5, role.getUpdateBy());
			ps.setDate(6, role.getLastUpdateTime());

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
	 * 更新数据
	 * @param role
	 */
	public void Update(Role role){
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("update role set role_name = ?,update_time = ?,update_by = ?,last_update_time = ? where role_id = ?");
			ps.setString(1, role.getRoleName());
			ps.setDate(2, role.getUpdateTime());
			ps.setInt(3, role.getUpdateBy());
			ps.setDate(4, role.getLastUpdateTime());
			ps.setInt(5, role.getRoleId());
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
	
	/**
	 * 根据名字查找
	 * @param roleName
	 * @return
	 */
	public List<Role> finByRoleName(String roleName) {
		List<Role> list= new ArrayList<Role>();
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select role_id,role_name,create_date,create_by,update_by,last_update_time from role where role_name = ?");
			ps.setString(1, roleName);
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
	/**
	 * 根据id查找
	 * @param roleName
	 * @return
	 */
	public Role finByRoleId(int roleId) {
		List<Role> list= new ArrayList<Role>();
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select role_id,role_name,create_date,create_by,update_by,last_update_time from role where role_id = ?");
			ps.setInt(1, roleId);
			rs =  ps.executeQuery();
			
			list = dealResultSet(rs);
			if(list.size() != 0)
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
	
	/**
	 * 查询全部数据
	 * @return
	 */
	public List<Role> queryAll() {
		List<Role> list= new ArrayList<Role>();
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select role_id,role_name,create_date,create_by,update_by,last_update_time from role");
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
	/**
	 * 解析resultset
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public List dealResultSet(ResultSet rs) throws SQLException{
		List<Role> list= new ArrayList<Role>();
		while(rs.next()) {
			Role role = new Role();
			int roleId = rs.getInt(1);
			String roleName = rs.getString(2);
			Date create_date = rs.getDate(3);
			int createBy = rs.getInt(4);
			int updateBy = rs.getInt(5);
			Date lastUpdateTime = rs.getDate(6);

			role.setRoleId(roleId);
			role.setRoleName(roleName);
			role.setCrateDate(create_date);
			role.setCreateBy(createBy);
			role.setUpdateBy(updateBy);
			role.setLastUpdateTime(lastUpdateTime);

			list.add(role);
		}
		return list;
	}
	
	public List<Role> finByMap(Map<String, Object> map,int start,int size){
		List<Role> list= new ArrayList<Role>();
		
		try {
			conn = DBUtil.getConnection();
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append("select role_id,role_name,create_date,create_by,update_by,last_update_time from role where 1 = 1");
			int index = 0;
			if(map.get("roleName") != null && map.get("roleName").toString().length() != 0) {
				sBuffer.append(" and role_name like ? ");
			}
			sBuffer.append(" order by role_id");
			if(start != -1 && size != -1)
				sBuffer.append(" limit ?,?");
			
			ps = conn.prepareStatement(sBuffer.toString());
			if(map.get("roleName") != null && map.get("roleName").toString().length() != 0) {
				ps.setString(++index, "%" +map.get("roleName").toString()+"%");
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
	
	public int getCountByMap(Map<String, Object> map){		
		try {
			conn = DBUtil.getConnection();
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append("select count(1) from role where 1 = 1");
			int index = 0;
			if(map.get("roleName") != null && map.get("roleName").toString().length() != 0) {
				sBuffer.append(" and role_name like ? ");
			}
			ps = conn.prepareStatement(sBuffer.toString());
			if(map.get("roleName") != null && map.get("roleName").toString().length() != 0) {
				ps.setString(++index, "%" +map.get("roleName").toString()+"%");
			}
			rs =  ps.executeQuery();
			int count = 0;
			if(rs.next()) {
				count = rs.getInt(1);
			}
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

}
