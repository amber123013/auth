package com.zhiyou.rj14.xuqiaoqiang.jdbc.business.role.action;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.role.entries.Role;
import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.role.service.RoleService;
import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.user.entries.User;

public class RoleServlet extends HttpServlet {

	RoleService roleService = new RoleService();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String method = req.getParameter("method");
		if("query".equals(method)) {
			showRoleList(req,resp);
		} else if("toAdd".equals(method)) {
			req.getRequestDispatcher("jsp/xuqiaoqiang/role/addrole.jsp").forward(req, resp);
		} else if("add".equals(method)) {
			insertRole(req,resp);
		} else if("delete".equals(method)) {
			//删除一条记录
			deleteRole(req,resp);
		} else if("toUpdate".equals(method)) {
			toUpdate(req,resp);
		} else if("update".equals(method)) {
			updateRole(req,resp);
		}
	}
	private void updateRole(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String roleName = req.getParameter("roleName");
		String roleId = req.getParameter("roleId");
		User user = (User)req.getSession().getAttribute("user");
		int updateBy ;

		updateBy = user.getUserId();
		Role tempRole = roleService.finRoleById(Integer.parseInt(roleId));

		Role role = new Role();
		Date updateTime = new Date(System.currentTimeMillis());;
		Date lastUpdateTime = new Date(System.currentTimeMillis());;
		
		role.setCrateDate(tempRole.getCrateDate());
		role.setCreateBy(tempRole.getCreateBy());
		role.setLastUpdateTime(lastUpdateTime);
		role.setRoleName(roleName);
		role.setUpdateBy(updateBy);
		role.setUpdateTime(updateTime);
		role.setRoleId(Integer.parseInt(roleId));
		roleService.updateByRoleId(role);
		
		resp.sendRedirect("role.do?method=query");
	}
	private void toUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("roleId");
		Role role = roleService.finRoleById(Integer.parseInt(id));
		req.setAttribute("role", role);
		req.getRequestDispatcher("jsp/xuqiaoqiang/role/updaterole.jsp").forward(req, resp);
	}
	private void deleteRole(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int roleId = Integer.parseInt(req.getParameter("roleId"));
		roleService.delete(roleId);
		resp.sendRedirect("role.do?method=query");
	}
	private void insertRole(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String roleName = req.getParameter("roleName");

		User user = (User)req.getSession().getAttribute("user");
		int createBy ;
		if(user == null)
			createBy = 1;
		else {
			createBy = user.getUserId();
		}
		
		Date createDate = new Date(System.currentTimeMillis());
		Date updateTIme = new Date(System.currentTimeMillis());
		Role role = new Role();
		int updateBy = createBy;
		Date updateTime = createDate;
		Date lastUpdateTime = createDate;

		role.setCrateDate(createDate);
		role.setCreateBy(createBy);
		role.setLastUpdateTime(lastUpdateTime);
		role.setRoleName(roleName);
		role.setUpdateBy(updateBy);
		role.setUpdateTime(updateTime);
		roleService.insert(role);
		
		resp.sendRedirect("role.do?method=query");
	}
	/**
	 * 显示role列表
	 */
	private void showRoleList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String,Object> map = new HashMap<String, Object>();
		String roleName = req.getParameter("roleName");
		if(roleName != null && !"".equals("roleName")) {
			map.put("roleName", roleName);
		}
		
		String pageSizeStr = req.getParameter("pageSize");
		String currentPageStr = req.getParameter("currentPage");
		//每页的记录数
		int pageSize = 3;
		//当前页
		int currentPage = 1;
		if(currentPageStr != null && !"".equals(currentPageStr)) {
			currentPage = Integer.parseInt(currentPageStr);
		}
		if(pageSizeStr != null && pageSizeStr.length() != 0){
			pageSize = Integer.parseInt(pageSizeStr);
			pageSize = pageSize < 1 ? 1 : pageSize;
		}
		//总记录条数	
		int totalNum = roleService.getCountByRole(map);
		//总页数
		int totalPageNum = totalNum / pageSize;
		if(totalNum % pageSize != 0){
			totalPageNum += 1;
		}
		if(currentPage > totalPageNum)
			currentPage = totalPageNum;
		if(currentPage < 1)
			currentPage = 1;
		int startNum = (currentPage - 1) * pageSize;
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("pageSize",pageSize);
		req.setAttribute("totalNum",totalNum);
		req.setAttribute("totalPageNum", totalPageNum);
		
		List<Role> list = roleService.finByMap(map,startNum,pageSize);
		req.setAttribute("roleList", list);
		req.getRequestDispatcher("jsp/xuqiaoqiang/role/rolelist.jsp").forward(req, resp);
	}

}
