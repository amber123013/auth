<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
	<link rel="stylesheet" href="css/xuqiaoqiang/style.css" />
</head>
<body>
welcome rolelist<br/>
<div>
		<br/>
		<input class="button" type="button" value="添加" onclick="toAdd()"/>
	</div>
	<div><br/>
		<form id="queryFm" action="${pageContext.request.contextPath}/role.do?method=query" method="post">
		用户名：<input type="text" name="roleName" placeHolder="这里输入角色名..."/>
		页数：<input id="currentPage" type="text" name="currentPage" value="${currentPage}" placeHolder="页数"/>
		
		<input type="submit" value="查询"  class="button">
		</form><br/>
	</div>
<table class="tablelist"">
	<tr>
		<td>ID</td>
		<td>用户名</td>
		<td>创建日期</td>
		<td>创建人</td>
		<td>最后更新时间</td>
		<td>更新人</td>
		<td>操作</td>
	</tr>
	<c:forEach var="role" items="${roleList}">
		<tr>
			<td>${role.roleId}</td>
			<td>${role.roleName}</td>
			<td>${role.crateDate}</td>
			<td>${role.createBy}</td>
			<td>${role.lastUpdateTime}</td>
			<td>${role.updateBy}</td>
			<td><a href="${pageContext.request.contextPath}/role.do?method=delete&roleId=${role.roleId}">删除</a> &nbsp;&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/role.do?method=toUpdate&roleId=${role.roleId}">更新</a></td>
			<td></td>
		</tr>
	</c:forEach>
</table>
<div>
	<a href="javascript:void(0);" onclick="pageTurn(${currentPage-1},${totalPageNum})">上一页</a>
	<a href="javascript:void(0);" onclick="pageTurn(${currentPage+1},${totalPageNum})">下一页</a>
当前页${currentPage} 页大小${pageSize} 总记录数 ${totalNum} 总页数${totalPageNum}</div>
</body>
<script type="text/javascript">
//翻页方法
	function pageTurn(pageNum,totalPage){
		if(pageNum > totalPage) {
			pageNum = totalPage;
			return;
		}
		document.getElementById("currentPage").value=pageNum;
		
		document.getElementById("queryFm").submit();
	}
	function toAdd(){
		window.location.href="${pageContext.request.contextPath}/role.do?method=toAdd";
	}
</script>
</html>