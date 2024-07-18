<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="org.wlgzs.model.User"%>
<%
	if(request.getAttribute("user")==null){
		String userName=null;
		String password=null;
		
		Cookie[] cookies=request.getCookies();
		for(int i=0;cookies!=null && i<cookies.length;i++){
			if(cookies[i].getName().equals("user")){
				userName=cookies[i].getValue().split("-")[0];
				password=cookies[i].getValue().split("-")[1];
			}
		}
		
		if(userName==null){
			userName="";
		}
		
		if(password==null){
			password="";
		}
		
		pageContext.setAttribute("user", new User(userName,password));
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>私人日志系统|登陆</title>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function checkForm(){
		var userName=document.getElementById("userName").value;
		var password=document.getElementById("password").value;
		if(userName==null || userName==""){
			document.getElementById("error").innerHTML="用户名不能为空";
			return false;
		}
		if(password==null || password==""){
			document.getElementById("error").innerHTML="密码不能为空";
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div class="login">
		<div class="box png">
			<div class="logo png"></div>
			<div class="input">
				<div class="log">
				<form name="myForm" class="form-signin" action="login" method="post" onsubmit="return checkForm()">
					<div class="name">
						<label>用户名</label><input type="text" class="text" id="value_1"
							placeholder="用户名" name="userName" tabindex="1" value="${user.userName }">
					</div>
					<div class="pwd">
						<label>密 码</label><input type="password" class="text" id="value_2"
							placeholder="密码" name="password" tabindex="2" value="${user.password }"> 
							<input type="submit" class="submit" tabindex="3" value="登录">
						<div class="check">
						</div>
						<label class="checkbox"> 
						<input id="remember"
							name="remember" type="checkbox" value="remember-me">记住我
							&nbsp;&nbsp;&nbsp;&nbsp; <font id="error" color="red" >${error }</font>
						</label>
					</div>
					</form>
					<div class="tip"></div>
				</div>
			</div>
		</div>
		<div class="air-balloon ab-1 png"></div>
		<div class="air-balloon ab-2 png"></div>
		<div class="footer"></div>
	</div>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/fun.base.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>


	<!--[if IE 6]>
<script src="js/DD_belatedPNG.js" type="text/javascript"></script>
<script>DD_belatedPNG.fix('.png')</script>
<![endif]-->
	<div
		style="text-align: center; margin: 50px 0; font: normal 14px/24px 'MicroSoft YaHei';">
		<p>适用浏览器：360、FireFox、Chrome、Safari、Opera、傲游、搜狗、世界之窗. 不支持IE8及以下浏览器。</p>
		<p>2018/10/29-2018/11/?基于Java1234网站jsp日记本项目</p>
	</div>
</body>
</html>