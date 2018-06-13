<%--
  Created by IntelliJ IDEA.
  User: jerry
  Date: 2018/6/7
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <%
        request.setAttribute("path",request.getContextPath());
    %>
    <title>Title</title>
    <link rel="stylesheet" href="../css/amazeui.css"/>
    <script src="../js/jquery.js"></script>
    <script src="../js/amazeui.js"></script>

    <script>

        //创造一个显示
        function query_createSingleGroup(index,obj) {
            var div = $("<div class='am-vertical-align'></div>");
            div.css("height","110px");
            if (index%2==0){
                div.css("background","#beccc9");
            }
            var divWrapper1 = $("<div class = 'am-u-sm-11 am-vertical-align-middle'></div>");
            divWrapper1.append("<div class = 'am-u-sm-12'><strong>"+obj.groupName+"</strong></div>");
            divWrapper1.append("<div class = 'am-u-sm-12'>词库描述："+obj.groupDesc+"</div>");
            divWrapper1.append("<div class = 'am-u-sm-12'>下载次数：xxx</div>");
            divWrapper1.append("<div class = 'am-u-sm-12'>更新时间："+obj.createTime+"</div>");
            var divWrapper2 = $("<div class = 'am-vertical-align-middle'></div>");
            console.log(obj.groupPath);
            divWrapper2.append("<a class='am-btn am-btn-success query_group_download' href='${path}/upload/group/"+obj.groupPath+"' download = '"+obj.groupName+"'>立即下载</a>");
            div.append(divWrapper1);
            div.append(divWrapper2);
            $("#content").append(div);
        }

        function query_handleView(content){
            for(var i = 0;i<content.length;i++){
                query_createSingleGroup(i,content[i]);
            }
        }

        function query_handlePagination(pageInfo) {
            var preLi = $("<li pageNum = '"+(pageInfo.pageNum-1)+"'></li>");
            if (!pageInfo.hasPreviousPage)
                preLi.attr("class","am-disabled");
            preLi.append("<a href = '#'>&laquo;</a>");
            $("#pagination").append(preLi);
            for (var i = 0;i<pageInfo.navigatepageNums.length;i++){
                var li = $("<li pageNum = '"+pageInfo.navigatepageNums[i]+"'></li>");
                if (pageInfo.pageNum==pageInfo.navigatepageNums[i])
                    li.attr("class","am-active");
                li.append("<a href = '#'>"+pageInfo.navigatepageNums[i]+"</a>");
                $("#pagination").append(li);
            }
            var nextLi = $("<li pageNum = '"+(pageInfo.pageNum+1)+"'></li>");
            if (!pageInfo.hasNextPage)
                nextLi.attr("class","am-disabled");
            nextLi.append("<a href = '#'>&raquo;</a>");
            $("#pagination").append(nextLi);
        }

        function query_handleData(jsonObj){
            //清空数据
            $("#content").html("");
            $("#pagination").html("");
            //展示数据
            query_handleView(jsonObj.content);
            //展示分页
            query_handlePagination(jsonObj.pageInfo);
        }

        //请求哪一页 的数据
        function query_request_group(page){
            $.ajax({
                url:"${path}/group/findGroup/"+page,
                success:function (data) {
                    console.log(data);
                    var  jsonObj;
                    if (typeof data=='string'){
                        jsonObj = JSON.parse(data);
                    }else{
                        jsonObj = data;
                    }
                    query_handleData(jsonObj);
                }
            });
        }

        $(document).ready(function () {
            query_request_group(1);

            $("#pagination li").on("click",function () {
                var pageNum = $(this).attr("pageNum");
                query_request_group(pageNum);
            });
        });

    </script>

</head>
<body>
<div id="content" class="am-u-sm-12 am-panel-group">

</div>
<div class="am-u-sm-12">
    <ul id="pagination" class="am-pagination am-pagination-centered ">
    </ul>
</div>
</body>
</html>
