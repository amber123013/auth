package com.zhiyou.rj14.xuqiaoqiang.jdbc.business.user.service;

import java.util.List;
import java.util.Map;

import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.user.dao.UserDao;
import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.user.entries.User;

public class UserService {
	UserDao userDao = new UserDao();
	
	public List<User> finByMap(Map<String, Object> map,int start,int size) {
		return userDao.finByMap(map,start,size);
	}
	public int finCountByMap(Map<String, Object> map) {
		return userDao.finCountByMap(map);
	}
	public User finUserById(int userId) {
		return userDao.finByUserId(userId);
	}
	public void insert(User user){
		userDao.insert(user);
	}
	
	public void delete(int userId) {
		userDao.delete(userId);
	}
	public void update(User user) {
		userDao.Update(user);
	}
	
	public User finUserByAccount(String account) {
		return userDao.finByAccount(account);
	}
}
