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
                    <p>Anrede: <input type="text" name="title"/></p>
                    <p>Vorname: <input type="text" name="firstname" /></p>
                    <p>Nachname: <input type="text" name="lastname" /></p>
                    <p>Geburtstag: <input type="date" name="birthday" /></p>
                    <p>Email: <input type="email" name="mail1" /></p>
                    <p>Wiederholung: <input type="email" name="mail2" /></p>
                    <p>Passwort: <input type="password" name="password1" /></p>
                    <p>Wiederholung: <input type="password" name="password2" /></p>
                    <p>Adresse:</p>
                    <p>Straße: <input type="text" name="street" /></p>
                    <p>Hausnummer: <input type="text" name="housenumber" /></p>
                    <p>Postleitzahl: <input type="number" name="postalcode" min="3" max="5" /></p>
                    <p>Stadt: <input type="text" name="city" /></p>
                    <p>Land: <input type="text" name="country" /></p>
                    <p>Region: <input type="text" name="region" /></p>
                    <p><input type="submit" value="Registrieren" /></p>
                    <p><input type="reset" value="Abbrechen" /></p>
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