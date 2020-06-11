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
                    <table class="table table-bordered table-condensed">
                        <thead>
                            <tr>
                                <th>订单编号</th>
                                <th>买家昵称</th>
                                <th>评价内容</th>
                                <th>评价时间</th>
                            </tr>
                        </thead>
                        <tbody>
                        <#list commentList as comment>
                            <tr>
                                <td>${comment.orderId}</td>
                                <td>${comment.commentName}</td>
                                <td>${comment.commentContent}</td>
                                <td>${comment.createTime}</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>