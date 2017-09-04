<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆系统</title>
<style>
	body{
		//background-color: #F0F0F0;
		background-image: url(img/id=26613563.jpg);
		//background-repeat: no-repeat;
		background-size:100%;
	}
	.admin_login{
		width:300px;
		height:auto;
		overflow:hidden;
		margin:10% auto 0 auto;
		padding:40px;
		box-shadow:0 -5px 22px #0d957a;
		border-radius:5px;
	}
	.admin_login dt{
		font-size:30px;
		font-weight:bold;
		text-align:center;
		color:#45bda6;
		text-shadow:0 0 1px #0e947a;
		margin-bottom:15px;
	}
	.admin_login dd{
		margin:14px 0;
		height:42px;
		overflow:hidden;
		position:relative;
	}
	.admin_login dd .login_txtbx{
		font-size:14px;
		height:26px;
		line-height:26px;
		padding:8px 5%;
		width:90%;
		text-indent:2em;
		border:none;
		background:#5cbdaa;
		color:white;
	}
	.admin_login dd .login_txtbx::-webkit-input-placeholder {
		color:#f4f4f4;
		line-height:inherit;
	} 
	.admin_login dd.user_icon:before{content:"u";}
	.admin_login dd.pwd_icon:before{content:"p";}
	.admin_login dd:before{
		position:absolute;
		top:0;
		left:10px;
		height:42px;
		line-height:42px;
		font-size:20px;
		color:#0c9076;
	}
	.admin_login dd .submit_btn{
		width:100%;
		height:42px;
		border:none;
		font-size:16px;
		background:#048f74;
		color:#f8f8f8;}
</style>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/login.do?method=login">
		<dl class="admin_login">
			<dt>
	  			<strong>登陆后台管理系统</strong>
 			</dt>
			<dd class="user_icon">
				<input type="text" name="account" placeholder="账号" class="login_txtbx" required/>
			</dd>
			<dd class="pwd_icon">
				<input type="password" name="password" placeholder="密码" class="login_txtbx" required/>
			</dd>

			<dd>
				<input type="submit" value="立即登陆" class="submit_btn" />
			</dd>
		</dl>					
	</form>
</body>
</html>