package com.zhiyou.rj14.zhouxin.auth.business.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhiyou.rj14.zhouxin.auth.business.filter.util.ResourceUtil;
import com.zhiyou.rj14.zhouxin.auth.business.resource.entries.Resource;
import com.zhiyou.rj14.zhouxin.auth.business.user.entries.User;

public class AuthorityFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String uri = req.getRequestURI();
		// 不拦截登录页面和一些静态资源
		if (uri.contains(".css") || uri.contains(".js") || uri.contains(".png") || uri.contains(".jpg")
				|| uri.endsWith("/login.do") || uri.equals(req.getContextPath() + "/")) {
			chain.doFilter(request, response);
		} else {
			User user = (User) req.getSession().getAttribute("CurrentUser");
			if (user == null) {
				resp.sendRedirect(req.getContextPath());
			} else {
				@SuppressWarnings("unchecked")
				List<Resource> resourceList = (List<Resource>) req.getSession().getAttribute("CurrentUserResources");
				if (ResourceUtil.canViewResource(resourceList, uri + "?method=" + req.getParameter("method").toString())) {
					chain.doFilter(request, response);
				}else{
					resp.sendRedirect(req.getContextPath() + "/jsp/zhouxin/forbidden.jsp");
				}
			}
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

}
