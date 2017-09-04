package com.zhiyou.rj14.xuqiaoqiang.jdbc.business.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.buf.CharChunk.CharInputChannel;

import com.zhiyou.rj14.xuqiaoqiang.jdbc.business.user.entries.User;

public class LoginInspectFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		User user = (User) req.getSession().getAttribute("user");
		String uri = req.getRequestURI();
		//如果是登陆界面和资源 则不拦截
		if(uri.contains("jsp/xuqiaoqiang/login.jsp") || uri.contains("login.do")
				 || uri.contains(".jpg") || uri.contains(".css") || uri.contains(".js")) {
			chain.doFilter(request, response);
		} else {
			//验证是否登陆过
			if(user == null) {
				resp.sendRedirect(req.getContextPath()+"/jsp/xuqiaoqiang/login.jsp");
			} else {
			//登陆通过 检查是否有权访问资源
			//to-do
				chain.doFilter(request, response);
			}
		}
		
	}

	@Override
	public void destroy() {
		
	}

}
