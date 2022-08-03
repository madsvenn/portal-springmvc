<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Hibiki
  Date: 2022-07-28
  Time: 15:51
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
 <a href="sex/list">机房管理</a>
 <a > 机房修改 </a>
 </span>
<form class="layui-form" lay-filter="form" action="room/edit" method="post">
    <input type="hidden" id="" name="id" value="${formData.id}">

    <div class="layui-form-item">
        <label class="layui-form-label">机房名称</label>
        <div class="layui-input-block">
            <input class="layui-input"
                   required
                   name="name"
                   value="${formData.name}"
                   lay-verify="required"
                   lay-verType="tips"
                   lay-reqText="机房名称不可以为空"
                   placeholder="请输⼊机房名称"
                   autocomplete="off"
                   type="text">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">机房状态</label>
        <div class="layui-input-block">
            <select class="layui-input"
                    required
                    name="status"
                    vocab="${formData.status}"
                    lay-verType="tips"
                    lay-verify="required"
                    lay-reqText="机房状态不可以为空"
                    placeholder="请输⼊机房状态"
            >
                <c:forEach items="${statusList}" var="s" varStatus="status">
                    <c:choose>
                        <c:when test="${s.id==formData.status}">
                            <option value="${s.id}" selected>${s.roomName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${s.id}" >${s.roomName}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

            </select>
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">机房区域</label>
        <div class="layui-input-block">
            <select class="layui-input"
                    required
                    name="areaId"
                    vocab="${formData.areaId}"
                    lay-verType="tips"
                    lay-verify="required"
                    lay-reqText="机房不可以为空"
                    placeholder="请输⼊机房区域"
            >
                <c:forEach items="${roomAreaList}" var="s" varStatus="status">
                    <c:choose>
                        <c:when test="${s.id==formData.areaId}">
                            <option value="${s.id}" selected>${s.areaName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${s.id}" >${s.areaName}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea
                      class="layui-textarea"
                      name="description"
                      lay-verify="required"
                      lay-verType="tips"
                      lay-reqText="设备描述不可以为空"
                      placeholder="请输⼊设备描述"
                      autocomplete="off">${formData.remark}</textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">机房面积</label>
        <div class="layui-input-block">
            <input class="layui-input"
                   required
                   name="size"
                   value="${formData.size}"
                   lay-verify="required"
                   lay-verType="tips"
                   lay-reqText="大小不可以为空"
                   placeholder="请输⼊大小"
                   autocomplete="off"
                   type="text">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">⽴即提交
            </button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/qs.min.js"></script>
</body>
</html>
