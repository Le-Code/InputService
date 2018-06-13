<%--
  Created by IntelliJ IDEA.
  User: jerry
  Date: 2018/6/4
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%
    request.setAttribute("path",request.getContextPath());
%>
<html>
<head>
    <title>Title</title>
    <link href="../css/amazeui.css" rel="stylesheet">
    <script src="../js/jquery.js"></script>
    <script src="../js/amazeui.js"></script>
    <script src="../js/md5.js"></script>
    <script src="../js/common.js"></script>
    <script>
        function createTable(jsonObj) {
            if (jsonObj==null){
                $("table").html("");
                $("#okSpace").css("display","none");
                return;
            }
            $("#manually").html("");
            var table = $("table");
            var innerHTML = "<thead><tr><td>汉字</td><td>拼音</td><td>翻译</td><td>操作</td></tr></thead>";
            table.html(innerHTML);
            var tmpStr;
            for (var i = 0;i<jsonObj.showData.length; i++) {
                var itemId = (jsonObj.pageNum-1)*10+i;
                tmpStr = "<tr>" +
                    "<td><input type='text' name='chinese_"+itemId+"' disabled value='" + jsonObj.showData[i].chinese + "'/></td>" +
                    "<td><input type='text' name='pinyin_"+itemId+"' value='" + jsonObj.showData[i].pinyin.replace(/'/g," ") + "'/></td>" +
                    "<td><input type='text' name='translate_"+itemId+"' value='" + jsonObj.showData[i].translate + "'/>" +
                    "<i class='am-icon-close clearInput' itemId = '"+itemId+"' ></i><i class='am-icon-cloud getTranslate' itemId = '"+itemId+"'></i> </td>" +
                    "<td><button class = 'am-btn am-btn-danger deleteItem' itemId = '" + itemId + "'><i class='am-icon-trash'></i> 删除</button>"
                if (!jsonObj.showData[i].isConfirm)
                    tmpStr+="<button class='am-btn am-btn-success saveItem' itemId = '"+itemId+"'><i class='am-icon-check'></i>保存</button>";
                tmpStr+="</td></tr>";
                table.append(tmpStr);
            }
        }
        function createPager(jsonObj){
            if (jsonObj==null){
                $("#pagination").html("");
                $("#okSpace").css("display","none");
                return;
            }
            $("#manually").html("");
            $("#okSpace").css("display","block");
            var pagination = $("#pagination");
            var innerHTML = "<li";
            if (!jsonObj.hasPrevious){
                innerHTML+=" class = 'am-disabled'";
            }
            innerHTML +=" pageNum = '"+(jsonObj.pageNum-1)+"'><a href = '#'>&laquo;</a><li>";
            pagination.html(innerHTML);
            for (var i = jsonObj.pageStart;i<=jsonObj.pageEnd;i++){
                innerHTML = "<li";
                if (i==jsonObj.pageNum)
                    innerHTML+=" class = 'am-active'";
                innerHTML+=" pageNum = "+i+"><a href = '#'>"+i+"</a><li>";
                pagination.append(innerHTML);
            }
            innerHTML = "<li";
            if (!jsonObj.hasNext)
                innerHTML+=" class = 'am-disabled'";
            innerHTML+=" pageNum = '"+(jsonObj.pageNum+1)+"'><a href = '#'>&raquo;</a></li>";
            pagination.append(innerHTML);
        }

        function operateData(data) {
            var jsonObj;
            if (typeof data == 'string') {
                jsonObj = JSON.parse(data);
            } else {
                jsonObj = data;
            }
            createTable(jsonObj);
            createPager(jsonObj);
        }

        $(function() {
            $('#doc-form-file').on('change', function() {
                var fileNames = '';
                $.each(this.files, function() {
                    fileNames += '<span class="am-badge">' + this.name + '</span> ';
                });
                $('#file-list').html(fileNames);
            });

            $("#fileSubmit").on("click",function () {
                var formData = new FormData($("#uploadFrom")[0]);
                $.ajax({
                    url:"${path}/group/upload",
                    type:"post",
                    data:formData,
                    cache:false,
                    processData:false,
                    contentType:false,
                    dataType:"json",
                    success:function (data) {
                        operateData(data);
                    }
                });
            });
            $(document).on("click","li",function () {
                var pageNum = $(this).attr("pageNum");
                $.ajax({
                    url:"${path}/group/requestForOtherPage/"+pageNum,
                    dataType:"json",
                    success:function (data) {
                        operateData(data);
                    }
                })
            });

            $("#submit").on("click",function () {
                if ($("#checkBox").is(":checked")){
                    $.ajax({
                        url:"${path}/group/findAllGroup",
                        success:function (data) {
                            var jsonObj;
                            if (typeof data == 'string'){
                                jsonObj = JSON.parse(data);
                            }else{
                                jsonObj = data;
                            }
                            $("select").html("<option value = ''>自定义</option>");
                            for (var i = 0;i<jsonObj.length;i++){
                                $("select").append("<option value = '"+jsonObj[i].groupName+"'>"+jsonObj[i].groupName+"</option>");
                            }
                        }
                    });
                }else{
                    alert("请先确认");
                    return false;
                }
            });

            $("#manuallyBtn").on("click",function () {
                var url = $(this).attr("url");
                createPager(null);
                createTable(null);
                requestForOtherSpace(url,$("#manually"));
            });
            $("select").change(function () {
               var val = $(this).val();
               if (val!=""){
                   $("#input-groupName").css("display","none");
               }else{
                   $("#input-groupName").css("display","block");
               }
                $("input[name = 'groupName']").val(val);
            });
            $(document).on("click",".clearInput",function () {
               var itemId = $(this).attr("itemId");
               $("input[name = 'translate_"+itemId+"'").val("");
            });
            $(document).on("click",".getTranslate",function () {
                var itemId = $(this).attr("itemId");
                getTranslateFromBaidu($("input[name = 'chinese_"+itemId+"'").val(),$("input[name = 'translate_"+itemId+"'"));
            });

            $(document).on("click","table .deleteItem",function () {
                var itemId = $(this).attr("itemId");
                var pageNum = $("#pagination .am-active").attr("pageNum");
                console.log(itemId,pageNum);
                $.ajax({
                    url:"${path}/group/deleteFileItem",
                    data:{"pageNum":pageNum,"itemId":itemId},
                    dataType:"json",
                    success:function (data) {
                        operateData(data);
                    }
                });
            });
            $(document).on("click","table .saveItem",function () {
                var itemId = $(this).attr("itemId");
                var pinyin = trim($("input[name = 'pinyin_"+itemId+"']").val());
                var translate = trim($("input[name = 'translate_"+itemId+"']").val());
                console.log(itemId,pinyin,translate);
                if (pinyin.indexOf(" ")==-1){
                    alert("拼音格式不正确，中间应有含空格");
                    return;
                }
                if (translate==""){
                    alert("翻译为空");
                    return;
                }
                $.ajax({
                    url:"${path}/group/saveFileItem",
                    data:{"pinyin":pinyin,
                        "translate":translate,
                        "pageNum":$("#pagination .am-active").attr("pageNum"),
                        "itemId":itemId},
                    dataType:"json",
                    success:function (data) {
                        operateData(data);
                    }
                });
            });
        });

    </script>
</head>
<body>
<div class="am-u-sm-12">
    <div class="am-u-sm-6">
        <form id="uploadFrom" class="am-form" action="#" enctype="multipart/form-data" method="post">
            <div class="am-form-group am-form-file">
                <button type="button" class="am-btn am-btn-danger am-btn-sm">
                    <i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
                <input id="doc-form-file" name="file" type="file">
            </div>
            <div id="file-list"></div>
            <p><button id="fileSubmit" type="button" class="am-btn am-btn-success">上传</button></p>
        </form>
    </div>
    <div class="am-u-sm-6">
        <button id="manuallyBtn" url = "${path}/group/group_add_manually.jsp" class="am-btn am-btn-default">手动输入</button>
    </div>
</div>

<div class="am-u-sm-12">
    <table class="am-table am-table-bordered">

    </table>
</div>

<div id="manually" class="am-u-sm-12">

</div>

<div class="am-u-sm-12">
    <div id="okSpace" class="am-u-sm-2" hidden>
        <input type="checkbox" id="checkBox" />确定提交
        <button id="submit" type="button"  data-am-modal="{target: '#my-popup'}" class="am-btn am-btn-success">提交</button>
    </div>
    <div class="am-u-sm-10">
        <ul id="pagination" class="am-pagination am-pagination-centered">

        </ul>
    </div>
</div>
<div class="am-popup" id="my-popup">
    <div class="am-popup-inner">
        <div class="am-popup-hd">
            <h4 class="am-popup-title">提交</h4>
            <span data-am-modal-close
                  class="am-close">&times;</span>
        </div>
        <div class="am-popup-bd">
            <form class="am-form" action = "${path}/group/saveGroupFile">
                <fieldset>
                    <div class="am-form-group">
                        <label>词库名</label>
                        <select>
                            <option value="">自定义</option>
                        </select>
                    </div>
                    <div class="am-form-group" id="input-groupName">
                        <label>词库名</label>
                        <input type="text" name="groupName" placeholder="输入词库名" />
                    </div>
                    <div class="am-form-group">
                        <label>来源</label>
                        <input type="text" name="originFile" placeholder="来自哪里（文章）"/>
                    </div>
                    <div class="am-form-group">
                        <label>描述</label>
                        <textarea name="groupDesc" rows="5" placeholder="留下点描述吧"/>
                    </div>
                    <p><button type="submit" class="am-btn am-btn-default">提交</button> </p>
                </fieldset>
            </form>
        </div>
    </div>
</div>
</body>
</html>
