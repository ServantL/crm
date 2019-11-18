<%--
  Created by IntelliJ IDEA.
  User: Servant
  Date: 2019/10/15
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html style="height: 100%">
<head>
    <meta charset="utf-8">
</head>
<body style="height: 100%; margin: 0">

<div id="Container"  style="height: 100%; "></div>

<%--<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/echarts-en.min.js"></script>
<script type="text/javascript">
    var legendData = new Array();
    var seriesData = [];
    <c:forEach items="${statisticList}" var="statisticMap">
        var json = {};
        json.name = "${statisticMap['statisticKey']}";
        json.value = "${statisticMap['statisticValue']}";
        legendData.push("${statisticMap['statisticKey']}");
        seriesData.push(json);
    </c:forEach>
    var dom = document.getElementById("Container");
    var myChart = echarts.init(dom);
    // var myChart = require('echarts').init(dom);
    // var app = {};
    // option = null;
    var option = {
        title : {
            text: '${typeName}统计',
            // subtext: '纯属虚构',
            x:'center'
        },
        // 提示框组件
        tooltip : {
            // 触发类型
            trigger: 'item',
            // 提示框浮层内容格式器，支持字符串模板和回调函数两种形式。
            // 饼图、仪表盘、漏斗图: {a}（系列名称），{b}（数据项名称），{c}（数值）, {d}（百分比）
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        // 图外的显示图像中某颜色对应的类型
        legend: {
            orient: 'vertical',
            left: 'left',
            data: legendData
                // ['搜索引擎','直接访问','邮件营销','联盟广告','视频广告']
        },
        // 图中的配置，pie表示饼图
        series : [
            {
                name: '${typeName}',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:seriesData,
                //     {value:335, name:'直接访问'},
                //     {value:310, name:'邮件营销'},
                //     {value:234, name:'联盟广告'},
                //     {value:135, name:'视频广告'},
                //     {value:1548, name:'搜索引擎'}
                // ],
                // 图像样式
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };



    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
</script>
</body>
</html>