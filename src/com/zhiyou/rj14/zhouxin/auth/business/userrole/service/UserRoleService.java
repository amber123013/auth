package com.zhiyou.rj14.zhouxin.auth.business.userrole.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhiyou.rj14.zhouxin.auth.business.userrole.dao.UserRoleDao;
import com.zhiyou.rj14.zhouxin.auth.business.userrole.entries.UserRole;

public class UserRoleService {
	private UserRoleDao userRoleDao = new UserRoleDao();

	public void create(UserRole userRole) {
		List<UserRole> list = new ArrayList<UserRole>();
		userRoleDao.create(list);
	}

	public void create(List<UserRole> userRoleList) {
		userRoleDao.create(userRoleList);
	}

	public void deleteByUserId(Long userId) {
		userRoleDao.deleteByUserId(userId);
	}

	public List<UserRole> findByRoleId(Long roleId) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		
		queryMap.put("roleId", roleId);
		return userRoleDao.findByQueryMap(queryMap);
	}

	public List<UserRole> findByUserId(Long userId) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		
		queryMap.put("userId", userId);
		return userRoleDao.findByQueryMap(queryMap);
	}
}
