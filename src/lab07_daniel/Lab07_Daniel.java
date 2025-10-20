/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab07_daniel;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author 6323612
 */
public class Lab07_Daniel extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        final int SCENE_WIDTH = 500, SCENE_LENGTH = 500;
        
        BorderPane root = new BorderPane();
        
        Rectangle pathRect = new Rectangle(0, 0, SCENE_WIDTH, SCENE_LENGTH);
        pathRect.setFill(null);
        pathRect.setStroke(Color.DARKBLUE);
        

        root.getChildren().add(pathRect);
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_LENGTH);
        stage.setScene(scene);
        stage.show();
    }
    
}
