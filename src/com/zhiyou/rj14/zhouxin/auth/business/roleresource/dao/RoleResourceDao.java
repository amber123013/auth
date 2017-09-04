package com.zhiyou.rj14.zhouxin.auth.business.roleresource.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhiyou.rj14.zhouxin.auth.business.roleresource.entries.RoleResource;
import com.zhiyou.rj14.zhouxin.auth.common.util.DBUtil;

public class RoleResourceDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	/**
	 * 创建新的user
	 */
	public void create(List<RoleResource> roleResourceList){
		StringBuilder sqlSb = new StringBuilder();
		try {
			conn = DBUtil.getConnection();
			
			sqlSb.append("INSERT INTO sys_role_resource(role_id,resource_id,create_by,last_update_by,create_time,last_update_time)");
			sqlSb.append(" VALUES(?,?,?,?,?,?)");
			//设置statement参数，要求返回主键
			ps = conn.prepareStatement(sqlSb.toString());
			for(RoleResource roleResource : roleResourceList){
				ps.setLong(1, roleResource.getRoleId());
				ps.setLong(2, roleResource.getResourceId());
				ps.setLong(3, roleResource.getCreateBy());
				ps.setLong(4, roleResource.getLastUpdateBy());
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

	public void deleteByRoleId(Long roleId){
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM sys_role_resource WHERE role_id=?";
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

	public List<RoleResource> findByQueryMap(Map<String, Object> queryMap){
		List<RoleResource> roleResourceList = new ArrayList<RoleResource>();
		StringBuilder sqlSb = new StringBuilder();
		try {
			conn = DBUtil.getConnection();
			
			sqlSb.append("SELECT id,role_id,resource_id");
			sqlSb.append(" FROM sys_role_resource");
			sqlSb.append(" WHERE 1=1");
			
			if(queryMap.get("roleId") != null){
				sqlSb.append(" AND role_id=?");
			}
			if(queryMap.get("resourceId") != null){
				sqlSb.append(" AND resource_id=?");
			}
			ps = conn.prepareStatement(sqlSb.toString());
			int index = 1;
			if(queryMap.get("roleId") != null){
				ps.setLong(index++, (Long)queryMap.get("roleId"));
			}
			if(queryMap.get("resourceId") != null){
				ps.setLong(index++, (Long)queryMap.get("resourceId"));
			}
			rs = ps.executeQuery();
			roleResourceList = dealResultSet(rs);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeConn(conn, ps, rs);
		}
		return roleResourceList;
	}
	/**
	 * 处理查询结果
	 */
	protected List<RoleResource> dealResultSet(ResultSet rs) throws SQLException{
		List<RoleResource> roleResourceList = new ArrayList<RoleResource>();
		while(rs.next()){
			RoleResource roleResource = new RoleResource();
			
			roleResource.setId(rs.getLong("id"));
			roleResource.setRoleId(rs.getLong("role_id"));
			roleResource.setResourceId(rs.getLong("resource_id"));
			
			roleResourceList.add(roleResource);
		}
		return roleResourceList;
	}

}
