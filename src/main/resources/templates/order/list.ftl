<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <!--侧边栏sidebar-->
    <#include "../common/nav.ftl">

    <!--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <nav class="navbar navbar-default" role="navigation">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse"
                                    data-target="#bs-example-navbar-collapse-1"><span
                                        class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span
                                        class="icon-bar"></span><span class="icon-bar"></span></button>
                            <a class="navbar-brand" href="/sell/seller/order/list">珊珊来吃</a>
                        </div>

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <li class="active">
                                    <a href="/sell/seller/order/list">全部订单</a>
                                </li>
                            </ul>
                            <form class="navbar-form navbar-left" role="search" action="/sell/seller/order/search" method="post">
                                <div class="form-group">
                                    <input type="text" class="form-control" name="search"/>
                                </div>
                                <button type="submit" class="btn btn-default">查询订单</button>
                            </form>
                        </div>

                    </nav>
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单编号</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTOPage.content as orderDTO>
                            <tr>
                                <td>${orderDTO.orderId}</td>
                                <td>${orderDTO.buyerName}</td>
                                <td>${orderDTO.buyerPhone}</td>
                                <td>${orderDTO.buyerAddress}</td>
                                <td>${orderDTO.orderAmount}</td>
                                <td>${orderDTO.getOrderStatusEnum().message}</td>
                                <td>${orderDTO.getPayStatusEnum().message}</td>
                                <td>${orderDTO.createTime}</td>
                                <td>
                                    <a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">订单详情</a>
                                </td>
                                <td>
                                    <#if orderDTO.getOrderStatusEnum().message == "新订单已支付">
                                        <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消订单</a>
                                    </#if>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <!--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${currentPage-1}&size=${size}">上一页</a></li>
                        </#if>

                        <#list 1..orderDTOPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>

                        <#if currentPage gte orderDTOPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${currentPage+1}&size=${size}">下一页</a></li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<!--弹窗-->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">提醒</h4>
            </div>
            <div class="modal-body">你有新的订单！</div>
            <div class="modal-footer">
                <button onclick="javascript:document.getElementById('notice').pause()" type="button"
                        class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">查看订单</button>
            </div>
        </div>
    </div>
</div>

<!--播放音乐-->
<audio id="notice" loop="loop">
    <source src="/sell/mp3/yinxiao1323.mp3" type="audio/mpeg"/>
</audio>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script>
    var websocket = null;
    if ('WebSocket' in window) {
        websocket = new WebSocket('ws://localhost:8080/sell/webSocket');
        // 执行上面语句之后，客户端就会与服务器进行连接。
    } else {
        alert('该浏览器不支持websocket!');
    }
    // 例对象的onopen属性，用于指定连接成功后的回调函数。
    websocket.onopen = function (event) {
        console.log('建立连接');
    }
    // 实例对象的onclose属性，用于指定连接关闭后的回调函数。
    websocket.onclose = function (event) {
        console('连接关闭');
    }
    // 实例对象的onmessage属性，用于指定收到服务器数据后的回调函数。
    websocket.onmessage = function (event) {
        console.log('收到消息：' + event.data);
        //弹窗提醒，播放音乐
        $('#myModal').modal('show');

        document.getElementById('notice').play();

    }
    // 实例对象的onerror属性，用于指定报错时的回调函数。
    websocket.onerror = function () {
        alert('websocket通信发生错误！');
    }

    window.onbeforeunload = function () {
        websocket.close();
    }
</script>

</body>
</html>