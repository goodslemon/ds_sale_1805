<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 王运
  Date: 2018/11/6
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="Sbox">
    <c:forEach items="${skuList}" var="sku">
        <div class="list">
            <a href="toItemPage.do?skuId=${sku.id}&spuId=${sku.shpId}" target="_blank">
                <div class="img"><img src="images/img_4.jpg" alt=""></div>
                <div class="price">¥${sku.jg}</div>
                <div class="title">${sku.skuMch}</div>
            </a>
        </div>
    </c:forEach>
</div>
</body>
</html>
