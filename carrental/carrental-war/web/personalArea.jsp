<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Persönlicher Bereich</title>
        <link rel="stylesheet" href="css/main.css"/>
    </head>
    <body>
        <%@include file="templates/head.jsp" %>
        <div class="main">
            ${SuccessfulRent}
        </div>
        <%@include file="templates/footer.jsp" %>
    </body>
</html>