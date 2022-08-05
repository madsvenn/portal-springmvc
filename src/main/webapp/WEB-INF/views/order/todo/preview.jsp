<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Hibiki
  Date: 2022-07-28
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"
%>
<html>
<head>
    <%@include file="../../basepath.jsp"%>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="css/common.css?v=
<%=Math.random()%>"/>
</head>
<body>
<span class="layui-breadcrumb">
 <%-- <a href="">⾸⻚</a>--%>
 <a href="order/todo/list">待办管理</a>
 <a >  处理待办</a>
 </span>
<form class="layui-form" lay-filter="form" action="order/todo/edit"
      method="post">
    <input value="${formData.id}" name="id" type="hidden">
    <div class="layui-form-item">
        <label class="layui-form-label">故障设备</label>
        <div class="layui-input-block">
            <select name="equipmentId" class="layui-select" disabled>
                <option value="" selected></option>
                <c:forEach items="${equipmentList}" var="item">
                    <c:if test="${item.id==formData.equipmentId}">
                        <option value="${item.id}" selected>${item.name}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">故障描述</label>
        <div class="layui-input-block">
            <textarea class="layui-textarea" name="description"
                      placeholder="请输⼊故障描述"
                      readonly
            >${formData.description}</textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea class="layui-textarea" name="remark"
                      placeholder="请输⼊故障备注"
                      readonly
            >${formData.remark}</textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">处理⼈</label>
        <div class="layui-input-block">
            <select class="layui-select" name="targetUserId" disabled>
                <option value="">请选择</option>
                <c:forEach items="${userList}" var="item">
                    <c:if test="${item.id==formData.targetUserId}">
                        <option value="${item.id}" selected>${item.username}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
    </div>
    <% Date date = new Date();

    %>
    <div class="layui-form-item">
        <label class="layui-form-label">故障时间</label>
        <div class="layui-input-block">
            <input class="layui-input" placeholder="请选择时间" readonly
                   type="text" name="problemTime" id="time" value="<fmt:formatDate value="${formData.problemTime}" pattern="yyyy-MM-dd"/>"/>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">故障原因</label>
        <div class="layui-input-block">
 <textarea class="layui-textarea" name="reason"
           placeholder="请输⼊故障原因"
           readonly
 >${formData.reason}</textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">处理结果</label>
        <div class="layui-input-block">
 <textarea class="layui-textarea" name="handleResult"
           placeholder="请输⼊处理结果"
           readonly
 >${formData.handleResult}</textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">⼯单状态</label>
        <div class="layui-input-block">
            <select class="layui-select" name="orderStatusId" disabled>
                <option value="">请选择</option>
                <c:forEach items="${orderStatusList}" var="item">
                    <c:choose>
                        <c:when test="${item.id==formData.orderStatusId}">
                            <option value="${item.id}" selected>${item.statusName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${item.id}">${item.statusName}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
    </div>

</form>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/qs.min.js"></script>
</body>
</html>
