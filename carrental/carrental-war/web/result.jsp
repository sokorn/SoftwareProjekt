<%@page import="model.Car"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css"/>
        <title>Suchergebnisse</title>
    </head>
    <%@include file="templates/head.jsp" %>
    <div class="main">
        <%  List<Car> carList = (List<Car>) session.getAttribute("carList");
            for (Car car : carList) {
        %>
        <div class="model">
            <div class="picture">
                <img src="<%=car.getModelpicture()%>" alt="<%=car.getModelname()%>">
            </div>
            <div class="description">
                <div class="modelHeader"><a href="/carrental-war/servlet?step=details&amp;id=<%=car.getCarId()%>">
                        <%=car.getBrandname()%> <%=car.getModelname()%></a>
                </div>
                <div>
                    Preis/Tag: <%=car.getPrice()%> €
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
    <%@include file="templates/footer.jsp" %>
</body>
</html>