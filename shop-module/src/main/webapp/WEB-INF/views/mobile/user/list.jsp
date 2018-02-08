<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/mobile" %>
<!DOCTYPE html>
<html>
<head>
    <title>移动商城管理系统 - 用户列表</title>
    <jsp:include page="/WEB-INF/views/includes/mobile/head.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%--自定义标签--%>
    <sys:nav/>
    <sys:menu parentmenu="用户管理" submenu="用户列表"/>

    <%--主内容--%>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                用户列表
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i>首页</a></li>
                <li><a href="#">用户管理</a></li>
                <li class="active">用户列表</li>
            </ol>
        </section>

        <div class="modal modal-danger fade" id="modal-danger">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span></button>
                        <h4 class="modal-title">提示...</h4>
                    </div>
                    <div class="modal-body">
                        <p id="p"> </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline pull-left" data-dismiss="modal">取消</button>
                        <a href="" id="confirm" class="btn btn-outline">确定</a>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>

        <c:if test="${message != null}">
            <div class="box-body">
                <div class="alert alert-success alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    <i class="icon fa fa-check"></i>${message}
                </div>
            </div>
        </c:if>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">用户列表</h3>

                    <div class="box-tools">
                        <div class="input-group input-group-sm" style="width: 150px;">
                            <input type="text" name="table_search" class="form-control pull-right" placeholder="搜索...">

                            <div class="input-group-btn">
                                <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="box-body table-responsive no-padding">
                        <table class="table table-bordered">
                            <tbody>
                            <tr>
                                <th>用户名</th>
                                <th>手机号</th>
                                <th>邮箱</th>
                                <th>创建日期</th>
                                <th>修改日期</th>
                                <th>编辑</th>
                            </tr>
                            <c:forEach items="${tbUsers}" var="tbUser">
                                <tr>
                                    <td>${tbUser.username}</td>
                                    <td>${tbUser.phone}</td>
                                    <td>${tbUser.email}</td>
                                    <td><fmt:formatDate value="${tbUser.created}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td><fmt:formatDate value="${tbUser.updated}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td>
                                        <a href="/user/form?id=${tbUser.id}" class="btn btn-info btn-xs"><i class="fa fa-fw fa-edit"></i>修改</a>|
                                        <a href="javascript:void(0)" onclick="del('${tbUser.id}','${tbUser.username}')" class="btn btn-danger btn-xs" data-toggle="modal" data-target="#modal-danger">
                                            <i class="fa fa-fw fa-times-circle"></i>删除
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div class="row">
                        <div class="col-sm-7">
                            <div class="dataTables_paginate paging_simple_numbers" id="example2_paginate">
                                <ul class="pagination">
                                    <li class="paginate_button previous disabled" id="example2_previous">
                                        <a href="#" aria-controls="example2" data-dt-idx="0" tabindex="0">上一页</a>
                                    </li>
                                    <li class="paginate_button active">
                                        <a href="#" aria-controls="example2" data-dt-idx="1" tabindex="0">1</a>
                                    </li>
                                    <li class="paginate_button next" id="example2_next">
                                        <a href="#" aria-controls="example2" data-dt-idx="7" tabindex="0">下一页</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="/WEB-INF/views/includes/foot.jsp"/>
</div>
<!-- ./wrapper -->
<jsp:include page="/WEB-INF/views/includes/jquery.jsp"/>
<script>
    function del(id,username){
        $("#p").text("确定删除"+username+"吗？");

        // 确认删除
        $("#confirm").click(function () {
            $("#confirm").prop("href","/user/delete?id="+id);
        });
    }
</script>
</body>
</html>
