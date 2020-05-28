/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import CONNECTION.DataSource;
import Entity.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PCS
 */
public class ProduitService {
    
            private Statement ste;

    
     public List<Produit> readAll(){
          
         List <Produit> arr = new ArrayList<>();
         Connection conn = DataSource.getInstance().getCnx();
        try {
            //*********
            PreparedStatement ps = conn.prepareStatement("select * from produit order by nom");
            ResultSet rs  = ps.executeQuery();
            while(rs.next()){
            Produit p =new Produit();
            p.setId(rs.getInt("id"));
            //p.setCategorie(pservice. s.getInt("id"));
           p.setNom(rs.getString("nom"));
            p.setPrix_Mg(rs.getDouble("prix_Mg"));
            p.setNomfile(rs.getString("nomfile"));
           p.setMarque(rs.getString("marque"));
           p.setQuantite(rs.getInt("quantite"));
            arr.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arr;
    }
     
      public int numberevent () throws SQLException{
         int y=0;
                  Connection con = DataSource.getInstance().getCnx();

           ste=con.createStatement() ;
           ResultSet rs=ste.executeQuery("SELECT COUNT(*) as total FROM produit ");
           while(rs.next())
           {
                y = rs.getInt("total");
               
               
           }
           System.out.println("total number : "+y);
           return y;
         
     }
    
}
