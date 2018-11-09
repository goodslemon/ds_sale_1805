<%--
  Created by IntelliJ IDEA.
  User: 王运
  Date: 2018/11/7
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="cart_pro">
    <c:forEach items="${cartList}" var="cart">
        <div class="one">
            <img src="images/lec1.png"/>
            <span class="one_name">
                ${cart.skuMch}
            </span>
            <span class="one_prece">
                ￥${cart.skuJg}x${cart.tjshl}<br />
                <br />
                删除
            </span>
        </div>
    </c:forEach>
    <div class="gobottom">
        共<span>${countSum}</span>件商品&nbsp;&nbsp;&nbsp;&nbsp;
        共计￥<span>${sumHj}</span>
        <button class="goprice">去购物车</button>
    </div>
</div>
</body>
</html>
