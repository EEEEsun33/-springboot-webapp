<html>
<#include "../common/header.ftl">
<body>
<style>
    /*#upload {*/
    /*    opacity: 0;*/
    /*    background-color: #1c1c1c;*/
    /*    color: white;*/
    /*}*/
</style>
<div id="wrapper" class="toggled">
    <!--侧边栏sidebar-->
    <#include "../common/nav.ftl">

    <!--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/seller/product/save">
                        <div class="form-group">
                            <label>名称</label>
                            <input name="productName" type="text" class="form-control" value="${(productInfo.productName)!""}" />
                        </div>
                        <div class="form-group">
                            <label>价格</label>
                            <input name="productPrice" type="text" class="form-control" value="${(productInfo.productPrice)!""}" />
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <input name="productStock" type="number" class="form-control" value="${(productInfo.productStock)!""}" />
                        </div>
                        <div class="form-group">
                            <label>描述</label>
                            <input name="productDescription" type="text" class="form-control" value="${(productInfo.productDescription)!""}" />
                        </div>
                        <div class="form-group">
                            <label>图片</label>
<#--                            <img width="100" height="100" border="0" src="${(productInfo.productIcon)!""}">-->
<#--                            <input name="productIcon" type="file" class="form-control" value="${(productInfo.productIcon)!""}" />-->
                            <input type="file" name="productIcon" id="pstimg" class="form-control" value="${(productInfo.productIcon)!""}"/>
                            <img id="loc_img" width="100" height="100" border="0" src="${(productInfo.productIcon)!""}"/>
                        </div>
                        <div class="form-group">
                            <label>类别</label>
                            <select name="categoryId" class="form-control">
                                <#list categoryList as category>
<#--                                    <option value="${category.categoryType}"-->
<#--                                            <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType> selected </#if>>-->
<#--                                        ${category.categoryName}-->
<#--                                    </option>-->
                                    <option value="${category.categoryId}"
                                            <#if (productInfo.categoryId)?? && productInfo.categoryId== category.categoryId> selected </#if>>
                                        ${category.categoryName}
                                    </option>
                                </#list>
                            </select>
                        </div>
                        <input hidden type="text" name="productId" value="${(productInfo.productId)!""}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script>
    $(function(){
        $("#pstimg").change(function(){
            var file = this.files[0];
            alert("文件大小:"+(file.size / 1024).toFixed(1)+"kB");
            if (window.FileReader) {
                var reader = new FileReader();
                reader.readAsDataURL(file);
                //监听文件读取结束后事件
                reader.onloadend = function (e) {
                    showXY(e.target.result,file.fileName);
                };
            }
        });
    });
    function showXY(source){
        var img = document.getElementById("loc_img");
        img.src = source;
        alert("Width:"+img.width+", Height:"+img.height);
    }
</script>
</html>