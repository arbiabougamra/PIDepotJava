/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entites.User;
import Services.UserServices;
import animatefx.animation.FadeOut;
import animatefx.animation.ZoomIn;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javax.swing.JOptionPane;
import java.util.Date;  
import java.util.List;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bia
 */
public class AddInterfaceController implements Initializable {

    @FXML
    private AnchorPane anchRoot;
    @FXML
    private Circle btnClose;
    @FXML
    private StackPane pnlStack;
    @FXML
    private Pane pnlUser;
    @FXML
    private TextField addEmail;
    @FXML
    private TextField addPseudo;
    @FXML
    private TextField addNom;
    @FXML
    private PasswordField addPass1;
    @FXML
    private PasswordField addPass2;
    @FXML
    private DatePicker date;
    @FXML
    private TextField addAdr;
    @FXML
    private TextField addTel;
    @FXML
    private Button addBtn;
    @FXML
    private ImageView btnBack;
    @FXML
    private ChoiceBox<String> choiceBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           choiceBox.getItems().add("a:1:{i:0;s:13:\"ROLE_CAISSIER\";}");
           choiceBox.getItems().add("a:1:{i:0;s:12:\"ROLE_LIVREUR\";}");
           choiceBox.getItems().add("a:1:{i:0;s:14:\"ROLE_OUVRIER\";}");
        
    }    

    @FXML
    private void AjouterUser(ActionEvent event) {
        
           UserServices us = new UserServices();
           User u = new User();
           u.setEmail(addEmail.getText());
           u.setEmailCanonical(addEmail.getText());
           u.setUsername(addPseudo.getText());
           u.setUsernameCanonical(addPseudo.getText());
           u.setPrenom(addNom.getText());
           u.setPassword(addPass1.getText());
           u.setPassword(addPass2.getText());
        /*u.setDateNai(date.get?());*/
           u.setAdresse(addAdr.getText());
        /*u.setTel(addTel.getInt());*/
            u.setRoles(choiceBox.getValue());
       
            us.create(u);
        
        JOptionPane.showMessageDialog(null, "Employee ajouté !");
    }

    @FXML
    private void handleMouseEvent(MouseEvent event) throws IOException {
          if (event.getSource() == btnClose){
            new FadeOut(anchRoot).play();
            System.exit(0);
        }
           if(event.getSource().equals(btnBack))
        {

        }
    }
    
}
