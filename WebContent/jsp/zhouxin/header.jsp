<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/zhouxin/mystyle.css?ddddd=12345"/>
<div class="site_header">
	<div class="header_logout">
		<span>欢迎你,${CurrentUser.username}</span>
		<a href="${pageContext.request.contextPath}/login.do?method=logout">退出</a>
	</div>
	<div class="header_nav">
		<ul class="nav_list">
			<li class="nav_item">
				<a class="link" href="${pageContext.request.contextPath}/jsp/zhouxin/homepage.jsp">
					<span>首页</span>
				</a>
			</li>
	<c:forEach var="resource" items="${sessionScope.CurrentUserResources}">
		<c:if test="${resource.resourceType eq '0'}">
			<li class="nav_item">
				<a class="link" href="${pageContext.request.contextPath}${resource.uri}">
					<span>${resource.resourceName}</span>
				</a>
			</li>
		</c:if>
	</c:forEach>
		</ul>
	</div>
	<div style="clear:both"></div>
</div>