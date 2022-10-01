<%--
  Created by IntelliJ IDEA.
  User: dongz
  Date: 2022/9/30
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%@include file="/pages/common/head.jsp"%></head>
</head>
<body>
    <div id="header">
        <img class="logo_img" alt="" src="static/img/logo.gif" >
        <span class="wel_word">订单详情</span>
        <%--静态包含登陆成功后的菜单--%>
        <%@include file="/pages/common/login_success_menu.jsp"%>
    </div>

    <div id="main">

        <table>
            <tr>
                <td>商品名称</td>
                <td>数量</td>
                <td>单价</td>
                <td>金额</td>
            </tr>

            <c:forEach items="${requestScope.orderItem}" var = "orderItem">
                <tr>
                    <td>${orderItem.name}</td>
                    <td>${orderItem.count}</td>
                    <td>${orderItem.price}</td>
                    <td>${orderItem.totalPrice}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <%@include file="/pages/common/footer.jsp"%>
</body>
</html>
