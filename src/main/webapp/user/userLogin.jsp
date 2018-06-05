<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>login</title>
    <link href="../css/amazeui.css" rel="stylesheet">
    <script src="../js/jquery.js"></script>
    <script src="../js/amazeui.js"></script>
    <style type="text/css">
        .login-container{
            margin: 0 auto;
        }
        .head-container{
            margin-top: 5%;
        }
        body{
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: 100% 100%;
            background-image: url("../image/back_image.jpg");
        }
    </style>
</head>
<body>
    <div class="am-g head-container">
        <div class="am-u-sm-6 am-u-sm-centered">
            <h1 style="text-align: center">E拼音后台</h1>
        </div>
    </div>
    <div class="am-g">
        <div class="am-u-sm-4 am-u-sm-centered login-container">
            <form class="am-form" action="../input-service.jsp" method="post">
                <div class="am-form-group">
                    <label class="am-form-label">username:</label>
                    <input type="text" name="username" placeholder="type username" />
                </div>
                <div class="am-form-group">
                    <label class="am-form-label">password:</label>
                    <input type="password" name="password" placeholder="type password" />
                </div>
                <div class="am-form-group">
                    <input type="submit" class="am-btn am-btn-success am-u-sm-2" value="login"/>
                    <input type="reset" class="am-btn am-btn-secondary am-u-sm-2 am-u-end am-u-lg-offset-1" value="reset" />
                </div>
            </form>
        </div>
    </div>
</body>
</html>