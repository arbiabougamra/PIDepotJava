/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import CONNECTION.DataSource;
import Entity.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author PCS
 */
public class CategorieService  {
     
  Connection con= DataSource.getInstance().getCnx();

    public void ajouter(Categorie c)  {
        String requete = "insert into categorie (nom) values (?)";    
        PreparedStatement ste;
      try {
          ste = con.prepareStatement(requete);
          ste.setString(1,c.getNom());
           ste.executeUpdate();
      } catch (SQLException ex) {
          Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
      }
             System.out.println("Categorie ajouté");

      
        

    }
    public void SupprimerParID(int id) {
        try {

            String requete = "DELETE FROM categorie WHERE id=" + id;
            Statement ste = con.createStatement();
            ste.executeUpdate(requete);
            System.out.println("Catégorie supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<Categorie> getListCategorie() {
        ObservableList<Categorie> list = FXCollections.observableArrayList();
        String requete = "select * from categorie";
        try {
            Statement ste = con.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
                Categorie c = new Categorie(rs.getInt("id"),rs.getString("nom"));
                
                list.add(c);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
        public int update(Categorie c) {
          String requete = "UPDATE Categorie set nom=? where id = ?";
          PreparedStatement ste;
        try {
             ste = con.prepareStatement(requete);
            ste.setString(1, c.getNom());
            ste.setInt(2, c.getId());
            System.out.println(c);
            ste.executeUpdate();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
}
