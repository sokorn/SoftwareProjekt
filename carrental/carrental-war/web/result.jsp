<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Suchergebnisse</title>
    </head>
    <body>
        <div class="body">
            <div id="topbar">
                <a href="/carrental-war/servlet?step=index">zur Startseite</a>
                <div class="content">
                    <span class="logo"></span>
                    <nav>
                        <ul></ul>
                    </nav>
                </div>
            </div>
            <div id="message"></div>
            <div id="main">
                <c:if test="carList.size() != 0">
                    Test
                </c:if>
            </div>
        </div>
        <div id="footer">
            <nav>
                <ul></ul>    
            </nav>
        </div>
    </body>
</html>