<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>403</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/error.css"/>
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1><i class="fa fa-ban red"></i> 403 Forbidden</h1>

        <p class="lead">Sorry! You don't have access permissions for that on <em><span id="display-domain"></span></em>.
        </p>
    </div>
</div>
</body>
</html>