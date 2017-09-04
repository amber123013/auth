package com.zhiyou.rj14.xuqiaoqiang.jdbc.business.userrole.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.user.dao.UserDao;
import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.userrole.entries.UserRole;
import com.zhiyou.rj14.xuqiaoqiang.jdbc.common.util.DBUtil;

public class UserRoleDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	ResultSet rs = null;
	
	public void insert(List<UserRole> list) {
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("insert into user_role(user_id,role_id,create_date,create_by,update_time,update_by) value(?,?,?,?,?,?)");
			for(int i=0;i<list.size();i++) {
				UserRole userRole = list.get(i);
				ps.setInt(1, userRole.getUserId());
				ps.setInt(2, userRole.getRoleId());
				ps.setDate(3, userRole.getCrateDate());
				ps.setInt(4, userRole.getCreateBy());
				ps.setDate(5, userRole.getUpdateTime());
				ps.setInt(6, userRole.getUpdateBy());
				ps.executeUpdate();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(conn, ps, rs);
		}
	}
	
	public List<Integer> finRoleIdsByUserId(int userId) {
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select role_id from user_role where user_id = ?");
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			List<Integer> list = new ArrayList<Integer>();
			while(rs.next()){
				list.add(rs.getInt(1));
			}
			return list;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return null;
	}
	
	public void deleteUserRoleByUserId(int userId) {
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("delete from user_role where user_id = ?");
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
}
