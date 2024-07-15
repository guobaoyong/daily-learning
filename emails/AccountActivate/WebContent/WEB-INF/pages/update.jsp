<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户信息</title>
<style type="text/css">
	.error {
		color: red;
		padding-left:2px;
	}
</style>
</head>
<body>
<form action="${pageContext.request.contextPath}/update" method="post">
		用户名：<input type="text" name="userName" value="${user.userName}" readonly="readonly"><span class="error">${errors.userName}</span><br/>
		email：<input type="text" name="email" value="${user.email}"><span class="error">${errors.email}</span><br/>
		<input type="submit" value="修改">
	</form>
</body>
</html>