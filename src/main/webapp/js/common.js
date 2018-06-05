/**
 * 显示结果在同一个页面的其他部分显示
 * @param url 路径
 * @param dest 目的地
 */
function requestForOtherSpace(url,dest){
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
            dest.html(xmlHttp.responseText);
            // executeScript(xmlHttp.responseText)//执行从服务器返回的页面内容里面包含的javaScript函数
        }else if (xmlHttp.status==404) {
            alert("出错了☹   （错误代码：404 Not Found），……！");
            /* 对404的处理 */
            return false;
        }else if (xmlHttp.status==403) {
            alert("出错了☹   （错误代码：403 Forbidden），……");
            /* 对403的处理  */
            return false;
        }else{
            alert("出错了☹   （错误代码：" + request.status + "），……");
            /* 对出现了其他错误代码所示错误的处理   */
            return false;
        }
    }
    //把请求发送到服务器上的制定文件进行处理
    xmlHttp.open("GET",url,true);//true表示异步处理
    xmlHttp.send();
}

/**
 * 翻译
 * @param str
 */
function getTranslateFromBaidu(str,show) {
    var appid = '20171207000103106';
    var key = 'C2L5SyWaRn3swHP5fUOZ';
    var salt = (new Date).getTime();
    var from = 'zh';
    var to = 'en';
    var str1 = appid + str + salt +key;
    var sign = MD5(str1);
    $.ajax({
        url: 'http://api.fanyi.baidu.com/api/trans/vip/translate',
        type: 'get',
        dataType: 'jsonp',
        data: {
            q: str,
            appid: appid,
            salt: salt,
            from: from,
            to: to,
            sign: sign
        },
        success: function (data) {
            var jsonObj;
            if (typeof data =='string'){
                jsonObj = JSON.parse(data);
            }else{
                jsonObj = data;
            }
            show.val(jsonObj.trans_result[0].dst.toLowerCase());
        }
    });
}

/**
 * 去掉字符串两边的空格
 * @param str
 */
function trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}