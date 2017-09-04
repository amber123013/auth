package com.zhiyou.rj14.zhouxin.auth.business.user.dao;

import java.util.List;

import com.zhiyou.rj14.zhouxin.auth.business.user.entries.User;


public class UserDaoTest {
	
	public static void main(String[] args){
		testQuery();
//		testCreate();
//		testDelete();
	}
	
	public static void testUpdate(){

		UserDao userDao = new UserDao();
		
		User user = new User();
		
		user.setUserId(4L);
		user.setUsername("张国荣");
		user.setPassword("123456");
		user.setEmail("zhangguorong@qq.com");
		user.setPhone("139111111");
		user.setCreateBy(1L);
		user.setLastUpdateBy(1L);
		
		userDao.create(user);
		
		System.out.println(user);
		
	}
	
	public static void testCreate(){
		UserDao userDao = new UserDao();
		
		User user = new User();
		
		System.out.println(user);
		
		user.setAccount("zhangguorong");
		user.setUsername("张国荣");
		user.setPassword("123456");
		user.setEmail("zhangguorong@qq.com");
		user.setPhone("13900000000");
		user.setCreateBy(1L);
		user.setLastUpdateBy(1L);
		
		userDao.create(user);
		
		System.out.println(user);
		
	}
	
	public static void testDelete(){
		new UserDao().delete(7L);
	}
	
	public static void testQuery(){
		UserDao userDao = new UserDao();
		//List<User> userList = userDao.queryAll();
		//List<User> userList = userDao.findByUsername("刘");
		//System.out.println(userList);
	}
}
