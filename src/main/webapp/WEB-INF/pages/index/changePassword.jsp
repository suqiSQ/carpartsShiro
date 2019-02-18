<%--
Created by IntelliJ IDEA.
User: lenovo
Date: 2019/1/6
Time: 16:21
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<link href="${ctx}/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"  src="${ctx}/js/jquery.min2.js"></script>
<script>
function changePwd(){
var oldPwd=$("#oldPwd").val();
var newPwd=$("#newPwd").val();
var confirmPwd=$("#confirmPwd").val();
if(newPwd==confirmPwd){
$.ajax({
    url:"${ctx}/index/changePassword.shtml",    //请求的url地址
    async:true,//请求是否异步，默认为异步，这也是ajax重要特性
    data:{"oldPwd":oldPwd,"newPwd":newPwd,},    //参数值
    type:"post",   //请求方式
    success:function(data){
       if(data==0){
       		alert("对不起，您输入的旧密码有误！");
       }
       if(data==1){
       alert("恭喜你，密码修改成功！");
       window.parent.location.href='${ctx}/login/logout.shtml';
       }
    },
});

}else{

alert("新密码和确认密码不一致！");
}
}
</script>


</head>

  <body>
  <div class="bg_color1 pagebody1 minheight1"><!--main颜色-->
        <div class="wid">
        	<div class="password">个人设置</div>
             <div class="dang">当前位置：修改密码</div>
            <div class="password_f"> 
                <dl><dt>原密码：</dt><dd><input type="password" name="email" id="oldPwd" class="in"/></dd><dd></dd></dl>
                <dl><dt>新密码：</dt><dd><input type="password" name="user" id="newPwd" class="in"/></dd><dd></dd></dl>
                <dl><dt>确认密码：</dt><dd><input type="password" name="user"  id="confirmPwd" class="in"/></dd><dd></dd></dl>
                <dl style="border:0;"><dt>&nbsp;</dt><dd><input type="button" value="确定" class="pa"  onclick="changePwd()"/></dd></dl>
           </div>
        </div>
    </div><!--main颜色-->
    <div class="bg_color2"><!--end底部-->
    	<div class="footer wid">Copyright © 2014-2024 www.xiupeilian.com  All Rights Reserved. 修配连 版权所有</div>
    </div><!--end底部-->
</body>
</html>