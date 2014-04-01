<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="../css/main.css"/>
<div>
    <%
        if (request.getParameter("step").equals("impressum")) {
    %>
    <div class="footer">
        Copyright Car Rental
    </div>
    <%
    } else {
    %>
    <div class="footer">
        Copyright Car Rental - <a href="/carrental-war/servlet?step=impressum">Impressum</a>
    </div>
    <%
        }
    %>
</div>