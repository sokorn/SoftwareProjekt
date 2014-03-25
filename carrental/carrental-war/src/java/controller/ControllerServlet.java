package controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Adress;
import model.Car;
import model.Rent;
import model.User;
import utils.Validator;

/**
 * Stellt die komplette Kommunikation zwischen JSP und EJB dar.
 *
 */
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
        HttpSession session = request.getSession();

        User sessionUser = (User) session.getAttribute("user");
        List<String> brandList = carBean.getNameList("brand");
        List<String> modelList = carBean.getNameList("model");
        session.setAttribute("brandList", brandList);
        session.setAttribute("modelList", modelList);

        if (!(session.getAttribute("selectedBrand") == null)) {
            modelList = carBean.getNameListOfCarsOfSelectedBrand(request.getParameter("brand"));
            session.setAttribute("modelList", modelList);
        }

        if (currentStep == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            switch (currentStep) {
                case "index":
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    break;
                case "loginPage":
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    break;
                case "login":
                    User user = userBean.login(request.getParameter("login"), request.getParameter("password"));
                    session.setAttribute("user", user);
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
                    user = null;
                    /**
                     * Testen ob es nicht ausgefüllte Felder gibt. Wenn ein Feld
                     * oder mehrere Felder leer sind, wird der Parameter
                     * EmptyFieldError auf der register.jsp gesetzt und die
                     * angegebene Nachricht angezeigt.
                     */
                    if (request.getParameter("title").isEmpty() || request.getParameter("firstname").isEmpty()
                            || request.getParameter("lastname").isEmpty() || request.getParameter("birthday").isEmpty()
                            || request.getParameter("mail1").isEmpty() || request.getParameter("mail2").isEmpty()
                            || request.getParameter("password1").isEmpty() || request.getParameter("password2").isEmpty()
                            || request.getParameter("street").isEmpty() || request.getParameter("housenumber").isEmpty()
                            || request.getParameter("postalcode").isEmpty() || request.getParameter("city").isEmpty()) {
                        request.setAttribute("EmptyFieldError", "Bitte alle mit * markierten Felder ausfüllen");
                    } /**
                     * Testen, ob die eingegebene Mail schon benutzt wird. Wenn
                     * ja, wird der Parameter MailInUseError auf der
                     * register.jsp gesetzt und die angegebene Nachricht
                     * angezeigt.
                     */
                    else if (userBean.mailAlreadyUsed(request.getParameter("mail1"))) {
                        request.setAttribute("MailInUseError", "Email bereits benutzt!");
                    } /**
                     * Testen, ob es sich bei der eingegebenen Mail, um eine
                     * gültige Mail handelt. Wenn ja, wird der Parameter
                     * IllegalMailError auf der register.jsp gesetzt und die
                     * angegebene Nachricht angezeigt.
                     */
                    else if (!Validator.validateMail(request.getParameter("mail1"))) {
                        request.setAttribute("IllegalMailError", "Keine gültige Mailadresse");
                    } /**
                     * Testen, ob Mail und Wiederholung übereinstimmen. Wenn sie
                     * nicht übereinstimmen, wird der Parameter
                     * MailsNotEqualError auf der register.jsp gesetzt und die
                     * angegebene Nachricht angezeigt.
                     */
                    else if (!request.getParameter("mail1").equals(request.getParameter("mail2"))) {
                        request.setAttribute("MailsNotEqualError", "Emails stimmen nicht überein!");
                    } /**
                     * Testen, ob die eingegebenen Passwörter übereinstimmen.
                     * Wenn sie nicht übereinstimmen, wird der Parameter
                     * PasswordsNotEqualError gesetzt und die angegebene
                     * Nachricht angezeigt.
                     */
                    else if (!request.getParameter("password1").equals(request.getParameter("password2"))) {
                        request.setAttribute("PasswordsNotEqualError", "Passwörter stimmen nicht überein!");
                    } /**
                     * Wenn alle bisherigen Tests negativ waren, kann der User
                     * angelegt werden. Dazu werden die benötigten Parameter aus
                     * der register.jsp an die UserSessionBean weitergegeben.
                     */
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
                        session.setAttribute("user", user);
                    }
                    if (user == null) {
                        request.getRequestDispatcher("/register.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/personalArea.jsp").forward(request, response);
                    }
                    break;
                case "search":
                    List<Car> carList;
                    /**
                     * Wenn keine Auswahl getroffen wurde, zeige alle Modelle
                     * aller Marken auf der result.jsp an.
                     */
                    if (request.getParameter("brand").equals("0") && request.getParameter("model").equals("0")) {
                        carList = carBean.getListOfCars();
                        session.setAttribute("carList", carList);
                        request.getRequestDispatcher("/result.jsp").forward(request, response);
                    }/**
                     * Es wurde nur bei der Marke eine Auswahl getroffen. Zeige
                     * alle Modelle der Marke auf der result.jsp an.
                     */
                    else if (!request.getParameter("brand").equals("0") && request.getParameter("model").equals("0")) {
                        carList = carBean.getListOfCarsOfSelectedBrand(request.getParameter("brand"));
                        session.setAttribute("carList", carList);
                        request.getRequestDispatcher("/result.jsp").forward(request, response);
                    } /**
                     * Es wurde bei Marke und Modell eine Auswahl getroffen.
                     * Zeige alle Objekte des Modells auf der result.jsp an.
                     */
                    else if (request.getParameter("brand").equals("0") && !request.getParameter("model").equals("0")) {
                        carList = carBean.getListOfCarsOfSelectedModel(request.getParameter("model"));
                        session.setAttribute("carList", carList);
                        request.getRequestDispatcher("/result.jsp").forward(request, response);
                    } else if (!request.getParameter("brand").equals("0") && !request.getParameter("model").equals("0")) {
                        carList = carBean.getListOfCarsOfSelectedModel(request.getParameter("model"));
                        session.setAttribute("carList", carList);
                        request.getRequestDispatcher("/result.jsp").forward(request, response);
                        break;
                    }

                case "logout":
                    if (sessionUser != null) {
                        session.setAttribute("user", null);
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                    }
                    break;
                case "details":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Car car = carBean.getCarById(id);
                    session.setAttribute("car", car);
                    request.getRequestDispatcher("/details.jsp").forward(request, response);
                    break;
                case "rentOverview":
                    id = Integer.parseInt(request.getParameter("id"));
                    car = carBean.getCarById(id);
                    if (sessionUser == null) {
                        request.setAttribute("NotLoggedInError", "Bitte melden Sie sich an, bevor Sie buchen");
                        request.getRequestDispatcher("/details.jsp").forward(request, response);
                    } else {
                        if (request.getParameter("startdate").equals("")) {
                            request.setAttribute("WrongStartDate", "Bitte geben Sie ein Startdatum ein");
                            request.getRequestDispatcher("/details.jsp").forward(request, response);
                        } else if (request.getParameter("enddate").equals("")) {
                            request.setAttribute("WrongEndDate", "Bitte geben Sie ein Enddatum ein");
                            request.getRequestDispatcher("/details.jsp").forward(request, response);
                        } else {
                            Rent rent = rentBean.prepareRent(request.getParameter("startdate"),
                                    request.getParameter("enddate"), sessionUser, car);
                            if (rent != null) {
                                session.setAttribute("rent", rent);
                                request.getRequestDispatcher("/rentOverview.jsp").forward(request, response);
                            }
                        }
                    }
                    break;
                case "rent":
                    Rent rent = (Rent) session.getAttribute("rent");
                    rentBean.blockCar(rent.getCarmodelId());
                    rentBean.persistRent(rent);
                    request.setAttribute("SuccessfulRent", "Buchung wurde erfolgreich ausgeführt");
                    request.getRequestDispatcher("/personalArea.jsp").forward(request, response);
                    break;
                default:

            }
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
