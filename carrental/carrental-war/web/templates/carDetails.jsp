<%@page import="java.io.File"%>
<%@page import="model.Car"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="css/stylesheet.css"/>
<link rel="stylesheet" href="css/carResults.css"/>
<div>
    <%
        Car car = (Car) session.getAttribute("car");
    %>
    <div>
        <h1><%=car.getBrandname()%> <%=car.getModelname()%></h1>
        <div id="picture"><img src="<%=car.getModelpicture()%>" alt="Auto"></div>
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
            if (car.isAvailable()) {
        %>
        <form method="post" action="/carrental-war/servlet?step=rent&id=<%=car.getCarId()%>">
            <p>Startdatum: <input type="date" name="startdate" /></p>
            <p>Enddatum: <input type="date" name="enddate" /></p>
            <p><input type="submit" value="Buchen" /></p>
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
