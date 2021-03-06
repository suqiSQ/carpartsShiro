<%--
Created by IntelliJ IDEA.
User: lenovo
Date: 2019/1/6
Time: 16:21
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>修配连汽配市场</title>
    <link href="${ctx}/css/index.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${ctx}/js/jquery.min2.js"></script>
    <script src="${ctx}/js/layer/layer.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        function news(url) {
            $.layer({
                type : 2,
                title : false,//关闭按钮
                shadeClose : false,
                shade : [ 0.6, '#000' ],//遮罩
                border : [ 0, 0, '#ccc' ],//边框
                area : [ '650px', '450px' ],//宽高
                iframe : {
                    src : '${ctx}' + url + ''
                },
                shift : [ "top" ], //从下面动画弹出
                end : function() {
                    window.location.reload();
                }
            });
        }

        function goToDynamicmessageList() {

            window.location.href = "${ctx}/index/dymsn.shtml";
        }

        function goToPublicmessageList() {

            window.location.href = "${ctx}/index/notice.shtml";
        }

        function goToNewsList() {

            window.location.href = "${ctx}/index/news.shtml";
        }
    </script>
</head>

<body>
<div class="bg_color1 border_end">
    <!--main颜色-->
    <div class="demo11 pagebody3 wid">
        <!--中间内容-->
        <ul class="tab_menu pagebody3_all">
            <li onclick="goToDynamicmessageList()" style="cursor:pointer">动态消息</li>
            <li  onclick="goToPublicmessageList()" style="cursor:pointer">公告</li>
            <li class="current" onclick="goToNewsList()" style="cursor:pointer">新闻</li>
        </ul>
        <div class="clear"></div>
        <div class="tab_box ">
            <!--滚动信息开始-->
            <div class="pagebody3_news" id="xiaoxi" style="height:500px;">

                <ul>
                    <c:forEach var="newsPage" items="${page2.list}" varStatus="status">
                        <li onclick="news('${newsPage.url}')"><a href="javascript:;" >${newsPage.title}</a><span><fmt:formatDate
                                value="${newsPage.createtime}" pattern="yyyy-MM-dd HH:mm:ss" /></span></li>
                    </c:forEach>
                </ul>




                <!--
                   page当中的pages代表一共分为几页
                                                循环遍历pages，生成页数,
                                          如果在循环过程当中，页数等于当前页数 ，a标签加个背景色即可
                 -->
                <ul class="page">
                    <c:forEach begin="1" end="${page2.pages}" var="pageNo">
                        <c:choose>
                            <c:when test="${pageNo==page2.pageNum}">
                                <a href="${ctx}/index/news.shtml?pageNo=${pageNo}&pageSize=10" style="background: #C30D23 none repeat scroll 0% 0%;color:#ffffff;">${pageNo}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="${ctx}/index/news.shtml?pageNo=${pageNo}&pageSize=10" >${pageNo}</a>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </ul>
                <div class="clear"></div>
            </div>

        </div>
        <!--滚动信息结束-->
    </div>
    <!--中间内容-->
</div>
<!--main颜色-->
<div class="footer wid">Copyright © 2014-2024 www.xiupeilian.com
    All Rights Reserved. 修配连 版权所有</div>
<!--end底部-->
</body>
</html>
