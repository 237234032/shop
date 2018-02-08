<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/mobile" %>

<!DOCTYPE html>
<html>
<head>
    <title>移动商城管理系统 - ${tbUser.id == null? "新增" : "编辑"}用户</title>
    <jsp:include page="/WEB-INF/views/includes/mobile/head.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%--自定义标签--%>
    <sys:nav/>
    <sys:menu parentmenu="用户管理" submenu="添加用户"/>

    <%--主内容--%>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                ${tbUser.id == null? "新增" : "编辑"}用户
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i>首页</a></li>
                <li><a href="#">用户管理</a></li>
                <li class="active">${tbUser.id == null? "新增" : "编辑"}用户</li>
            </ol>
        </section>

        <c:if test="${message != null}">
            <div class="box-body">
                <div class="alert alert-danger alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    <i class="icon fa fa-ban"></i>${message}
                </div>
            </div>
        </c:if>

        <!-- Main content -->
        <section class="content">

            <form:form id="userForm" action="/user/list" method="post" class="form-horizontal" modelAttribute="tbUser">
                <div class="box">
                    <div class="box-body">
                        <form:hidden path="id"/>

                        <div class="form-group">
                            <form:label path="username" class="col-sm-2 control-label">用户名</form:label>
                            <div class="col-sm-10">
                                <form:input path="username" class="form-control" placeholder="用户名"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <form:label path="password" class="col-sm-2 control-label">密码</form:label>
                            <div class="col-sm-10">
                                <form:password path="password" class="form-control" placeholder="密码"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <form:label path="phone" class="col-sm-2 control-label">手机号</form:label>
                            <div class="col-sm-10">
                                <form:input path="phone" class="form-control mobile" placeholder="手机号"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <form:label path="email" class="col-sm-2 control-label">邮箱</form:label>
                            <div class="col-sm-10">
                                <form:input path="email" class="form-control" placeholder="邮箱"/>
                            </div>
                        </div>

                        <button type="submit" id="btn" class="btn btn-info pull-right">提交</button>

                    </div>
                    <!-- /.box-footer -->
                </div>
            </form:form>
        </section>
    </div>
    <jsp:include page="/WEB-INF/views/includes/foot.jsp"/>
</div>
<!-- ./wrapper -->
<jsp:include page="/WEB-INF/views/includes/jquery.jsp"/>

<script>
   $(function () {
       UserValidator.validate();
   });
</script>
</body>
</html>