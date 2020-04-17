/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import animatefx.animation.*;
import javafx.scene.layout.AnchorPane;
import Entites.User;
import Services.UserServices;
import Utils.DataSource;
import Utils.SessionUser;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Bia
 */
public class LoginInterfaceController implements Initializable {

    @FXML
    private Circle btnClose;
    @FXML
    private StackPane pnlStack;
    @FXML
    private Pane pnlSignUp;
    @FXML
    private Label lblSignUp;
    @FXML
    private TextField tfPseudoUp;
    @FXML
    private TextField tfEmail;
    @FXML
    private ChoiceBox<String> tfRole;
    @FXML
    private PasswordField tfPassUp;
    @FXML
    private PasswordField tfRePassUp;
    @FXML
    private ImageView btnBack;
    @FXML
    private Pane pnlSignIn;
    @FXML
    private TextField tfPseudo;
    @FXML
    private PasswordField tfPass;
    @FXML
    private Button btnSignIn;
    @FXML
    private Button btnSignUp;
    @FXML
    private Label lblForgot;
    @FXML
    private Label lblForgot1;
    @FXML
    private AnchorPane anchRoot;
    @FXML
    private Label lblErrors;
    
    public void loginAction(ActionEvent event) {
        String username =  tfPseudo.getText();
        String password = tfPass.getText();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
           tfRole.getItems().add("Admin");
           tfRole.getItems().add("Fournisseur");
           tfRole.getItems().add("Client");
        
    }  

    @FXML
    private void AuthentificationAction(ActionEvent event) throws IOException {

           UserServices us = new UserServices();
            User u = new User();

           u.setUsername(tfPseudo.getText());
            u.setPassword(tfPass.getText());
        /* u.setRoles(tfRole.getValue());*/

        if (us.Authentification(u)) {
            /* if (tfRole.getValue().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {*/
            if (tfPseudo.getText().equals("admin")) {
                Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/UserInterface.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); 
                app_stage.setScene(home_page_scene);
                app_stage.show();

            } else if (tfRole.getValue().equals("fournisseur"))  {
                SessionUser.getInstance(SessionUser.getUser());
                Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/FournisseurFXML.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); 
                app_stage.setScene(home_page_scene);
                app_stage.show();
                app_stage.setOnCloseRequest(e -> {
                });
                
            } 
            else if (tfRole.getValue().equals("client"))  {
                SessionUser.getInstance(SessionUser.getUser());
                Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/ClientFXML.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); 
                app_stage.setScene(home_page_scene);
                app_stage.show();
                app_stage.setOnCloseRequest(e -> {
                });
                
            } 
            else {
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Veuillez vérifier vos champs!");
                alert.showAndWait();
                System.out.println("no");
            }

    }
    }
    
    @FXML
    /*private void SignUp(ActionEvent event) throws SQLException, IOException {

           String email = tfEmail.getText();
           String email_canonical = tfEmail.getText();
           String username = tfPseudo.getText();
           String username_canonical = tfPseudo.getText();
           String password = tfPassUp.getText();
           String roles = tfRole.getValue();
       
           UserServices us= new UserServices();
           User u = new User(username, username_canonical, email, email_canonical, password, roles);
           us.create(u);
           pnlSignIn.toFront();

    }*/

    private void handleMouseEvent(MouseEvent event) {
        if (event.getSource() == btnClose){
            new FadeOut(anchRoot).play();
            System.exit(0);
        }
        if(event.getSource().equals(btnBack))
        {
            new ZoomIn(pnlSignIn).play();
            pnlSignIn.toFront();
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource().equals(btnSignUp))
        {
            new ZoomIn(pnlSignUp).play();
            pnlSignUp.toFront();
        }
    }
    
}
