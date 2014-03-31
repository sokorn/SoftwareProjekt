<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Konto löschen</title>
        <link rel="stylesheet" href="css/main.css"/>
    </head>
    <body>
        <%@include file="templates/head.jsp" %>
        <div class="main">
            <div>Sind Sie sicher, dass Sie Ihr Konto löschen wollen?</div>
            <div>Dieser Schritt ist unwiederruflich!</div>
            <div>Bitte geben Sie ihre Mailadresse und Ihr Passwort ein</div>
            <form method="post" action="/carrental-war/servlet?step=confirmDelete">
                <p>Mail: <input type="email" name="email"/></p>
                <p>Passwort: <input type="password" name="password"/></p>
                <p><input type="submit" value="Entgültig löschen" /></p>
            </form>
            <div>
                ${EmptyFieldError}
                ${WrongMailError}
                ${PasswordError}
            </div>
        </div>
        <%@include file="templates/footer.jsp" %>
    </body>
</html>
