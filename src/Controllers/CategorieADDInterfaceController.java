/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CONNECTION.DataSource;
import Entity.Categorie;
import Services.IserviceCategorie;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PCS
 */
public class CategorieADDInterfaceController implements Initializable ,IserviceCategorie<ActionEvent> {

    private DataSource conn;
    private PreparedStatement pst;
    
   
    
    @FXML
    private JFXTextField nom_tf;
    private Categorie selectedC; 
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    @FXML
    public void AddCategorie(ActionEvent t) throws SQLException {
               
         conn = DataSource.getInstance();
         String sql = "insert into categorie (nom) values (?)";
         String nom = nom_tf.getText();
          if (nom_tf.getText().isEmpty()){
             Alert fail = new Alert(Alert.AlertType.WARNING);
            fail.setTitle("Erreur");
            fail.setHeaderText(null);
            fail.setContentText("Entrez le nom de catégorie svp ! ");
            fail.showAndWait();}
          else {
         pst = conn.getCnx().prepareStatement(sql);
         pst.setString(1, nom);
          
         int i = pst.executeUpdate();
            if (i == 1) {
                    
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("INFORMATION DIALOG");
             alert.setHeaderText(null);
             alert.setContentText("Categorie HAS BEEN INSERTED");
             alert.showAndWait();
              pst.close();
            try {
                CloseStageAutomaticly(t);
            } catch (IOException ex) {
                Logger.getLogger(CategorieADDInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
                }
          }
        
        
    }
        
       
        
       
    

    @Override
    public boolean DeleteCategorie(ActionEvent t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean UpdateCategorie(ActionEvent t) throws SQLException {
          conn = DataSource.getInstance();
        try {
       

            String sql = "update categorie set nom=? where id=?";

            pst = conn.getCnx().prepareStatement(sql);

            pst.setString(1, nom_tf.getText());
          
            pst.setInt(7, selectedC.getId() );
            
            
            pst.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION DIALOG");
            alert.setHeaderText(null);
            alert.setContentText("Categorie HAS BEEN UPDATED");
            alert.showAndWait();

            pst.close();
            CloseStageAutomaticly(t);

        } catch (SQLException el) {

            System.err.println(el);
        } catch (IOException ex) {
            Logger.getLogger(StockUPDATEController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public void InitCategorie() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @FXML
    private void CloseStageAutomaticly(Event e) throws IOException{
    final Node source = (Node) e.getSource();
    final Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
    }
    
}
