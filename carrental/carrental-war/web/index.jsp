<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Startseite</title> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css"/>
    </head>
    <body>
        <%@include file="templates/head.jsp" %>
        <div class="main">
            <div>${NotLoggedInError}</div>
            <form method="post" action="/carrental-war/servlet?step=search">
                <p>
                    <select name="brand">
                        <option value="0">Marke auswählen</option>
                        <% if (!(session.getAttribute("brandList") == null)) {
                                List<String> brandList = (List<String>) session.getAttribute("brandList");
                                for (String brand : brandList) {
                        %>
                        <option value="<%=brand%>"><%=brand%></option>
                        <%
                                }
                            }
                        %>
                    </select>
                    <select name="model">
                        <option value="0">Model auswählen</option>
                        <% if (!(session.getAttribute("modelList") == null)) {
                                List<String> modelList = (List<String>) session.getAttribute("modelList");
                                for (String model : modelList) {
                        %>
                        <option value="<%=model%>"><%=model%></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </p>
                <p>
                    <input type="submit" value="Suchen" />
                </p>
            </form>
        </div>
        <%@include file="templates/footer.jsp" %>
    </body>
</html>