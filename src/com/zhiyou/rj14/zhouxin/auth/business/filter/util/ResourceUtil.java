package com.zhiyou.rj14.zhouxin.auth.business.filter.util;

import java.util.List;

import com.zhiyou.rj14.zhouxin.auth.business.resource.entries.Resource;

public class ResourceUtil {
	public static boolean canViewResource(List<Resource> list, String uri){
		if(list == null || list.isEmpty())
			return false;
		for(Resource resource : list){
			if(uri.contains(resource.getUri())){
				return true;
			}
		}
		return false;
	}
}
