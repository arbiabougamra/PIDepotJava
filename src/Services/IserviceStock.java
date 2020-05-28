/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Etudiant
 */
public interface IserviceStock <T>{
    void AddStock(T t) throws SQLException;
    boolean DeleteStock(T t) throws SQLException;
    boolean UpdateStock(T t) throws SQLException;
    void InitStock() throws SQLException;
    void search(T t);
    
}