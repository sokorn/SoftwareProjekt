<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="css/stylesheet.css"/>
    </head>
    <body>
        <div class="body">
            <div id="topbar">
                <a href="/carrental-war/servlet?step=index">zur Startseite</a>
                <div class="content">
                    <span class="logo"></span>
                    <nav>
                    </nav>
                </div>
            </div>
            <div id="main">
                <form method="post" action="/carrental-war/servlet?step=login">
                    <p>Login: <input type="text" name="login" /></p>
                    <p>Passwort: <input type="password" name="password" /></p>
                    <p><input type="submit" value="Anmelden" /></p>
                </form>
                <p>${LoginError}</p>
            </div>
        </div>
        <div id="footer">
            <nav>
                <ul></ul>    
            </nav>
        </div>
    </body>
</html>