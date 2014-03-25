<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="../css/main.css"/>
<div class="head">
    <%
        User user = (User) session.getAttribute("user");
        if (user == null) {
    %>
    <div class="header_right"><a href="/carrental-war/servlet?step=registerPage">Registrieren</a>
        <a href="/carrental-war/servlet?step=loginPage">Anmelden</a></div>
        <%
        } else {
        %>
    <div class="header_right">Willkommen, <%=user.getTitle()%> <%=user.getLastname()%> <a href="/carrental-war/servlet?step=logout">Abmelden</a></div>
    <%
        }
        if (request.getParameter("step") != null) {
            if (!request.getParameter("step").equals("index")) {
    %>
    <div class="header_left"><a href="/carrental-war/servlet?step=index">zur Startseite</a></div>
    <%
            }
        }
    %>
</div>