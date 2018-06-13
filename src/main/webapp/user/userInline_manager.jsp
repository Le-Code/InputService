<%--
  Created by IntelliJ IDEA.
  User: jerry
  Date: 2018/6/9
  Time: 16:23
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
    <script>

        function forbidden(id) {
            $.ajax({
                url:"${path}/userInline/forbidden/"+id,
                success:function (data) {
                    if (data=="ok"){
                        $("#userInline_manager_certified"+id).html("是否认证：非认证")
                    }else{
                        alert("操作失败");
                        return;
                    }
                }
            });
        }

        //创造一个显示
        function userInline_manager_createSingleUser(index,obj) {
            var div = $("<div class='am-vertical-align'></div>");
            div.css("height","85px");
            if (index%2==0){
                div.css("background","#beccc9");
            }
            var divWrapper1 = $("<div class = 'am-u-sm-11 am-vertical-align-middle'></div>");
            divWrapper1.append("<div class = 'am-u-sm-12'>用户ip："+obj.ip+"</div>");
            divWrapper1.append("<div class = 'am-u-sm-12'>是否在线："+(obj.state==1?"在线":"不在线")+"</div>");
            divWrapper1.append("<div id = 'userInline_manager_certified"+obj.id+"' class = 'am-u-sm-12'>是否认证："+(obj.certified==1?"认证":"非认证")+"</div>");
            var divWrapper2 = $("<div class = 'am-vertical-align-middle'></div>");
            divWrapper2.append("<button class='am-btn am-btn-warning userInline_manager_forbidden' onclick = 'forbidden("+obj.id+")' id = '"+obj.id+"'>停止使用</a>");
            div.append(divWrapper1);
            div.append(divWrapper2);
            $("#userInline_manager_show").append(div);
        }

        function userInline_manager_handleView(content){
            for(var i = 0;i<content.length;i++){
                userInline_manager_createSingleUser(i,content[i]);
            }
        }

        function userInline_manager_handlePagination(pageInfo) {
            var preLi = $("<li pageNum = '"+(pageInfo.pageNum-1)+"'></li>");
            if (!pageInfo.hasPreviousPage)
                preLi.attr("class","am-disabled");
            preLi.append("<a href = '#'>&laquo;</a>");
            $("#userInline_manager_pagination").append(preLi);
            for (var i = 0;i<pageInfo.navigatepageNums.length;i++){
                var li = $("<li pageNum = '"+pageInfo.navigatepageNums[i]+"'></li>");
                if (pageInfo.pageNum==pageInfo.navigatepageNums[i])
                    li.attr("class","am-active");
                li.append("<a href = '#'>"+pageInfo.navigatepageNums[i]+"</a>");
                $("#userInline_manager_pagination").append(li);
            }
            var nextLi = $("<li pageNum = '"+(pageInfo.pageNum+1)+"'></li>");
            if (!pageInfo.hasNextPage)
                nextLi.attr("class","am-disabled");
            nextLi.append("<a href = '#'>&raquo;</a>");
            $("#userInline_manager_pagination").append(nextLi);
        }


        function userInline_manager_handleData(jsonObj){
            //清空数据
            $("#userInline_manager_show").html("");
            $("#userInline_manager_pagination").html("");
            //展示数据
            userInline_manager_handleView(jsonObj.content);
            //展示分页
            userInline_manager_handlePagination(jsonObj.pageInfo);
        }

        //请求哪一页 的数据
        function userInline_manager_request_user(page){
            $.ajax({
                url:"${path}/userInline/findAllUser/"+page,
                success:function (data) {
                    console.log(data);
                    var  jsonObj;
                    if (typeof data=='string'){
                        jsonObj = JSON.parse(data);
                    }else{
                        jsonObj = data;
                    }
                    userInline_manager_handleData(jsonObj);
                }
            });
        }

        $(function () {
            userInline_manager_request_user(1);
            $("#userInline_manager_pagination li").on("click",function () {
                var pageNum = $(this).attr("pageNum");
                query_request_group(pageNum);
            });

            /*$(document).on("click",".userInline_manager_forbidden",function () {
                var id = $(this).attr("id");

            })*/
        })
    </script>
</head>
<body>
    <div id="userInline_manager_show"  class="am-u-sm-12 am-panel-group">

    </div>
    <div class="am-u-sm-12">
        <ul id="userInline_manager_pagination" class="am-pagination am-pagination-centered ">
        </ul>
    </div>
</body>
</html>
