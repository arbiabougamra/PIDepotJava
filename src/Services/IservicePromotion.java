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
public interface IservicePromotion <T> {
     void AddPromotion(T t) throws SQLException;
    boolean DeletePromotion(T t) throws SQLException;
    boolean UpdatePromotion(T t) throws SQLException;
    void InitPromotion() throws SQLException;
}
