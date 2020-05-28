/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.DynamicView;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author PCS
 */
public class ContentAreaController implements Initializable {
    @FXML
    private VBox content_area;
    
    @FXML
    private BorderPane contents;
    
    
    @FXML
    private HBox menubar;
    
    @FXML
    private HBox idMg;
   
    boolean flag = true;
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public BorderPane getContents(){
        return contents ;
    }
    
    
    @FXML
    private void open_sidebar(ActionEvent event) throws IOException {
        BorderPane border_pane = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
        if (flag == true) {
            Parent sidebar = FXMLLoader.load(getClass().getResource("/GUI/Sidebar.fxml"));
            border_pane.setLeft(sidebar);
            flag = false;
        } else {
            border_pane.setLeft(null);
            flag = true;
        }
        
    }
  /*  @FXML
    private void magasin(MouseEvent event) throws IOException{
        DynamicView.Change_content(contents,"MagasinInterface");
    }
    */
     @FXML
    private void Magasin (MouseEvent event) throws IOException {
        DynamicView.Change_content(contents, "MagasinInterface");
    }
    
     @FXML
    private void Catégorie (MouseEvent event) throws IOException {
        DynamicView.Change_content(contents, "CategorieInterface");
    }
    
      @FXML
    private void Promo (MouseEvent event) throws IOException {
        DynamicView.Change_content(contents, "PromotionInterface");
    }
    
      @FXML
    private void Client (MouseEvent event) throws IOException {
        DynamicView.Change_content(contents, "PageClient");
    }
      @FXML
    private void Stock (MouseEvent event) throws IOException {
        DynamicView.Change_content(contents, "StockInterface");
    }
     
}
