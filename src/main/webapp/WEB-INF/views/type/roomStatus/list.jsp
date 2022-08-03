<%--
  Created by IntelliJ IDEA.
  User: Hibiki
  Date: 2022-08-03
  Time: 18:23
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
                <input class="layui-input" name="roomName" value="{{d.where.roomName}}" type="text" placeholder="请输⼊状态名称"/>
            </div>
        </div>
        <div class="layui-inline">
            <!-- 查询按钮需要出发事件所以使⽤button lay-event是出发事件的⼀个key后续使⽤-->
            <shiro:hasPermission name="permission:query">
                <button type="button"
                        lay-event="query"
                        class="layui-btn ">查询</button>
            </shiro:hasPermission>
            <!-- 新增按钮会跳转⻚⾯所以这⾥使⽤a标签超链接按钮来做展示-->
        </div>
    </form>
</script>


<script type="text/javascript">
    //利⽤layui的api初始化table组件
    layui.use('table',function(){
        //获取表格对象
        var table = layui.table;
        //加载静态列表的⽅式，第⼀个参数table代表table上设置的lay-filter的值
        //第⼆个参数按照官⽅⽂档传⼊
        table.init('table',{
            //表格的选择器，按照css选择器的⽅式传⼊，这⾥使⽤了table的id
            elem: '#t',
            //表格的⾼度，可以设置数字代表固定值，使⽤full-数字代表动态设置⾼度
            height: 'full-80',
            //代表默认不做静态数据的排序，这⾥是开启后台服务排序的意思
            autoSort: false ,
            //表格的表头设置，需要使⽤⼆维数组
            //field代表每⼀列的属性值，与java中的实体对象Sex⼀⼀对应
            //title代表表头展示的内容，sort代表开启排序

            toolbar: '#query-form',
            cols:[[
                {field:'id',title:'主键',sort: true},
                {field:'roomName',title:'状态名称'},
                {title:'操作',templet: '#tool'}
            ]],
            // data:[
            //     {id:'1',sexName:'男'},
            //     {id:'2',sexName: '女'}
            // ],
            page:true,

            url:'room_status/list/page',

            response: {
                //将返回的默认成功状态码定义为200
                statusCode: 200
            },
            request: {
                pageName: 'pno', //⻚码的参数名称，默认：page
                limitName: 'psize' //每⻚数据量的参数名，默认：limit
            },
            where:{
                roomName:''
            }
        });
        table.on('toolbar(table)',function (obj){
            console.log(obj);
            if (obj.event === 'query'){

                var queryForm = $('#form').serialize();

                var queryFormObj = Qs.parse(queryForm);
                console.log(queryFormObj);

                table.reload('t',{
                    where:queryFormObj,
                    page:{curr:1}
                })
            }
        });

        table.on('sort(table)',function(obj){
            console.log(obj);
            //获取当前表单数据
            var queryForm = $('#form').serialize();
            var queryFormObj = Qs.parse(queryForm);
            table.reload('t',{
                initSort: obj,
                where:{
                    //这⾥是js的es6写法可以快捷合并对象，主要是为了防⽌切换排序条件的时候当前查询条件消失
                    ...queryFormObj,
                    //传⼊排序依据和排序⽅式
                    sortField:obj.field,
                    sortType:obj.type
                }
            })
        });



    })


</script>


<span class="layui-breadcrumb">
 <a href="room_status/list">机房状态维护</a>
 <a href="room_status/list">机房状态列表</a>
 </span>
<table id="t" lay-filter="table"></table>

</body>
</html>
