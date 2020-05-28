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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PCS
 */
public class AffecterMgInterfaceController implements Initializable {

    private DataSource conn;
    private PreparedStatement pst;
    private Produit selectedS;
    @FXML
    private JFXTextField quantite_mg;
    @FXML
    private JFXTextField prix_mg;
   
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
 @FXML
 public boolean AffecterMg(ActionEvent t) throws SQLException {
      conn = DataSource.getInstance();
        try {
            String sql = "update produit set nvquantite=?,prix_Mg=? where id=?";
            pst = conn.getCnx().prepareStatement(sql);
            if (quantite_mg.getText().isEmpty()){
            Alert fail = new Alert(Alert.AlertType.WARNING);
            fail.setTitle("Erreur");
            fail.setHeaderText(null);
            fail.setContentText("Entrez la quantité svp ! ");
            fail.showAndWait();}
            if (prix_mg.getText().isEmpty()){
            Alert fail = new Alert(Alert.AlertType.WARNING);
            fail.setTitle("Erreur");
            fail.setHeaderText(null);
            fail.setContentText("Entrez le prix svp ! ");
            fail.showAndWait();}
            else {
            pst.setInt(1, Integer.parseInt(quantite_mg.getText()));
            pst.setDouble(2, Integer.parseInt(prix_mg.getText()));
            pst.setInt(3, selectedS.getId() );            
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION DIALOG");
            alert.setHeaderText(null);
            alert.setContentText("Produit ajouté au Magasin");
            alert.showAndWait();
            pst.close();
            CloseStageAutomaticly(t);
        }} catch (SQLException el) {
            System.err.println(el);
        } catch (IOException ex) {
            Logger.getLogger(AffecterMgInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
         }
    
 
 
    public void TranslatingData(Produit m) {
        selectedS = m;
        quantite_mg.setText(String.valueOf(selectedS.getNvquantite()));
        prix_mg.setText(String.valueOf(selectedS.getPrix_Mg()));
       } 

    @FXML
   private void CloseStageAutomaticly(Event e) throws IOException{
        final Node source = (Node) e.getSource();
    final Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
    }
   
   
}

