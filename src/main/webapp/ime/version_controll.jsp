<%--
  Created by IntelliJ IDEA.
  User: jerry
  Date: 2018/7/3
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        request.setAttribute("path",request.getContextPath());
    %>
    <title>Title</title>
    <link href="../css/amazeui.css" rel="stylesheet">
    <script src="../js/jquery.js"></script>
    <script src="../js/amazeui.js"></script>
    <script src="../js/md5.js"></script>
    <script src="../js/common.js"></script>
    <script>
        $(function () {
            $('#ime_doc-form-file').on('change', function() {
                var fileNames = '';
                $.each(this.files, function() {
                    fileNames += '<span class="am-badge">' + this.name + '</span> ';
                });
                $('#ime_file-list').html(fileNames);
            });
        })
    </script>
</head>
<body>
<div class="am-u-sm-12 am-center">
    <form id="uploadFrom" class="am-form" action="${path}/imeVersion/add" enctype="multipart/form-data" method="post">

        <div class="am-form-group">
            <label for="doc-ipt-pwd-1">版本</label>
            <input type="text" class="" name="version" id="doc-ipt-pwd-1" placeholder="xx.xx.xx">
        </div>

        <div class="am-form-group am-form-file">
            <button type="button" class="am-btn am-btn-danger am-btn-sm">
                <i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
            <input id="ime_doc-form-file" name="file" type="file">
        </div>
        <div id="ime_file-list"></div>
        <p><button type="submit" class="am-btn am-btn-success">上传</button></p>
    </form>
</div>
</body>
</html>
