<%--
  Created by IntelliJ IDEA.
  User: jerry
  Date: 2018/6/4
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <%
        request.setAttribute("path",request.getContextPath());
    %>
    <title>group</title>
    <link rel="stylesheet" href="../css/amazeui.css"/>
    <script src="../js/jquery.js"></script>
    <script src="../js/amazeui.js"></script>
    <script src="../js/common.js"></script>

    <script type="text/javascript">

        function showGroup() {
            requestForOtherSpace("${path}/group/group_query.jsp",$("#container"));
        }
        
        function addGroup() {
            requestForOtherSpace("${path}/group/groupAdd.jsp",$("#container"));
        }
        
        function handle(text){
            if (text=="查看"){
                showGroup();
            }else{
                addGroup();
            }
        }

        $(function () {
            $("#doc-form-file").on("change",function () {
                var fileName = this.name;
                $("#file-list").html(fileName);
            });

            $(document).on("click","#doc-topbar-collapse ul li",function () {
                $("li").each(function () {
                    $(this).attr("class","");
                });
                $(this).attr("class","am-active");
                handle($(this).text());
            });
        })
    </script>
</head>
<body>
<header class="am-topbar">
    <h1 class="am-topbar-brand">groupManager</h1>

    <div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav">
            <li class="am-active"><a href="#">添加</a></li>
            <li><a href="#">查看</a></li>
        </ul>

        <form class="am-topbar-form am-topbar-left am-form-inline" role="search">
            <div class="am-form-group">
                <input type="text" class="am-form-field am-input-sm" placeholder="搜索">
            </div>
        </form>
    </div>
</header>
<div id="container" class="am-g">
    <jsp:include page="groupAdd.jsp"></jsp:include>
</div>
</body>
</html>
