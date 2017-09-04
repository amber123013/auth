<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="user.do?method=update" method="post">
		<p>账  号：<input type = "text" name="account" value="${user.account}"/></p>
		<p>姓  名：<input type = "text" name="userName" value="${user.userName}"/></p>
		<p>密  码：<input type = "text" name="password" value="${user.password}"/></p>
		<p>更新人：<input type = "text" name="updateBy" value="${user.updateBy}"/></p>
		<input type = "hidden" name="userId" value="${user.userId}"/>
		<input type="submit" value="更新" class="button">
	</form>
</body>
</html>