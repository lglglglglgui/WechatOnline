<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
    <title>chatOnline | 注册</title>
    <link href="<%=path%>/static/source/css/login.css" rel='stylesheet' type='text/css'/>
    <script src="<%=path%>/static/plugins/jquery/jquery-2.1.4.min.js"></script>
    <script src="<%=path%>/static/plugins/layer/layer.js"></script>
</head>
<body>

<h1>chatOnline</h1>
<div class="login-form">
    <div class="close"></div>
    <div class="head-info">
        <label class="lbl-1"></label>
        <label class="lbl-2"></label>
        <label class="lbl-3"></label>
    </div>
    <div class="clear"></div>
    <div class="avtar"><img src="<%=path%>/static/source/img/avtar.png"/></div>
    <form id="login-form" action="<%=path%>/user/register" method="post" onsubmit="return checkLoginForm()">
        <div class="key">
            <input type="text" pattern="\w{3,10}" title="输入3至10位的字母或数字" onblur="checkUsername(this)"  id="username" name="username" placeholder="请输入注册的账号">
        </div>
        <p id="p1"></p>

        <div class="key">
            <input type="text" id="nickname" name="nickname" placeholder="请输入注册的昵称">
        </div>


        <div class="key">
            <input type="password" pattern="\w{6,18}" title="输入6至18位的字母或数字" id="password" name="paw" placeholder="请输入密码">
        </div>

        <div class="key">
            <input type="password" pattern="\w{6,20}"  id="repaw" name="repaw" placeholder="请确认密码">
        </div>

        <div class="signin">
            <input type="submit" id="submit" value="Register" style="margin-bottom: 20px">
            <input id="but"  type="button" onclick="login()" value="已有账号?进行登录！" style="margin-bottom: 20px">
        </div>

    </form>
</div>

<script>
    var flag = false;
    $(function () {
        <c:if test="${not empty param.timeout}">
        layer.msg('连接超时,请重新登陆!', {
            offset: 0,
            shift: 6
        });
        </c:if>

        if ("${error}") {
            $('#submit').attr('value', "${error}").css('background', 'red');
        }

        if ("${message}") {
            layer.msg('${message}', {
                offset: 0,
            });
        }

        $('.close').on('click', function (c) {
            $('.login-form').fadeOut('slow', function (c) {
                $('.login-form').remove();
            });
        });

        $('#username,#password').change(function () {
            $('#submit').attr('value', 'Login').css('background', '#3ea751');
        });
    });

    function login() {
        location.href="${pageContext.request.contextPath}/login.jsp";
    }

    function checkUsername(user){
        //获取文本框中的值
        var username=$("#username").val();
        //为空跳过,不为空发送异步请求
        if(username!=""){
            $.ajax({
                url:"${pageContext.request.contextPath}/user/repeat",
                type:"POST",
                data:{"username":username},
                dataType:"text",
                success:function(data){
                    if(data=="false"){
                        var re="用户名已存在！！";
                        $("#p1").text(re);
                        $("#p1").addClass("white");
                        flag=false;
                    }else{
                        $("#p1").text("");
                        flag=true;
                    }
                }
            });
        }
    }

    /**
     * check the login form before user login.
     * @returns {boolean}
     */
    function checkLoginForm() {
        var username = $('#username').val();
        var password = $('#password').val();
        var repaw =  $('#repaw').val();
        if (isNull(username) && isNull(password)) {
            $('#submit').attr('value', '请输入账号和密码!!!').css('background', 'red');
            return false;
        }
        if (isNull(username)) {
            $('#submit').attr('value', '请输入账号!!!').css('background', 'red');
            return false;
        }
        if (isNull(password)) {
            $('#submit').attr('value', '请输入密码!!!').css('background', 'red');
            return false;
        }

        if (repaw != password){
            $('#submit').attr('value', '两次密码不一致!!!').css('background', 'red');
            return false;
        }
        if(!flag){
            $('#submit').attr('value', '用户名重复!!!').css('background', 'red');
            return false;
        }

        $('#submit').attr('value', 'Logining~');
        return true;
    }

    /**
     * check the param if it's null or '' or undefined
     * @param input
     * @returns {boolean}
     */
    function isNull(input) {
        if (input == null || input == '' || input == undefined) {
            return true;
        }
        else {
            return false;
        }
    }
</script>
</body>
</html>