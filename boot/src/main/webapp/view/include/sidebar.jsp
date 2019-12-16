<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!-- sidebar start -->
<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
    <div class="am-offcanvas-bar admin-offcanvas-bar">
        <ul class="am-list admin-sidebar-list">
            <li><a href="${ctx}/chat/index"><span class="am-icon-commenting"></span> 聊天大厅</a></li>

            <li class="admin-parent">
                <a onclick="getMylist()" class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-cogs"></span> 我加入的聊天室 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
                    <li><a href="${ctx}/${userid}/config"><span class="am-icon-group"></span>聊天室1</a></li>
                </ul>
            </li>

            <li class="admin-parent">
                <a class="am-cf" data-am-collapse="{target: '#collapse-navs'}"><span class="am-icon-cogs"></span> 我创建的聊天室 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-navs">
                    <li><a href="${ctx}/${userid}/config"><span class="am-icon-group"></span> 聊天室2</a></li>
                </ul>
            </li>
            <li><a href="${ctx}/${userid}" class="am-cf"><span class="am-icon-book"></span>我的申请<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
            <li><a href="${ctx}/${userid}" class="am-cf"><span class="am-icon-book"></span> 个人信息<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>



        <%--<li><a href="${ctx}/fiveChess"><span class="am-icon-sign-out"></span> 五子棋</a></li>--%>
        </ul>
        <div class="am-panel am-panel-default admin-sidebar-panel">
            <div class="am-panel-bd">
                <p><span class="am-icon-tag"></span> 公告</p>
                <p>${room.notice}</p>
            </div>
        </div>
    </div>
</div>
<!-- sidebar end -->

<script>
    function getMylist() {
        $.ajax({
            url:"${pageContext.request.contextPath}/chat/addlist",
            type:"POST",
            data:{"userid":${user.userId}},
            dataType:"json",
            success:function(data){

            }
        });
    }
</script>