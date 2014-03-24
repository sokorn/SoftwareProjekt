<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="css/stylesheet.css"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Suchergebnisse</title>
    </head>
    <body>
        <div id="head">
            <a href="/carrental-war/servlet?step=index">zur Startseite</a>
            <%@include file="templates/head.jsp" %>
        </div>
        <div id="main">
            <%@include file="templates/carResults.jsp" %>
        </div>
        <div id="footer">
        </div>
    </body>
</html>