package com.zhiyou.rj14.xuqiaoqiang.jdbc.business.role.service;

import java.util.List;
import java.util.Map;

import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.role.dao.RoleDao;
import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.role.entries.Role;

public class RoleService {
	RoleDao roleDao = new RoleDao();
	
	public List<Role> finByMap(Map<String, Object> map,int start,int size) {
		return roleDao.finByMap(map,start,size);
	}
	
	public int getCountByRole(Map<String, Object> map) {
		return roleDao.getCountByMap(map);
	}
	public void delete(int roleId) {
		roleDao.delete(roleId);
	}
	public Role finRoleById(int roleId) {
		return roleDao.finByRoleId(roleId);
	}
	public void insert(Role role) {
		roleDao.insert(role);
	}
	public void updateByRoleId(Role role){
		roleDao.Update(role);
	}

}
