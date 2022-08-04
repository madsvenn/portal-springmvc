<%--
  Created by IntelliJ IDEA.
  User: Hibiki
  Date: 2022-08-04
  Time: 7:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"
%>
<html>
<head>
    <!-- 引⼊本⻚⾯的basePath⽂件，设置项⽬的根路径 -->
    <%@include file="../../basepath.jsp"%>
    <title>Title</title>
    <!-- 引⼊layui框架的样式⽂件 -->
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css"/>
    <!-- 引⼊全局的⾃定义css⽂件 -->
    <link rel="stylesheet" type="text/css" href="css/common.css?v=<%=Math.random()%>"/>
    <!-- 引⼊layui的js⽂件-->
    <script type="text/javascript" src="layui/layui.js"></script>
    <!-- 引⼊jquery的js⽂件-->
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <!-- 引⼊qs参数处理框架 -->
    <script type="text/javascript" src="js/qs.min.js"></script>
</head>
<body>
<span class="layui-breadcrumb">
 <%-- <a href="">⾸⻚</a>--%>
 <a href="orderStatus/list">订单状态管理</a>
 <a > 状态新增 </a>
 </span>


<!-- 集成表单验证，的表单对象-->
<form class="layui-form" lay-filter="form" action="orderStatus/add" method="post">
    <div class="layui-form-item">
        <label class="layui-form-label">状态名称</label>
        <div class="layui-input-block">
            <input class="layui-input"
                   required
                   name="statusName"
                   lay-verify="required"
                   lay-verType="tips"
                   lay-reqText="状态名称不可以为空"
                   placeholder="请输⼊状态名称"
                   autocomplete="off"
                   type="text">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">⽴即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

</body>
</html>