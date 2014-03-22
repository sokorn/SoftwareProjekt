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
                <a href="/carrental-war/servlet?step=index">zur Startseite</a>
                <div class="content">
                    <span class="logo"></span>
                    <nav>
                    </nav>
                </div>
            </div>
            <div id="main">
                <form method="post" action="/carrental-war/servlet?step=register">
                    <p>Anrede: 
                        <select name="title">
                            <option value="Herr">Herr</option>
                            <option value="Frau">Frau</option>
                        </select>
                    </p>
                    <p>Vorname*: <input type="text" name="firstname" /></p>
                    <p>Nachname*: <input type="text" name="lastname" /></p>
                    <p>Geburtstag*: <input type="date" name="birthday" /></p>
                    <p>Email*: <input type="email" name="mail1" />${MailInUseError}${IllegalMailError}</p>
                    <p>Wiederholung*: <input type="email" name="mail2" />${MailsNotEqualError}</p>
                    <p>Passwort*: <input type="password" name="password1" /></p>
                    <p>Wiederholung*: <input type="password" name="password2" />${PasswordsNotEqualError}</p>
                    <p>Adresse*:</p>
                    <p>Straße*: <input type="text" name="street" /></p>
                    <p>Hausnummer*: <input type="text" name="housenumber" /></p>
                    <p>Postleitzahl*: <input type="number" name="postalcode"/></p>
                    <p>Stadt*: <input type="text" name="city" /></p>
                    <p>Land:   
                        <select name="country">
                            <option value="Deutschland">Deutschland</option>
                            <option value="Schweiz">Schweiz</option>
                            <option value="Österreich">Österreich</option>
                        </select>
                    </p>
                    <p>Region: <input type="text" name="region" /></p>
                    <p><input type="submit" value="Registrieren" /></p>
                    <p><input type="reset" value="Abbrechen" /></p>
                </form>
                <p>Felder mit * müssen ausgefüllt werden</p>
                <p id="error">${EmptyFieldError}</p>
            </div>
        </div>
        <div id="footer">
            <nav>  
            </nav>
        </div>

    </body>
</html>