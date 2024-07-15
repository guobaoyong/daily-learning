<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帐户激活结果</title>
</head>
<body>
${user.userName}，${user.activated ? "<span style='color:green;'>激活成功！</span>" : "<span style='color:red;'>激活失败！</span>"}
</body>
</html>