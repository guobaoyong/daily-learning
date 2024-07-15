<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录成功${user.activated ? "，帐户已激活" : "，帐户尚未激活"}</title>
</head>
<body>
<c:if test="${not user.activated}">
	${user.userName}，尚未激活，请到邮箱中查看帐户激活邮件，并点击帐户激活链接将帐户激活。<br/>
	如果激活邮件已丢失，请点击<a href="${pageContext.request.contextPath}/applyActivateLink">申请激活链接</a>，系统将重新发送激链接到你的邮箱。<br/>
	如果注册时填写的邮箱帐户不正确，请点击<a href="${pageContext.request.contextPath}/updateUI">修改个人信息</a>，填写正确的email地址，系统将重新发送帐户激活链接到你的邮箱。
</c:if>
<c:if test="${user.activated }">
	${user.userName}，欢迎你！<%-- <a href="${pageContext.request.contextPath}/updateUI">修改个人信息</a> --%>
</c:if>
</body>
</html>