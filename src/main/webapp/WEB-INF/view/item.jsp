<%--
  Created by IntelliJ IDEA.
  User: 王运
  Date: 2018/11/7
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<script src="/js/jquery-1.7.2.min.js"></script>
<head>
    <meta charset="UTF-8">
    <title>商品详情</title>
    <link rel="stylesheet" type="text/css" href="css/css.css">
</head>
<body>

    <jsp:include page="top.jsp"></jsp:include>
    <jsp:include page="search.jsp"></jsp:include>

<div class="Dbox">
    <div class="box">
        <div class="left">
            <div class="timg"><img src="images/img_5.jpg" alt=""></div>
            <div class="timg2">
                <div class="lf"><img src="images/lf.jpg" alt=""></div>
                <div class="center">
                    <span><img src="images/icon_2.jpg" alt=""></span>
                    <span><img src="images/icon_3.jpg" alt=""></span>
                    <span><img src="images/icon_2.jpg" alt=""></span>
                    <span><img src="images/icon_3.jpg" alt=""></span>
                    <span><img src="images/icon_2.jpg" alt=""></span>
                </div>
                <div class="rg"><img src="images/rg.jpg" alt=""></div>
            </div>
            <div class="goods"><img src="images/img_6.jpg" alt=""></div>
        </div>
        <div class="cent">
            <div class="title">${itmVO.skuMch}</div>
            <div class="bg">
                <p>市场价：<strong>￥${itmVO.jg}</strong></p>
                <p>促销价：<strong>￥${itmVO.jg}</strong></p>
            </div>
            <div class="clear">
                <div class="min_t">选择版本：</div>
                <c:forEach var="sku" items="${skuList}">
                    <div class="min_mx" onclick=func($(this),'0')>
                        <a href="toItemPage.do?skuId=${sku.id}&spuId=${sku.shpId}">
                            ${sku.skuMch}
                        </a>
                    </div>
                </c:forEach>
            </div>
            <div class="clear">
                <div class="min_t" onclick=func($(this),'1')>服务：</div>
                <div class="min_mx" onclick=func($(this),'1')>服务1号1</div>
            </div>
            <div class="clear" style="margin-top:20px;">
                <div class="min_t" style="line-height:36px">数量：</div>
                <div class="num_box">
                    <input type="text" name="num" id="valIpu" value="1" style="width:40px;height:32px;text-align:center;float:left">
                    <div class="rg">
                        <img src="images/jia.jpg" onclick="changNum(1)" id='jia' alt="">
                        <img src="images/jian.jpg" onclick="changNum(2)" id='jian' alt="">
                    </div>
                </div>
            </div>
            <div class="clear" style="margin-top:20px;">
                <img src="images/mai.jpg" alt="">
                <img src="images/shop.jpg"  onclick="saveCart()"  alt="">
            </div>
        </div>
    </div>
</div>
<div class="Dbox1">
    <div class="boxbottom">
        <div class="top">
            <span>
            </span>
            <span>评价</span>
        </div>
        <div class="btm">
            <c:forEach items="${itmVO.avList}" var="av">
                ${av.shxMch}:${av.shxZh}
                <br>
            </c:forEach>
        </div>
    </div>
</div>
<div class="footer">
    <div class="top"></div>
    <div class="bottom"><img src="images/foot.jpg" alt=""></div>
</div>
    <form action="saveCart.do" id="toCartPageForm" method="post">
        <input type="text" name="skuMch" value="${itmVO.skuMch}">
        <input type="text" name="skuJg" value="${itmVO.jg}">
        <input type="text" name="tjshl" value="1">
        <input type="text" name="shfxz" value="1">
        <input type="text" name="shpId" value="${itmVO.shpId}">
        <input type="text" name="skuId" value="${itmVO.id}">
        <input type="text" name="shpTp" value="${itmVO.imgList.get(0).url}">
        <input type="text" name="kcdz" value="${itmVO.kcdz}">
    </form>
</body>
<script>
        function saveCart() {
            $("#toCartPageForm").submit()
        }
        function changNum(flag){
            var num = $("#valIpu")
            //为1就加1
            if (flag ==1){
                num.val(parseInt(num.val())+1)
            }else if (flag ==2){
                //val大于一的时候才能减
                if (num.val()>1){
                    num.val(num.val() -1)
                }
            }
        }
</script>
</html>