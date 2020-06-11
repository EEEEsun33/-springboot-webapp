<html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <title>卖家管理系统后台</title>
    <link rel="stylesheet" href="/sell/css/style.css">
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/sell/css/login.css">
</head>
<style>
    body {
        background-image: url("images/bluebg.jpg");
        background-repeat:no-repeat;
        background-size: 100% 100%;
        background-position: center center;
        overflow: auto;
    }
</style>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script>
    $().ready(function(){
        $(function(){
            $(".nav a").off("click").on("click",function(){
                var index = $(this).index();
                $(this).addClass("on").siblings().removeClass("on");
                $(".contentbox .box").eq(index).addClass("active").siblings().removeClass("active");
            });
        });
    })
</script>
<body>
<div class="container" style="margin-top:100px;">
<div class="section">
    <div class="all">
        <div class="nav">
            <a class="login on">登录</a>
<#--            <a class="login">注册</a>-->
        </div>
        <div class="contentbox">
            <div class="box active">
                <div class="cont-1">
                    <input id="phoneInput" class="icon phone" type="text" name="phone" placeholder="手机号">
                    <input id="pwdInput" class="icon password" name="password" type="password" placeholder="密码">
                    <button class="btn" id="loginBtn">登录</button>
                    <div class="about"><a href="#">忘记密码?&nbsp;&nbsp;<span></span>&nbsp;&nbsp;注册&nbsp;&nbsp;<span></span>&nbsp;&nbsp;</a></div>
                </div>
            </div>
<#--            <div class="box">-->
<#--                <div class="cont-1">-->
<#--                    <input id="registerPhone" class="icon phone" name="phone" type="text" placeholder="手机号">-->
<#--                    <input id="registerPwd" class="icon password" name="password" type="password" placeholder="密码">-->
<#--                    <button id="registerBtn" class="btn">注册</button>-->
<#--                    <div class="about"><a href="#">忘记密码?&nbsp;&nbsp;<span></span>&nbsp;&nbsp;注册&nbsp;&nbsp;<span></span>&nbsp;&nbsp;</a></div>-->
<#--                </div>-->
<#--            </div>-->
        </div>
    </div>
</div>
</div>

</body>
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<#--自动机定义的本地js请求-->
<script src="../../js/login.js"></script>
</html>