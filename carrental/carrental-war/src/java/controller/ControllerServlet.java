package controller;

import utils.DateParser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Adress;
import model.User;

@WebServlet(urlPatterns
        = {
            "/servlet"
        })

public class ControllerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String currentStep = request.getParameter("step");
        UserSessionBeanLocal userBean = BeanFactory.getUserSessionBean();
        AdressSessionBeanLocal adressBean = BeanFactory.getAdressSessionBean();
        User user = null;

        switch (currentStep) {
            case "index":
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "loginPage":
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                break;
            case "login":
                user = userBean.login(request.getParameter("login"), request.getParameter("password"));
                if (user != null) {
                    request.getRequestDispatcher("/personalArea.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
                break;
            case "registerPage":
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                break;
            case "register":
                Adress adress = adressBean.createAdress(request.getParameter("street"), request.getParameter("housenumber"), request.getParameter("city"), request.getParameter("country"), request.getParameter("postalCode"), true, true);
                user = userBean.createUser(request.getParameter("mail1"), DateParser.parseToDate(request.getParameter("birthdate")), request.getParameter("loginname"), request.getParameter("title"), request.getParameter("firstname"), request.getParameter("lastname"),request.getParameter("password1"), adress);
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
