<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限管理系统</title>
</head>
<body>
<%@include file="/jsp/zhouxin/header.jsp"%>
<div class="site_content">
	<div class="add_main">
		<div class="page_title">
			<h3>用户角色分配</h3>
		</div>
		<form method="post" action="${pageContext.request.contextPath}/user.do?method=assignRole"
			onsubmit="return checkdata()">
			<input type="hidden" name="user_id" value="${user.userId}">
			<div class="info_list">
				<div class="tit">
					<i>*</i>账号：
				</div>
				<div class="content">
					<span>${user.account}</span>
				</div>
			</div>
			<div class="info_list">
				<div class="tit">
					<i>*</i>姓名：
				</div>
				<div class="content">
					<span>${user.username}</span>
				</div>
			</div>
			<div class="info_list">
				<div class="tit">
					<i>*</i>角色：
				</div>
				<div class="checklist">
					<c:forEach var="role" items="${roleList}" varStatus="status">
						<c:if test="${status.index != 0 && status.index % 4 == 0}">
							<br/>
						</c:if>
						<input type="checkbox" id="role${role.roleId}" name="roleId" value="${role.roleId}">${role.roleName}
					</c:forEach>
				</div>
			</div>
			<div class="info_list">
				<input class="W_submit" type="submit" value="提交">
				<input class="W_submit" type="button" value="取消" onclick="back()">
			</div>
		</form>
	</div>
</div>
<%@include file="/jsp/zhouxin/foot.jsp"%>
</body>
<script type="text/javascript">
	function checkdata(){
		//如果校验通过 return true form表单将进行提交
		//如果校验没通过 return false form表单将不提交
		return true;
	}
	function back(){
		//校验
		window.location.href="${pageContext.request.contextPath}/user.do?method=list";
	}
	function initChekbox(){
		var arr = new Array(${ownRoleIds});
		for(var i=0;i<arr.length;i++){
			document.getElementById("role"+arr[i]).checked=true;
		}
	}
	initChekbox();
</script>
</html>