<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新用户注册</title>
<style type="text/css">
	.error {
		color: red;
		padding-left:2px;
	}
</style>
</head>
<body>
	<form action="${pageContext.request.contextPath}/register" method="post">
		用户名：<input type="text" name="userName" value="${param.userName}"><span class="error">${errors.userName}</span><br/>
		密码：<input type="password" name="password" ><span class="error">${errors.password}</span><br/>
		确认密码：<input type="password" name="password2"><span class="error">${errors.password2}</span><br/>
		email：<input type="text" name="email" value="${param.email}"><span class="error">${errors.email}</span><br/>
		<input type="submit" value="注册">
	</form>
</body>
</html>