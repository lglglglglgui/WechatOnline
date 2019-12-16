<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>chatOnline | 聊天大厅</title>
    <jsp:include page="include/commonfile.jsp"/>
</head>
<body>
<jsp:include page="include/header.jsp"/>
<div class="am-cf admin-main">
    <jsp:include page="include/sidebar.jsp"/>

    <!-- content start -->
    <div class="admin-content">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">聊天列表</strong> /${length} <small></small></div>
        </div>
        <div class="am-tabs am-margin" data-am-tabs>
            <ul class="am-tabs-nav am-nav am-nav-tabs">
                <c:set var="flag" value="${true}" ></c:set>
                 <c:forEach items="${list}" var="room"  varStatus="status">
                     <c:choose>
                         <c:when test="${flag}">
                             <li class="am-active"><a href="#tab0">${room.roomname}</a></li>
                             <c:set var="flag" value="${false}"></c:set>
                         </c:when>
                         <c:otherwise>
                             <li><a href="#tab${status.index}">${room.roomname}</a></li>
                         </c:otherwise>
                     </c:choose>
                </c:forEach>
            </ul>
            <div class="am-tabs-bd">
                   <c:forEach items="${list}" var="room"  varStatus="status">
                       <c:choose>
                           <c:when test="${empty Mymap[room.roomid]}">
                               <c:set var="count" value="${0}"></c:set>
                           </c:when>
                           <c:otherwise>
                               <c:set var="count" value="${Mymap[room.roomid]}"></c:set>
                           </c:otherwise>
                       </c:choose>
                       <c:choose>
                               <c:when test="${!flag}">
                                   <div class="am-tab-panel am-fade am-in am-active" id="tab0">
                                       <hr>
                                       <blockquote>
                                           <p><img src="/chatOnline${room.roomheadfile}"></p>
                                           <p>${room.explain}</p>
                                           <p>在线人数: ${count}/${room.number}</p>
                                           <p><button onclick="chat(${room.roomid})">加入聊天</button></p>
                                       </blockquote>
                                       <c:set var="flag" value="${true}"></c:set>
                                   </div>
                               </c:when>
                               <c:otherwise>
                                   <div class="am-tab-panel am-fade am-in" id="tab${status.index}">
                                       <hr>
                                       <blockquote>
                                           <p><img src="/chatOnline${room.roomheadfile}"></p>
                                           <p>${room.explain}</p>
                                           <p>在线人数: ${count}/${room.number}</p>
                                           <p><button onclick="chat(${room.roomid})">加入聊天</button></p>
                                       </blockquote>
                                   </div>
                               </c:otherwise>
                           </c:choose>
                   </c:forEach>
            </div>
        </div>
        <!-- content end -->
    </div>
    <a href="#" class="am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}">
        <span class="am-icon-btn am-icon-th-list"></span>
    </a>
    <jsp:include page="include/footer.jsp"/>

        <script>
            function  chat(roomid) {
                window.location.href="${pageContext.request.contextPath}/chat/room/"+roomid;
            }
        </script>
</body>
</html>
