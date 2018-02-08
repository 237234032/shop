<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>商城系统 - 登录界面</title>
    <jsp:include page="/WEB-INF/views/includes/head.jsp"/>
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="#"><b>商城后台管理系统</b></a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p id="box" class="login-box-msg">欢迎登录商城后台管理系统</p>

        <c:if test="${message != null}">
            <div class="alert alert-danger alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h5>${message}</h5>
            </div>
        </c:if>

        <form action="/login" method="post">
            <div class="form-group has-feedback">
                <input name="loginID" value="${dto.loginID}" type="text" class="form-control" placeholder="账号">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>

            <div class="form-group has-feedback">
                <input name="loginPWD" value="${dto.loginPWD}" type="password" class="form-control" placeholder="密码">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>

            <div class="form-group">
                <input id="validateCode" name="validateCode" type="text" class="form-control pull-left" placeholder="验证码" style="width: 170px;">
                <img id="imgValidateCode" src="/validate/code" style="cursor: pointer;" />
            </div>

            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <label>
                            <input name="remember" ${dto.remember} type="checkbox"> 记住密码
                        </label>
                    </div>
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" id="bt" class="btn btn-primary btn-block btn-flat">登录</button>
                </div>
                <!-- /.col -->
            </div>
        </form>

        <!-- /.social-auth-links -->

        <a href="#">忘记密码？</a>

        <input id="text" type="text" value="http://10.3.134.76/login" /><br />
        <div id="qrcode"></div>
    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->
<jsp:include page="/WEB-INF/views/includes/jquery.jsp"/>
<!-- iCheck -->
<script src="/static/plugins/iCheck/icheck.min.js"></script>

<script>
    $(function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });

        var qrcode = new QRCode(document.getElementById("qrcode"), {
            width : 100,
            height : 100
        });

        function makeCode () {
            var elText = document.getElementById("text");
            if (!elText.value) {
                alert("Input a text");
                elText.focus();
                return;
            }
            qrcode.makeCode(elText.value);
        }

        makeCode();

        $("#text").on("blur", function () {
            makeCode();
        }).on("keydown", function (e) {
            if (e.keyCode == 13) {
                makeCode();
            }
        });

        // 验证码切换
        $("#imgValidateCode").bind("click", function () {
            $(this).attr("src", "/validate/code?" + Math.random());
        });
    });
</script>
</body>
</html>