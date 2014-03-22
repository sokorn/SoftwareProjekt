package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Adress;
import model.User;
import utils.Validator;

@WebServlet(urlPatterns
        = {
            "/servlet"
        })

public class ControllerServlet extends HttpServlet {

    @EJB
    private UserSessionBeanLocal userBean;
    @EJB
    private AdressSessionBeanLocal adressBean;
    @EJB
    private CarSessionBeanLocal carBean;
    @EJB
    private RentSessionBeanLocal rentBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String currentStep = request.getParameter("step");
        User user = null;
        HttpSession session;

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
                    request.setAttribute("LoginError", "Fehler beim Login");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/personalArea.jsp").forward(request, response);
                }
                break;
            case "registerPage":
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                break;
            case "register":
                //Testen ob es nicht ausgefüllte Felder gibt
                if (request.getParameter("title").isEmpty() || request.getParameter("firstname").isEmpty()
                        || request.getParameter("lastname").isEmpty() || request.getParameter("birthday").isEmpty()
                        || request.getParameter("mail1").isEmpty() || request.getParameter("mail2").isEmpty()
                        || request.getParameter("password1").isEmpty() || request.getParameter("password2").isEmpty()
                        || request.getParameter("street").isEmpty() || request.getParameter("housenumber").isEmpty()
                        || request.getParameter("postalcode").isEmpty() || request.getParameter("city").isEmpty()) {
                    request.setAttribute("EmptyFieldError", "Bitte alle mit * markierten Felder ausfüllen");
                } //Testen, ob die eingegebene Mail schon benutzt wird
                else if (userBean.mailAlreadyUsed(request.getParameter("mail1"))) {
                    request.setAttribute("MailInUseError", "Email bereits benutzt!");
                } //Testen, ob es sich bei der eingegebenen Mail, um eine gültige Mail handelt
                else if (!Validator.validateMail(request.getParameter("mail1"))) {
                    request.setAttribute("IllegalMailError", "Keine gültige Mailadresse");
                } //Testen, ob Mail und Wiederholung übereinstimmen
                else if (!request.getParameter("mail1").equals(request.getParameter("mail2"))) {
                    request.setAttribute("MailsNotEqualError", "Emails stimmen nicht überein!");
                } //Testen, ob die eingegebenen Passwörter übereinstimmen
                else if (!request.getParameter("password1").equals(request.getParameter("password2"))) {
                    request.setAttribute("PasswordNotEqualError", "Passwörter stimmen nicht überein!");
                } //Wenn alle bisherigen Tests negativ waren, kann der User angelegt werden
                else {
                    user = userBean.createUser(request.getParameter("title"),
                            request.getParameter("firstname"), request.getParameter("lastname"),
                            request.getParameter("birthday"), request.getParameter("mail1"),
                            request.getParameter("password1"));
                    Adress adress = adressBean.createAdress(request.getParameter("street"),
                            request.getParameter("housenumber"), request.getParameter("city"),
                            request.getParameter("country"), request.getParameter("postalcode"),
                            true, true, request.getParameter("region"), user);
                    userBean.addAdressToUser(user, adress);
                }
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
