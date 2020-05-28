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
public class CategorieUPDATEController implements Initializable,IserviceCategorie<ActionEvent> {
    
    @FXML
    private JFXTextField nom_tf;
    
    @FXML
    private JFXTextField id_tf;
    private Categorie selectedC;
    private DataSource conn;
    private PreparedStatement pst;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void AddCategorie(ActionEvent t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            if (nom_tf.getText().isEmpty()){
             Alert fail = new Alert(Alert.AlertType.WARNING);
            fail.setTitle("Erreur");
            fail.setHeaderText(null);
            fail.setContentText("Entrez le nom de catégorie svp ! ");
            fail.showAndWait();}
            else {
            pst = conn.getCnx().prepareStatement(sql);
            pst.setString(1, nom_tf.getText());
             pst.setInt(2, selectedC.getId() );
         
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION DIALOG");
            alert.setHeaderText(null);
            alert.setContentText("Categorie HAS BEEN UPDATED");
            alert.showAndWait();
             pst.execute();
            pst.close();
            CloseStageAutomaticly(t);

             }} catch (SQLException el) {

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
   
     public void TranslatingData(Categorie c) {
        selectedC = c;
        id_tf.setText(String.valueOf(selectedC.getId()));
        nom_tf.setText(selectedC.getNom());
        }
     
     @FXML
      private void CloseStageAutomaticly(Event e) throws IOException{
        final Node source = (Node) e.getSource();
    final Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
    }
     
    
}
