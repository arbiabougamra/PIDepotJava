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
public interface IserviceMagasin<T>{
      void AddProduitMg(T t) throws SQLException;
    boolean DeleteProduitMg(T t) throws SQLException;
    boolean UpdateProduitMg(T t) throws SQLException;
    void InitProduitMg() throws SQLException;
    void search(T t);
}
