package com.zhiyou.rj14.zhouxin.auth.business.user.service;

import java.util.List;
import java.util.Map;

import com.zhiyou.rj14.zhouxin.auth.business.user.dao.UserDao;
import com.zhiyou.rj14.zhouxin.auth.business.user.entries.User;

public class UserService {
	UserDao userDao = new UserDao();
	
	public User findByPrimaryKey(Long userId){
		return userDao.findByUserId(userId);
	}

	public User findByAccount(String account){
		return userDao.findByAccount(account);
	}

	/**
	 * 根据条件查询记录条数
	 * @param queryMap
	 * @return
	 */
	public int countByQueryMap(Map<String, Object> queryMap){
		return userDao.countByQueryMap(queryMap);
	}

	/**
	 * 根据条件查询user信息
	 * @param queryMap 查询条件组成的map
	 * @param start 开始
	 * @param size 查询条数
	 * @return
	 */
	public List<User> findByQueryMap(Map<String, Object> queryMap, int start, int size){
		return userDao.findByQueryMap(queryMap, start, size);
	}

	public void create(User user){
		userDao.create(user);
	}
	
	public void delete(Long userId){
		userDao.delete(userId);
	}
	
	public void update(User user){
		userDao.update(user);
	}
}
