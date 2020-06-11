/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazaar;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static javafx.application.Application.launch;
import animatefx.animation.*;

/**
 *
 * @author Bia
 */
public class Bazaar extends Application {
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/LoginInterface.fxml"));
        
        Scene scene = new Scene(root);
        
           stage.setScene(scene);
           stage.initStyle(StageStyle.TRANSPARENT);
        
        root.setOnMousePressed( (MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        }) ;
        
        root.setOnMouseDragged( (MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);

        });
        
        stage.setScene(scene);
        new FadeIn(root).play();
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         launch(args);
    }
    
}

