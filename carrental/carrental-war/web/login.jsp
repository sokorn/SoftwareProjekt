<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="css/main.css"/>
    </head>
    <body>
        <%@include file="templates/head.jsp" %>
        <div class="main">
            <form method="post" action="/carrental-war/servlet?step=login">
                <p>Login: <input type="text" name="login"/></p>
                <p>Passwort: <input type="password" name="password"/></p>
                <p><input type="submit" value="Anmelden" /></p>
            </form>
            <p>${LoginError}</p>
        </div>
        <%@include file="templates/footer.jsp" %>
    </body>
</html>