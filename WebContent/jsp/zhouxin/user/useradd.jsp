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
			<h3>新增用户</h3>
		</div>
		<form method="post" action="${pageContext.request.contextPath}/user.do?method=add"
			onsubmit="return checkdata()">
			<div class="info_list">
				<div class="tit">
					<i>*</i>账号：
				</div>
				<div>
					<input class="W_input" type="text" name="account" value="${user.account}" maxlength="20" placeholder="不超过12个字符" required>
					<c:if test="${!empty msg}">
					<span class="error_icon"></span>
					<span class="error_text">${msg}</span>
					</c:if>
				</div>
			</div>
			<div class="info_list">
				<div class="tit">
					<i>*</i>设置密码：
				</div>
				<div>
					<input class="W_input" type="password" name="password" maxlength="12" placeholder="8-12个字符" required>
				</div>
			</div>
			<div class="info_list">
				<div class="tit">
					<i>*</i>姓名：
				</div>
				<div>
					<input class="W_input" type="text" name="username" value="${user.username}" maxlength="10" placeholder="不超过10个字符" required>
				</div>
			</div>
			<div class="info_list">
				<div class="tit">
					<span class="mail_ico"></span>
					<i>*</i>邮箱：
				</div>
				<div>
					<input class="W_input" type="email" name="email" value="${user.email}" maxlength="20" placeholder="仅限163邮箱" required>
				</div>
			</div>
			<div class="info_list">
				<div class="tit">
					<span class="mobile_ico"></span>电话：
				</div>
				<div>
					<input class="W_input" type="number" name="phone" value="${user.phone}" maxlength="11"  placeholder="仅限手机号">
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
</script>
</html>