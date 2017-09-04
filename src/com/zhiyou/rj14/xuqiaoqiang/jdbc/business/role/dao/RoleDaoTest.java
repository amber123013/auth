package com.zhiyou.rj14.xuqiaoqiang.jdbc.business.role.dao;

import java.sql.Date;

import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.role.entries.Role;

public class RoleDaoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//insertTest();
		//System.out.println(new RoleDao().queryAll());
		new RoleDao().delete(6);
	}
	
	public static void updateTest(){
		
		Role role = new Role();
		role.setRoleName("guanliyuan");
		role.setUpdateBy(2);		
		role.setRoleId(1);
		role.setUpdateTime(new Date(System.currentTimeMillis()));
		role.setLastUpdateTime(new Date(System.currentTimeMillis()));
		
		new RoleDao().Update(role);
	}
	
	public static void insertTest(){
		Role role = new Role();
		role.setRoleName("guanliyuan");		
		role.setCreateBy(1);
		role.setUpdateBy(1);
		role.setUpdateTime(new Date(System.currentTimeMillis()));
		role.setLastUpdateTime(new Date(System.currentTimeMillis()));
		role.setCrateDate(new Date(System.currentTimeMillis()));
		
		new RoleDao().insert(role);
	}

}
