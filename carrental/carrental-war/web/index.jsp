<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Startseite</title> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/stylesheet.css"/>
    </head>
    <body>
        <h1 align="center">Willkommen bei Car Rental</h1>
        <font color="#FF0000"></font>
        <p>Billig kann jeder!</p>

        <div id="head">
            <a href="/carrental-war/servlet?step=index">zur Startseite</a>
            <%@include file="templates/head.jsp" %>
        </div>
        <div id="main">
            <form method="post" action="/carrental-war/servlet?step=search">
                <p><select name="brand">
                        <option value="0">Marke auswählen</option>

                    </select>
                    <select name="model">
                        <option value="0">Model auswählen</option>
                    </select></p>
                <p><input type="submit" value="Suchen" /></p>
            </form>
        </div>
        <div id="footer">
        </div>
    </body>
</html>