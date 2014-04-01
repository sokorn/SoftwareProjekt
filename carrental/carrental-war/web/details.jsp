<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="model.Car"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Details</title>
        <link rel="stylesheet" href="css/main.css"/>
        <link rel="stylesheet" href="css/carResults.css"/>
    </head>
    <body>
        <%@include file="templates/head.jsp" %>
        <div class="main">
            <%        Car car = (Car) session.getAttribute("car");
            %>
            <div>
                <h1><%=car.getBrandname()%> <%=car.getModelname()%></h1>
                <div id="picture"><img src="<%=car.getModelpicture()%>" alt="<%=car.getModelname()%>"></div>
                <div id="main">
                    <div>Leistung: <%=car.getPower()%> PS</div>
                    <div>Hubraum: <%=car.getCcm()%> Liter</div>
                    <div>Drehmoment: <%=car.getTourque()%> Nm</div>
                    <div>Beschleunigung: <%=car.getAcceleration()%> s</div>
                    <div>Höchstgeschwindigkeit: <%=car.getMaxSpeed()%> km/h</div>
                    <div>Gewicht: <%=car.getWeight()%> kg</div>
                    <div>Preis <%=car.getPrice()%> Euro/Tag</div>
                </div>
                <%
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String dateString = formatter.format(new Date());
                    if (car.isAvailable()) {
                %>
                <form method="post" action="/carrental-war/servlet?step=rentOverview&id=<%=car.getCarId()%>">
                    <p>Startdatum: <input type="date" name="startdate" min="<%=dateString%>"/>${WrongStartDate}</p>
                    <p>Enddatum: <input type="date" name="enddate" min="<%=dateString%>"/>${WrongEndDate}</p>
                    <p><input type="submit" value="Buchungsübersicht" /></p>
                </form>
                <%
                } else {
                %>
                <div>Auto ist zur Zeit leider nicht verfügbar!</div>
                <%
                    }
                %>    
                ${NotLoggedInError}
            </div>
        </div>
        <%@include file="templates/footer.jsp" %>
    </body>
</html>
