<%@page import="java.util.List"%>
<%@page import="model.Adress"%>
<%@page import="model.Adress"%>
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
            %>
            <div>
                <div>Persönliche Daten:</div>
                <div>Anrede: <%=user.getTitle()%></div>
                <div>Vorname: <%=user.getFirstname()%></div>
                <div>Nachname: <%=user.getLastname()%></div>
                <div>Geburtstag: <%=user.getBirthdate()%></div>
                <div>Mail: <%=user.getMail()%></div>
                <div><a href="/carrental-war/servlet?step=changes" onclick="<%session.setAttribute("action", "personalData");%>">Persönliche Daten ändern</a></div>
                <div><a href="/carrental-war/servlet?step=changes" onclick="<%session.setAttribute("action", "password");%>">Passwort ändern</a></div>
                <%  if (user.getAdressCollection().size() == 1) {
                        List<Adress> adressList = (List<Adress>) session.getAttribute("adressList");
                        Adress adress = adressList.get(0);
                %>
                <div>Standardadresse:</div>
                <div><%=adress.getStreet()%> <%=adress.getHousenumber()%></div>
                <div><%=adress.getPostalCode()%> <%=adress.getCity()%></div>
                <% if (adress.getRegion() != null) {
                %>
                <div><%=adress.getRegion()%></div>
                <% }
                %>
                <div><%=adress.getCountry()%></div>
                <% }
                %>
                <div><a href="/carrental-war/servlet?step=changes" onclick="<%session.setAttribute("action", "adress");%>">Adresse ändern</a></div>
            </div>
            ${SuccessfulRent}
            ${passwordChanged}
            ${persDataChanged}
            ${RentError}
        </div>
        <%@include file="templates/footer.jsp" %>
    </body>
</html>