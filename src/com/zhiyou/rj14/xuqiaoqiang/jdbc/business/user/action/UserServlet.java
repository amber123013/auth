package com.zhiyou.rj14.xuqiaoqiang.jdbc.business.user.action;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
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
import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.user.service.UserService;
import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.userrole.entries.UserRole;
import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.userrole.service.UserRoleService;

public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserService userService = new UserService();
	RoleService roleService = new RoleService();
	UserRoleService userRoleService = new UserRoleService();
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {

		String method = arg0.getParameter("method");
		// TODO Auto-generated method stub
		//System.out.println("shuchu");
		//arg1.sendRedirect("https://www.baidu.com");//重定向
		if("toAdd".equals(method)) {
			//跳转到添加
			arg0.getRequestDispatcher("jsp/xuqiaoqiang/user/adduser.jsp").forward(arg0, arg1); //zhuanfa
		} else if("query".equals(method)) {
			setUserContent(arg0,arg1);
		} else if("add".equals(method)) {
			insertUser(arg0,arg1);
		} else if("delete".equals(method)) {
			//删除一条记录
			deleteUserById(Integer.parseInt(arg0.getParameter("userId")),arg0,arg1);
		} else if("toUpdate".equals(method)) {
			String id = arg0.getParameter("userId");
			User user = userService.finUserById(Integer.parseInt(id));
			arg0.setAttribute("user", user);
			arg0.getRequestDispatcher("jsp/xuqiaoqiang/user/updateuser.jsp").forward(arg0, arg1);
		} else if("update".equals(method)) {
			updateUser(arg0, arg1);
		} else if("toAssignRole".equals(method)) {
			toAssignRole(arg0,arg1);
		} else if("assignRole".equals(method)) {
			assignRole(arg0,arg1);
		}
	}
	/**
	 *分配角色 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void assignRole(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		String userIdString = arg0.getParameter("userId");
		int userId = Integer.parseInt(userIdString);
		//全部角色id
		String[] roleIdStrings = arg0.getParameterValues("roleId");
		//当前登陆的用户
		User user = (User)arg0.getSession().getAttribute("user");
		if(roleIdStrings != null && roleIdStrings.length >0) {
			//删除user_role角色表中当前用户的所有角色（之后进行添加
			userRoleService.deleteUserRoleByUserId(userId);
			List<UserRole> list = new ArrayList<UserRole>();
			for(String roleIdString:roleIdStrings) {
				UserRole userRole = new UserRole();
				userRole.setUserId(userId);
				userRole.setRoleId(Integer.parseInt(roleIdString));
				//userRole.setCreateBy(user.getUserId());
				//userRole.setUpdateBy(user.getUserId());
				userRole.setCrateDate(new Date(System.currentTimeMillis()));
				userRole.setUpdateTime(new Date(System.currentTimeMillis()));
				list.add(userRole);
			}
			userRoleService.insert(list);
		}
		
		arg0.getRequestDispatcher("user.do?method=query").forward(arg0, arg1);
	}

	private void toAssignRole(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		String userIdString = arg0.getParameter("userId");
		//要分配角色的用户
		User user = userService.finUserById(Integer.parseInt(userIdString));
		//全部的角色
		List<Role> roleList = roleService.finByMap(new HashMap<String, Object>(), -1, -1);
		//找出要分配角色当前全部已经拥有的角色id
		List<Integer> roleIdList = userRoleService.finRoleIdsByUserId(Integer.parseInt(userIdString));
		StringBuffer sBuffer = new StringBuffer();
		for(int id :roleIdList) {
			sBuffer.append("\""+id+"\"" +",");
		}
		if(sBuffer.length() != 0)
			sBuffer.deleteCharAt(sBuffer.length()-1);
		
		arg0.setAttribute("user", user);
		arg0.setAttribute("roleList", roleList);
		arg0.setAttribute("roleIds", sBuffer.toString());
		arg0.getRequestDispatcher("jsp/xuqiaoqiang/user/assignrole.jsp").forward(arg0, arg1);
	}

	private void deleteUserById(int userId,HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		userService.delete(userId);
		setUserContent(arg0, arg1);
	}
	
	private void setUserContent(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException{
		String pageSizeStr = arg0.getParameter("pageSize");
		String currentPageStr = arg0.getParameter("currentPage");
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
		String userName = arg0.getParameter("userName");
		String account = arg0.getParameter("account");
		Map<String,Object> map = new HashMap<String,Object>();
		if(userName !=null && "".equals(userName));
			map.put("userName", userName);
		if(account !=null && "".equals(account));
			map.put("account",account);
		//总记录条数	
		int totalNum = userService.finCountByMap(map);
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
		List<User> userList = userService.finByMap(map,startNum,pageSize);
		arg0.setAttribute("userList", userList);
		arg0.setAttribute("currentPage", currentPage);
		arg0.setAttribute("pageSize",pageSize);
		arg0.setAttribute("totalNum",totalNum);
		arg0.setAttribute("totalPageNum", totalPageNum);
		arg0.setAttribute("queryMap", map);
		
		arg0.getRequestDispatcher("jsp/xuqiaoqiang/user/userlist.jsp").forward(arg0, arg1); //zhuanfa
	}
	private void insertUser(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException{
		String account = arg0.getParameter("account");
		String userName = arg0.getParameter("userName");
		String createBy = arg0.getParameter("createBy");
		User user = new User();
		user.setAccount(account);
		user.setUserName(userName);
		user.setCreateBy(Integer.parseInt(createBy));
		
		userService.insert(user);
		
		arg1.sendRedirect("user.do?method=query");
	}
	private void updateUser(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException{
		String account = arg0.getParameter("account");
		String userName = arg0.getParameter("userName");
		String updateBy = arg0.getParameter("updateBy");
		String password = arg0.getParameter("password");
		String userId = arg0.getParameter("userId");
		User user = new User();
		user.setAccount(account);
		user.setUserName(userName);
		user.setUpdateBy(Integer.parseInt(updateBy));
		user.setPassword(password);
		user.setUserId(Integer.parseInt(userId));
		
		
		userService.update(user);
		
		setUserContent(arg0, arg1);
	}
	

}
