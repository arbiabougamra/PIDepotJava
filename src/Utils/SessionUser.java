/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entity.User;
import Services.UserServices;

/**
 *
 * @author firas
 */
public class SessionUser {
    
    private static final UserServices fs = new UserServices();
    
    private static SessionUser instance = null;
    private static User user = null;

    
    private SessionUser(User userConnected) {
        super();
        SessionUser.user = userConnected;  
    }
    
    private SessionUser(User userConnected, Boolean b) {
        super();
        SessionUser.user = userConnected;
    }
    
    public final static SessionUser getInstance(User userConnected) {

        if (SessionUser.instance == null) {
            SessionUser.instance = new SessionUser(userConnected);
        }
        return SessionUser.instance;
    }
    
    public final static SessionUser getFirstInstance(User userConnected) {

        if (SessionUser.instance == null) {

            SessionUser.instance = new SessionUser(userConnected,false);
      
        }
        return SessionUser.instance;
    }

    public static UserServices getFs() {
        return fs;
    }

    public static SessionUser getInstance() {
        return instance;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        SessionUser.user = user;
    }
    
}
