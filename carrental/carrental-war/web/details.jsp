<%-- 
    Document   : details
    Created on : 24.03.2014, 12:41:14
    Author     : Korn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Details</title>
    </head>
    <body>
        <div id="head">
            <a href="/carrental-war/servlet?step=index">zur Startseite</a>
            <%@include file="templates/head.jsp" %>
        </div>
        <div id="main">
            <%@include file="templates/carDetails.jsp" %>
        </div>
        <div id="footer">
        </div>
    </body>
</html>
