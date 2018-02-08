<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/mobile" %>
<!DOCTYPE html>
<html>
<head>
    <title>移动商城管理系统 - 主页</title>
    <jsp:include page="/WEB-INF/views/includes/mobile/head.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">
        <%--自定义标签--%>
        <sys:nav/>
        <sys:menu parentmenu="" submenu=""/>

        <%--主内容--%>
        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                    首页
                    <small>移动端</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content" id="main">

            </section>
        </div>
        <jsp:include page="/WEB-INF/views/includes/foot.jsp"/>
    </div>
    <!-- ./wrapper -->
    <jsp:include page="/WEB-INF/views/includes/mobile/jquery.jsp"/>

    <script>
       $(function () {
           if(UserAgent.isWeChat()){
               var btn = $("<input type='button' value='点击跳转' id='btn'/>")
               $("#main").append(btn)
           }
       })
    </script>
</body>
</html>