<%--
  Created by IntelliJ IDEA.
  User: Hibiki
  Date: 2022-07-28
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
<script id="query-form" type="text/html">
    <form class="layui-form" id="form">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: auto">名称</label>
            <div class="layui-input-inline">
                <input class="layui-input" name="assetTypeName" value="{{d.where.assetTypeName}}" type="text" placeholder="请输⼊类别名称"/>
            </div>
        </div>
        <div class="layui-inline">
            <shiro:hasPermission name="permission:query">
                <button type="button"
                        lay-event="query"
                        class="layui-btn ">查询</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="permission:insert">
                <a href="#" class="layui-btn ">新增</a>
            </shiro:hasPermission>
        </div>
    </form>
</script>

<script type="text/html" id="tool">
    <shiro:hasPermission name="permission:update">
        <a href="#" class="layui-btn layui-btn-warm layui-btn-xs" >修改</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="permission:delete">
        <button type="button" lay-event="delete"
                class="layui-btn layui-btn-danger layui-btn-xs">删除</button>
    </shiro:hasPermission>
</script>

<script type="text/javascript">

    layui.use('table',function(){
        var table = layui.table
        table.init('table',{
            elem: '#t',
            height: 'full-80',
            autoSort: false ,

            toolbar: '#query-form',
            cols:[[
                {field:'id',title:'id',sort: true},
                {field:'assetTypeName',title:'类型名'},
                {title:'操作',templet: '#tool'}
            ]],
            page:true,

            url:'assetType/list/page',

            response: {
                statusCode: 200
            },
            request: {
                pageName: 'pno',
                limitName: 'psize'
            },
            where:{
                assetTypeName:''
            }
        })
        table.on('toolbar(table)',function (obj){
            console.log(obj)
            if (obj.event == 'query'){

                var queryForm = $('#form').serialize()

                var queryFormObj = Qs.parse(queryForm)
                console.log(queryFormObj)

                table.reload('t',{
                    where:queryFormObj
                })
            }
        })

        table.on('sort(table)',function(obj){
            console.log(obj)
            var queryForm = $('#form').serialize()
            var queryFormObj = Qs.parse(queryForm)
            table.reload('t',{
                initSort: obj,
                where:{
                    ...queryFormObj,
                    sortField:obj.field,
                    sortType:obj.type
                }
            })
        })


    })


</script>



<span class="layui-breadcrumb">
 <a href="/assetType/list">类型维护</a>
 <a href="#">类型列表</a>
 </span>
<table id="t" lay-filter="table"></table>

</body>
</html>
