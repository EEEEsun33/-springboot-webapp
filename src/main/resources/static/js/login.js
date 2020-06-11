/*!
 * qcl
 */
"use strict";
//# sourceURL=
// DOM 加载完再执行
$(function () {
    //点击登录按钮
    $("#loginBtn").click(function () {
        login();
    });

    $("#registerBtn").click(function () {
        register();
        console.log("点击了注册")
    })

    //管理登录
    function login() {
        let phone = $(" #phoneInput ").val();
        let psw = $(" #pwdInput ").val();
        if (phone == undefined || phone == "") {
            alert("请输入手机号");
            return;
        }
        if (psw == undefined || psw == "") {
            alert("请输入密码");
            return;
        }
        $.ajax({
            url: "/sell/admin/login",
            data: {
                "phone": phone,
                "password": psw
            },
            success: function (data) {
                $("#mainContainer").html(data);
                console.log("登录成功", data);
                window.location.href = "/sell/seller/order/list";
            },
            error: function (err) {
                console.log("登录失败,用户名或者密码不正确", err);
                alert("登录失败,手机号或者密码不正确");
                toastr.error("error!");
            }
        });
    }

    // 管理注册
    function register() {
        let phone = $(" #registerPhone ").val();
        let psw = $(" #registerPwd ").val();
        if (phone == undefined || phone == "") {
            alert("请输入手机号");
            return;
        }
        if (psw == undefined || psw == "") {
            alert("请输入密码");
            return;
        }
        $.ajax({
            url: "/sell/admin/register",
            method: 'POST',
            data: {
                "phone": phone,
                "password": psw
            },
            success: function (data) {
                $("#mainContainer").html(data);
                console.log("注册成功", data);
                window.location.href = "/sell/seller/order/list";
            },
            error: function (err) {
                console.log("注册失败,用户名或者密码不正确", err);
                alert("注册失败,手机号或者密码不正确");
                toastr.error("error!");
            }
        });
    }
});