
<%--
  Created by IntelliJ IDEA.
  User: Hibiki
  Date: 2022-07-28
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <style type="text/css">
        #equipment-img img{
            width: 150px;
            height: 150px;
            border: 1px dashed #444;
            border-radius:7px ;
            object-fit: contain;
        }
    </style>
</head>
<body>
<span class="layui-breadcrumb">
 <%-- <a href="">⾸⻚</a>--%>
 <a href="sex/list">设备管理</a>
 <a > 设备新增 </a>
 </span>


<!-- 集成表单验证，的表单对象-->
<form class="layui-form" lay-filter="form" action="eq/add" method="post">
    <div class="layui-form-item">
        <label class="layui-form-label">设备名称</label>
        <div class="layui-input-block">
            <input class="layui-input"
                   required
                   name="name"
                   lay-verify="required"
                   lay-verType="tips"
                   lay-reqText="设备名称不可以为空"
                   placeholder="请输⼊设备名称"
                   autocomplete="off"
                   type="text">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">设备编号</label>
        <div class="layui-input-block">
            <input class="layui-input"
                   required
                   name="equipmentNo"
                   lay-verify="required"
                   lay-verType="tips"
                   lay-reqText="设备编号不可以为空"
                   placeholder="请输⼊设备编号"
                   autocomplete="off"
                   type="text">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">设备品牌</label>
        <div class="layui-input-block">
            <select
                    name="brandId"
                    class="layui-select"
                    lay-verify="required"
                    lay-verType="tips"
                    lay-reqText="设备品牌不可以为空"
                    placeholder="请选择设备品牌"
            >
                <option value="">请选择</option>
                <c:forEach items="${equipmentBrandList}" var="item">
                    <option value="${item.id}">${item.brandName}</option>
                </c:forEach>
            </select>

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">设备⼚家</label>
        <div class="layui-input-block">
            <input class="layui-input"
                   required
                   name="factory"
                   lay-verify="required"
                   lay-verType="tips"
                   lay-reqText="设备⼚家不可以为空"
                   placeholder="请输⼊设备⼚家"
                   autocomplete="off"
                   type="text">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">设备图⽚</label>
        <div class="layui-input-block">
            <input name="img"
                   style="visibility: hidden;height: 0;width: 0"
                   lay-verify="required"
                   lay-verType="tips"
                   lay-reqText="设备图⽚不可以为空"
                   autocomplete="off"
            />
            <a id="equipment-img" target="_blank">
                <img />
            </a>
            <button type="button" class="layui-btn" id="upload">
                <i class="layui-icon">&#xe67c;</i>上传图⽚
            </button>

            <button type="button" class="layui-btn layui-btn-danger" id="del-img">删除图⽚
            </button>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">设备描述</label>
        <div class="layui-input-block">
 <textarea
         class="layui-textarea"
         name="description"
         lay-verify="required"
         lay-verType="tips"
         lay-reqText="设备描述不可以为空"
         placeholder="请输⼊设备描述"
         autocomplete="off"
 ></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">设备备注</label>
        <div class="layui-input-block">
         <textarea
         class="layui-textarea"
         name="remark"
         placeholder="请输⼊设备备注"
         autocomplete="off"
         ></textarea>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">⽴即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script type="text/javascript">
    layui.use('form',function(){
        var upload = layui.upload;
        var uploadInst = upload.render({
            elem: '#upload' //绑定元素
            ,url: 'file/upload' //上传接⼝
            ,done: function(res){
                //上传完毕回调
                console.log(res);
                if (res.code == 200){
                    $("#equipment-img").prop("href",res.data.url);

                    $("#equipment-img img").prop('src',res.data.url);

                    $('[name="img"]').val(res.data.url);

                }
            }
            ,error: function(){
                //请求异常回调
                console.log('error')
            },

        });

        $('#del-img').on('click',function(){
            console.log('del');
            //调⽤删除接⼝
            $.ajax({
                url:'file/delete',
                method:'post',
                data:{
                    url:$('[name="img"]').val()
                },
                success(res){
                    if(res.code == 200){
                        //如果删除成功就执⾏重制图⽚预览部分组件
                        $("#equipment-img").removeProp("href");
                        $("#equipment-img img").prop('src',"");
                        $('[name="img"]').val("")
                    }
                }
            });

            $('#equipment-img img').on("error",function () {
                $(this).prop('src','image/no-img.jpg')
            })
        });


    })
</script>

</body>
</html>
