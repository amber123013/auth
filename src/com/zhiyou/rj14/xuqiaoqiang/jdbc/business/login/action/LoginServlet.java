package com.zhiyou.rj14.xuqiaoqiang.jdbc.business.login.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.user.entries.User;
import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.user.service.UserService;

public class LoginServlet extends HttpServlet {

	UserService userService = new UserService();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String method = req.getParameter("method");
		
		if("login".equals(method)) {
			login(req,resp);
		} else if("loginout".equals(method)) {
			//登出
		}
	}
	
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//登录
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		if(account != null  && !"".equals(account)) {
			User user = userService.finUserByAccount(account);
			if(user != null && password.equals(user.getPassword())) {
				//密码验证成功
				req.getSession().setAttribute("user", user);
				req.getRequestDispatcher("/jsp/xuqiaoqiang/main.jsp").forward(req, resp);
			}else{
				//	2).如果密码错误，转到登陆页面
				req.getRequestDispatcher("/jsp/xuqiaoqiang/login.jsp").forward(req, resp);
			}
		}
	}

}
