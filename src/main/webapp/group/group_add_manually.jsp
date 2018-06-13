<%--
  Created by IntelliJ IDEA.
  User: jerry
  Date: 2018/6/4
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%
    request.setAttribute("path",request.getContextPath());
%>
<html>
<head>
    <title>Title</title>
    <link href="../css/amazeui.css" rel="stylesheet">
    <script src="../js/jquery.js"></script>
    <script src="../js/amazeui.js"></script>
    <script src="../js/common.js"></script>
    <script>

        function submitOne(chinese,pinyin,translate){
            $.ajax({
                contentType:"application/x-www-form-urlencoded; charset=UTF-8",
                url:"${path}/group/saveManuallySingle",
                data:{"chinese":chinese,"pinyin":pinyin,"translate":translate},
                type:'get',
                success:function () {
                    $("input[name = 'chinese']").val("");
                    $("input[name = 'pinyin']").val("");
                    $("input[name = 'translate']").val("");
                    var str = "<tr><td>"+chinese+"</td><td>"+pinyin+"</td><td>"+translate+"</td></tr>";
                    $("#showSubmit").append(str);
                },
                error:function (status) {
                    alert(status);
                }
            });
        }

        function submitAll(){
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
            })
        }

        function manuallySubmit(){
            var radioVal = $("input[name = 'docInlineRadio']:checked").val();
            if (radioVal=="one"){
                var regex = "[\u4e00-\u9fa5]+";
                var chinese = trim($("input[name = 'chinese']").val());
                var pinyin = trim($("input[name = 'pinyin']").val());
                var translate = trim($("input[name = 'translate']").val());
                //判断是否为空
                if (chinese==""||translate==""||pinyin==""){
                    alert("存在文本框为空，请检查");
                    return ;
                }else if (!chinese.match(regex)){
                    alert("中文不能存在非汉字");
                    return;
                } else
                    submitOne(chinese,pinyin,translate);
                return false;
            }else{
                submitAll();
            }
        }

        $(function () {
            $(document).on("change","#manuallyChineseInput",function () {
                var chinese = $(this).val();
                if(chinese.length<2){
                    alert("汉字的长度小于2");
                    return;
                }
                $.ajax({
                    url:"${path}/group/getPyAndTranslateForWord",
                    data:{"chinese":chinese},
                    success:function (data) {
                        if (data!="@"){
                            var arr = data.split("@");
                            $("#manuallyPinyinInput").val(arr[0]);
                            $("#manuallyTranslateInput").val(arr[1]);
                        }
                    }
                })
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

            $(document).on("change","input[type = 'radio']",function () {
                var val = $(this).val();
                if (val=="one"){
                    $("#submitOneManually").attr("data-am-modal","");
                }else{
                    $("#submitOneManually").attr("data-am-modal","{target: '#manually-popup'}");
                }
            })
        })
    </script>
</head>
<body>
    <div class="am-u-sm-12">
        <div class="am-u-sm-6">
            <form class="am-form" action="#">
                <fieldset>
                    <legend>自定义词库</legend>

                    <div class="am-form-group">
                        <label>中文</label>
                        <input type="text" id = "manuallyChineseInput" name="chinese" class="" placeholder="输入中文汉字"/>
                    </div>

                    <div class="am-form-group">
                        <label>拼音</label>
                        <input type="text" id = "manuallyPinyinInput" name="pinyin" class="" placeholder="输入汉字拼音" />
                    </div>

                    <div class="am-form-group">
                        <label>翻译</label>
                        <input type="text" id = "manuallyTranslateInput" name="translate" class="" placeholder="输入汉字翻译" />
                    </div>
                    <div class="am-form-group">
                        <label class="am-radio-inline">
                            <input type="radio"  value="one" name="docInlineRadio" checked /> 单个
                        </label>
                        <label class="am-radio-inline">
                            <input type="radio" value="all" name="docInlineRadio" /> 全部
                        </label>
                    </div>
                    <p><button id="submitOneManually" type="button" onclick="return manuallySubmit()"  class="am-btn am-btn-default">提交</button></p>
                </fieldset>
            </form>
        </div>
        <div class="am-u-sm-6">
            <table id = "showSubmit" class="am-table">

            </table>
        </div>
    </div>

    <%--提交词库出现的悬浮窗--%>
    <div class="am-popup" id="manually-popup">
        <div class="am-popup-inner">
            <div class="am-popup-hd">
                <h4 class="am-popup-title">提交</h4>
                <span data-am-modal-close
                      class="am-close">&times;</span>
            </div>
            <div class="am-popup-bd">
                <form class="am-form" action = "${path}/group/saveManuallyGroupFile" method="post">
                    <fieldset>
                        <div class="am-form-group">
                            <label>词库名</label>
                            <select>
                                <option value="">自定义</option>
                            </select>
                        </div>
                        <div class="am-form-group" id="input-groupName">
                            <label>词库名</label>
                            <input type="text" id="manually_groupName_input" name="groupName" placeholder="输入词库名" />
                        </div>
                        <div class="am-form-group">
                            <label>来源</label>
                            <input type="text" id="manually_originFile_input" name="originFile" placeholder="来自哪里（文章）"/>
                        </div>
                        <div class="am-form-group">
                            <label>描述</label>
                            <textarea id="manually_groupDesc_input" name="groupDesc" rows="5" placeholder="留下点描述吧"/>
                        </div>
                        <p><button type="submit" class="am-btn am-btn-default">提交</button> </p>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
