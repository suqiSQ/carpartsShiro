<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/1/7
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function login() {
            location.href="<%=request.getContextPath()%>/login/toLogin.shtml";
        }
    </script>
</head>
<body>
<em style="font-size: 20px;color: #1aa2d8">汽车配件 <button onclick="login()">百度一下</button></em>
</body>
</html>
