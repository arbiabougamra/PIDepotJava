/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.User;
import java.util.List;

/**
 *
 * @author Bia
 */
public interface IUserServices  {
    
public boolean  Authentification(User u);
public void create(User u);
public void update(User u);
public void delete(User u);
public List<User> findAll();
public String CheckRole(User u );
    
}
