<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/1/6
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<% pageContext.setAttribute("path",request.getContextPath());%>--%>
<html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修配连汽配市场</title>
<link href="${ctx}/css/index.css" rel="stylesheet" type="text/css" />
<!--弹出页-->s
<script type="text/javascript" src="${ctx}/js/jquery.min2.js"></script>
<script src="${ctx}/js/layer/layer.min.js" type="text/javascript"></script>
<script type="text/javascript">
	function mygs() {
		var url = "${ctx}/login/forgetPassword.shtml";
		$.layer({
			type : 2,
			title : false,//关闭按钮
			shadeClose : false,
			shade : [ 0.6, '#000' ],//遮罩
			border : [ 0, 0, '#ccc' ],//边框
			area : [ '650px', '450px' ],//宽高
			iframe : {
				src : '' + url + ''
			},
			shift : [ "top" ], //从下面动画弹出
			end : function() {
				window.location.reload();
			}
		});
	}

	function refreshCode() {
		document.getElementById('imageValidate').src = '${ctx}/login/Kaptcha.jpg?'
				+ Math.random();
	}

	// 如果在框架中，则跳转刷新上级页面
	if (self.frameElement && self.frameElement.tagName == "IFRAME") {
		parent.location.reload();
	}

	function login() {
		var loginName = $("#loginName").val();
		var password = $("#password").val();
		var validateCode = $("#validateCode").val();
		if (loginName == '' || password == '' || validateCode == "") {
			alert("用户名、密码、验证码不能为空");

		} else {
			$.ajax({
				type : "post",
				url : "${ctx}/login/login.shtml",
				data : {
					'loginName' : loginName,
					'password' : password,
					'validate' : validateCode
				},
				success : function(data) {
                  if(data=="1"){
                	  window.location.href="${ctx}/index/index.shtml";
                  }else  {
                      $("#tips").html(data);
                  }
				}
			});

		}

	}
</script>
</head>

<body>
	<div class="wid top1">
		<img src="${ctx}/images/logo1.jpg" />
	</div>
	<div class="land">
		<div class="wid land_1">
			<div class="title">
				<ul>
					<li class="ziti1"><a href="javascript:;">xiupeilian.com</a></li>
					<li class="ziti2">修配连汽配市场</li>
					<li class="ziti3">我的<span>网上汽配店铺</span></li>
				</ul>
			</div>
			<div class="login">
				<form>
					<div class="login_name">
						<input
							style="width:268px;height:38px;font-size:14px;font-family:'微软雅黑';color:#494848;padding-left:13px;border:1px solid #cdcdcd;"
							type="text" placeholder="用户名/邮箱/手机号" id="loginName" />
					</div>
					<div class="login_password">
						<input type="password"
							style="width:268px;height:38px;font-size:14px;font-family:'微软雅黑';color:#494848;padding-left:13px;border:1px solid #cdcdcd;"
							placeholder="登陆密码" id="password" />
					</div>
					<div class="login_password">
						<input type="text"
							style="width:100px;height:38px;font-size:14px;font-family:'微软雅黑';color:#494848;padding-left:13px;border:1px solid #cdcdcd;"
							name="validateCode" id="validateCode" class="delValue"
							placeholder="验证码" /> <label for="validateCode"
							class="checkCodePlaceholder" id="checkCodePlaceholder">看不清</label>
						<img
							style="height:24px;width:60px;display:inline-block; vertical-align:middle;cursor:pointer;"
							src="${ctx}/login/Kaptcha.jpg" border="0" title="看不清,请点击图片换一张"
							id="imageValidate" onclick="refreshCode()"> <span><a
								onclick="mygs();" href="javascript:;" style="color:red;">忘记密码？</a></span>
					</div>

					<p id="tips"
						style="margin-left:100px;margin-top:60px;color:red;font-size:16px"></p>

					<button type="button" class="button" onclick="login()">登录</button>
				</form>
				<div class="position">
					还没有修配连账号吗？赶快<span><a href="${ctx}/login/toRegister.shtml">点击注册</a></span>加入我们吧！
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div class="footer wid">Copyright © 2014-2024 www.xiupeilian.com
		All Rights Reserved. 修配连 版权所有</div>
</body>
</html>

