<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="model.Rent"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css"/>
        <title>Meine Buchungen</title>
    </head>
    <body>
        <%@include file="templates/head.jsp" %>
        <div class="main">
            <% if (session.getAttribute("rentList") == null) {
            %>
            Sie haben bisher noch keine Buchungen vorgenommen.
            <%  } else {
                List<Rent> rentList = (List<Rent>) session.getAttribute("rentList");
            %>
            <table>
                <tr>
                    <th>
                        Marke
                    </th>
                    <th>
                        Modell
                    </th>
                    <th>
                        Länge
                    </th>
                    <th>
                        Startdatum
                    </th>
                    <th>
                        Enddatum
                    </th>
                    <th>
                        Preis
                    </th>
                    <th>
                        Stornieren
                    </th>
                </tr>
                <%
                    for (Rent rent : rentList) {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                        String startDate = formatter.format(rent.getStartdate());
                        String endDate = formatter.format(rent.getEnddate());
                %>
                <tr>
                    <td>
                        <%=rent.getCarmodelId().getBrandname()%>
                    </td>
                    <td>
                        <%=rent.getCarmodelId().getModelname()%>
                    </td>
                    <td>
                        <%=rent.getLength()%> Tag(e)
                    </td>
                    <td>
                        <%=startDate%>
                    </td>
                    <td>
                        <%=endDate%>
                    </td>
                    <td>
                        <%=rent.getTotalPrice()%> €
                    </td>
                    <td>
                        <%  Date now = new Date();
                            if ((rent.getStartdate().getTime() - now.getTime()) > 0) {
                        %>
                        <a href="/carrental-war/servlet?step=cancelRent&amp;rentId=<%=rent.getRentId()%>">Buchung stornieren</a>
                        <%
                        } else {
                        %>
                        Keine Stornierung möglich
                        <%  }
                        %>
                    </td>
                </tr>
                <%      }
                %>
            </table>
            <%
                }
            %>
        </div>
        <%@include file="templates/footer.jsp" %>
    </body>
</html>
