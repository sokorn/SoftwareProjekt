<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Startseite</title>
    </head>
    <body>
        <div class="body">
            <div id="topbar">
                <div class="content">
                    <span class="logo"></span>
                    <nav>
                        <ul></ul>
                    </nav>
                </div>
            </div>
            <div id="message"></div>
            <div id="main">
                <form method="post" action="/carrental-war/servlet?step=search">
                    <p><input type="text" name="search" /></p>
                    <p><input type="submit" value="Suchen" /></p>
                </form>
                <p><a href="/carrental-war/servlet?step=registerPage">Registrieren</a></p>
                <p><a href="/carrental-war/servlet?step=loginPage">Anmelden</a></p>
            </div>
        </div>
        <div id="footer">
            <nav>
                <ul></ul>    
            </nav>
        </div>
    </body>
</html>