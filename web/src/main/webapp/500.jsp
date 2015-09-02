<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>500</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/error.css"/>
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1><span class="fa fa-fire red"></span> 500 Internal Server Error</h1>

        <p>The web server is returning an internal error for <em><span id="display-domain"><%=request.getRequestURL()%></span></em>.
    </div>
</div>
</body>
</html>
