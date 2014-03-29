<%@page import="model.Rent"%>
<%@page import="model.Car"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css"/>
        <title>Buchungsübersicht</title>
    </head>
    <body>
        <%@include file="templates/head.jsp" %>
        <div class="main">
            <%  Rent rent = (Rent) session.getAttribute("rent");
                Car car = rent.getCarmodelId();
            %>
            <h1>Übersicht:</h1>
            <div>Ausgewähltes Fahrzeug: <%=car.getBrandname()%> <%=car.getModelname()%></div>
            <div>Beginn: <%=rent.getStartdate()%></div>
            <div>Ende: <%=rent.getEnddate()%></div>
            <div>Dauer: <%=rent.getLength()%> Tage</div>
            <div>Preis: <%=rent.getTotalPrice()%> Euro</div>
            <form method="post" action="/carrental-war/servlet?step=rent">
                <p><input type="submit" value="Bestellen" /></p>
            </form>
        </div>
        <%@include file="templates/footer.jsp" %>
    </body>
</html>
