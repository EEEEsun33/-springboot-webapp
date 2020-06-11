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
                    <canvas class="js-chartjs-bars"></canvas>
                </div>
                <div class="col-md-12 column">
                    <canvas class="js-chartjs-lines"></canvas>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="../../js/Chart.js"></script>
<script>

    $(document).ready(function(e) {
        const $dashChartBarsCnt = jQuery('.js-chartjs-bars')[0].getContext('2d');
        const $dashChartLinesCnt = jQuery( '.js-chartjs-lines' )[0].getContext( '2d' );

        let productArray = [];
        let salesArray = [];
        <#if monthlySales??>
        <#list monthlySales as item>
        productArray.push("${item.productName}");
        salesArray.push("${item.count}");
        </#list>
        </#if>

        const top5Product = productArray.slice(-5);
        console.log("销售前五名为" + top5Product);

        let buyerArray = [];
        let sumArray = [];
        <#if top5Buyers??>
        <#list top5Buyers as item>
        buyerArray.push("${item.buyerName}");
        sumArray.push("${item.sum}");
        </#list>
        </#if>

        const $dashChartBarsData = {
            labels: buyerArray,
            datasets: [
                {
                    label: '本月消费最高顾客',
                    borderWidth: 1,
                    borderColor: 'rgba(0,0,0,0)',
                    backgroundColor: 'rgba(51,202,185,0.5)',
                    hoverBackgroundColor: "rgba(51,202,185,0.7)",
                    hoverBorderColor: "rgba(0,0,0,0)",
                    data: sumArray
                }
            ]
        };

        var $dashChartLinesData = {
            labels: productArray,
            datasets: [
                {
                    label: '商品月销量',
                    data: salesArray,
                    borderColor: '#358ed7',
                    backgroundColor: 'rgba(53, 142, 215, 0.175)',
                    borderWidth: 1,
                    fill: false,
                    lineTension: 0.5
                }
            ]
        };

        new Chart($dashChartLinesCnt, {
            type: 'line',
            data: $dashChartLinesData,
        });

        new Chart($dashChartBarsCnt, {
            type: 'bar',
            data: $dashChartBarsData
        });

    })
</script>
</html>