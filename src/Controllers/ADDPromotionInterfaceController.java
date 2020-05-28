/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CONNECTION.DataSource;
import Entity.Produit;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PCS
 */
public class ADDPromotionInterfaceController implements Initializable {
    @FXML
    private ComboBox<String> produit_field_box;
   private DataSource conn;

    private PreparedStatement pst;
    private ResultSet rs;
    @FXML
    private DatePicker datef_promo;
   
  
    @FXML
    private JFXTextField Valeur_tf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                 try {
                     conn = DataSource.getInstance();
                     
                     ObservableList<String> lst=FXCollections.observableArrayList();
                     
                     String sql = "SELECT * FROM produit";
                     rs = conn.getCnx().createStatement().executeQuery(sql);
                                          
                     try {
                         while(rs.next())
                         {
                             lst.add(rs.getString(3));
                         }
                     } catch (SQLException ex) {
                         Logger.getLogger(ADDPromotionInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     produit_field_box.setItems(lst);
                     
                     // TODO
                 } catch (SQLException ex) {
            Logger.getLogger(ADDPromotionInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
     
    

     @FXML
    public void AddPromotion(ActionEvent t) throws SQLException {
          
        try {
           
             String name = produit_field_box.getValue();
            String sql = "insert into promotion (id_produit,valeur,datef) values (?,?,?)";
            
            //Integer.valueOf(Idm_tf.getText());
            Produit Prod = findProduitByName(name);
            double p = findProduitByName(name).getNvprix();
            System.out.println("Controllers.ADDPromotionInterfaceController.AddPromotion()"+p);

            double valeur = Integer.valueOf(Valeur_tf.getText());
            
           
            Date df = java.sql.Date.valueOf(datef_promo.getValue());
           
            try {
                conn = DataSource.getInstance();
             
                pst = conn.getCnx().prepareStatement(sql);
                pst.setInt(1, findProduitByName(name).getId());
                pst.setDouble(2, valeur);
                pst.setDate(3, df);
                Prod.setNvprix((p*(100-valeur))/100);
            
                nvpProduit(Prod);
                int i = pst.executeUpdate();
                if (i == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFORMATION DIALOG");
                    alert.setHeaderText(null);
                    alert.setContentText("Promo HAS BEEN INSERTED");
                    alert.showAndWait();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            pst.close();
            CloseStageAutomaticly(t);
        } catch (IOException ex) {
            Logger.getLogger(ADDPromotionInterfaceController.class.getName()).log(Level.SEVERE, null,ex);
        }
    }
            
    
            @FXML
            private void CloseStageAutomaticly(Event e) throws IOException{
            final Node source = (Node) e.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            }
            
             public void nvpProduit( Produit p )
            {
                try {
             PreparedStatement pst=conn.getCnx().prepareStatement("UPDATE `produit` SET `nvprix`=? WHERE `id` = ?");
             pst.setDouble(1,p.getNvprix());
             pst.setInt(2,p.getId());
             pst.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(ADDPromotionInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
             
             
            public Produit findProduitByName(String  name) {
                    try {
             Produit evt=new Produit();
             int c=0;
             String req="select * from produit where nom='"+name+"'";
             Statement st=conn.getCnx().createStatement();
             ResultSet rs=st.executeQuery(req);
             while(rs.next())
             {
                 evt.setId(rs.getInt(1));
                evt.setMarque(rs.getString(8));
                evt.setNvprix(rs.getDouble(12));
                 c++;
                         }
             if(c==0)
             {
                 return null;
             }else {
                 return evt;
             }
         } catch (SQLException ex) {
             Logger.getLogger(ADDPromotionInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
             return null;

         }
       } 
      
    
          
      
        

}
