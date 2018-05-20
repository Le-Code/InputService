<%--
  Created by IntelliJ IDEA.
  User: jerry
  Date: 2018/5/20
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setAttribute("path",request.getContextPath());
%>
<html>
<head>
    <title>Title</title>
    <link href="css/amazeui.css" rel="stylesheet">
    <script src="js/jquery.js"></script>
    <script src="js/amazeui.js"></script>
    <style type="text/css">
        .header {
            background-color: rgba(23, 23, 23, 0.58);
        }

        .user {
            margin-right: 20px;
        }

        .whole-font {
            color: black;
        }

        .content-main {
            margin-top: 20px;
        }

        .option-a {
            display: block;
        }

        .option-a:hover {
            background-color: #cccccc;
        }
    </style>
    <script type="text/javascript">

        function requestForRight(url){
            var xmlHttp;
            //创建xmlHttpRequest对象
            if (window.XMLHttpRequest) {
                // code for IE7+, Firefox, Chrome, Opera, Safari
                xmlHttp = new XMLHttpRequest();
            }else{
                // code for IE6, IE5
                xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
            }
            xmlHttp.onreadystatechange = function () {
                if (xmlHttp.status==200){
                    $("#show-detail").html(xmlHttp.responseText);
                    // executeScript(xmlHttp.responseText)//执行从服务器返回的页面内容里面包含的javaScript函数
                }else if (xmlHttp.status==404) {
                    alert("出错了☹   （错误代码：404 Not Found），……！");
                    /* 对404的处理 */
                    return;
                }else if (xmlHttp.status==403) {
                    alert("出错了☹   （错误代码：403 Forbidden），……");
                    /* 对403的处理  */
                    return;
                }else{
                    alert("出错了☹   （错误代码：" + request.status + "），……");
                    /* 对出现了其他错误代码所示错误的处理   */
                    return;
                }
            }
            //把请求发送到服务器上的制定文件进行处理
            xmlHttp.open("GET",url,true);//true表示异步处理
            xmlHttp.send();
        }

        /*function executeScript(html){
            var reg = /<script[^>]*>([^\x00]+)$/i;
            //对整段HTML片段按<\/script>拆分
            var htmlBlock = html.split("<\/script>");
            for (var i in htmlBlock)
            {
                var blocks;//匹配正则表达式的内容数组，blocks[1]就是真正的一段脚本内容，因为前面reg定义我们用了括号进行了捕获分组
                if (blocks = htmlBlock[i].match(reg))
                {
                    //清除可能存在的注释标记，对于注释结尾-->可以忽略处理，eval一样能正常工作
                    var code = blocks[1].replace(/<!--/, '');
                    try
                    {
                        eval(code) //执行脚本
                    }
                    catch (e)
                    {
                    }
                }
            }
        }*/

        $(function () {
            $(".option-a").click(function () {
                var href = $(this).attr("url");//跳转的地址
                requestForRight(href);
            });
        });


    </script>
</head>
<body>
<div class="am-g am-cf am-header am-nav header">
    <ul class="am-fr user">
        <li class="am-dropdown" data-am-dropdown>
            <a class="am-dropdown-toggle whole-font" data-am-dropdown-toggle href="javascript:;">
                user <span class="am-icon am-icon-caret-down"></span>
            </a>
            <ul class="am-dropdown-content">
                <li><a class="option-a" href="#">注销</a></li>
            </ul>
        </li>
    </ul>
</div>
<div class="am-g content-main">
    <div class="am-u-sm-2">
        <div class="am-panel-group" id="accordion">
            <div class="am-panel am-panel-default">
                <div class="am-panel-hd">
                    <h4 class="am-panel-title am-cf" data-am-collapse="{parent: '#accordion', target: '#do-not-say-1'}">
                        课程<span class="am-icon-bars am-fr"></span>
                    </h4>
                </div>
                <div id="do-not-say-1" class="am-panel-collapse am-collapse am-in">
                    <div class="am-panel-bd">
                        <a class="option-a" url = "${path}/courseFeedback/showCourseFeedback.do" href="#" toPosition = "feedback/CourseFeedbackShow.jsp">课件反馈</a>
                        <hr/>
                        <a class="option-a" href="#">课件管理</a>
                    </div>
                </div>
            </div>
            <div class="am-panel am-panel-default">
                <div class="am-panel-hd">
                    <h4 class="am-panel-title am-cf" data-am-collapse="{parent: '#accordion', target: '#do-not-say-2'}">
                        输入法<span class="am-icon-bars am-fr"></span>
                    </h4>
                </div>
                <div id="do-not-say-2" class="am-panel-collapse am-collapse">
                    <div class="am-panel-bd">
                        <a class="option-a" href="#">输入法反馈</a>
                        <hr/>
                        <a class="option-a" href="#">词库管理</a>
                        <hr/>
                        <a class="option-a" href="#">广告管理</a>
                    </div>
                </div>
            </div>
            <div class="am-panel am-panel-default">
                <div class="am-panel-hd">
                    <h4 class="am-panel-title" data-am-collapse="{parent: '#accordion', target: '#do-not-say-3'}">
                        用户管理<span class="am-icon-bars am-fr"></span>
                    </h4>
                </div>
                <div id="do-not-say-3" class="am-panel-collapse am-collapse">
                    <div class="am-panel-bd">
                        <a class="option-a" href="#">查看所有用户</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="show-detail" class="am-u-sm-10">
        <%--<jsp:include page="feedback/CourseFeedbackShow.jsp"></jsp:include>--%>
    </div>
</div>
</body>
</html>
