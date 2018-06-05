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

        }

        function manuallySubmit(){
            var radioVal = $("input[name = 'docInlineRadio']:checked").val();
            if (radioVal=="one"){
                var regex = "[\u4e00-\u9fa5]+";
                var chinese = $("input[name = 'chinese']").val();
                var pinyin = $("input[name = 'pinyin']").val();
                var translate = $("input[name = 'translate']").val();
                //判断拼音是否符合标准
                if (pinyin.indexOf("'")==-1){
                    alert("拼音不符合标准");
                    return ;
                }else if (chinese==""||translate==""){
                    alert("存在文本框为空，请检查");
                    return ;
                }else if (!chinese.match(regex)){
                    alert("中文不能存在非汉字");
                    return;
                } else
                    submitOne(chinese,pinyin,translate);
            }else{
                submitAll();
            }
            return false;
        }
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
                        <input type="text" name="chinese" class="" placeholder="输入中文汉字"/>
                    </div>

                    <div class="am-form-group">
                        <label>拼音</label>
                        <input type="text" name="pinyin" class="" placeholder="输入汉字拼音" />
                    </div>

                    <div class="am-form-group">
                        <label>翻译</label>
                        <input type="text" name="translate" class="" placeholder="输入汉字翻译" />
                    </div>
                    <div class="am-form-group">
                        <label class="am-radio-inline">
                            <input type="radio"  value="one" name="docInlineRadio" checked /> 单个
                        </label>
                        <label class="am-radio-inline">
                            <input type="radio" value="all" name="docInlineRadio" /> 全部
                        </label>
                    </div>
                    <p><button id="submitOneManually" onclick="manuallySubmit()" type="button" class="am-btn am-btn-default">提交</button></p>
                </fieldset>
            </form>
        </div>
        <div class="am-u-sm-6">
            <table id = "showSubmit" class="am-table">

            </table>
        </div>
    </div>
</body>
</html>
