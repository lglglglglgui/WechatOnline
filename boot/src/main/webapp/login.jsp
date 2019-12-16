<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
    <title>chatOnline | 登陆</title>
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
    <form id="login-form" action="<%=path%>/user/login" method="post" onsubmit="return checkLoginForm()">
        <div class="key">
            <input type="text" value="${user.username}" id="username" name="username" placeholder="请输入账号">
        </div>

        <div class="key">
            <input type="password" value="${user.paw}" id="password" name="paw" placeholder="请输入密码">
        </div>

        <div class="key" style=" margin:20px 0px; color: #9199aa;font-size: 18px;">
            <input type="radio" value="0" name="role" checked="checked">  用户
            <input type="radio" value="1" name="role"> 管理员
        </div>

       <div class="signin">
            <input type="submit" id="submit" value="Login" class="am-btn am-btn-xs am-btn-danger" style="margin-bottom: 20px">
            <input id="but"  type="button" onclick="register()" value="没有账号?进行注册！" class="am-btn am-btn-xs am-btn-danger" style="margin-bottom: 20px">
        </div>
    </form>
</div>

<script>
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

    /**
     * check the login form before user login.
     * @returns {boolean}
     */

    function register() {
        location.href="${pageContext.request.contextPath}/view/register.jsp";
    }
    function checkLoginForm() {
        var username = $('#username').val();
        var password = $('#password').val();
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