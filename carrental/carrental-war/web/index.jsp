<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Startseite</title>

    </head>
    <body>
        <div class="body">
            <div id="topbar">
                <div class="content">
                    <span class="logo"></span>
                    <nav id="head">
                        <p><a href="/carrental-war/servlet?step=registerPage">Registrieren</a>
                            <a href="/carrental-war/servlet?step=loginPage">Anmelden</a></p>
                    </nav>
                </div>
            </div>
            <div id="main">
                <form method="post" action="/carrental-war/servlet?step=search">
                    <p><select name="brand">
                            <option value="0">Marke auswählen</option>
     
                        </select>
                        <select name="model">

                        </select></p>
                    <p><input type="submit" value="Suchen" /></p>
                </form>
            </div>
        </div>
        <div id="footer">
            <nav>
                <ul></ul>    
            </nav>
        </div>
    </body>
</html>