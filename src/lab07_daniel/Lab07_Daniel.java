/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab07_daniel;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

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
        final int SCENE_WIDTH = 500, SCENE_HEIGHT = 500;
        final int ANIMATION_POS_X = 50, ANIMATION_POS_Y = 100, ANIMATION_SIZE_X = 400, ANIMATION_SIZE_Y = 300;
        final int ANIMATION_POS_RIGHT = ANIMATION_POS_X + ANIMATION_SIZE_X, ANIMATION_POS_BOTTOM = ANIMATION_POS_Y + ANIMATION_SIZE_Y;
        
        BorderPane root = new BorderPane();
        
        Pane animationRoot = new Pane();
        
        
        //Show path rectangle
        Rectangle pathRect = new Rectangle(ANIMATION_POS_X, ANIMATION_POS_Y, ANIMATION_SIZE_X, ANIMATION_SIZE_Y);
        pathRect.setFill(null);
        
        pathRect.setStroke(Color.DARKBLUE);
        
        //Create path animation
        PathTransition pathTransition = new PathTransition();

        //Other one
        Path path = new Path();
        //Create 4 points of path
       
        for (int i = 3; i >= 0; i--) {
            int pointPositionX = i == 1 || i == 2 ?   ANIMATION_POS_RIGHT : ANIMATION_POS_X, pointPositionY = (i < 2) ? ANIMATION_POS_BOTTOM : ANIMATION_POS_Y ;
            //Point2D point = new Point2D(pointPositionX, pointPositionY);
            path.getElements().add(new LineTo(pointPositionX, pointPositionY));    
            System.out.println(String.format("%s, %s", pointPositionX, pointPositionY));
        }
        Circle circle = new Circle(ANIMATION_POS_X, ANIMATION_POS_Y, 5);
        System.out.println(path.getElements());
        ///pathTransition.setPath(path);
        pathTransition.setPath(pathRect);
        pathTransition.setNode(circle); 
        pathTransition.setRate(-1);
        pathTransition.setDuration(Duration.seconds(10));
        pathTransition.setCycleCount(Animation.INDEFINITE);
        pathTransition.play();
        
        animationRoot.getChildren().addAll(pathRect, circle);
        root.getChildren().addAll(animationRoot);
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
    
}
