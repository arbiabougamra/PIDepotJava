/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Etudiant
 */
public class Amine extends Application {
    public static Stage stage = null;
    @Override
    public void start(Stage stage) throws IOException {
   /* 
    Parent root = FXMLLoader.load(getClass().getResource("/GUI/CategorieInterface.fxml"));
     Parent root = FXMLLoader.load(getClass().getResource("/GUI/StockInterface.fxml")); 
   Parent root = FXMLLoader.load(getClass().getResource("/GUI/MagasinInterface.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/PromotionInterface.fxml")); 
        Scene scene = new Scene(root);
        //stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    */
      // Parent root = FXMLLoader.load(getClass().getResource("/GUI/StockInterface.fxml"));
       Parent root = FXMLLoader.load(getClass().getResource("/GUI/main.fxml"));
      // Parent root = FXMLLoader.load(getClass().getResource("/GUI/PageClient.fxml"));
 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        this.stage = stage;
        stage.show();
    
    
    }
        
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}