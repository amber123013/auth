<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="user.do?method=add" method="post">
		<p>账  号：<input type = "text" name="account"/></p>
		<p>姓  名：<input type = "text" name="userName"/></p>
		<p>创建人：<input type = "text" name="createBy"/></p>
		<input type="submit" value="注册">
	</form>
</body>
</html>