<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrieren</title>
    </head>
    <body>
        <div class="body">
            <div id="topbar">
                <a href="/CarRental/servlet?step=index">zur Startseite</a>
                <div class="content">
                    <span class="logo"></span>
                    <nav>
                        <ul></ul>
                    </nav>
                </div>
            </div>
            <div id="message"></div>
            <div id="main">
                <form method="post" action="/CarRental/servlet?step=register">
                    <p>Vorname: <input type="text" name="firstname" /></p>
                    <p>Nachname: <input type="text" name="lastname" /></p>
                    <p>Email: <input type="text" name="mail1" /></p>
                    <p>Wiederholung: <input type="text" name="mail2" /></p>
                    <p>Passwort: <input type="password" name="password1" /></p>
                    <p>Wiederholung: <input type="password" name="password2" /></p>
                    <p><input type="submit" value="Registrieren" /></p>
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