package com.zhiyou.rj14.xuqiaoqiang.jdbc.business.userrole.service;

import java.util.List;

import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.userrole.dao.UserRoleDao;
import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.userrole.entries.UserRole;

public class UserRoleService {
	UserRoleDao userRoleDao = new UserRoleDao();
	public void insert(List<UserRole> list) {
		userRoleDao.insert(list);
	}
	
	public List<Integer> finRoleIdsByUserId(int userId) {
		return userRoleDao.finRoleIdsByUserId(userId);
	}
	
	public void deleteUserRoleByUserId(int userId) {
		userRoleDao.deleteUserRoleByUserId(userId);
	}
}
