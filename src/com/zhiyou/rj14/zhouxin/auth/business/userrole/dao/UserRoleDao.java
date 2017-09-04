package com.zhiyou.rj14.zhouxin.auth.business.userrole.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhiyou.rj14.zhouxin.auth.business.userrole.entries.UserRole;
import com.zhiyou.rj14.zhouxin.auth.common.util.DBUtil;

public class UserRoleDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	/**
	 * 创建新的user
	 */
	public void create(List<UserRole> userRoleList){
		StringBuilder sqlSb = new StringBuilder();
		try {
			conn = DBUtil.getConnection();
			
			sqlSb.append("INSERT INTO sys_user_role(user_id,role_id,create_by,last_update_by,create_time,last_update_time)");
			sqlSb.append(" VALUES(?,?,?,?,?,?)");
			//设置statement参数，要求返回主键
			ps = conn.prepareStatement(sqlSb.toString());
			for(UserRole userRole : userRoleList){
				ps.setLong(1, userRole.getUserId());
				ps.setLong(2, userRole.getRoleId());
				ps.setLong(3, userRole.getCreateBy());
				ps.setLong(4, userRole.getLastUpdateBy());
				ps.setDate(5, new java.sql.Date(System.currentTimeMillis()));
				ps.setDate(6, new java.sql.Date(System.currentTimeMillis()));
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeConn(conn, ps, rs);
		}
	}

	public void deleteByUserId(Long userId){
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM sys_user_role WHERE user_id=?";
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

	public List<UserRole> findByQueryMap(Map<String, Object> queryMap){
		List<UserRole> userRoleList = new ArrayList<UserRole>();
		StringBuilder sqlSb = new StringBuilder();
		try {
			conn = DBUtil.getConnection();
			
			sqlSb.append("SELECT id,user_id,role_id");
			sqlSb.append(" FROM sys_user_role");
			sqlSb.append(" WHERE 1=1");
			
			if(queryMap.get("userId") != null){
				sqlSb.append(" AND user_id=?");
			}
			if(queryMap.get("roleId") != null){
				sqlSb.append(" AND role_id=?");
			}
			ps = conn.prepareStatement(sqlSb.toString());
			int index = 1;
			if(queryMap.get("userId") != null){
				ps.setLong(index++, (Long)queryMap.get("userId"));
			}
			if(queryMap.get("roleId") != null){
				ps.setLong(index++, (Long)queryMap.get("roleId"));
			}
			rs = ps.executeQuery();
			userRoleList = dealResultSet(rs);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeConn(conn, ps, rs);
		}
		return userRoleList;
	}
	
	/**
	 * 处理查询结果
	 */
	protected List<UserRole> dealResultSet(ResultSet rs) throws SQLException{
		List<UserRole> userRoleList = new ArrayList<UserRole>();
		while(rs.next()){
			UserRole userRole = new UserRole();
			
			userRole.setId(rs.getLong("id"));
			userRole.setUserId(rs.getLong("user_id"));
			userRole.setRoleId(rs.getLong("role_id"));
			
			userRoleList.add(userRole);
		}
		return userRoleList;
	}

}
