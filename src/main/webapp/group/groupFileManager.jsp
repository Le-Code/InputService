<%--
  Created by IntelliJ IDEA.
  User: jerry
  Date: 2018/6/4
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>group</title>

    <script type="text/javascript">
        $(function () {
            $("#doc-form-file").on("change",function () {
                var fileName = this.name;
                $("#file-list").html(fileName);
            });
        })
    </script>
</head>
<body>
<header class="am-topbar">
    <h1 class="am-topbar-brand">
        <a href="#">group</a>
    </h1>

    <div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav">
            <li class="am-active"><a href="#">添加</a></li>
            <li><a href="#">查看</a></li>
            <li class="am-dropdown" data-am-dropdown>
                <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
                    排序 <span class="am-icon-caret-down"></span>
                </a>
                <ul class="am-dropdown-content">
                    <li class="am-dropdown-header">时间</li>
                    <li class="am-active"><a href="#">升序</a></li>
                    <li><a href="#">降序</a></li>
                </ul>
            </li>
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
