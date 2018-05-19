<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setAttribute("path", request.getContextPath());
%>
<html>
<head>
    <title>Title</title>
    <link href="../css/amazeui.css" rel="stylesheet">
    <script src="../js/jquery.js"></script>
    <script src="../js/amazeui.js"></script>
    <style type="text/css">
        .table-container {
            margin-top: 10px;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $("#isRead").change(function () {
                handleChange($("#isRead"),$("#timeOrder"));
            });
            $("#timeOrder").change(function () {
                handleChange($("#isRead"),$("#timeOrder"));
            });


            $(document).on("click","#deleteFeedback",function () {
                var feedId = $(this).attr("feedId");
                var isRead = $("#isRead").val();
                var timeOrder = $("#timeOrder").val();
                if (confirm("确定删除")){
                    $.ajax({
                        contentType:"application/x-www-form-urlencoded; charset=UTF-8",
                        url:"${path}/courseFeedback/deleteFeedback.do",
                        data:{"id":feedId,"isRead":isRead,"timeOrder":timeOrder},
                        dataType:"json",
                        success:function (data) {
                            console.log(data);
                            handleData(data);
                        }
                    });
                } else{
                    return false;
                }
            });

            $("#readFeedback").click(function () {
               if (confirm("阅读")){
                   console.log("yuedu");
               } else{
                   console.log("tuichu");
               }
            });

        });

        function handleChange(isReadElement,timeOrderElement) {
            var isRead = isReadElement.val();
            var timeOrder = timeOrderElement.val();
            $.ajax({
                url: "${path}/courseFeedback/changeCourseFeedbackList.do",
                type: "GET",
                contentType:"application/x-www-form-urlencoded; charset=UTF-8",
                dateType: "json",
                data: {isRead: isRead, timeOrder: timeOrder},
                success: function (data) {
                    console.log(data)
                    handleData(data);
                }
            });
        }

        function handleData(data) {
            var table = $("table");
            var innerHTML = "<thead><tr><td>编号</td><td>反馈内容</td><td>反馈时间</td><td>操作</td></tr></thead>";
            table.html(innerHTML);
            var feedbacks;
            if (typeof data == 'string')
                feedbacks = JSON.parse(data);
            else
                feedbacks = data;
            var tmpStr;
            for (var i = 0; i < feedbacks.length; i++) {
                tmpStr = "<tr";
                if (feedbacks[i].isRead==0)
                    tmpStr+=" class = 'am-warning'>";
                else
                    tmpStr+=">";
                tmpStr+="<td>"+feedbacks[i].id +"</td><td>"+feedbacks[i].info+"</td><td>"+feedbacks[i].feedTime+"</td><td>" +
                    "<button id = 'readFeedback' feedId = '"+feedbacks[i].id+"' class='am-btn am-btn-success'>阅读</button>" +
                    "<button id = 'deleteFeedback' feedId = '"+feedbacks[i].id+"' class='am-btn am-btn-danger'>删除</button></td> "+
                    "</tr>";
                table.append(tmpStr);
            }
        }
    </script>
</head>
<body>
<div class="am-g">
    <div class="am-u-sm-4">
        <label class="am-form-label">条件：</label>
        <select id="isRead" class="am-selected">
            <option value="-1">全部</option>
            <option value="0">未读</option>
            <option value="1">已读</option>
        </select>
    </div>
    <div class="am-u-sm-4 am-u-end">
        <label class="am-form-label">顺序：</label>
        <select id="timeOrder" class="am-selected">
            <option value="asc">时间从前到后</option>
            <option value="desc">时间从后到前</option>
        </select>
    </div>
</div>
<div id="table" class="am-g am-u-sm-12 am-u-sm-centered table-container">
    <table class="am-table am-table-bordered am-table-radius">
        <thead>
        <tr>
            <th>编号</th>
            <th>反馈内容</th>
            <th>反馈时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <c:forEach items="${feedbacks}" var="feedback">
            <tr class="<c:if test="${feedback.isRead==0}"> am-warning </c:if>">
                <td>${feedback.id}</td>
                <td>${feedback.info}</td>
                <td>${feedback.feedTime}</td>
                <td>
                    <button id="readFeedback" class="am-btn am-btn-success">阅读</button>
                    <button id="deleteFeedback" feedId = "${feedback.id}" class="am-btn am-btn-danger">删除</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="am-g">
    <ul class="am-pagination am-pagination-centered">
        <li class="am-disabled"><a href="#">&laquo;</a></li>
        <li class="am-active"><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
        <li><a href="#">&raquo;</a></li>
    </ul>
</div>
</body>
</html>