<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div>
    <%
        User user = (User) session.getAttribute("user");
        if (user == null) {
    %>
    <div><a href="/carrental-war/servlet?step=registerPage">Registrieren</a>
        <a href="/carrental-war/servlet?step=loginPage">Anmelden</a></div>
        <%
        } else {
        %>
    <div>Willkommen, <%=user.getTitle()%> <%=user.getLastname()%> <a href="/carrental-war/servlet?step=logout">Abmelden</a></div>
    <%
        }
    %>
</div>