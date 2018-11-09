<%--
  Created by IntelliJ IDEA.
  User: 王运
  Date: 2018/11/5
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>


    $(function () {
        var yhMch = getCookieById("yhMch")
        $("#yhMchId").html(yhMch)
    })


    function getCookieById(key) {
        var val ="";
        var ck = document.cookie;
        var ck = ck.replace(" ","");
        var ckArr = ck.split(";")
        for (var i= 0 ;i<ckArr.length;i++){
            console.log(ckArr[i])
            var arr = ckArr[i].split("=");
            if (arr[0] == key){
                val = arr[1];
            }
        }
        return val;
    }
</script>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <div class="top">
            <div class="top_text">
                <c:if test="${empty user}">
                    <a href="<%=request.getContextPath()%>/toLoginPage.do">用户登录：<span id="yhMchId"><font color="red"></font></span></a>
                    <a href="/toRegPage.do">用户注册</a>
                </c:if>
                <c:if test="${!empty user}">
                    <a href="<%=request.getContextPath()%>/toLoginPage.do">已登录：<font color="red">${user.yhMch}</font></a>
                    <a href="logout.do">退出登录</a>
                </c:if>
            </div>
        </div>
        <div class="top_img">
            <img src="./images/top_img.jpg" alt="">
        </div>
</body>
</html>
