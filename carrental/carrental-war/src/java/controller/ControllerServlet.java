package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns
        = {
            "/servlet"
        })
public class ControllerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String currentStep = request.getParameter("step");
        
        switch (currentStep) {
            case "index":
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "loginPage":
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                break;
            case "login":
                /*
                Vergleiche den User und Password mit Werten in der DB
                */
                request.getRequestDispatcher("/personalArea.jsp").forward(request, response);
                break;
            case "registerPage":
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                break;
            case "register":
                /*
                Nimm alle Parameter der Seite register.jsp und lege einen neuen
                User in der DB an.
                */
                break;
            case "search":
                /*
                Nimm den Parameter "search" rufe die Methode ??? auf, leite auf
                die Ergebnisseite um und zeige die Resultate an.
                */
                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}