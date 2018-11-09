<%--
  Created by IntelliJ IDEA.
  User: 王运
  Date: 2018/11/5
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script>
    $(function () {
        $.getJSON("json/class_1.js",function (json) {
            $(json).each(function (i,data) {
                $(".nav_mini ul").append('<li onmouseout="getClass2('+data.id+')"><a href="">'+data.flmch1+'</a></li>')
            })
        })
    })

    function getClass2(id) {
        $(".two_nav").empty()
        $.getJSON("json/class_2_"+id+".js",function (json) {
            $(json).each(function (i,data) {
                $(".two_nav").append('<a href="toListPage.do?flbh2='+data.id+'" target="_blank">'+data.flmch2+'</a>')
                $(".two_nav").show();
            })
        })
    }
</script>
<body>
<div class="menu">
    <div class="nav">
        <div class="navs">
            <div class="left_nav">
                全部商品分类
                <div class="nav_mini">
                    <ul>
                        <li>
                            <div class="two_nav">
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <ul>
                <li><a href="">服装城</a></li>
                <li><a href="">美妆馆</a></li>
                <li><a href="">超市</a></li>
                <li><a href="">全球购</a></li>
                <li><a href="">闪购</a></li>
                <li><a href="">团购</a></li>
                <li><a href="">拍卖</a></li>
                <li><a href="">金融</a></li>
                <li><a href="">智能</a></li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
