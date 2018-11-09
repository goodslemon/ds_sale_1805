<%--
  Created by IntelliJ IDEA.
  User: 王运
  Date: 2018/11/5
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>注册页面</title>
    <link rel="stylesheet" href="css/sign.css"/>
    <script src="js/jquery-1.7.2.min.js"></script>
    <script>
        //判断账号
        var str="必须以字母开头，允许5-16字节，允许字母数字下划线";
        var str1="用户名可用";
        function pdUserName(val){
            var guize =  /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/;
            var b = guize.test(val);
            if (b){
                $("#userNameId").html("<font color='green'>"+str1+"</font>")
            }else{
                $("#userNameId").html("<font color='red'>"+str+"</font>")
            }
        }
        //判断密码
        var str2 = "长度在6~10之间，只能包含字母、数字";
        var str3="密码可用";
        function pdPassWord(val) {
            var guize =/^[A-Za-z0-9]{6,10}$/;
            var b = guize.test(val);
            if (b){
                $("#passWordId").html("<font color='green'>"+str3+"</font>")
            }else{
                $("#passWordId").html("<font color='red'>"+str2+"</font>")
            }
        }
        //再次判断密码
        var str4 = "两次输入密码不一致";
        function pdPassWord2(val) {
            var ps = $("#pwd").val();
            if (ps==val&&val!=""){
                $("#passWordIdT").html("<font color='green'>"+str3+"</font>")
            }else{
                $("#passWordIdT").html("<font color='red'>"+str4+"</font>")
            }
        }
        //验证邮箱
        var str5 = "邮箱可用";
        var str6 = "邮箱格式不正确";
        function pdEmail(val){
            var guize = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
            var b = guize.test(val);
            if (b){
                $("#emailId").html("<font color='green'>"+str5+"</font>")
            }else{
                $("#emailId").html("<font color='red'>"+str6+"</font>")
            }
        }
        //验证手机
        var str7 = "手机号可用";
        var str8 = "手机号格式不正确";
        function pdPhone(val){
            var guize =/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/
            var b = guize.test(val);
            if (b){
                $("#phoneId").html("<font color='green'>"+str7+"</font>")
            }else{
                $("#phoneId").html("<font color='red'>"+str8+"</font>")
            }
        }
    </script>
</head>
<body>
<!--头部-->
<div class="header">
    <a class="logo" href="##"></a>
    <div class="desc">欢迎注册</div>
</div>
<!--版心-->
<form action="reg.do" method="post">
<div class="container">
    <!--京东注册模块-->
    <div class="register">
        <!--用户名-->
        <div class="register-box">
            <!--表单项-->
            <div class="box default">
                <label for="userName">用&nbsp;户&nbsp;名</label>
                <input type="text" id="userName" placeholder="您的账户名和登录名" onblur="pdUserName(this.value)"/>
                <i></i>
            </div>
            <!--提示信息-->
            <div class="tip">
                <span id="userNameId"></span>
            </div>
        </div>
        <!--设置密码-->
        <div class="register-box">
            <!--表单项-->
            <div class="box default">
                <label for="pwd">设 置 密 码</label>
                <input type="password" id="pwd" placeholder="建议至少两种字符组合" onblur="pdPassWord(this.value)"/>
                <i></i>
            </div>
            <!--提示信息-->
            <div class="tip">
                <span id="passWordId"></span>
            </div>
        </div>
        <!--确认密码-->
        <div class="register-box">
            <!--表单项-->
            <div class="box default">
                <label for="pwd2">确 认 密 码</label>
                <input type="password" id="pwd2" placeholder="请再次输入密码" onblur="pdPassWord2(this.value)" />
                <i></i>
            </div>
            <!--提示信息-->
            <div class="tip">
                <span id="passWordIdT"></span>
            </div>
        </div>
        <!--设置密码-->
        <div class="register-box">
            <!--表单项-->
            <div class="box default">
                <label for="email">邮 箱 验 证</label>
                <input type="text" id="email" placeholder="请输入邮箱" onblur="pdEmail(this.value)"/>
                <i></i>
            </div>
            <!--提示信息-->
            <div class="tip">
                <span id="emailId"></span>
            </div>
        </div>
        <!--手机验证-->
        <div class="register-box">
            <!--表单项-->
            <div class="box default">
                <label for="mobile">手 机 验 证</label>
                <input type="text" id="mobile" placeholder="请输入手机号" onblur="pdPhone(this.value)"/>
                <i></i>
            </div>
            <!--提示信息-->
            <div class="tip">
                <span id="phoneId"></span>
            </div>
        </div>
        <!--注册协议-->
        <div class="register-box xieyi">
            <!--表单项-->
            <div class="box default">
                <input type="checkbox" id="ck" />
                <span>我已阅读并同意<a href="##">《京东用户注册协议》</a></span>
            </div>
            <!--提示信息-->
            <div class="tip">
                <i></i>
                <span></span>
            </div>
        </div>
        <!--注册-->
        <button id="btn">注册</button>
    </div>

</div>
</form>
</body>
<script>
    $("#btn").click(function () {
        if ($("#ck").checked){
            location.href="reg.do";
        }else{
            alert("请先阅读京东用户注册协议")
        }
    })

</script>
</html>