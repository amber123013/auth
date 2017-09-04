<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="role.do?method=update" method="post">
		<p>角色名：<input type = "text" name="roleName" value="${role.roleName}"/></p>
		<input type = "hidden" name="roleId" value="${role.roleId}"/>
		<input type="submit" value="更新" class="button">
	</form>
</body>
</html>