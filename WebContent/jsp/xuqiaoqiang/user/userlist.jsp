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
welcome userlist<br/>
<div>
		<br/>
		<input class="button" type="button" value="添加" onclick="toAdd()"/>
	</div>
	<div><br/>
		<form id="queryFm" action="${pageContext.request.contextPath}/user.do?method=query" method="post">
		用户名：<input type="text" name="userName" value="${queryMap.userName}" placeHolder="这里输入用户名..."/>    账户：<input type="text"  value="${queryMap.account}" name="account" placeHolder="这里输入账户"/>
		页数：<input id="currentPage" type="text" name="currentPage" value="${currentPage}" placeHolder="页数"/>
		<input type="submit" value="查询"  class="button">
		</form><br/>
	</div>
<table class="tablelist"">
	<tr>
		<td>用户名</td>
		<td>账户</td>
		<td>ID</td>
		<td>创建日期</td>
		<td>操作</td>
		<td>更新</td>
	</tr>
	<c:forEach var="user" items="${userList}">
		<tr>
			<td>${user.userName}</td>
			<td>${user.account}</td>
			<td>${user.userId}</td>
			<td>${user.createDate}</td>
			<td><a href="${pageContext.request.contextPath}/user.do?method=delete&userId=${user.userId}">删除</a></td>
			<td><a href="${pageContext.request.contextPath}/user.do?method=toUpdate&userId=${user.userId}">更新</a></td>
			<td><a href="${pageContext.request.contextPath}/user.do?method=toAssignRole&userId=${user.userId}">分配角色</a></td>
		</tr>
	</c:forEach>
</table>
<div>
	<a href="javascript:void(0);" onclick="pageTurn(${currentPage-1})">上一页</a>
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
		window.location.href="${pageContext.request.contextPath}/user.do?method=toAdd";
	}
</script>
</html>