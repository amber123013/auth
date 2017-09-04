package com.zhiyou.rj14.zhouxin.auth.business.roleresource.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhiyou.rj14.zhouxin.auth.business.roleresource.dao.RoleResourceDao;
import com.zhiyou.rj14.zhouxin.auth.business.roleresource.entries.RoleResource;

public class RoleResourceService {
	private RoleResourceDao roleResourceDao = new RoleResourceDao();

	public void create(RoleResource roleResource) {
		List<RoleResource> list = new ArrayList<RoleResource>();
		roleResourceDao.create(list);
	}

	public void create(List<RoleResource> roleResourceList) {
		roleResourceDao.create(roleResourceList);
	}

	public void deleteByRoleId(Long roleId) {
		roleResourceDao.deleteByRoleId(roleId);
	}

	public List<RoleResource> findByRoleId(Long roleId) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		
		queryMap.put("roleId", roleId);
		return roleResourceDao.findByQueryMap(queryMap);
	}

}
