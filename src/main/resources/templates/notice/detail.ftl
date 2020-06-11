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
                    <div class="col-md-8 col-md-offset-2">
                        <h2>${noticeDetail.noticeTitle}</h2>
                        <p>${noticeDetail.createTime}</p>
                        <div>${noticeDetail.noticeContent}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>