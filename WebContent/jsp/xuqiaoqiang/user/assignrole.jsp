<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分配角色</title>
</head>
<body>
	<form method="post" action="user.do?method=assignRole">
		<div>
			账号：${user.account}
		</div>
		<div>
			用户名：${user.userName}
			<input type="hidden" name="userId" value="${user.userId}">
		</div>
		<div>
		角色：<c:forEach var="role" items="${roleList}">
			  	<input type="checkbox" id="${role.roleId}" name="roleId" value="${role.roleId}">${role.roleName}
			  </c:forEach>
		</div>
		<input type="submit" value="提交">
		<input type="reset" value="取消">
	</form>
</body>
<script type="text/javascript">
	function initCheck(){
		
		//已选中的checkbox
		var array = new Array(${roleIds});		
		for(var i=0;i<array.length;i++) {
			//alert(array[i]);
			document.getElementById(array[i]).checked = true;
		}
	}
	initCheck();
</script>
</html>