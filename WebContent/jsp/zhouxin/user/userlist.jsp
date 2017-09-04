<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<div class="content_query">
		<form method="post" id="queryFm">
			<input type="hidden" name="pageSize" value="${page.pageSize}"/>
			<input type="hidden" id="currPageNum" name="currPageNum" value="${page.currPageNum}"/>
			<input type="hidden" id="selUserId" name="selUserId"/>
			账号：<input type="text" name="q_account" value="${queryMap.account}" maxlength="10"/>
			用戶名：<input type="text" name="q_username" value="${queryMap.username}" maxlength="10"/>
			<input class="btn" type="button" value="查询" onclick="queryUser()">
		</form>
	</div>
	<div class="content_btn">
		<input class="btn" type="button" value="新增" onclick="toAdd()"/>
	</div>
	<div class="part_line"></div>
	<div class="content_msg" id="msg">
		<div class="${success}_info">
			<span class="${success}_icon"></span>
			<span class="${success}_msg">${msg}</span>
		</div>
	</div>
	<div class="content_list">
		<table class="table_list">
			<tr>
				<th>账号</th>
				<th>姓名</th>
				<th>邮箱</th>
				<th>电话</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>更新人</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>
		<c:forEach var="user" items="${userList}">
			<tr>
				<td>${user.account}</td>
				<td>${user.username}</td>
				<td>${user.email}</td>
				<td>${user.phone}</td>
				<td>${user.createUsername}</td>
				<td>${user.createTime}</td>
				<td>${user.lastUpdateUsername}</td>
				<td>${user.lastUpdateTime}</td>
				<td>
					<a href="javascript:void(0);">查看</a>
					<c:forEach var="resource" items="${sessionScope.CurrentUserResources}">
						<c:if test="${resource.uri eq '/user.do?method=delete'}">
							<a href="javascript:void(0);" onclick="delUser(${user.userId})">删除</a>
						</c:if>
						<c:if test="${resource.uri eq '/user.do?method=update'}">
							<a href="javascript:void(0);" onclick="updateUser(${user.userId})">修改</a>
						</c:if>
						<c:if test="${resource.uri eq '/user.do?method=toAssignRole'}">
							<a href="javascript:void(0);" onclick="assignRole(${user.userId})">分配角色</a>
						</c:if>
					</c:forEach>
				</td>
			</tr>
		</c:forEach>
		</table>
	</div>
	<div class="page">
		<c:if test="${page.totalPage > 1}">
		<a href="javascript:void(0);" onclick="pageTurn(1)">首页</a>
		<a href="javascript:void(0);" onclick="pageTurn(${page.currPageNum - 1})">上一页</a>
		<a href="javascript:void(0);" onclick="pageTurn(${page.currPageNum + 1})">下一页</a>
		<a href="javascript:void(0);" onclick="pageTurn(${page.totalPage})">尾页</a>
		</c:if>
		共${page.totalNum}条记录,${page.pageSize}条/页，当前第${page.currPageNum}页，共${page.totalPage}页
	</div>
	<div class="part_line"></div>
</div>
<%@include file="/jsp/zhouxin/foot.jsp"%>
</body>
<script type="text/javascript">
	function toAdd(){
		window.location.href="${pageContext.request.contextPath}/user.do?method=toAdd";
	}
	function clearMsg(){
		document.getElementById("msg").innerHTML="";
	}
	function queryUser(){
		var qFm = document.getElementById("queryFm");
		qFm.action = "${pageContext.request.contextPath}/user.do?method=list";
		qFm.submit();
	}
	function delUser(userId){
		if(confirm("确定删除该用户？")){
			var qFm = document.getElementById("queryFm");
			document.getElementById("selUserId").value = userId;
			qFm.action = "${pageContext.request.contextPath}/user.do?method=delete";
			qFm.submit();
		}
	}
	function updateUser(userId){
		var qFm = document.getElementById("queryFm");
		document.getElementById("selUserId").value = userId;
		qFm.action = "${pageContext.request.contextPath}/user.do?method=toUpdate";
		qFm.submit();
	}
	function pageTurn(pageNum){
		var qFm = document.getElementById("queryFm");
		document.getElementById("currPageNum").value = pageNum;
		qFm.action = "${pageContext.request.contextPath}/user.do?method=list";
		qFm.submit();
	}
	function assignRole(userId){
		var qFm = document.getElementById("queryFm");
		document.getElementById("selUserId").value = userId;
		qFm.action = "${pageContext.request.contextPath}/user.do?method=toAssignRole";
		qFm.submit();
	}
	window.setTimeout("clearMsg()", 2000);
</script>
</html>