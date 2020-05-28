/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CONNECTION.DataSource;
import Entity.Produit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import Services.IserviceStock;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Etudiant
 */
public class StockUPDATEController implements Initializable, IserviceStock<ActionEvent> {
    @FXML
    private JFXButton Browse_btn;
    @FXML
    private JFXTextField pic_tf;
    @FXML
    private JFXButton cancel_btn;
    @FXML
    private JFXButton confirm_btn;
    @FXML
    private DatePicker datep2_tf;
    @FXML
    private DatePicker datep1_tf;
    @FXML
    private JFXTextField quantite_tf;
     @FXML
    private JFXTextField prix_tf;
    @FXML
    private JFXTextField marque_tf;
    @FXML
    private JFXTextField nom_tf;
      @FXML
    private ComboBox<String> categorie_field_box;
    
    private PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    private ResultSet rs;
    private DataSource conn;
    
    private Produit selectedS;
    @FXML
    private JFXTextField id_tf;
    @FXML
    private ImageView imgview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         conn = DataSource.getInstance();
        // TODO
    }    

    @Override
    @FXML
    public void AddStock(ActionEvent t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean DeleteStock(ActionEvent t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void TranslatingData(Produit m) {
        selectedS = m;
        id_tf.setText(String.valueOf(selectedS.getId()));
        nom_tf.setText(selectedS.getNom());
        marque_tf.setText(selectedS.getMarque());
        quantite_tf.setText(String.valueOf(selectedS.getQuantite()));
        pic_tf.setText(selectedS.getNomfile());
        prix_tf.setText(String.valueOf(selectedS.getPrix()));
         }
    
    
    
    @Override
    @FXML
    public boolean UpdateStock(ActionEvent t) throws SQLException {
           conn = DataSource.getInstance();
        try {
       

            String sql = "update produit set nom=?,quantite=?,prix=?,marque=?,dates=?,datee=?,nomfile=? where id=?";

            pst = conn.getCnx().prepareStatement(sql);
            pst.setString(1, nom_tf.getText());
            pst.setInt(2, Integer.parseInt(quantite_tf.getText()));
            pst.setDouble(3, Integer.parseInt(prix_tf.getText()));
            pst.setString(4, marque_tf.getText());
            pst.setDate(5, java.sql.Date.valueOf(datep1_tf.getValue()));
            pst.setDate(6, java.sql.Date.valueOf(datep2_tf.getValue()));
            pst.setString(7, pic_tf.getText() );
            pst.setInt(8, selectedS.getId() );
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION DIALOG");
            alert.setHeaderText(null);
            alert.setContentText("Stock HAS BEEN UPDATED");
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
    public void InitStock() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void search(ActionEvent t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
              
    
    
    @FXML
    private void browse(ActionEvent event) {
        
        
               FileChooser filechooser = new FileChooser();
                
                filechooser.getExtensionFilters().addAll(
               // new ExtensionFilter("Text Files","*.txt"),
                new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif"));
         //new ExtensionFilter("All Files","*."));
             
        
         File file = filechooser.showOpenDialog(Browse_btn.getScene().getWindow());
         if(file!=null){
            
            pic_tf.setText(file.getAbsolutePath());
            Image img=new Image(file.toURI().toString(),172,172,true,true);
             imgview.setImage(img);
         }

         
         
         }
    
    @FXML 
    private void CloseStageAutomaticly(Event e) throws IOException{
        final Node source = (Node) e.getSource();
    final Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
    }
     
    
}
