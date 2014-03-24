<%@page import="java.util.List"%>
<%@page import="model.Car"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="css/carResults.css"/>
<div id="result">
    <%
        List<Car> carList = (List<Car>) session.getAttribute("carList");
        for (Car car : carList) {
    %>
    <div class="model">
        <div class="picture">...</div>
        <div class="description">
            <div class="modelHeader"><a href="/carrental-war/servlet?step=details&id=<%=car.getCarId()%>"><%=car.getBrandname()%> <%=car.getModelname()%></a></div>
            <div><%=car.getPrice()%></div>
        </div>
    </div>
    <%
        }
    %>
</div>