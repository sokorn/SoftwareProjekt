/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Timer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Rent;

/**
 * Timer der jeden Tag um 18:30 Uhr über alle Buchungen iteriert und das
 * Auto der Buchung wieder auf verfügbar setzt, falls das Enddatum der
 * Buchung erreicht ist
 *
 * @author Marco
 */
@Singleton
@LocalBean
public class UnblockCarTimer {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Schedule(dayOfWeek = "*", minute = "45", hour = "16")
    public void automaticUnblockCarTimer(Timer timer) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(new Date());
        Date date = DateParser.parseToDate(dateString);
        Query query = entityManager.createNamedQuery("Rent.findAll");
        List<Rent> queryResult = query.getResultList();
        for (Rent rent : queryResult) {
            if (rent.getEnddate().before(date) || rent.getEnddate().equals(date)) {
                rent.getCarmodelId().setAvailable(true);
                entityManager.merge(rent.getCarmodelId());
            }
        }
        timer.cancel();
    }
}