/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab07_daniel;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
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
        final int ELLIPSE_POS_X = ANIMATION_POS_X + ANIMATION_SIZE_X/2, ELLIPSE_POS_Y = ANIMATION_POS_Y + ANIMATION_SIZE_Y/2;
        final int ELLIPSE_SIZE_X = 20, ELLIPSE_SIZE_Y = 35;
        final int ANIMATION_DURATION = 10;
        
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
       
        //Creates path, but path doesn't work
        for (int i = 3; i >= 0; i--) {
            int pointPositionX = i == 1 || i == 2 ?   ANIMATION_POS_RIGHT : ANIMATION_POS_X, pointPositionY = (i < 2) ? ANIMATION_POS_BOTTOM : ANIMATION_POS_Y ;
            //Point2D point = new Point2D(pointPositionX, pointPositionY);
            path.getElements().add(new LineTo(pointPositionX, pointPositionY));    
            System.out.println(String.format("%s, %s", pointPositionX, pointPositionY));
        }
        ///pathTransition.setPath(path);
        Circle circle = new Circle(ANIMATION_POS_X, ANIMATION_POS_Y, 5);
        System.out.println(path.getElements());
        
        pathTransition.setPath(pathRect);
        pathTransition.setNode(circle); 
        pathTransition.setRate(-1);
        pathTransition.setDuration(Duration.seconds(ANIMATION_DURATION));
        pathTransition.setCycleCount(Animation.INDEFINITE);
        pathTransition.play();
        
        //Ellipse
        Ellipse ellipse = new Ellipse(ELLIPSE_POS_X, ELLIPSE_POS_Y, ELLIPSE_SIZE_X, ELLIPSE_SIZE_Y);
        ellipse.setFill(null);
        ellipse.setStroke(Color.DARKBLUE);
        
        FadeTransition fadeTrans = new FadeTransition();
        ScaleTransition scaleTrans = new ScaleTransition();
        RotateTransition rotTrans = new RotateTransition();
        TranslateTransition tranTrans = new TranslateTransition();
        final Transition[] TRANSITIONS = {fadeTrans, scaleTrans, rotTrans, tranTrans};
        
        for (int i = 0; i < 4; i++) {
            Transition transition = TRANSITIONS[i];
            transition.setCycleCount(Animation.INDEFINITE);

        }
        fadeTrans.setDuration(Duration.seconds(ANIMATION_DURATION / 4));
        fadeTrans.setNode(ellipse);
        fadeTrans.setFromValue(1);
        fadeTrans.setToValue(0.25);
        
        rotTrans.setDuration(Duration.seconds(ANIMATION_DURATION / 4));
        rotTrans.setNode(ellipse);
        rotTrans.setByAngle(360);
        rotTrans.setCycleCount(100);
        
        scaleTrans.setDuration(Duration.seconds(ANIMATION_DURATION / 4));
        scaleTrans.setNode(ellipse);
        scaleTrans.setFromY(1);
        scaleTrans.setToY(.75);
        
        tranTrans.setDuration(Duration.seconds(ANIMATION_DURATION / 4));
        tranTrans.setNode(ellipse);
        tranTrans.setFromY(1);
        tranTrans.setToY(.75);
        
        for (int i = 0; i < 4; i++) {
            Transition transition = TRANSITIONS[i];
            transition.setCycleCount(Animation.INDEFINITE);
            transition.play();
        }

        
        animationRoot.getChildren().addAll(pathRect, circle, ellipse);
        root.getChildren().addAll(animationRoot);
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
    
}
