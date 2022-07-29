<%--
  Created by IntelliJ IDEA.
  User: Hibiki
  Date: 2022-07-29
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
 <a href="dept/list?pid=${pid}">部⻔管理</a>
 <a > 部⻔新增 </a>
 </span>
<form class="layui-form" lay-filter="form" action="dept/add" method="post">
    <!-- 隐藏提交pid保证层级-->
    <input type="hidden" name="pid" value="${pid}"/>
    <div class="layui-form-item">
        <label class="layui-form-label">部⻔名称</label>
        <div class="layui-input-block">
            <!--
            这⾥介绍⼀下默认的表单验证
            lay-verify代表表单验证触发的验证内容
            lay-verType代表提示形式
            lay-reqText代表提示⽂字
            -->
            <input class="layui-input"
                   required
                   name="name"
                   lay-verify="required"
                   lay-verType="tips"
                   lay-reqText="部⻔名称不可以为空"
                   placeholder="请输⼊部⻔名称"
                   autocomplete="off"
                   type="text">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">允许下级</label>
<%--        开启允许下级--%>
        <div class="layui-input-block"
             lay-verify="isLeaf"
             lay-verType="tipe">
            <input type="radio" name="isLeaf"
                    value="0" title="允许">
            <input type="radio" name="isLeaf"
                   value="1" title="不允许">
        </div>

    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">部⻔图标</label>
        <div class="layui-input-block">
            <select name="icon"
                    required
                    lay-verify="required"
                    lay-verType="tips"
                    lay-reqText="请选择图标"
            >
                <option value="">请选择</option>
                <c:forEach items="${iconList}" var="icon">
                <option value="${icon}">${icon}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">⽴即提交
            </button>
            <button type="reset" class="layui-btn layui-btn-primary">重
                置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/qs.min.js"></script>
<script type="text/javascript">
    layui.use('form',function () {
        var form = layui.form;
        form.verify({
            isLeaf:function (value,item) {

                var arr = [];
                $('[name="isLeaf"]').each(function (index,item) {
                    arr.push(item.checked)
                });
                //代表没选择
                if (arr[0] === arr[1]) {
                    return "请至少选择一项"
                }else{
                    return  false
                }
            }
        })
    })
</script>
</body>
</html>
