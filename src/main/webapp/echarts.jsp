<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/29
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <script src="echarts.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/echarts.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    window.onload=function () {
        var name=[];
        var values=[];
    $.ajax({
        type:"GET",
        url:"${pageContext.request.contextPath}/echarts/data",
        success:function (msg) {
            for (key in msg){
                name.push(key);
                values.push(msg[key])
            }

            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: 'ECharts 入门示例'
                },
                tooltip: {},
                legend: {
                    data:['销量']
                },
                xAxis: {
                    data: name
                },
                yAxis: {},
                series: [{
                    name: '销量',
                    type: 'bar',
                    data: values
                },{
                    name: '销量',
                    type: 'line',
                    data: values
                }

                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }


    });


    }
</script>
</body>
</html>