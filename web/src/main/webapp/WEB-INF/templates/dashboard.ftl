<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>

    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/angular.min.js"></script>
    <script type="text/javascript" src="/semantic-ui/semantic.min.js"></script>

    <link rel="stylesheet" href="/semantic-ui/semantic.min.css"/>

    <script type="text/javascript">

        $(document).ready(function () {
            $(".accordion").accordion();
        });
    </script>

    <style>
        aside .content a {
            padding-left: 36px !important;
            position: relative;
            margin: 0;
        }

        aside .content span {
            background: #425668 !important;
            bottom: auto !important;
            content: "" !important;
            height: 8px !important;
            width: 8px !important;
            left: 23px !important;
            margin-top: 4px !important;
            position: absolute !important;
            right: auto !important;
            z-index: 1 !important;
            -webkit-border-radius: 50% !important;
            -moz-border-radius: 50% !important;
            border-radius: 50% !important;
            display: block;
        }

        aside .content label {
            bottom: 0 !important;
            content: "" !important;
            left: 27px !important;
            position: absolute !important;
            top: 0 !important;
            border-left: 1px solid #425668 !important;
        }

    </style>

</head>
<body>
<header>
    Header
</header>

<aside id="main-menu" class="ui accordion fixd">
    <div>
        <div class="title">
            <i class="dropdown icon"></i>
            What is a dog?
        </div>
        <div class="content ui list">
            <div class="item">
                <i class="users icon"></i>

                <div class="content">
                    Semantic UI
                </div>
            </div>
            <div class="item">
                <i class="marker icon"></i>

                <div class="content">
                    New York, NY
                </div>
            </div>
            <div class="item">
                <i class="mail icon"></i>

                <div class="content">
                    <a href="mailto:jack@semantic-ui.com">jack@semantic-ui.com</a>
                </div>
            </div>
            <div class="item">
                <i class="linkify icon"></i>

                <div class="content">
                    <a href="http://www.semantic-ui.com">semantic-ui.com</a>
                </div>
            </div>
        </div>
        <div>
            <div class="title">
                <i class="dropdown icon"></i>
                What kinds of dogs are there?
            </div>
            <div class="ui secondary vertical menu content">
                <a class="item">
                    <span></span>
                    <label></label>
                    Account
                </a>
                <a class="item">
                    Settings
                </a>
            </div>
        </div>
</aside>

<div id="main-content">
</div>
</body>
</html>