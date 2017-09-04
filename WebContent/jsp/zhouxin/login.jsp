<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登陆系统</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/zhouxin/mystyle.css"/>
</head>
<body>
<div class="log_main">
	<div class="log_part">
		<h3>账号登录</h3>
		<div class="user_info">
			<form method="post" action="${pageContext.request.contextPath}/login.do?method=login">
				<input class="login_input user_name" type="text" name="account" maxlength="20" placeholder="输入用户名" required />
				<input class="login_input pass_word" type="password" name="password" maxlength="12" placeholder="输入密码" required />
				<c:if test="${!empty msg}">
				<div class="error_info">
					<span class="error_icon"></span>
					<span class="error_msg">帐户名或登录密码不正确，请重新输入</span>
				</div>
				</c:if>
				<input class="login_input logging" type="submit" value="登陆">
			</form>
		</div>
	</div>
</div>
</body>
</html>