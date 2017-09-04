<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理系统</title>
</head>
<body>
<%@include file="/jsp/zhouxin/header.jsp"%>
<div class="site_content">
	<div class="add_main">
		<div class="page_title">
			<h3>新增资源</h3>
		</div>
		<form method="post" action="${pageContext.request.contextPath}/resource.do?method=add"
			onsubmit="return checkdata()">
			<div class="info_list">
				<div class="tit">
					<i>*</i>资源名称：
				</div>
				<div>
					<input class="W_input" type="text" name="resourceName" value="${resource.resourceName}" maxlength="20" placeholder="不超过12个字符" required>
					<c:if test="${!empty msg}">
					<span class="error_icon"></span>
					<span class="error_text">${msg}</span>
					</c:if>
				</div>
			</div>
			<div class="info_list">
				<div class="tit">
					<i>*</i>资源类型：
				</div>
				<div>
					<select name="resourceType">
						<option value="0" <c:if test="${resource.resourceType == '0'}">selected="selected"</c:if>>菜单</option>
						<option value="1" <c:if test="${resource.resourceType == '1'}">selected="selected"</c:if>>路径</option>
					</select>
				</div>
			</div>
			<div class="info_list">
				<div class="tit">
					<i>*</i>资源路径：
				</div>
				<div>
					<input class="W_input" type="text" name="uri" value="${resource.uri}" maxlength="100" placeholder="不超过100个字符" required>
				</div>
			</div>
			<div class="info_list">
				<div class="tit">
					备注：
				</div>
				<div>
					<textarea rows="9" cols="50" name="description">${resource.description}</textarea>
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
		window.location.href="${pageContext.request.contextPath}/resource.do?method=list";
	}
</script>
</html>