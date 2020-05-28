/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CONNECTION.DataSource;
import Entity.Produit;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import Services.IserviceStock;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Produit
 */
public class StockADDInterfaceController implements Initializable, IserviceStock<ActionEvent> {

    @FXML
    private JFXTextField nom_tf;
    @FXML
    private JFXTextField marque_tf;
    @FXML
    private JFXTextField quantite_tf;
    @FXML
    private JFXTextField prix_tf;
    @FXML
    private DatePicker datep1_tf;
    @FXML
    private DatePicker datep2_tf;
    private Connection conn = DataSource.getInstance().getCnx();
    
    private PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    @FXML
    private ComboBox<String> categorie_field_box;
    private ObservableList<String> Categories=FXCollections.observableArrayList(getCategorie());
    
    
    private ResultSet rs;
    
    @FXML
    private JFXTextField pic_tf;
    @FXML
    private JFXButton Browse_btn;
    @FXML
    private JFXButton confirm_btn;
    @FXML
    private JFXButton cancel_btn;
    @FXML
    private ImageView imgview;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categorie_field_box.setItems(Categories);

        }    
         
 
    public List<String> getCategorie() {
        List<String> arr = new ArrayList<>();

        try {

            String sql = "SELECT * FROM categorie";
            rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                
                arr.add(rs.getString(2));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
        return arr;
    }
    
    
  
    
    
    @Override
    @FXML
    public void AddStock(ActionEvent t) throws SQLException {
          
        try {
           
            String url2 = pic_tf.getText().replaceAll("//", "////");
            String sql = "insert into produit (nom,quantite,prix,id_categorie,marque,dates,datee,nomfile) values (?,?,?,?,?,?,?,?)";
            
            //Integer.valueOf(Idm_tf.getText());
            String name = nom_tf.getText();
            int quan = Integer.valueOf(quantite_tf.getText());
            int categorie = Integer.valueOf(categorie_field_box.getValue());
            String marque = marque_tf.getText();
            double prix = Integer.valueOf(prix_tf.getText());
            Date d1 = java.sql.Date.valueOf(datep1_tf.getValue());
            Date d2 = java.sql.Date.valueOf(datep2_tf.getValue());
            String aa = pic_tf.getText();
            
            
            try {
                pst = conn.prepareStatement(sql);
                // pst.setInt(1, Integer.valueOf(Idm_tf.getText()));
                pst.setString(1, name);
                pst.setInt(2, quan);
                pst.setInt(4,(categorie));
                pst.setDouble(3, prix);
                pst.setString(5, marque);
                pst.setDate(6, d1);
                pst.setDate(7, d2);
                pst.setString(8, aa);
                int i = pst.executeUpdate();
                if (i == 1) {
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFORMATION DIALOG");
                    alert.setHeaderText(null);
                    alert.setContentText("Stock HAS BEEN INSERTED");
                    alert.showAndWait();
                    
                }
            } catch (SQLException ex) {
                System.out.println(ex);
                
            }
            pst.close();
            CloseStageAutomaticly(t);
        } catch (IOException ex) {
            Logger.getLogger(StockADDInterfaceController.class.getName()).log(Level.SEVERE, null,ex);

        }
    }
    
   
    
          
        

    

    @Override
    public boolean DeleteStock(ActionEvent t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean UpdateStock(ActionEvent t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        //new ExtensionFilter("All Files","*."));

        File file = filechooser.showOpenDialog(Browse_btn.getScene().getWindow());
        if (file != null) {

            pic_tf.setText(file.getName());
            Image img = new Image(file.toURI().toString());
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
