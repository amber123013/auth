package com.zhiyou.rj14.zhouxin.auth.business.role.service;

import java.util.List;
import java.util.Map;

import com.zhiyou.rj14.zhouxin.auth.business.role.dao.RoleDao;
import com.zhiyou.rj14.zhouxin.auth.business.role.entries.Role;

public class RoleService {
	private RoleDao roleDao = new RoleDao();
	
	public Role findByPrimaryKey(Long roleId){
		return roleDao.findByPrimaryKey(roleId);
	}
	
	public void create(Role role){
		roleDao.create(role);
	}
	
	public void delete(Long roleId){
		roleDao.delete(roleId);
	}
	
	public List<Role> findByQueryMap(Map<String, Object> queryMap, int start, int size){
		return roleDao.findByQueryMap(queryMap, start, size);
	}
	
	public int countByQueryMap(Map<String, Object> queryMap){
		return roleDao.countByQueryMap(queryMap);
	}
}
