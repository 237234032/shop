var UserValidator = function () {
    // 规则判断
    var handleValidator = function () {
        $("#userForm").validate({
            errorPlacement: function(error, element) {
                element.parent().append( error );
                element.parent().parent().attr("class","form-group has-error");
            },
            errorElement: "span",
            errorClass: "help-block",

            rules:{
                username: {
                    required:true,
                    minlength:3,
                    maxlength:20,
                    remote: {
                        url: "/user/check",     //后台处理程序
                        type: "post",               //数据发送方式
                        dataType: "json"           //接受数据格式
                    }
                },
                phone:{
                    required:true,
                    remote: {
                        url: "/user/check",     //后台处理程序
                        type: "post",               //数据发送方式
                        dataType: "json"           //接受数据格式
                    }
                },
                email:{
                    required:true,
                    email:true,
                    remote: {
                        url: "/user/check",     //后台处理程序
                        type: "post",               //数据发送方式
                        dataType: "json"           //接受数据格式
                    }
                }
            },

            messages:{
                username: {
                    required:"请输入用户名",
                    minlength:"长度最少3位",
                    maxlength:"长度不能超过20位"
                },
                phone:{
                    required:"请输入手机号",
                },
                email:{
                    required:"请输入邮箱",
                    email:"请输入正确的邮箱格式"
                }
            }
        });
        isSuccess()
    }

    // 判断输入的数据是否唯一
    var isSuccess = function () {

        var inputs = $("#userForm").find("input");
        inputs.each(function () {
            if($(this).attr("type")==="text"){
                $(this).bind("blur",function () {
                    var errorSpan = $(this).parent().find("span")[0];
                    var errorHtml = $(errorSpan).html();
                    if($(this).val().length>0){
                        if(errorHtml == 0||errorSpan == null){
                            $(this).parent().parent().attr("class","form-group has-success");
                            $(errorSpan).remove();
                        }
                    }
                })
            }
        })
    }

    return{
        validate:function () {
            handleValidator()
        }
    }
}();