package com.zhiyou.rj14.zhouxin.auth.business.resource.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.zhiyou.rj14.zhouxin.auth.business.resource.entries.Resource;
import com.zhiyou.rj14.zhouxin.auth.common.util.DBUtil;

public class ResourceDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	/**
	 * 更新resource部分信息
	 */
	public void update(Resource resource){
		StringBuilder sqlSb = new StringBuilder();
		try{
			//得到连接
			conn = DBUtil.getConnection();
			
			sqlSb.append("UPDATE sys_resource SET resource_name=?,uri=?,resource_type=?,description=?,last_update_by=?,last_update_time=?");
			sqlSb.append(" WHERE resource_id=?");
			
			ps = conn.prepareStatement(sqlSb.toString());
			//设置参数
			ps.setString(1, resource.getResourceName());
			ps.setString(2, resource.getUri());
			ps.setString(3, resource.getResourceType());
			ps.setString(4, resource.getDescription());
			ps.setLong(5, resource.getLastUpdateBy());
			ps.setDate(6, new java.sql.Date(System.currentTimeMillis()));
			ps.setLong(7, resource.getResourceId());
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
	 * 创建resource
	 */
	public void create(Resource resource){
		StringBuilder sqlSb = new StringBuilder();//线程不安全
		try {
			conn = DBUtil.getConnection();
			
			sqlSb.append("INSERT INTO sys_resource(resource_name,uri,resource_type,description,create_by,last_update_by,create_time,last_update_time)");
			sqlSb.append(" VALUES(?,?,?,?,?,?,?,?)");
			//设置statement参数，要求返回主键
			ps = conn.prepareStatement(sqlSb.toString(), Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, resource.getResourceName());
			ps.setString(2, resource.getUri());
			ps.setString(3, resource.getResourceType());
			ps.setString(4, resource.getDescription());
			ps.setLong(5, resource.getCreateBy());
			ps.setLong(6, resource.getLastUpdateBy());
			ps.setDate(7, new java.sql.Date(System.currentTimeMillis()));
			ps.setDate(8, new java.sql.Date(System.currentTimeMillis()));
			
			ps.executeUpdate();
			//得到数据库自动生成的主键
			rs = ps.getGeneratedKeys();
			if(rs.next()){
				Long resourceId = rs.getLong(1);
				resource.setResourceId(resourceId);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeConn(conn, ps, rs);
		}
	}
	/**
	 * 删除资源
	 */
	public void delete(Long resourceId){
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM sys_resource WHERE resource_id=?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, resourceId);
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
	 * 根据主键查询resource
	 */
	public Resource findByPrimaryKey(Long resourceId){
		Resource resource = null;
		try {
			//1.得到连接
			conn = DBUtil.getConnection();
			//2.准备SQL
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("SELECT resource_id,resource_name,uri,resource_type,description,create_by,last_update_by,create_time,last_update_time");
			sqlSb.append(" FROM sys_resource");
			sqlSb.append(" WHERE resource_id=?");
			ps = conn.prepareStatement(sqlSb.toString());
			ps.setLong(1, resourceId);
			//3.发送给数据库服务器执行
			//4.收到数据库服务器返回的结果
			rs = ps.executeQuery();
			//5.处理结果
			List<Resource> resourceList = dealResultSet(rs);
			if(resourceList != null && resourceList.size() > 0){
				resource = resourceList.get(0);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//6.关闭连接
			DBUtil.closeConn(conn, ps, rs);
		}
		return resource;
	}
	
	/**
	 * 根据资源名称查询resource
	 */
	public Resource findByResourceName(String resourceName){
		Resource resource = null;
		try {
			//1.得到连接
			conn = DBUtil.getConnection();
			//2.准备SQL
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("SELECT resource_id,resource_name,uri,resource_type,description,create_by,last_update_by,create_time,last_update_time");
			sqlSb.append(" FROM sys_resource");
			sqlSb.append(" WHERE resource_name=?");
			ps = conn.prepareStatement(sqlSb.toString());
			ps.setString(1, resourceName);
			//3.发送给数据库服务器执行
			//4.收到数据库服务器返回的结果
			rs = ps.executeQuery();
			//5.处理结果
			List<Resource> resourceList = dealResultSet(rs);
			if(resourceList != null && resourceList.size() > 0){
				resource = resourceList.get(0);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//6.关闭连接
			DBUtil.closeConn(conn, ps, rs);
		}
		return resource;
	}

	public int countByQueryMap(Map<String, Object> map){
		int totalNum = 0;
		try {
			//1.得到连接
			conn = DBUtil.getConnection();
			//2.准备SQL
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("SELECT count(1)");
			sqlSb.append(" FROM sys_resource");
			sqlSb.append(" WHERE 1=1");
			
			if(map.get("resourceName") != null && map.get("resourceName").toString().trim().length() != 0){
				sqlSb.append(" AND resource_name LIKE ?");
			}
			if(map.get("resourceType") != null && map.get("resourceType").toString().trim().length() != 0){
				sqlSb.append(" AND resource_type=?");
			}
			ps = conn.prepareStatement(sqlSb.toString());
			int index = 1;
			if(map.get("resourceName") != null && map.get("resourceName").toString().trim().length() != 0){
				ps.setString(index++, "%" + map.get("resourceName").toString().trim() + "%");
			}
			if(map.get("resourceType") != null && map.get("resourceType").toString().trim().length() != 0){
				ps.setString(index++, map.get("resourceType").toString().trim());
			}
			//3.发送给数据库服务器执行
			//4.收到数据库服务器返回的结果
			rs = ps.executeQuery();
			//5.处理结果
			if(rs.next()){
				totalNum = rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//6.关闭连接
			DBUtil.closeConn(conn, ps, rs);
		}
		return totalNum;
	}

	public List<Resource> findByQueryMap(Map<String, Object> map, int start, int size){
		List<Resource> resourceList = new ArrayList<Resource>();
		try {
			//1.得到连接
			conn = DBUtil.getConnection();
			//2.准备SQL
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("SELECT resource_id,resource_name,uri,resource_type,description,create_by,last_update_by,create_time,last_update_time");
			sqlSb.append(" FROM sys_resource");
			sqlSb.append(" WHERE 1=1");
			
			if(map.get("resourceName") != null && map.get("resourceName").toString().trim().length() != 0){
				sqlSb.append(" AND resource_name LIKE ?");
			}
			if(map.get("resourceType") != null && map.get("resourceType").toString().trim().length() != 0){
				sqlSb.append(" AND resource_type=?");
			}
			sqlSb.append(" ORDER BY resource_name");
			if(start != -1 && size != -1){
				sqlSb.append(" LIMIT ?,?");
			}
			
			ps = conn.prepareStatement(sqlSb.toString());
			
			int index = 1;
			if(map.get("resourceName") != null && map.get("resourceName").toString().trim().length() != 0){
				ps.setString(index++, "%" + map.get("resourceName").toString().trim() + "%");
			}
			if(map.get("resourceType") != null && map.get("resourceType").toString().trim().length() != 0){
				ps.setString(index++, map.get("resourceType").toString().trim());
			}
			if(start != -1 && size != -1){
				ps.setInt(index++, start);
				ps.setInt(index++, size);
			}
			//3.发送给数据库服务器执行
			//4.收到数据库服务器返回的结果
			rs = ps.executeQuery();
			//5.处理结果
			resourceList = dealResultSet(rs);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//6.关闭连接
			DBUtil.closeConn(conn, ps, rs);
		}
		return resourceList;
	}
	
	/**
	 * 处理查询结果
	 */
	protected List<Resource> dealResultSet(ResultSet rs) throws SQLException{
		List<Resource> resourceList = new ArrayList<Resource>();
		while(rs.next()){
			Resource resource = new Resource();
			
			resource.setResourceId(rs.getLong("resource_id"));
			resource.setResourceName(rs.getString("resource_name"));
			resource.setUri(rs.getString("uri"));
			resource.setResourceType(rs.getString("resource_type"));
			resource.setDescription(rs.getString("description"));
			resource.setCreateBy(rs.getLong("create_by"));
			resource.setCreateTime(rs.getDate("create_time"));
			resource.setLastUpdateBy(rs.getLong("last_update_by"));
			resource.setLastUpdateTime(rs.getDate("last_update_time"));
			
			resourceList.add(resource);
		}
		return resourceList;
	}
	/**
	 * 根据用户ID查找资源的uri
	 * @param userId
	 * @return
	 */
	public List<Resource> findUrisByUserId(Long userId){
		List<Resource> resourceList = new ArrayList<Resource>();
		try {
			//1.得到连接
			conn = DBUtil.getConnection();
			//2.准备SQL
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("SELECT DISTINCT re.resource_id,re.resource_name,re.uri,re.resource_type");
			sqlSb.append(" FROM sys_user_role ur");
			sqlSb.append(" LEFT JOIN sys_role r");
			sqlSb.append(" ON ur.role_id=r.role_id");
			sqlSb.append(" LEFT JOIN sys_role_resource rr");
			sqlSb.append(" ON r.role_id=rr.role_id");
			sqlSb.append(" LEFT JOIN sys_resource re");
			sqlSb.append(" ON rr.resource_id=re.resource_id");
			sqlSb.append(" WHERE re.uri IS NOT NULL AND ur.user_id=?");
			
			ps = conn.prepareStatement(sqlSb.toString());
			
			ps.setLong(1, userId);
			//3.发送给数据库服务器执行
			//4.收到数据库服务器返回的结果
			rs = ps.executeQuery();
			//5.处理结果
			while(rs.next()){
				Resource resource = new Resource();
				
				resource.setResourceId(rs.getLong("resource_id"));
				resource.setResourceName(rs.getString("resource_name"));
				resource.setUri(rs.getString("uri"));
				resource.setResourceType(rs.getString("resource_type"));
				
				resourceList.add(resource);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//6.关闭连接
			DBUtil.closeConn(conn, ps, rs);
		}
		return resourceList;
	
	}
}
