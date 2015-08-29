<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>

    <link rel="stylesheet" href="${rc.contextPath}/vendor/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="${rc.contextPath}/vendor/font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${rc.contextPath}/css/pine.css"/>

    <script type="text/javascript" src="<@s.url "/vendor/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<@s.url "/vendor/bootstrap/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript" src="${rc.contextPath}/vendor/metisMenu.js"></script>

    <script type="text/javascript">
        $(function(){
            $('#side-menu').metisMenu();
        });
    </script>

</head>
<body>
ContextParameterInjector
<@s.message "test.message"/>
<div class="col-sm-4" style="padding: 0;" role="navigation">
    ${sidebarMenu}
</div>

<div id="main-content">
    HELLO</br>
    WORLD
</div>

</body>
</html>