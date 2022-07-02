<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script>
		$(function () {
			$(window).keydown(function (e) {
				if (e.keyCode==13){
					$("#loginBtn").click();
				}
			})
			$("#loginBtn").click(function () {
				var username = $.trim($("#username").val());
				var password = $.trim($("#password").val());
				var check = $("#checked").prop("checked");
				if (username==""){
					alert("用户名不能为空");
				}else if (password==""){
					alert("密码不能为空");
				}else {
					$("#msg").text("登陆中，请稍等");
					$.ajax({
						url:'settings/qx/user/Login.do',
						data:{
							username:username,
							password:password,
							check:check
						},
						type:'post',
						dataType:'json',
						success:function (data) {
							if (data.code=='0'){
								$("#msg").text(data.message);
								$("#loginBtn").removeAttr("disabled");
							}else {
								window.location.href = "workbench/index.do";
							}
						}
					})
				}
			})
		})

	</script>
</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2017&nbsp;动力节点</span></div>
	</div>
	
	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<form action="workbench/index.html" class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input id="username" class="form-control" type="text" placeholder="用户名" value="${cookie.username.value}">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input id="password" class="form-control" type="password" placeholder="密码" value="${cookie.password.value}">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
						<label>
							<c:if test="${not empty cookie.username and not empty cookie.password}">
								<input type="checkbox" id="checked" checked>
							</c:if>
							<c:if test="${empty cookie.username and empty cookie.password}">
								<input type="checkbox" id="checked">
							</c:if>
						</label>记住密码
							<span id="msg"></span>
						
					</div>
					<button type="button" id="loginBtn" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>