<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>404</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/error.css"/>
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1><i class="fa fa-frown-o red"></i> 404 Not Found</h1>

        <p class="lead">We couldn't find what you're looking for on <em><span
                id="display-domain"><%=request.getLocalAddr()%><%=request.getAttribute("javax.servlet.error.request_uri")%></span></em>.</p>
    </div>
</div>
</body>
</html>