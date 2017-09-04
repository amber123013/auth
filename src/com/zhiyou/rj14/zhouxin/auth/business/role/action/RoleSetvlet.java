package com.zhiyou.rj14.zhouxin.auth.business.role.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhiyou.rj14.zhouxin.auth.business.resource.entries.Resource;
import com.zhiyou.rj14.zhouxin.auth.business.resource.service.ResourceService;
import com.zhiyou.rj14.zhouxin.auth.business.role.entries.Role;
import com.zhiyou.rj14.zhouxin.auth.business.role.service.RoleService;
import com.zhiyou.rj14.zhouxin.auth.business.roleresource.entries.RoleResource;
import com.zhiyou.rj14.zhouxin.auth.business.roleresource.service.RoleResourceService;
import com.zhiyou.rj14.zhouxin.auth.business.user.entries.User;
import com.zhiyou.rj14.zhouxin.auth.common.entries.Page;

public class RoleSetvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RoleService roleService = new RoleService();
	private RoleResourceService roleResourceService = new RoleResourceService();
	private ResourceService resourceService = new ResourceService();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		if("list".equals(method)){
			//查询用户
			list(req, resp);
		}else if("toAdd".equals(method)){
			//到达添加页面
			toAdd(req, resp);
		}else if("add".equals(method)){
			//添加用户
			addRole(req, resp);
		}else if("delete".equals(method)){
			//删除用户
			deleteRole(req, resp);
		} else if("toAssignResource".equals(method)){
			//到达资源分配页面
			toAssignResource(req, resp);
		} else if("assignResource".equals(method)){
			//分配资源
			assignResource(req, resp);
		}
	}
	
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
		String roleCode = req.getParameter("q_roleCode");
		String roleName = req.getParameter("q_roleName");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("roleCode", roleCode);
		queryMap.put("roleName", roleName);
		
		int totalNum = roleService.countByQueryMap(queryMap);
		Page page = new Page(pageSize);
		page.setCurrPageNum(currPageNum);
		page.setTotalNum(totalNum);
		List<Role> roleList = roleService.findByQueryMap(queryMap, page.getStartNum(), page.getPageSize());
		
		req.setAttribute("page", page);
		req.setAttribute("roleList", roleList);
		req.getRequestDispatcher("/jsp/zhouxin/role/rolelist.jsp").forward(req, resp);
	}
	
	protected void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/jsp/zhouxin/role/roleadd.jsp").forward(req, resp);
	}
	
	protected void addRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String roleCode = req.getParameter("roleCode");
		String roleName = req.getParameter("roleName");
		String description = req.getParameter("dedcription");
		
		Role role = new Role();
		
		role.setRoleCode(roleCode);
		role.setRoleName(roleName);
		role.setDescription(description);
		User currUser = (User)req.getSession().getAttribute("CurrentUser");
		role.setCreateBy(currUser.getUserId());
		role.setLastUpdateBy(currUser.getUserId());
		
		roleService.create(role);
		req.setAttribute("success", "success");
		req.setAttribute("msg", "添加角色成功！");
		req.getRequestDispatcher("/role.do?method=list").forward(req, resp);
	}
	
	protected void deleteRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String roleId = req.getParameter("selRoleId");
		roleService.delete(Long.parseLong(roleId));
		req.setAttribute("success", "success");
		req.setAttribute("msg", "删除角色成功！");
		req.getRequestDispatcher("/role.do?method=list").forward(req, resp);
	}
	
	protected void toAssignResource(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String roleIdStr = req.getParameter("selRoleId");
		Long roleId = Long.parseLong(roleIdStr);
		Role role = roleService.findByPrimaryKey(roleId);
		List<RoleResource> roleSourceList = roleResourceService.findByRoleId(roleId);
		StringBuffer resourceIdsSb = new StringBuffer();
		for(RoleResource rr : roleSourceList){
			resourceIdsSb.append("\"" + rr.getResourceId().longValue() + "\",");
		}
		if(resourceIdsSb.length() > 0){
			resourceIdsSb.deleteCharAt(resourceIdsSb.length() - 1);
		}
		List<Resource> resourceList = resourceService.findByQueryMap(new HashMap<String, Object>(), -1, -1);
		
		req.setAttribute("role", role);
		req.setAttribute("ownResourceIds", resourceIdsSb.toString());
		req.setAttribute("resourceList", resourceList);
		req.getRequestDispatcher("/jsp/zhouxin/role/assignresource.jsp").forward(req, resp);
	}
	
	protected void assignResource(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String roleIdStr = req.getParameter("role_id");
		String[] resourceIds = req.getParameterValues("resourceId");
		Long roleId = Long.parseLong(roleIdStr);
		User currUser = (User)req.getSession().getAttribute("CurrentUser");
		Long currUserId = currUser.getUserId();
		if(resourceIds != null && resourceIds.length > 0){
			List<RoleResource> list = new ArrayList<RoleResource>();
			for(String idStr : resourceIds){
				RoleResource rr = new RoleResource();
				rr.setRoleId(roleId);
				rr.setResourceId(Long.parseLong(idStr));
				rr.setCreateBy(currUserId);
				rr.setLastUpdateBy(currUserId);
				
				list.add(rr);
			}
			roleResourceService.deleteByRoleId(roleId);
			roleResourceService.create(list);
		}
		req.setAttribute("success", "success");
		req.setAttribute("msg", "分配资源成功");
		req.getRequestDispatcher("/role.do?method=list").forward(req, resp);
	}

}
