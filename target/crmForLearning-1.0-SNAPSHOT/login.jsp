<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

	<script>

		$(function () {

			//页面加载完毕后，让用户的文本框自动获得焦点
			$("#loginAct").focus();

			$("#submitBtn").click(function () {

				login();

			})

			//为当前登录页面窗口绑定敲键盘事件
			//event:这个参数可以取得我们敲的是哪个键
			$(window).keydown(function (event) {

				//alert(event.keyCode);

				//如果取得的键位值为13，表示敲的是回车键
				if(event.keyCode==13){

					login();

				}

			})

		})
		
		function login() {

			//alert("登录操作");
			//验证账号密码不能为空
			//取得账号密码
			//将文本中的左右空格去掉，使用$.trim(文本)
			var loginAct = $.trim($("#loginAct").val());
			var loginPwd = $.trim($("#loginPwd").val());

			if(loginAct == "" || loginPwd == ""){

				$("#msg").html("账号密码不能为空");

				//如果账号密码为空，则需要及时强制终止该方法
				return false;

			}

			$.ajax({

				url : "settings/user/login.do",
				data : {

					"loginAct" : loginAct,
					"loginPwd" : loginPwd

				},
				type : "post",
				dataType : "json",
				success : function (data) {

					//data需要
					// 1.登录结果（成功或者失败） success : true/false
					// 2.失败的原因  msg : "原因"

					//如果登录成功
					if(data.success){

						window.location.href = "workbench/index.html";

					}
					//如果登录失败
					else {

						$("#msg").html(data.msg);

					}


				}

			})


		}
		

	</script>
</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 14px;">&copy;2021&nbsp;Eugene Y</span></div>
	</div>
	
	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<form action="workbench/index.html" class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input class="form-control" type="text" placeholder="用户名" id="loginAct">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input class="form-control" type="password" placeholder="密码" id="loginPwd">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
						
							<span id="msg" style="color: red">test</span>
						
					</div>
					<!--

						按钮写在form表单中，默认的行为就是提交表单
						一定要将按钮的类型设置为button
						按钮所触发的行为应该由我们手动写js代码来决定

					-->
					<button id="submitBtn" type="button" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>