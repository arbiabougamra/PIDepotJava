/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CONNECTION.DataSource;
import Entity.Categorie;
import Entity.Produit;
import Services.IserviceCategorie;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PCS
 */
public class CategorieInterfaceController implements Initializable , IserviceCategorie<ActionEvent>{
    private DataSource conn;
    private PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton add_btn;
    @FXML
    private TableView<Categorie> Table_categorie;
    @FXML
    private TableColumn<?, ?> nom_col;
    private ResultSet rs;
    private ObservableList<Categorie> data;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = DataSource.getInstance();
        try {
            InitCategorie();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @Override
    public void AddCategorie(ActionEvent t) throws SQLException {
        try {
            stage = (Stage) add_btn.getScene().getWindow();
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/GUI/CategorieADDInterface.fxml"));
            Parent root = (Parent) Loader.load();
            Stage stage = new Stage();
            stage.setTitle("Categorie ADD");
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean DeleteCategorie(ActionEvent t) throws SQLException {
       conn = DataSource.getInstance();
        int selectedIndex = Table_categorie.getSelectionModel().getSelectedIndex();
        Categorie c = (Categorie) Table_categorie.getSelectionModel().getSelectedItem();
        int tempItemTag = c.getId();
         if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + c.getNom()+ " ?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                conn = DataSource.getInstance();
                String query1 = "DELETE FROM categorie WHERE id=?";
                pst = conn.getCnx().prepareStatement(query1);
                pst.setInt(1, tempItemTag);
                pst.execute();
                Table_categorie.getItems().remove(selectedIndex);
            }
        }
        return true;
    }

    @Override
    public boolean UpdateCategorie(ActionEvent t) throws SQLException {
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/CategorieUPDATE.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("UPDATE");
            CategorieUPDATEController upc = fxmlLoader.getController();
            upc.TranslatingData(Table_categorie.getSelectionModel().getSelectedItem());
            stage.setScene(new Scene(root));
            stage.show();
       
        } catch (IOException ex) {
            Logger.getLogger(CategorieInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
            return true;
    }
        
    
    

    @Override
    public void InitCategorie() throws SQLException {
       conn = DataSource.getInstance();

        try {

            data = FXCollections.observableArrayList();
            String sql = "SELECT * FROM categorie";
            rs = conn.getCnx().createStatement().executeQuery(sql);

            while (rs.next()) {
                conn = DataSource.getInstance();
                
                data.add(new Categorie(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nom"));
       

        Table_categorie.setItems(null);
       Table_categorie.setItems(data);
    }
    
    @FXML
    private void refresh(ActionEvent event) throws IOException {
        
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage s1 = new Stage();
        FXMLLoader L = new FXMLLoader();
        Pane root = L.load(getClass().getResource("/GUI/ContentArea.fxml"));
        Scene c = new Scene(root);

        s1.setScene(c);
        s1.centerOnScreen();
        s1.show();

    }
     
    
    
    @FXML
    private void selectionne(MouseEvent event) throws SQLException, MalformedURLException {
        
    conn= DataSource.getInstance();
    int indexSelected = Table_categorie.getSelectionModel().getSelectedIndex();
    Categorie c = (Categorie) Table_categorie.getSelectionModel().getSelectedItem();
    conn= DataSource.getInstance();
    int id = c.getId();
    
    
   
    
}
    
}
