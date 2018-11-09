<%--
  Created by IntelliJ IDEA.
  User: 王运
  Date: 2018/11/9
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="Cbox">
        <table class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th>
                    <input type="checkbox">
                </th>
                <th>商品图片</th>
                <th>商品名称</th>
                <th>商品属性</th>
                <th>商品价格</th>
                <th>商品数量</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="cart" items="${cartList}">
                <tr>
                    <td>
                        <input type="checkbox" onclick="changShfxz(${cart.skuId},this.checked)"
                        ${cart.shfxz == 1?"checked":""}>
                    </td>
                    <td><img src="images/icon_2.jpg" alt=""></td>
                    <td>${cart.skuMch}</td>
                    <td>
                        颜色：<span style='color:#ccc'>白色</span><br>
                        尺码：<span style='color:#ccc'>L</span>
                    </td>
                    <td>${cart.skuJg}</td>
                    <td><input type="text" name="min" value="${cart.tjshl}" style="width:50px;text-align:center"></td>
                    <td>
                        <a href="javascript:deleteCart(${cart.skuId})">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="Cprice">
        <div class="price">总价：${hjSum}</div>
        <div class="jiesuan">结算</div>
    </div>
    <div class="footer">
        <div class="top"></div>
        <div class="bottom"><img src="images/foot.jpg" alt=""></div>
    </div>
</body>
</html>
