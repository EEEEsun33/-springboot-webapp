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
                    <form role="form" method="post" action="/sell/notice/save">
                        <div class="form-group">
                            <label>公告标题</label>
                            <input name="noticeTitle" type="text" class="form-control" value="${(notice.noticeTitle)!""}" />
                        </div>
                        <div class="form-group">
                            <label>公告内容</label>
                            <textarea name="noticeContent" class="form-control" cols="50" rows="10" value="">${notice.noticeContent}</textarea>
                        </div>
                        <input hidden type="text" name="noticeId" value="${(notice.noticeId)!""}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>