<%--
  Created by IntelliJ IDEA.
  User: 王运
  Date: 2018/11/6
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<script src="/js/jquery-1.7.2.min.js"></script>
<head>
    <meta charset="UTF-8">
    <title>检索</title>
    <link rel="stylesheet" type="text/css" href="css/css.css">
</head>
<body>
    <jsp:include page="top.jsp"></jsp:include>

    <jsp:include page="search.jsp"></jsp:include>

    <jsp:include page="list-filter.jsp"></jsp:include>

    <jsp:include page="list-Sscreen.jsp"></jsp:include>
    <div id="listSboxInner">
        <jsp:include page="list-Sbox.jsp"></jsp:include>
    </div>

    <jsp:include page="list-footer.jsp"></jsp:include>



</body>
<script>

    function skuListByAttr(attrId,valId) {

        var flbh2 = ${flbh2}

        var param = "flbh2="+flbh2;

        // var str = "flbh2="+flbh2+"&attrId="+attrId+"&valId="+valId

        var val="{\"attrId\":\""+attrId+"\",\"valId\":\""+valId+"\"}";

        //  <input type="text" value="val">

        var str = '<input type="hidden" name="attr_val_id" value='+val+'>';

        $("#param_Div").append(str)

        $("[name='attr_val_id']").each(function (i,json) {

            var obj = jQuery.parseJSON($(json).val());

            //传递属性名的Id，属性值的Id
            param = param+"&attrValueList["+i+"].shxmId="+obj.attrId+"" +
                          "&attrValueList["+i+"].shxzhId="+obj.valId;

        })

        $.post("selectSkuListByAttr.do",param,function (data) {
            $("#listSboxInner").html(data)
        })
    }
</script>
</html>