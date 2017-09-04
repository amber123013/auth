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
			<input type="hidden" id="selResourceId" name="selResourceId">
			资源类型：
			<select name="q_resourceType">
				<option value=""></option>
				<option value="0" <c:if test="${queryMap.resourceType == '0'}">selected="selected"</c:if>>菜单</option>
				<option value="1" <c:if test="${queryMap.resourceType == '1'}">selected="selected"</c:if>>路径</option>
			</select>
			资源名称：<input type="text" name="q_resourceName" value="${queryMap.resourceName}" maxlength="10">
			<input class="btn" type="button" value="查询" onclick="queryResource()">
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
				<th>资源名称</th>
				<th>资源路径</th>
				<th>资源类型</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>更新人</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>
		<c:forEach var="resource" items="${resourceList}">
			<tr>
				<td>${resource.resourceName}</td>
				<td>${resource.uri}</td>
				<td>
					<c:if test="${resource.resourceType == '0'}">菜单</c:if>
					<c:if test="${resource.resourceType == '1'}">路径</c:if>
				</td>
				<td>${resource.createBy}</td>
				<td>${resource.createTime}</td>
				<td>${resource.lastUpdateBy}</td>
				<td>${resource.lastUpdateTime}</td>
				<td>
					<a href="javascript:void(0);">查看</a>
			<c:forEach var="resource" items="${sessionScope.CurrentUserResources}">
				<c:if test="${resource.uri eq '/resource.do?method=delete'}">
					<a href="javascript:void(0);" onclick="delResource(${resource.resourceId})">删除</a>
				</c:if>
				<c:if test="${resource.uri eq '/resource.do?method=toUpdate'}">
					<a href="javascript:void(0);" onclick="updateResource(${resource.resourceId})">修改</a>
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
		window.location.href="${pageContext.request.contextPath}/resource.do?method=toAdd";
	}
	function clearMsg(){
		document.getElementById("msg").innerHTML="";
	}
	function queryResource(){
		var qFm = document.getElementById("queryFm");
		qFm.action = "${pageContext.request.contextPath}/resource.do?method=list";
		qFm.submit();
	}
	function delResource(userId){
		if(confirm("确定删除该用户？")){
			var qFm = document.getElementById("queryFm");
			document.getElementById("selResourceId").value = userId;
			qFm.action = "${pageContext.request.contextPath}/resource.do?method=delete";
			qFm.submit();
		}
	}
	function updateResource(userId){
		var qFm = document.getElementById("queryFm");
		document.getElementById("selResourceId").value = userId;
		qFm.action = "${pageContext.request.contextPath}/resource.do?method=toUpdate";
		qFm.submit();
	}
	function pageTurn(pageNum){
		var qFm = document.getElementById("queryFm");
		document.getElementById("currPageNum").value = pageNum;
		qFm.action = "${pageContext.request.contextPath}/resource.do?method=list";
		qFm.submit();
	}
	window.setTimeout("clearMsg()", 2000);
</script>
</html>