package controller;

import java.io.IOException;
import javax.ejb.EJB;
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

    @EJB
    private UserSessionBeanLocal userBean;
    private AdressSessionBeanLocal adressBean;
    private CarSessionBeanLocal carBean;
    private RentSessionBeanLocal rentBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String currentStep = request.getParameter("step");
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
                if (user == null) {
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/personalArea.jsp").forward(request, response);
                }
                break;
            case "registerPage":
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                break;
            case "register":
                Adress adress = adressBean.createAdress(request.getParameter("street"),
                        request.getParameter("housenumber"), request.getParameter("city"),
                        request.getParameter("country"), request.getParameter("postalCode"),
                        true, true);
                user = userBean.createUser(request.getParameter("title"),
                        request.getParameter("firstname"), request.getParameter("lastname"),
                        request.getParameter("birthday"), request.getParameter("mail1"),
                        request.getParameter("mail2"), request.getParameter("password1"),
                        request.getParameter("password2"), adress);
                if (user == null) {
                    request.getRequestDispatcher("/register.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/personalArea.jsp").forward(request, response);
                }
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
