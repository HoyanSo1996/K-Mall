<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
    Feature: 作为网站的入口页面, 直接请求CustomerFurnitureServlet 获取网站首页要显示的分页数据
    User: KennySo
    Date: 2024/4/8
--%>
<jsp:forward page="/customerFurnitureServlet?action=page&pageNo=1" />