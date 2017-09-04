package com.zhiyou.rj14.zhouxin.auth.business.role.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhiyou.rj14.zhouxin.auth.business.role.entries.Role;
import com.zhiyou.rj14.zhouxin.auth.common.util.DBUtil;

public class RoleDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	/**
	 * 创建新的user
	 */
	public void create(Role role){
		StringBuilder sqlSb = new StringBuilder();
		try {
			conn = DBUtil.getConnection();
			
			sqlSb.append("INSERT INTO sys_role(role_code,role_name,description,create_by,last_update_by,create_time,last_update_time)");
			sqlSb.append(" VALUES(?,?,?,?,?,?,?)");
			//设置statement参数，要求返回主键
			ps = conn.prepareStatement(sqlSb.toString(), Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, role.getRoleCode());
			ps.setString(2, role.getRoleName());
			ps.setString(3, role.getDescription());
			ps.setLong(4, role.getCreateBy());
			ps.setLong(5, role.getLastUpdateBy());
			ps.setDate(6, new java.sql.Date(System.currentTimeMillis()));
			ps.setDate(7, new java.sql.Date(System.currentTimeMillis()));
			
			ps.executeUpdate();
			//得到数据库自动生成的主键
			rs = ps.getGeneratedKeys();
			while(rs.next()){
				Long userId = rs.getLong(1);
				role.setRoleId(userId);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeConn(conn, ps, rs);
		}
	}

	public void delete(Long roleId){
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM sys_role WHERE role_id=?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, roleId);
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeConn(conn, ps, rs);
		}
	}
	
	public int countByQueryMap(Map<String, Object> queryMap){
		int total = 0;
		StringBuilder sqlSb = new StringBuilder();
		try {
			conn = DBUtil.getConnection();
			
			sqlSb.append("SELECT COUNT(1)");
			sqlSb.append(" FROM sys_role");
			sqlSb.append(" WHERE 1=1");
			ps = conn.prepareStatement(sqlSb.toString());
			rs = ps.executeQuery();
			if(rs.next()){
				total = rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeConn(conn, ps, rs);
		}
		return total;
	}
	
	public Role findByPrimaryKey(Long roleId){
		Role role = null;
		StringBuilder sqlSb = new StringBuilder();
		try {
			conn = DBUtil.getConnection();
			
			sqlSb.append("SELECT role_id,role_code,role_name,description,create_by,last_update_by,create_time,last_update_time");
			sqlSb.append(" FROM sys_role");
			sqlSb.append(" WHERE role_id=?");
			ps = conn.prepareStatement(sqlSb.toString());
			ps.setLong(1, roleId);
			rs = ps.executeQuery();
			List<Role> roleList = dealResultSet(rs);
			if(roleList != null && roleList.size() > 0){
				role = roleList.get(0);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeConn(conn, ps, rs);
		}
		return role;
	}
	
	public List<Role> findByQueryMap(Map<String, Object> queryMap, int start, int size){
		List<Role> roleList = new ArrayList<Role>();
		StringBuilder sqlSb = new StringBuilder();
		try {
			conn = DBUtil.getConnection();
			
			sqlSb.append("SELECT role_id,role_code,role_name,description,create_by,last_update_by,create_time,last_update_time");
			sqlSb.append(" FROM sys_role");
			sqlSb.append(" WHERE 1=1");
			sqlSb.append(" ORDER BY role_id DESC");
			if(start != -1 && size != -1){
				sqlSb.append(" LIMIT ?,?");
			}
			ps = conn.prepareStatement(sqlSb.toString());
			int index = 1;
			if(start != -1 && size != -1){
				ps.setInt(index++, start);
				ps.setInt(index++, size);
			}
			
			rs = ps.executeQuery();
			roleList = dealResultSet(rs);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeConn(conn, ps, rs);
		}
		return roleList;
	}
	
	/**
	 * 处理查询结果
	 */
	protected List<Role> dealResultSet(ResultSet rs) throws SQLException{
		List<Role> roleList = new ArrayList<Role>();
		while(rs.next()){
			Role role = new Role();
			
			role.setRoleId(rs.getLong("role_id"));
			role.setRoleCode(rs.getString("role_code"));
			role.setRoleName(rs.getString("role_name"));
			role.setDescription(rs.getString("description"));
			role.setCreateBy(rs.getLong("create_by"));
			role.setCreateTime(rs.getDate("create_time"));
			role.setLastUpdateBy(rs.getLong("last_update_by"));
			role.setLastUpdateTime(rs.getDate("last_update_time"));
			
			roleList.add(role);
		}
		return roleList;
	}

}