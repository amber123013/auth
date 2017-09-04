package com.zhiyou.rj14.zhouxin.auth.business.user.action;

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
import com.zhiyou.rj14.zhouxin.auth.business.role.entries.Role;
import com.zhiyou.rj14.zhouxin.auth.business.role.service.RoleService;
import com.zhiyou.rj14.zhouxin.auth.business.user.entries.User;
import com.zhiyou.rj14.zhouxin.auth.business.user.service.UserService;
import com.zhiyou.rj14.zhouxin.auth.business.user.util.UserUtil;
import com.zhiyou.rj14.zhouxin.auth.business.userrole.entries.UserRole;
import com.zhiyou.rj14.zhouxin.auth.business.userrole.service.UserRoleService;
import com.zhiyou.rj14.zhouxin.auth.common.entries.Page;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserService();
	RoleService roleService = new RoleService();
	UserRoleService userRoleService = new UserRoleService();

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
			addUser(req, resp);
		}else if("toUpdate".equals(method)){
			toUpdate(req, resp);
		}else if("update".equals(method)){
			//更新用户
			updateUser(req, resp);
		} else if("delete".equals(method)){
			//删除用户
			deleteUser(req, resp);
		} else if("toAssignRole".equals(method)){
			//分配角色
			toAssignRole(req, resp);
		} else if("assignRole".equals(method)){
			assignRole(req, resp);
		}
	}

	protected void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/jsp/zhouxin/user/useradd.jsp").forward(req, resp);
	}
	
	protected void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String account = req.getParameter("account");
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String password = req.getParameter("password");
		
		User user = new User();
		
		user.setAccount(account);
		user.setPassword(password);
		user.setUsername(username);
		user.setEmail(email);
		user.setPhone(phone);
		
		//看这个account在系统中是否已经存在，如果存在则不允许添加
		if(userService.findByAccount(account) != null){
			req.setAttribute("user", user);
			req.setAttribute("msg", "该账户已被注册!");
			req.getRequestDispatcher("/jsp/zhouxin/user/useradd.jsp").forward(req, resp);
		}else{
			//从session得到当前用户
			User currUser = (User)req.getSession().getAttribute("CurrentUser");
			user.setCreateBy(currUser.getUserId());
			user.setLastUpdateBy(currUser.getUserId());
			
			userService.create(user);
			req.setAttribute("success", "success");
			req.setAttribute("msg", "添加用户成功！");
			//保存成功后，转发到列表页面
			req.getRequestDispatcher("/user.do?method=list").forward(req, resp);
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
		String account = req.getParameter("q_account");
		String userName = req.getParameter("q_username");
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("account", account);
		queryMap.put("username", userName);
		
		int totalNum = userService.countByQueryMap(queryMap);
		Page page = new Page(pageSize);
		page.setCurrPageNum(currPageNum);
		page.setTotalNum(totalNum);
		
		List<User> userList = userService.findByQueryMap(queryMap, page.getStartNum(), page.getPageSize());
		
		@SuppressWarnings("unchecked")
		List<Resource> resourceList = (List<Resource>)req.getSession().getAttribute("CurrentUserResources");
		req.setAttribute("resourceList", resourceList);
		req.setAttribute("page", page);
		req.setAttribute("queryMap", queryMap);
		req.setAttribute("userList", userList);
		
		req.getRequestDispatcher("/jsp/zhouxin/user/userlist.jsp").forward(req, resp);//转发
	}
	
	protected void toUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String userIdStr = req.getParameter("selUserId");
		User user = null;
		if(UserUtil.validateUserId(userIdStr)){
			user = userService.findByPrimaryKey(Long.parseLong(userIdStr));
		}else{
			req.setAttribute("success", "error");
			req.setAttribute("msg", "用户ID不正确！");
			req.getRequestDispatcher("/user.do?method=list").forward(req, resp);
		}
		if(user == null){
			req.setAttribute("success", "error");
			req.setAttribute("msg", "用户不存在！");
			req.getRequestDispatcher("/user.do?method=list").forward(req, resp);
		}else {
			req.setAttribute("user", user);
			req.getRequestDispatcher("/jsp/zhouxin/user/userupdate.jsp").forward(req, resp);
		}
	}
	
	protected void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String userId = req.getParameter("user_id");
		String password = req.getParameter("password");
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		
		User user = new User();
		
		user.setUserId(Long.parseLong(userId));
		user.setPassword(password);
		user.setUsername(username);
		user.setEmail(email);
		user.setPhone(phone);
		//从session得到当前用户
		User currUser = (User)req.getSession().getAttribute("CurrentUser");
		user.setLastUpdateBy(currUser.getUserId());
		
		userService.update(user);
		
		req.setAttribute("success", "success");
		req.setAttribute("msg", "更新用户信息成功！");
		
		req.getRequestDispatcher("/user.do?method=list").forward(req, resp);//转发
	}
	
	protected void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String userIdStr = req.getParameter("selUserId");
		if(UserUtil.validateUserId(userIdStr)){
			userService.delete(Long.parseLong(userIdStr));
			req.setAttribute("success", "success");
			req.setAttribute("msg", "删除用户成功！");
		}else{
			req.setAttribute("success", "error");
			req.setAttribute("msg", "删除用户失败！");
		}
		
		req.getRequestDispatcher("/user.do?method=list").forward(req, resp);//转发
	}
	
	protected void toAssignRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String userIdStr = req.getParameter("selUserId");
		Long userId = Long.parseLong(userIdStr);
		
		User user = userService.findByPrimaryKey(userId);
		List<Role> roleList = roleService.findByQueryMap(null, -1, -1) ;
		List<UserRole> userRoleList = userRoleService.findByUserId(userId);
		
		StringBuffer ownRoleIds = new StringBuffer();
		for(UserRole userRole : userRoleList){
			ownRoleIds.append("\"" + userRole.getRoleId().longValue() + "\",");
		}
		if(ownRoleIds.length() != 0){
			ownRoleIds.deleteCharAt(ownRoleIds.length() - 1);
		}
		req.setAttribute("ownRoleIds", ownRoleIds);
		req.setAttribute("user", user);
		req.setAttribute("roleList", roleList);
		req.setAttribute("userRoleList", userRoleList);
		req.getRequestDispatcher("/jsp/zhouxin/user/assignrole.jsp").forward(req, resp);
	}
	
	protected void assignRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String[] roleIdStrs = req.getParameterValues("roleId");
		String useIdStr = req.getParameter("user_id");
		User currUser = (User)req.getSession().getAttribute("CurrentUser");
		Long userId = Long.parseLong(useIdStr);
		Long currUserId = currUser.getUserId();
		
		List<UserRole> userRoleList = new ArrayList<UserRole>();
		
		if(roleIdStrs != null && roleIdStrs.length > 0){
			for(String roleIdStr : roleIdStrs){
				Long roleId = Long.parseLong(roleIdStr);
				
				UserRole userRole = new UserRole();
				
				userRole.setUserId(userId);
				userRole.setRoleId(roleId);
				userRole.setCreateBy(currUserId);
				userRole.setLastUpdateBy(currUserId);
				
				userRoleList.add(userRole);
			}
			userRoleService.deleteByUserId(userId);//先删除分配的角色信息
			userRoleService.create(userRoleList);//新增角色信息
		}
		
		req.setAttribute("success", "success");
		req.setAttribute("msg", "分配角色成功！");
		req.getRequestDispatcher("/user.do?method=list").forward(req, resp);
	}
	
}
