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

public class CarRentalServlet extends HttpServlet {

    @EJB
    private UserSessionBeanLocal userBean;
    @EJB
    private AdressSessionBeanLocal adressBean;
    @EJB
    private CarSessionBeanLocal carBean;
    @EJB
    private RentSessionBeanLocal rentBean;

    private User sessionUser;
    private List<Adress> sessionAdressList;
    private List<Rent> sessionRentList;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String currentStep = request.getParameter("step");
        HttpSession session = request.getSession();
        session.setAttribute("action", currentStep);

        sessionUser = (User) session.getAttribute("user");
        sessionAdressList = (List<Adress>) session.getAttribute("adressList");
        sessionRentList = (List<Rent>) session.getAttribute("rentList");

        List<String> brandList = carBean.getNameList("brand");
        List<String> modelList = carBean.getNameList("model");
        session.setAttribute("brandList", brandList);
        session.setAttribute("modelList", modelList);

        if (currentStep == null) {
            request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
        } else {
            switch (currentStep) {
                case "index":
                    request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    break;
                case "loginPage":
                    if (sessionUser == null) {
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    }
                    break;
                case "login":
                    if (sessionUser == null) {
                        User user = userBean.login(request.getParameter("login"), request.getParameter("password"));
                        session.setAttribute("user", user);
                        if (user == null) {
                            request.setAttribute("LoginError", "Fehler beim Login");
                            request.getRequestDispatcher("/login.jsp").forward(request, response);
                        } else {
                            request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                        }
                    } else {
                        request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    }
                    break;
                case "registerPage":
                    if (sessionUser == null) {
                        request.getRequestDispatcher("/register.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    }
                    break;
                case "register":
                    if (sessionUser == null) {
                        User user = null;
                        /**
                         * Testen ob es nicht ausgefüllte Felder gibt. Wenn ein
                         * Feld oder mehrere Felder leer sind, wird der
                         * Parameter EmptyFieldError auf der register.jsp
                         * gesetzt und die angegebene Nachricht angezeigt.
                         */
                        if (request.getParameter("title").isEmpty() || request.getParameter("firstname").isEmpty()
                                || request.getParameter("lastname").isEmpty() || request.getParameter("birthday").isEmpty()
                                || request.getParameter("mail1").isEmpty() || request.getParameter("mail2").isEmpty()
                                || request.getParameter("password1").isEmpty() || request.getParameter("password2").isEmpty()
                                || request.getParameter("street").isEmpty() || request.getParameter("housenumber").isEmpty()
                                || request.getParameter("postalcode").isEmpty() || request.getParameter("city").isEmpty()) {
                            request.setAttribute("password1", null);
                            request.setAttribute("password2", null);
                            request.setAttribute("EmptyFieldError", "Bitte alle mit * markierten Felder ausfüllen");
                            request.getRequestDispatcher("/register.jsp").forward(request, response);
                        } /**
                         * Testen, ob die eingegebene Mail schon benutzt wird.
                         * Wenn ja, wird der Parameter MailInUseError auf der
                         * register.jsp gesetzt und die angegebene Nachricht
                         * angezeigt.
                         */
                        else if (userBean.mailAlreadyUsed(request.getParameter("mail1"))) {
                            request.setAttribute("mail1", null);
                            request.setAttribute("mail2", null);
                            request.setAttribute("password1", null);
                            request.setAttribute("password2", null);
                            request.setAttribute("MailInUseError", "Email bereits benutzt!");
                            request.getRequestDispatcher("/register.jsp").forward(request, response);
                        } /**
                         * Testen, ob es sich bei der eingegebenen Mail, um eine
                         * gültige Mail handelt. Wenn ja, wird der Parameter
                         * IllegalMailError auf der register.jsp gesetzt und die
                         * angegebene Nachricht angezeigt.
                         */
                        else if (!Validator.validateMail(request.getParameter("mail1"))) {
                            request.setAttribute("mail1", null);
                            request.setAttribute("mail2", null);
                            request.setAttribute("password1", null);
                            request.setAttribute("password2", null);
                            request.setAttribute("IllegalMailError", "Keine gültige Mailadresse");
                            request.getRequestDispatcher("/register.jsp").forward(request, response);
                        } /**
                         * Testen, ob Mail und Wiederholung übereinstimmen. Wenn
                         * sie nicht übereinstimmen, wird der Parameter
                         * MailsNotEqualError auf der register.jsp gesetzt und
                         * die angegebene Nachricht angezeigt.
                         */
                        else if (!request.getParameter("mail1").equals(request.getParameter("mail2"))) {
                            request.setAttribute("mail1", null);
                            request.setAttribute("mail2", null);
                            request.setAttribute("password1", null);
                            request.setAttribute("password2", null);
                            request.setAttribute("MailsNotEqualError", "Emails stimmen nicht überein!");
                            request.getRequestDispatcher("/register.jsp").forward(request, response);
                        } /**
                         * Testen, ob die eingegebenen Passwörter
                         * übereinstimmen. Wenn sie nicht übereinstimmen, wird
                         * der Parameter PasswordsNotEqualError gesetzt und die
                         * angegebene Nachricht angezeigt.
                         */
                        else if (!request.getParameter("password1").equals(request.getParameter("password2"))) {
                            request.setAttribute("password1", null);
                            request.setAttribute("password2", null);
                            request.setAttribute("PasswordsNotEqualError", "Passwörter stimmen nicht überein!");
                            request.getRequestDispatcher("/register.jsp").forward(request, response);
                        } /**
                         * Wenn alle bisherigen Tests negativ waren, kann der
                         * User angelegt werden. Dazu werden die benötigten
                         * Parameter aus der register.jsp an die UserSessionBean
                         * weitergegeben.
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
                            sessionUser = (User) session.getAttribute("user");
                            List<Adress> adressList = adressBean.getAdresses(sessionUser);
                            session.setAttribute("adressList", adressList);
                            sessionAdressList = (List<Adress>) session.getAttribute("adressList");
                        }
                        if (user == null) {
                            request.getRequestDispatcher("/register.jsp").forward(request, response);
                        } else {
                            request.getRequestDispatcher("/personalArea.jsp?step=personal").forward(request, response);
                        }
                    } else {
                        request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
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
                    }
                    break;
                case "logout":
                    if (sessionUser == null) {
                        request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    } else {
                        session.removeAttribute("user");
                        session.invalidate();
                        request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    }
                    break;
                case "details":
                    if (request.getParameter("id") == null) {
                        request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    } else {
                        int id = Integer.parseInt(request.getParameter("id"));
                        Car car = carBean.getCarById(id);
                        session.setAttribute("car", car);
                        request.getRequestDispatcher("/details.jsp").forward(request, response);
                    }
                    break;
                case "rentOverview":
                    if (request.getParameter("id") == null) {
                        request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    } else {
                        if (sessionUser == null) {
                            request.setAttribute("NotLoggedInError", "Bitte melden Sie sich an, bevor Sie buchen");
                            request.getRequestDispatcher("/details.jsp").forward(request, response);
                        } else {
                            int id = Integer.parseInt(request.getParameter("id"));
                            Car car = carBean.getCarById(id);
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
                    }
                    break;
                case "rent":
                    if (sessionUser == null) {
                        request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    } else {
                        if (sessionAdressList == null) {
                            List<Adress> adressList = adressBean.getAdresses(sessionUser);
                            session.setAttribute("adressList", adressList);
                            sessionAdressList = (List<Adress>) session.getAttribute("adressList");
                        }
                        Rent rent = (Rent) session.getAttribute("rent");
                        if (rent.getCarmodelId().isAvailable()) {
                            rentBean.blockCar(rent.getCarmodelId());
                            rentBean.persistRent(rent);
                            request.setAttribute("SuccessfulRent", "Buchung wurde erfolgreich ausgeführt");
                            request.getRequestDispatcher("/personalArea.jsp?step=personal").forward(request, response);
                        } else {
                            request.setAttribute("RentError", "Fehler bei der Buchung");
                            request.getRequestDispatcher("/personalArea.jsp?step=personal").forward(request, response);
                        }
                    }
                    break;
                case "impressum":
                    request.getRequestDispatcher("/impressum.jsp").forward(request, response);
                    break;
                case "personal":
                    if (sessionUser == null) {
                        request.setAttribute("NotLoggedInError", "Sie müssen angemeldet sein, um diese Seite zu sehen");
                        request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    } else {
                        List<Adress> adressList = adressBean.getAdresses(sessionUser);
                        session.setAttribute("adressList", adressList);
                        request.getRequestDispatcher("/personalArea.jsp?step=personal").forward(request, response);
                    }
                    break;
                case "adressChanges":
                    if (sessionUser == null) {
                        request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/changes.jsp").forward(request, response);
                    }
                    break;
                case "pwdChanges":
                    if (sessionUser == null) {
                        request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/changes.jsp").forward(request, response);
                    }
                    break;
                case "persDataChanges":
                    if (sessionUser == null) {
                        request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/changes.jsp").forward(request, response);
                    }
                    break;
                case "changepwd":
                    if (sessionUser == null) {
                        request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    } else {
                        if (request.getParameter("oldPassword").isEmpty()
                                || request.getParameter("newPassword").isEmpty()
                                || request.getParameter("newPassword2").isEmpty()) {
                            request.setAttribute("EmptyFieldError", "Bitte alle Felder ausfüllen");
                            request.getRequestDispatcher("/changes.jsp").forward(request, response);
                        } else if (!request.getParameter("newPassword")
                                .equals(request.getParameter("newPassword2"))) {
                            request.setAttribute("PasswordsNotEqualError", "Passwörter stimmen nicht überein");
                            request.getRequestDispatcher("/changes.jsp").forward(request, response);
                        } else {
                            if (userBean.changePassword(sessionUser,
                                    request.getParameter("oldPassword"),
                                    request.getParameter("newPassword"))) {
                                request.setAttribute("passwordChanged", "Passwort erfolgreich aktualisiert");
                                request.getRequestDispatcher("/personalArea.jsp?step=personal").forward(request, response);

                            } else {
                                request.setAttribute("passwordChanged", "Fehler bei der Passwortaktualisierung, bitte versuchen Sie es später noch einmal");
                                request.getRequestDispatcher("/personalArea.jsp?step=personal").forward(request, response);
                            }
                        }
                    }
                    break;
                case "changPersData":
                    if (sessionUser == null) {
                        request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    } else {
                        if (request.getParameter("firstname").isEmpty() || request.getParameter("lastname").isEmpty()
                                || request.getParameter("email1").isEmpty() || request.getParameter("email2").isEmpty()) {
                            request.setAttribute("EmptyFieldError", "Bitte alle Felder ausfüllen");
                            request.getRequestDispatcher("/changes.jsp").forward(request, response);
                        } else if (!sessionUser.getMail().equals(request.getParameter("email1"))
                                && userBean.mailAlreadyUsed(request.getParameter("email1"))) {
                            request.setAttribute("MailAlreadyUsedError", "Mailadresse ist bereist vorhanden");
                            request.getRequestDispatcher("/changes.jsp").forward(request, response);
                        } else if (!sessionUser.getMail().equals(request.getParameter("email1"))
                                && !Validator.validateMail(request.getParameter("email1"))) {
                            request.setAttribute("MailNotValideError", "Bitte geben Sie eine gültige Mailadresse ein");
                            request.getRequestDispatcher("/changes.jsp").forward(request, response);
                        } else if (!request.getParameter("email1").equals(request.getParameter("email2"))) {
                            request.setAttribute("MailNotEqualError", "Mailadressen stimmen nicht überein");
                            request.getRequestDispatcher("/changes.jsp").forward(request, response);
                        } else {
                            if (!sessionUser.getFirstname().equals(request.getParameter("firstname"))) {
                                userBean.changeFirstname(sessionUser, request.getParameter("firstname"));
                            }
                            if (!sessionUser.getLastname().equals(request.getParameter("lastname"))) {
                                userBean.changeLastname(sessionUser, request.getParameter("lastname"));
                            }
                            if (!sessionUser.getMail().equals(request.getParameter("email1"))) {
                                userBean.changeMail(sessionUser, request.getParameter("email1"));
                            }
                            request.setAttribute("persDataChanged", "Persönliche Daten aktualisiert");
                            request.getRequestDispatcher("/personalArea.jsp?step=personal").forward(request, response);
                        }
                    }
                    break;
                case "changAdress":
                    if (sessionUser == null) {
                        request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    } else {
                        if (request.getParameter("street").isEmpty()
                                || request.getParameter("housenumber").isEmpty()
                                || request.getParameter("postalcode").isEmpty()
                                || request.getParameter("city").isEmpty()
                                || request.getParameter("country").isEmpty()) {
                            request.setAttribute("EmptyFieldError", "Bitte alle Felder ausfüllen");
                            request.getRequestDispatcher("/changes.jsp").forward(request, response);
                        } else {
                            if (sessionUser.getAdressCollection().size() == 1) {
                                List<Adress> adressList = (List<Adress>) session.getAttribute("adressList");
                                Adress adress = adressList.get(0);
                                if (!adress.getStreet().equals(request.getParameter("street"))) {
                                    adressBean.changeCity(adress, request.getParameter("street"));
                                }
                                if (!adress.getHousenumber().equals(request.getParameter("housenumber"))) {
                                    adressBean.changeHousenumber(adress, request.getParameter("housenumber"));
                                }
                                if (!adress.getPostalCode().equals(request.getParameter("postalcode"))) {
                                    adressBean.changePostalcode(adress, request.getParameter("postalcode"));
                                }
                                if (!adress.getCity().equals(request.getParameter("city"))) {
                                    adressBean.changeCity(adress, request.getParameter("city"));
                                }
                                if (!adress.getCountry().equals(request.getParameter("country"))) {
                                    adressBean.changeCountry(adress, request.getParameter("country"));
                                }
                                if (!adress.getRegion().equals(request.getParameter("region"))) {
                                    adressBean.changeRegion(adress, request.getParameter("region"));
                                }
                                request.setAttribute("AdressChanged", "Adresse aktualisiert");
                                request.getRequestDispatcher("/personalArea.jsp?step=personal").forward(request, response);
                            }
                        }
                    }
                    break;
                case "deleteAcc":
                    if (sessionUser == null) {
                        request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    } else {
                        User user = sessionUser;
                        List<Rent> rentList = rentBean.getRents(user);
                        if (rentList == null) {
                            request.getRequestDispatcher("/confirmDelete.jsp").forward(request, response);
                        } else {
                            if (rentBean.activeRents(user)) {
                                request.setAttribute("ActiveRentsError", "Sie können Ihr Konto nicht löschen, wenn Sie aktive Buchungen haben");
                                request.getRequestDispatcher("/personalArea.jsp?step=personal").forward(request, response);
                            } else {
                                request.getRequestDispatcher("/confirmDelete.jsp").forward(request, response);
                            }
                        }

                    }
                    break;
                case "confirmDelete":
                    if (sessionUser == null) {
                        request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    } else {
                        if (request.getParameter("email").isEmpty() || request.getParameter("password").isEmpty()) {
                            request.setAttribute("EmptyFieldError", "Bitte alle Felder ausfüllen");
                            request.getRequestDispatcher("/confirmDelete.jsp").forward(request, response);
                        } else if (!sessionUser.getMail().equals(request.getParameter("email"))) {
                            request.setAttribute("WrongMailError", "Bitte geben Sie die richtige Mail an");
                            request.getRequestDispatcher("/confirmDelete.jsp").forward(request, response);
                        } else {
                            if (userBean.confirmPassword(request.getParameter("email"), request.getParameter("password"))) {
                                User user = sessionUser;
                                List<Adress> adressList = adressBean.getAdresses(user);
                                if (adressList == null) {
                                } else {
                                    for (Adress adress : adressList) {
                                        adressBean.removeAdress(adress);
                                    }
                                }
                                List<Rent> rentList = rentBean.getRents(user);
                                if (rentList == null) {
                                } else {
                                    for (Rent rent : rentList) {
                                        rentBean.cancelRent(rent);
                                    }
                                }
                                userBean.removeUser(user);
                                session.removeAttribute("user");
                                session.removeAttribute("adressList");
                                session.invalidate();
                                request.setAttribute("AccDeleteDone", "Ihr Konto wurde erfolgreich gelöscht");
                                request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                            } else {
                                request.setAttribute("PasswordError", "Falsches Passwort");
                                request.getRequestDispatcher("/confirmDelete.jsp").forward(request, response);
                            }
                        }
                    }
                    break;
                case "personalRents":
                    if (sessionUser == null) {
                        request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    } else {
                        User user = sessionUser;
                        List<Rent> rentList = rentBean.getRents(user);
                        session.setAttribute("rentList", rentList);
                        request.getRequestDispatcher("/personalRents.jsp").forward(request, response);
                    }
                    break;
                case "cancelRent":
                    if (request.getParameter("rentId") == null) {
                        request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    } else {
                        if (sessionUser == null) {
                            request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                        } else {
                            int id = Integer.parseInt(request.getParameter("rentId"));
                            Rent rent = rentBean.getRentById(id);
                            session.setAttribute("rent", rent);
                            request.getRequestDispatcher("/cancelRent.jsp").forward(request, response);
                        }
                    }
                    break;
                case "canceled":
                    if (sessionUser == null) {
                        request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    } else {
                        Rent rent = (Rent) session.getAttribute("rent");
                        carBean.unBlockCar(rent.getCarmodelId());
                        rentBean.cancelRent(rent);
                        request.setAttribute("RentCancelled", "Buchung wurde storniert");
                        request.getRequestDispatcher("/personalArea.jsp").forward(request, response);
                    }
                    break;
                default:
                    request.getRequestDispatcher("/index.jsp?step=index").forward(request, response);
                    break;
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
