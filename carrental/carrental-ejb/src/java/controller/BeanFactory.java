/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.naming.InitialContext;

/**
 *
 * @author Marco
 */
@Stateless
@LocalBean
public class BeanFactory {
    
 public static UserSessionBeanLocal getSessionBean()
    {
        UserSessionBeanLocal bean = null;
        try
        {
            InitialContext ctx = new InitialContext();
            bean = (UserSessionBeanLocal) ctx.lookup("java:global/EnterpriseApplication/EnterpriseApplication-ejb/SessionBean!controller.SessionBeanLocal");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return bean;
    }
}

