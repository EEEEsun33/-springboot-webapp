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
                            <th>公告标题</th>
                            <th>创建时间</th>
                            <th colspan="3">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list notices as notice>
                            <tr>
                                <td>${notice.noticeTitle}</td>
                                <td>${notice.createTime}</td>
                                <td>
                                    <a href="/sell/notice/detail?noticeId=${notice.noticeId}">查看详情</a>
                                </td>
                                <td>
                                    <a href="/sell/notice/edit?noticeId=${notice.noticeId}">修改</a>
                                </td>
                                <td>
                                    <a href="/sell/notice/delete?noticeId=${notice.noticeId}" onclick="return confirm('你确定要删除此公告吗？')">删除</a>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>