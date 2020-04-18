/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.UserServices;
import animatefx.animation.FadeOut;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import Entity.User;

/**
 * FXML Controller class
 *
 * @author Bia
 */
public class UserInterfaceController implements Initializable {

    @FXML
    private AnchorPane anchRoot;
    @FXML
    private Circle btnClose;
    @FXML
    private StackPane pnlStack;
    @FXML
    private Pane pnlUser;
    @FXML
    private TableView<User> tableUser;
    @FXML
    private TableColumn<User, String> pseudoCol;
    @FXML
    private TableColumn<User, String> prenomCol;
    @FXML
    private TableColumn<User, Object> dateCol;
    @FXML
    private TableColumn<User, String> roleCol;
    @FXML
    private TableColumn<User, String> emailCol;
    @FXML
    private TableColumn<User, Integer> telCol;
    @FXML
    private TableColumn<User, Integer> IdCol;
    @FXML
    private Button addScreen;
    @FXML
    private Button modifyBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private TextField flSearch;
    @FXML
    private TableColumn<User, String> adrCol;
    @FXML
    private Button refresh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
           IdCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
           pseudoCol.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
           prenomCol.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
           dateCol.setCellValueFactory(new PropertyValueFactory<User, Object>("dateNai"));
           roleCol.setCellValueFactory(new PropertyValueFactory<User, String>("roles"));
           emailCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
           telCol.setCellValueFactory(new PropertyValueFactory<User,Integer>("tel"));
           adrCol.setCellValueFactory(new PropertyValueFactory<User, String>("adresse"));
          UserServices  us = new UserServices();
    }    

    @FXML
    private void AddScreenAction(ActionEvent event) throws IOException {
        Parent add_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/AddUserInterface.fxml"));
                Scene add_page_scene = new Scene(add_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); 
                app_stage.setScene(add_page_scene);
                app_stage.show();
    }
    
        @FXML
    private void handleMouseEvent(MouseEvent event) {
        if (event.getSource() == btnClose){
            new FadeOut(anchRoot).play();
            System.exit(0);
        }
    }

}
