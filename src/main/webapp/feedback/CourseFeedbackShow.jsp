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

            $("div ul").on("click","li",function () {
                var pageNum = $(this).attr("pageNum");
                var isRead = $("#isRead").val();
                var timeOrder = $("#timeOrder").val();
                $.ajax({
                    url:"${path}/courseFeedback/changeCourseFeedbackList.do",
                    data:{"pageNum":pageNum,"isRead":isRead,"timeOrder":timeOrder},
                    type:"GET",
                    dataType:"json",
                    success:function (data) {
                        handleData(data);
                    }
                });
            })

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
                            handleData(data);
                        }
                    });
                } else{
                    return false;
                }
            });
            
            $(document).on("click","#readFeedback",function () {
                var feedId = $(this).attr("feedId");
                $.ajax({
                    url:"${path}/courseFeedback/getFeedbackById/"+feedId,
                    type:"GET",
                    success:function (data) {
                        jsonObj = JSON.parse(data);
                        $("#info p").html(jsonObj["info"]);
                        $("#time p").html(jsonObj["feedTime"]);
                        $("#"+feedId).attr("class","");
                    }
                })
            })
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
                    handleData(data);
                }
            });
        }

        function handleData(data) {
            var table = $("table");
            var innerHTML = "<thead><tr><td>编号</td><td>反馈内容</td><td>反馈时间</td><td>操作</td></tr></thead>";
            table.html(innerHTML);
            var jsonObj;
            if (typeof data == 'string')
                jsonObj = JSON.parse(data);
            else
                jsonObj = data;
            var feedbacks = jsonObj["content"];
            var pageInfo = jsonObj["pageInfo"];
            var tmpStr;
            for (var i = 0; i < feedbacks.length; i++) {
                tmpStr = "<tr id = "+feedbacks[i].id;
                if (feedbacks[i].isRead==0)
                    tmpStr+=" class = 'am-warning'>";
                else
                    tmpStr+=">";
                tmpStr+="<td>"+feedbacks[i].id +"</td><td>"+feedbacks[i].info+"</td><td>"+feedbacks[i].feedTime+"</td><td>" +
                    "<button id = 'readFeedback' feedId = '"+feedbacks[i].id+"' class='am-btn am-btn-success'  data-am-modal=\"{target: '#my-popup'}\">阅读</button>" +
                    "<button id = 'deleteFeedback' feedId = '"+feedbacks[i].id+"' class='am-btn am-btn-danger'>删除</button></td> "+
                    "</tr>";
                table.append(tmpStr);
            }

            //添加分页
            var pagination = $("#pagination");
            var innerHTML = "<li";
            if (!pageInfo.hasPreviousPage){
                innerHTML+=" class = 'am-disabled'";
            }
            innerHTML +=" pageNum = '"+(pageInfo.pageNum-1)+"'><a href = '#'>&laquo;</a><li>";
            pagination.html(innerHTML);
            for (var i = 0;i<pageInfo.navigatepageNums.length;i++){
                innerHTML = "<li";
                if (pageInfo.pageNum==pageInfo.navigatepageNums[i])
                    innerHTML+=" class = 'am-active'";
                innerHTML+=" pageNum = "+pageInfo.navigatepageNums[i]+"><a href = '#'>"+pageInfo.navigatepageNums[i]+"</a></li>";
                pagination.append(innerHTML);
            }
            innerHTML = "<li";
            if (!pageInfo.hasNextPage){
                innerHTML+=" class = 'am-disabled'";
            }
            innerHTML +=" pageNum = '"+(pageInfo.pageNum+1)+"'><a href = '#'>&raquo;</a><li>";
            pagination.append(innerHTML);
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
            <tr id="${feedback.id}" class="<c:if test="${feedback.isRead==0}"> am-warning </c:if>">
                <td>${feedback.id}</td>
                <td>${feedback.info}</td>
                <td>${feedback.feedTime}</td>
                <td>
                    <button id="readFeedback" feedId = "${feedback.id}" class="am-btn am-btn-success" data-am-modal="{target: '#my-popup'}">阅读</button>
                    <button id="deleteFeedback" feedId = "${feedback.id}" class="am-btn am-btn-danger">删除</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<%--弹出阅读的对话框--%>
<div class="am-popup" id="my-popup">
    <div class="am-popup-inner">
        <div class="am-popup-hd">
            <h4 class="am-popup-title">反馈</h4>
            <span data-am-modal-close
                  class="am-close">&times;</span>
        </div>
        <div class="am-popup-bd">
            <label>内容<span class="am-icon-building"></span>: </label>
            <div id="info">
                <p></p>
            </div>
            <hr>
            <label>时间<span class="am-icon-times"></span> :</label>
            <div id="time">
                <p></p>
            </div>
        </div>
    </div>
</div>

<div class="am-g">
    <ul id="pagination" class="am-pagination am-pagination-centered">
        <li class="<c:if test="${!pageInfo.hasPreviousPage}">am-disabled</c:if>" pageNum = "${pageInfo.pageNum-1}"><a href="#">&laquo;</a></li>
        <c:forEach items="${pageInfo.navigatepageNums}" var="item">
            <li class="<c:if test="${pageInfo.pageNum==item}">am-active</c:if> " pageNum = "${item}"><a href="#">${item}</a> </li>
        </c:forEach>
        <li class="<c:if test="${!pageInfo.hasNextPage}">am-disabled</c:if>" pageNum = "${pageInfo.pageNum+1}"><a href="#">&raquo;</a></li>
    </ul>
</div>

</body>
</html>