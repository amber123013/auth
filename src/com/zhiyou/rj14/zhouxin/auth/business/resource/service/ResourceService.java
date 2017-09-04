package com.zhiyou.rj14.zhouxin.auth.business.resource.service;

import java.util.List;
import java.util.Map;

import com.zhiyou.rj14.zhouxin.auth.business.resource.dao.ResourceDao;
import com.zhiyou.rj14.zhouxin.auth.business.resource.entries.Resource;

public class ResourceService {
	ResourceDao resourceDao = new ResourceDao();
	
	public Resource findByPrimaryKey(Long resourceId){
		return resourceDao.findByPrimaryKey(resourceId);
	}

	public int countByQueryMap(Map<String, Object> queryMap){
		return resourceDao.countByQueryMap(queryMap);
	}
	
	public Resource findByResourceName(String resourceName){
		return resourceDao.findByResourceName(resourceName);
	}

	public List<Resource> findByUserId(Long userId){
		return resourceDao.findUrisByUserId(userId);
	}

	public List<Resource> findByQueryMap(Map<String, Object> queryMap, int start, int size){
		return resourceDao.findByQueryMap(queryMap, start, size);
	}

	public void create(Resource resource){
		resourceDao.create(resource);
	}
	
	public void delete(Long userId){
		resourceDao.delete(userId);
	}
	
	public void update(Resource resource){
		resourceDao.update(resource);
	}
}
