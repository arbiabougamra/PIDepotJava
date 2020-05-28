/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.SQLException;

/**
 *
 * @author PCS
 */
public interface IserviceCategorie <T>{
    void AddCategorie(T t) throws SQLException;
    boolean DeleteCategorie(T t) throws SQLException;
    boolean UpdateCategorie(T t) throws SQLException;
    void InitCategorie() throws SQLException;
   
    
}
