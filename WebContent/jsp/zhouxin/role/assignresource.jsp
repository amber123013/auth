<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<h3>角色资源分配</h3>
		</div>
		<form method="post" action="${pageContext.request.contextPath}/role.do?method=assignResource"
			onsubmit="return checkdata()">
			<input type="hidden" name="role_id" value="${role.roleId}">
			<div class="info_list">
				<div class="tit">
					<i>*</i>账号：
				</div>
				<div class="content">
					<span>${role.roleCode}</span>
				</div>
			</div>
			<div class="info_list">
				<div class="tit">
					<i>*</i>姓名：
				</div>
				<div class="content">
					<span>${role.roleName}</span>
				</div>
			</div>
			<div class="info_list">
				<div class="tit">
					<i>*</i>资源：
				</div>
				<div class="checklist">
					<c:forEach var="resource" items="${resourceList}" varStatus="status">
						<c:if test="${status.index != 0 && status.index % 4 == 0}">
							<br/>
						</c:if>
						<input type="checkbox" id="resource${resource.resourceId}" name="resourceId" value="${resource.resourceId}">${resource.resourceName}
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
		window.location.href="${pageContext.request.contextPath}/role.do?method=list";
	}
	function initChekbox(){
		var arr = new Array(${ownResourceIds});
		for(var i=0;i<arr.length;i++){
			document.getElementById("resource"+arr[i]).checked=true;
		}
	}
	initChekbox();
</script>
</html>