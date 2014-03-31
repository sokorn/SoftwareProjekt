<%@page import="java.util.List"%>
<%@page import="model.Adress"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Änderungen vornehmen</title>
        <link rel="stylesheet" href="css/main.css"/>
    </head>
    <body>
        <%@include file="templates/head.jsp" %>
        <div class="main">
            <% String currentStep = (String) session.getAttribute("action");
                if (currentStep.equals("pwdChanges")) {
            %>
            <form method="post" action="/carrental-war/servlet?step=changepwd">
                <p>altes Passwort: <input type="password" name="oldPassword" /></p>
                <p>neues Passwort: <input type="password" name="newPassword" /></p>
                <p>neues Passwort bestätigen: <input type="password" name="newPassword2" /></p>
                <p><input type="submit" value="Passwort ändern" /></p>
            </form>
            <%  } else if (currentStep.equals("persDataChanges")) {
            %>
            <form method="post" action="/carrental-war/servlet?step=changPersData">
                <p>Anrede: 
                    <select name="title">
                        <option value="Herr">Herr</option>
                        <option value="Frau">Frau</option>
                    </select>
                </p>
                <p>Vorname: <input type="text" name="firstname" value="<%=user.getFirstname()%>"/></p>
                <p>Nachname: <input type="text" name="lastname" value="<%=user.getLastname()%>"/></p>
                <p>Mail: <input type="email" name="email1" value="<%=user.getMail()%>"/></p>
                <p>Wiederholung: <input type="email" name="email2" value="<%=user.getMail()%>"/></p>
                <p><input type="submit" value="Persönliche Daten ändern" /></p>
            </form>
            <%  } else if (currentStep.equals("adressChanges")) {
                List<Adress> adressList = (List<Adress>) session.getAttribute("adressList");
                Adress adress = adressList.get(0);
            %>
            <form method="post" action="/carrental-war/servlet?step=changAdress">
                <p>Straße: <input type="text" name="street" value="<%=adress.getStreet()%>"/></p>
                <p>Hausnummer: <input type="text" name="housenumber" value="<%=adress.getHousenumber()%>"/></p>
                <p>Postleitzahl: <input type="number" name="postalcode" value="<%=Integer.parseInt(adress.getPostalCode())%>"/></p>
                <p>Stadt: <input type="text" name="city" value="<%=adress.getCity()%>"/></p>
                <p>Land:   
                    <select name="country">
                        <option value="Deutschland">Deutschland</option>
                        <option value="Schweiz">Schweiz</option>
                        <option value="Österreich">Österreich</option>
                    </select>
                </p>
                <p>Region: <input type="text" name="region" value="<%=adress.getRegion()%>" /></p>
                <p><input type="submit" value="Adresse ändern" /></p>
            </form>
            <%  }
            %>
            ${EmptyFieldError} 
            ${PasswordsNotEqualError}
        </div>
        <%@include file="templates/footer.jsp" %>
    </body>
</html>
