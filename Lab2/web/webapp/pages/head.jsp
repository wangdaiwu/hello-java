<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
%>

<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-3.6.0.js"></script>