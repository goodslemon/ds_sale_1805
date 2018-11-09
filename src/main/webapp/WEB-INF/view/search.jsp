<%--
  Created by IntelliJ IDEA.
  User: 王运
  Date: 2018/11/5
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="/js/jquery-1.7.2.min.js"></script>
<head>
    <title>Title</title>
</head>
<body>
<div class="search">
    <div class="logo"><img src="./images/logo.jpg" alt=""></div>
    <div class="search_on">
        <div class="se">
            <input type="text" name="search" class="lf">
            <input type="submit" class="clik" value="搜索">
        </div>
        <div class="se">
            <a href="">取暖神奇</a>
            <a href="">1元秒杀</a>
            <a href="">吹风机</a>
            <a href="">玉兰油</a>
        </div>
    </div>
    <div class="card" onmouseover="selectMiniCart()" onmouseout="outMiniCart()">
        <a href="toCartListPage.do">购物车<div class="num">0</div></a>
        <!--购物车商品-->
        <div id="MiniCartDiv" style="display:none;">

        </div>
    </div>
</div>
</body>
<script>
    function  selectMiniCart(){
        $.post("selectMiniCart.do",function (data) {
            $("#MiniCartDiv").html(data)
        })
        $("#MiniCartDiv").show();
    }

    function outMiniCart() {
        $("#MiniCartDiv").hide();
    }
</script>
</html>
