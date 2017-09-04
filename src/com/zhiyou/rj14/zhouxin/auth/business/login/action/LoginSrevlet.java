package com.zhiyou.rj14.zhouxin.auth.business.login.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhiyou.rj14.zhouxin.auth.business.resource.entries.Resource;
import com.zhiyou.rj14.zhouxin.auth.business.resource.service.ResourceService;
import com.zhiyou.rj14.zhouxin.auth.business.user.entries.User;
import com.zhiyou.rj14.zhouxin.auth.business.user.service.UserService;

public class LoginSrevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserService();
	ResourceService resourceService = new ResourceService();
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		if("login".equals(method)){
			//登入
			login(req, resp);
		}else if("logout".equals(method)){
			//退出
			logout(req, resp);
		}
	}
	
	protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//1.先从前台获取用户名和账号
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		//2.根据账号找到用户信息
		User user = userService.findByAccount(account);
		//3.验证密码是否正确
		if(user != null && password.equals(user.getPassword())){
			//  1）.如果密码正确，将user放入session里，跳转到首页
			req.getSession().setAttribute("CurrentUser", user);
			//将该用户可见的资源放到session中
			List<Resource> resourceList = resourceService.findByUserId(user.getUserId());
			req.getSession().setAttribute("CurrentUserResources", resourceList);
			req.getRequestDispatcher("/jsp/zhouxin/homepage.jsp").forward(req, resp);
		}else{
			//  2).如果密码错误，转到登陆页面
			req.setAttribute("msg", "error");
			req.getRequestDispatcher("/jsp/zhouxin/login.jsp").forward(req, resp);
		}
	}
	
	protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getSession().removeAttribute("CurrentUser");
		resp.sendRedirect(req.getContextPath());
	}
}
