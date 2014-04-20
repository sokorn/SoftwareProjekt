<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="model.Address"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Persönlicher Bereich</title>
        <link rel="stylesheet" href="css/main.css"/>
    </head>
    <body>
        <%@include file="templates/head.jsp" %>
        <div class="main">
            <%  user = (User) session.getAttribute("user");
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String birthdate = formatter.format(user.getBirthdate());
            %>
            <div>
                <h3>Persönliche Daten:</h3>
                <div>Anrede: <%=user.getTitle()%></div>
                <div>Vorname: <%=user.getFirstname()%></div>
                <div>Nachname: <%=user.getLastname()%></div>
                <div>Geburtstag: <%=birthdate%></div>
                <div>Mail: <%=user.getMail()%></div>
                <div><a href="/carrental-war/servlet?step=persDataChanges">Persönliche Daten ändern</a></div>
                <div><a href="/carrental-war/servlet?step=pwdChanges">Passwort ändern</a></div>
                <%@include file="templates/addressOverview.jsp" %>
                <%
                    if (addressList.size() == 1)
                    {
                %>
                <div><a href="/carrental-war/servlet?step=adressChanges">Adresse ändern</a></div>
                <%  } else
                {
                %>
                <div><a href="/carrental-war/servlet?step=adressChanges">Lieferadresse ändern</a></div>
                <div><a href="/carrental-war/servlet?step=adressChanges">Rechnungsadresse ändern</a></div>
                <%    }
                %>
                <h4><a href="/carrental-war/servlet?step=personalRents">Meine Buchungen</a></h4>
                <form method="post" action="/carrental-war/servlet?step=deleteAcc">
                    <p><input type="submit" value="Konto löschen" /></p>
                </form>
            </div>
            <div class="messages">
                ${SuccessfulRent}
                ${passwordChanged}
                ${persDataChanged}
                ${RentError}
                ${AdressChanged}
                ${ActiveRentsError}
                ${RentCancelled}
            </div>

        </div>
        <%@include file="templates/footer.jsp" %>
    </body>
</html>