<%--
  Created by IntelliJ IDEA.
  User: 王运
  Date: 2018/11/6
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="Sscreen">
    <div class="title">
        平板电视 商品筛选 共1205个商品
    </div>
    <div id="param_Div"></div>
    <c:forEach items="${attrList}" var="attr">
        <div class="list">
            <a href="">${attr.shxmMch}</a>
            <c:forEach items="${attr.valueList}" var="val">
                <a href="javascript:skuListByAttr(${attr.id},${val.id})">${val.shxzh}${val.shxzhMch}</a>
            </c:forEach>
        </div>
    </c:forEach>
    <div class="list">
        <span class="list_span" id="list_beas">销量</span>
        <span class="list_span">价格</span>
        <span class="list_span">评论数</span>
        <span class="list_span">上架时间</span>
    </div>
</div>
</body>
</html>
