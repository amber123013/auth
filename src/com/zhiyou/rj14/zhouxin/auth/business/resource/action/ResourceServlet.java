package com.zhiyou.rj14.zhouxin.auth.business.resource.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhiyou.rj14.zhouxin.auth.business.resource.entries.Resource;
import com.zhiyou.rj14.zhouxin.auth.business.resource.service.ResourceService;
import com.zhiyou.rj14.zhouxin.auth.business.user.entries.User;
import com.zhiyou.rj14.zhouxin.auth.common.entries.Page;

public class ResourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ResourceService resourceService = new ResourceService();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		if("list".equals(method)){
			//查询用户
			list(req, resp);
		}else if("toAdd".equals(method)){
			//到达添加页面
			toAdd(req, resp);
		}else if("add".equals(method)){
			//添加用户
			addResource(req, resp);
		}else if("toUpdate".equals(method)){
			toUpdate(req, resp);
		}else if("update".equals(method)){
			//更新用户
			updateResource(req, resp);
		} else if("delete".equals(method)){
			//删除用户
			deleteResource(req, resp);
		}
	}

	protected void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/jsp/zhouxin/resource/resourceadd.jsp").forward(req, resp);
	}
	
	protected void addResource(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String resourceName = req.getParameter("resourceName");
		String uri = req.getParameter("uri");
		String description = req.getParameter("description");
		String resourceType = req.getParameter("resourceType");
		
		Resource resource = new Resource();
		
		resource.setResourceName(resourceName);
		resource.setUri(uri);
		resource.setResourceType(resourceType);
		resource.setDescription(description);
		
		//看这个account在系统中是否已经存在，如果存在则不允许添加
		if(resourceService.findByResourceName(resourceName) != null){
			req.setAttribute("resource", resource);
			req.setAttribute("msg", "该资源名称在系统中已存在!");
			req.getRequestDispatcher("/jsp/zhouxin/resource/resourceadd.jsp").forward(req, resp);
		}else{
			//从session得到当前用户
			User currUser = (User)req.getSession().getAttribute("CurrentUser");
			Long currUserId = currUser.getUserId();
			resource.setCreateBy(currUserId);
			resource.setLastUpdateBy(currUserId);
			
			resourceService.create(resource);
			req.setAttribute("success", "success");
			req.setAttribute("msg", "添加资源成功！");
			//保存成功后，转发到列表页面
			req.getRequestDispatcher("/resource.do?method=list").forward(req, resp);
		}
	}
	
	/**
	 * 查询用户
	 */
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//分页信息
		int pageSize = 7;
		int currPageNum = 1;
		String pageSizeStr = req.getParameter("pageSize");
		if(pageSizeStr != null && pageSizeStr.length() != 0 ){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		String currPageNumStr = req.getParameter("currPageNum");
		if(currPageNumStr != null && currPageNumStr.length() != 0){
			currPageNum = Integer.parseInt(currPageNumStr);
		}
		//查询条件
		String resourceType = req.getParameter("q_resourceType");
		String resourceName = req.getParameter("q_resourceName");
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("resourceType", resourceType);
		queryMap.put("resourceName", resourceName);
		
		int totalNum = resourceService.countByQueryMap(queryMap);
		Page page = new Page(pageSize);
		page.setCurrPageNum(currPageNum);
		page.setTotalNum(totalNum);
		
		List<Resource> resourceList = resourceService.findByQueryMap(queryMap, page.getStartNum(), page.getPageSize());
		
		req.setAttribute("page", page);
		req.setAttribute("queryMap", queryMap);
		req.setAttribute("resourceList", resourceList);
		
		req.getRequestDispatcher("/jsp/zhouxin/resource/resourcelist.jsp").forward(req, resp);//转发
	}
	
	protected void toUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String resourceIdStr = req.getParameter("selResourceId");
		Resource resource = resourceService.findByPrimaryKey(Long.parseLong(resourceIdStr));
		if(resource == null){
			req.setAttribute("success", "error");
			req.setAttribute("msg", "该资源不存在！");
			req.getRequestDispatcher("/resource.do?method=list").forward(req, resp);
		}else {
			req.setAttribute("resource", resource);
			req.getRequestDispatcher("/jsp/zhouxin/resource/resourceupdate.jsp").forward(req, resp);
		}
	}
	
	protected void updateResource(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String resourceIdStr = req.getParameter("resourceId");
		String resourceName = req.getParameter("resourceName");
		String uri = req.getParameter("uri");
		String resourceType = req.getParameter("resourceType");
		String description = req.getParameter("description");
		
		Resource resource = new Resource();
		
		resource.setResourceId(Long.parseLong(resourceIdStr));
		resource.setResourceName(resourceName);
		resource.setUri(uri);
		resource.setResourceType(resourceType);
		resource.setDescription(description);
		//从session得到当前用户
		User currUser = (User)req.getSession().getAttribute("CurrentUser");
		resource.setLastUpdateBy(currUser.getUserId());
		
		resourceService.update(resource);
		
		req.setAttribute("success", "success");
		req.setAttribute("msg", "更新用户信息成功！");
		
		req.getRequestDispatcher("/resource.do?method=list").forward(req, resp);//转发
	}
	
	protected void deleteResource(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String resourceIdStr = req.getParameter("selResourceId");
		resourceService.delete(Long.parseLong(resourceIdStr));
		req.setAttribute("success", "success");
		req.setAttribute("msg", "删除用户成功！");
		
		req.getRequestDispatcher("/resource.do?method=list").forward(req, resp);//转发
	}

}
