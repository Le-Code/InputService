<%--
  Created by IntelliJ IDEA.
  User: jerry
  Date: 2018/6/9
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <%
        request.setAttribute("path",request.getContextPath());
    %>
    <title>Title</title>
    <link href="../css/amazeui.css" rel="stylesheet">
    <script src="../js/jquery.js"></script>
    <script src="../js/amazeui.js"></script>
    <script src="../js/common.js"></script>
    <style>
        body{
            padding: 10px;
        }
    </style>
    <script>
        $(function () {
            $(document).on("click","#userInline_manager_btn",function () {
                var url = $(this).attr("url");
                requestForOtherSpace(url,$("#userInline_container"));
            })
        })
    </script>
</head>
<body>
    <div class="am-g">
        <div class="am-u-sm-12">
            <div class="am-u-sm-1">
                <button id="userInline_manager_btn" url = "${path}/user/userInline_manager.jsp" class="am-btn am-btn-success">管理用户</button>
            </div>
            <div class="am-u-sm-1 am-u-end">
                <button class="am-btn am-btn-success">用户趋势</button>
            </div>
        </div>
        <div id="userInline_container" class="am-u-sm-12 am-padding-top-lg">

        </div>
    </div>
</body>
</html>
