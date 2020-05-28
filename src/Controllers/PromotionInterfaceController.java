/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CONNECTION.DataSource;
import Entity.Produit;
import Entity.Promotion;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PCS
 */
public class PromotionInterfaceController implements Initializable {
        private ResultSet rs;
        private DataSource conn;
        private PreparedStatement pst;
        private PreparedStatement pst2;
        private ObservableList<Promotion> data;
        @FXML
        private TableColumn<?, ?> valeur_col;
        @FXML
        private TableColumn<?, ?> prod_col;
        @FXML
        private TableColumn<?, ?> datef_col;
        @FXML
        private TableColumn<?, ?> nvprix_col;
        @FXML
        private TableView<Promotion> Table_Promo;
        @FXML
        private ComboBox<String> produit_field_box;
        @FXML
        private JFXButton add_btn;
        private Stage stage;
        private Scene scene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         conn = DataSource.getInstance();
        try {
            InitPromotion();
        } catch (SQLException ex) {
            Logger.getLogger(PromotionInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void InitPromotion() throws SQLException {
        conn = DataSource.getInstance();

        try {

            data = FXCollections.observableArrayList();
            String sql = "SELECT * FROM promotion";
            
            rs = conn.getCnx().createStatement().executeQuery(sql);

            while (rs.next()) {
                conn = DataSource.getInstance();
               // data.addAll(data);
           data.add(new Promotion(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getDate(4)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
       
        valeur_col.setCellValueFactory(new PropertyValueFactory<>("valeur"));
        prod_col.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        datef_col.setCellValueFactory(new PropertyValueFactory<>("datef"));
       
       
        Table_Promo.setItems(null);
        Table_Promo.setItems(data);
    } 


        @FXML
        public void AddPromotion(ActionEvent t) throws SQLException {
        try {
            stage = (Stage) add_btn.getScene().getWindow();
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/GUI/ADDPromotionInterface.fxml")); //
            Parent root = (Parent) Loader.load();
            //scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Promotion ADD");
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PromotionInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     @FXML
    public boolean DeletePromotion(ActionEvent t) throws SQLException {
         
        conn = DataSource.getInstance();
        int selectedIndex = Table_Promo.getSelectionModel().getSelectedIndex();
        Promotion m = (Promotion) Table_Promo.getSelectionModel().getSelectedItem();
        
        int tempItemTag = m.getId();
      
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + m.getId_produit()+ " ?", ButtonType.YES, ButtonType.CANCEL);

            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                conn = DataSource.getInstance();
                String query1 = "UPDATE `produit` SET `nvprix`=0 WHERE `id` ="+m.getId_produit();
                String query = "DELETE FROM promotion WHERE id=?";
                pst = conn.getCnx().prepareStatement(query1);
                pst2= conn.getCnx().prepareStatement(query);
                
                System.out.println("Controllers.heloooooo.DeletePromotion()"+tempItemTag);
                pst2.setInt(1, tempItemTag);
                pst.execute();
                pst2.execute();
                Table_Promo.getItems().remove(selectedIndex);
            }
        }
        return true;
    
    }
     @FXML
    private void refresh(ActionEvent event) throws IOException {
        
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage s1 = new Stage();
        FXMLLoader L = new FXMLLoader();
        Pane root = L.load(getClass().getResource("/GUI/PromotionInterface.fxml"));
        Scene c = new Scene(root);
        s1.setScene(c);
        s1.centerOnScreen();
        s1.show();

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
                evt.setPrix(3);
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
      
       public String nomProduit(int id){
            String c = "";
        try {
            PreparedStatement pt =conn.getCnx().prepareStatement("select nom from produit where id = "+id);
            ResultSet rs = pt.executeQuery();
            
            while(rs.next()){
                c = rs.getString(3);
//System.out.println("commande [id:"+rs.getInt(1)+" ,date: "+rs.getString(2)+",id_user: "+rs.getInt(2)+"]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }        
            return c;
    }
}
