/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CONNECTION.DataSource;
import Entity.Produit;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author PCS
 */
public class ProduitClientInterfaceController implements Initializable {
   @FXML
    private ComboBox<String> choice;
    @FXML
    private ComboBox<String> combobox_filter;
    private ObservableList<String> ob = FXCollections.observableArrayList("marque", "categorie", "all");
    private DataSource conn;
    private PreparedStatement pst;
    private ResultSet rs;
    private ObservableList<Produit> data;
  
     @FXML
    private TableColumn<?, ?> nom_col;
    @FXML
    private TableColumn<?, ?> categorie_col;
    @FXML
    private TableColumn<?, ?> marque_col;
    
    @FXML
    private TableColumn<?, ?> nvprix_col;
    
    @FXML
    private TableColumn<?, ?> prixmg_col;
 
    @FXML
    private TableView<Produit> Table_client;
    
    @FXML
    private ImageView image_v;
    
    @FXML
    private JFXTextField tfsearchbyid;
     
    @FXML
    private TextField searsh_product_field;
   
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       conn = DataSource.getInstance();
        try {
            InitProduitClient();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
         combobox_filter.setItems(ob);
    }    
     public void InitProduitClient() throws SQLException {
        conn = DataSource.getInstance();

        try {

            data = FXCollections.observableArrayList();
           
            String sql = "SELECT * FROM produit";
           
            rs = conn.getCnx().createStatement().executeQuery(sql);
           

            while (rs.next()) {
                conn = DataSource.getInstance();
               // data.addAll(data);
       data.add(new Produit(rs.getInt(1),rs.getString(3),rs.getDouble(12),rs.getString(4),rs.getInt(2),rs.getDouble(8),rs.getInt(6)));
       
                }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prixmg_col.setCellValueFactory(new PropertyValueFactory<>("prix_Mg"));
        marque_col.setCellValueFactory(new PropertyValueFactory<>("marque"));
        categorie_col.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        nvprix_col.setCellValueFactory(new PropertyValueFactory<>("nvprix"));
       
        
        Table_client.setItems(null);
        Table_client.setItems(data);
        
    }
       @FXML
    private void selectionne(MouseEvent event) throws SQLException, MalformedURLException {
        conn= DataSource.getInstance();
        int indexSelected = Table_client.getSelectionModel().getSelectedIndex();
        Produit s = (Produit) Table_client.getSelectionModel().getSelectedItem();
         conn= DataSource.getInstance();
        int id = s.getId();
            
        try {
            data = FXCollections.observableArrayList();
            String sql = "SELECT * FROM produit where produit.id='"+id+"'";
            rs = conn.getCnx().createStatement().executeQuery(sql);

            while (rs.next()) {
                conn= DataSource.getInstance();
                String lien = rs.getString("nomfile");
                System.out.print(lien);
                String lien2 = lien.replaceAll("//", "/");
                File file = new File(lien2);
              //  String localUrl = file.toURI().toURL().toString();
                Image image = new Image(file.toURI().toString());
            //   Image image = new Image(localUrl);
                image_v.setImage(image);

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
    
    }
    
    
      @FXML
    public void search(ActionEvent t) {
                ObservableList data = Table_client.getItems();
        tfsearchbyid.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                Table_client.setItems(data);
            }
            String value = newValue.toLowerCase();
            ObservableList<Produit> subentries = FXCollections.observableArrayList();

            long count = Table_client.getColumns().stream().count();
            for (int i = 0; i < Table_client.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + Table_client.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(Table_client.getItems().get(i));
                        break;
                    }
                }
            }
            Table_client.setItems(subentries);

        });
    }
    
     @FXML
    private void combobox_filter(ActionEvent event) {
        ObservableList<String> ob = FXCollections.observableArrayList();

        if (combobox_filter.getValue().toString().equals("categorie")) {
            
            try {
                String sql = "select id from categorie";
                rs = conn.getCnx().createStatement().executeQuery(sql);
                
                while (rs.next()) {
                    ob.add(rs.getString(1));
                }
            } catch (Exception e) {
            }
        } else if (combobox_filter.getValue().toString().equals("marque")) {
            
            try {
                String sql = "select marque from produit";
                  rs = conn.getCnx().createStatement().executeQuery(sql);
               
                while (rs.next()) {
                    ob.add(rs.getString(1));
                }
            } catch (Exception e) {
            }
        } else {
            ob = null;
        }
        choice.setItems(ob);

    }
     @FXML
    private void choice(ActionEvent event) {
        ObservableList<Produit> ob = FXCollections.observableArrayList();
        String s = "";
         MagasinInterfaceController mgc = new MagasinInterfaceController();
        s = choice.getValue();
        if (combobox_filter.getValue().toString().equals("marque")) {
            ob.clear();
            ob = mgc.afficherProduitParMarque(s);
            Table_client.getItems().clear();
            Table_client.setItems(ob);
        }
            else if (combobox_filter.getValue().toString().equals("categorie")) {
               
            ob.clear();
            ob = mgc.afficherProduitParCategorie(s);
            Table_client.getItems().clear();
            Table_client.setItems(ob);
            }
            else {
                     ob.clear();
            ob = mgc.afficherProduit();
            Table_client.getItems().clear();
            Table_client.setItems(ob);
                    }
            
                    
            }
       

        }
    
    
     

