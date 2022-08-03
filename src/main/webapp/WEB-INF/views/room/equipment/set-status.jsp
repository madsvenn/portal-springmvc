
<%--
  Created by IntelliJ IDEA.
  User: Hibiki
  Date: 2022-08-03
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
 <a href="sex/list">设备管理</a>
 <a > 设备修改 </a>
 </span>
<form class="layui-form" lay-filter="form" action="sex/edit" method="post">
    <input type="hidden" id="" name="id" value="${formData.id}">
    <div class="layui-form-item">
        <label class="layui-form-label">设备名称</label>
        <div class="layui-input-block">
            <input class="layui-input"
                   required
                   name="name"
                   value="${formData.name}"
                   lay-verify="required"
                   lay-verType="tips"
                   lay-reqText="设备名称不可以为空"
                   placeholder="请输⼊设备名称"
                   autocomplete="off"
                   type="text">
        </div>
    </div>

    <!-- 需要在本⻚引⼊jstl的 c标签 -->
    <div class="layui-form-item">
        <label class="layui-form-label">设备状态</label>
        <div class="layui-input-block">
            <select name="statusId" lay-verify="required"
                    lay-verType="tips"
                    lay-reqText="设备状态不可以为空" >
                <option value="">请选择</option>
                <c:forEach items="${equipmentStatusList}" var="item">
                    <option value="${item.id}" ${item.id == formData.statusId?'selected':''}
                    >${item.statusName}</option>
                </c:forEach>
            </select>
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