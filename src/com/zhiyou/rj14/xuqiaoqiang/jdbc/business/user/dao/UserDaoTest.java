package com.zhiyou.rj14.xuqiaoqiang.jdbc.business.user.dao;

import java.sql.Date;

import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.role.dao.RoleDao;
import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.role.entries.Role;
import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.user.entries.User;

public class UserDaoTest {

	public static void main(String[] args) {
		//System.out.println(new UserDao().finByUsername("%ad%"));
		//updateTest()
		new UserDao().delete(4);
	}
	
	public static void updateTest(){
		
		User user = new User();
		user.setUserName("mingming");
		user.setUpdateBy(2);		
		user.setUserId(4);
		user.setUpdateTime(new Date(System.currentTimeMillis()));
		user.setAccount("mingming");
		
		new UserDao().Update(user);
	}
	public static void insertTest(){
		User user = new User();
		user.setUserName("linlin");		
		user.setCreateBy(1);
		user.setUpdateBy(1);
		user.setUpdateTime(new Date(System.currentTimeMillis()));
		user.setCreateDate(new Date(System.currentTimeMillis()));
		user.setAccount("linlin");
		
		new UserDao().insert(user);
	}
}