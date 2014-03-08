<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <div class="body">
            <div id="topbar">
                <a href="/servlet?step=index">zur Startseite</a>
                <div class="content">
                    <span class="logo"></span>
                    <nav>
                        <ul></ul>
                    </nav>
                </div>
            </div>
            <div id="message"></div>
            <div id="main">
                <form method="post" action="/carrental-war/servlet?step=login">
                    <p>Login: <input type="text" name="login" /></p>
                    <p>Passwort: <input type="password" name="password" /></p>
                    <p><input type="submit" value="Anmelden" /></p>
                </form>
            </div>
        </div>
        <div id="footer">
            <nav>
                <ul></ul>    
            </nav>
        </div>
    </body>
</html>