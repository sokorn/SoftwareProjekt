<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrieren</title>
        <link rel="stylesheet" href="css/main.css"/>
    </head>
    <body>
        <%@include file="templates/head.jsp" %>
        <div class="main">
            <form method="post" action="/carrental-war/servlet?step=register">
                <p>Anrede: 
                    <select name="title">
                        <option value="Herr">Herr</option>
                        <option value="Frau">Frau</option>
                    </select>
                </p>
                <p>Vorname*: <input type="text" name="firstname" 
                                    value="<%=(request.getParameter("firstname") == null)
                                            ? "" : request.getParameter("firstname")%>"/></p>
                <p>Nachname*: <input type="text" name="lastname" 
                                     value="<%=(request.getParameter("lastname") == null)
                                             ? "" : request.getParameter("lastname")%>"/></p>
                <p>Geburtstag*: <input type="date" name="birthday" 
                                       value="<%=(request.getParameter("birthday") == null)
                                               ? "" : request.getParameter("birthday")%>"/></p>
                <p>Email*: <input type="email" name="mail1" 
                                  value="<%=(request.getParameter("mail1") == null)
                                          ? "" : request.getParameter("mail1")%>"/>
                    ${MailInUseError}${IllegalMailError}</p>
                <p>Wiederholung*: <input type="email" name="mail2" 
                                         value="<%=(request.getParameter("mail2") == null)
                                                 ? "" : request.getParameter("mail2")%>"/>
                    ${MailsNotEqualError}</p>
                <p>Passwort*: <input type="password" name="password1" 
                                     value="<%=(request.getParameter("password1") == null)
                                             ? "" : request.getParameter("password1")%>"/></p>
                <p>Wiederholung*: <input type="password" name="password2" 
                                         value="<%=(request.getParameter("password2") == null)
                                                 ? "" : request.getParameter("password2")%>"/>
                    ${PasswordsNotEqualError}</p>
                <p>Adresse*:</p>
                <p>Straße*: <input type="text" name="street" 
                                   value="<%=(request.getParameter("street") == null)
                                           ? "" : request.getParameter("street")%>"/></p>
                <p>Hausnummer*: <input type="text" name="housenumber" 
                                       value="<%=(request.getParameter("housenumber") == null)
                                               ? "" : request.getParameter("housenumber")%>"/></p>
                <p>Postleitzahl*: <input type="number" name="postalcode" 
                                         value="<%=(request.getParameter("postalcode") == null)
                                                 ? "" : request.getParameter("postalcode")%>"/></p>
                <p>Stadt*: <input type="text" name="city" 
                                  value="<%=(request.getParameter("city") == null)
                                          ? "" : request.getParameter("city")%>"/></p>
                <p>Land:   
                    <select name="country">
                        <option value="Deutschland">Deutschland</option>
                        <option value="Schweiz">Schweiz</option>
                        <option value="Österreich">Österreich</option>
                    </select>
                </p>
                <p>Region: <input type="text" name="region" 
                                  value="<%=(request.getParameter("region") == null)
                                          ? "" : request.getParameter("region")%>"/></p>
                <p><input type="submit" value="Registrieren" /></p>
                <p><input type="reset" value="Abbrechen" /></p>
            </form>
            <p>Felder mit * müssen ausgefüllt werden</p>
            <p id="error">${EmptyFieldError}</p>
        </div>
        <%@include file="templates/footer.jsp" %>
    </body>
</html>