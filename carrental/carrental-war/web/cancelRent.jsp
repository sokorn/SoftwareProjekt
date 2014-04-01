<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css"/>
        <title>Buchung stornieren</title>
    </head>
    <body>
        <%@include file="templates/head.jsp" %>
        <div class="main">
            Wollen Sie folgende Buchung wirklich stornieren?
            <form method="post" action="/carrental-war/servlet?step=canceled">
                <input type="submit" value="Ja" />
            </form>
            <form method="post" action="/carrental-war/servlet?step=personalRents">
                <input type="submit" value="Nein" />
            </form>
        </div>
        <%@include file="templates/footer.jsp" %>
    </body>
</html>
