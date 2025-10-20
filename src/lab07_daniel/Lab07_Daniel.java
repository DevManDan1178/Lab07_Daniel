
package lab07_daniel;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.*;    
import javafx.scene.text.Font;
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
        final int ELLIPSE_SIZE_X = 35, ELLIPSE_SIZE_Y = 20;
        final int ANIMATION_DURATION = 10;
        final int BUTTONS_FONT_SIZE = 25;
        BorderPane root = new BorderPane();
        VBox rootVBox = new VBox();
        Pane animationRoot = new Pane();
        
        
        //Show path rectangle
        Rectangle pathRect = new Rectangle(ANIMATION_POS_X, ANIMATION_POS_Y, ANIMATION_SIZE_X, ANIMATION_SIZE_Y);
        pathRect.setFill(null);
        
        pathRect.setStroke(Color.DARKBLUE);
        
        //Create path animation
        PathTransition pathTransition = new PathTransition();

        
        //Create the path
        Path path = new Path();
        for (int i = 0; i < 4; i++) {
            int pointPositionX = i == 1 || i == 2 ?   ANIMATION_POS_RIGHT : ANIMATION_POS_X, pointPositionY = (i < 2) ? ANIMATION_POS_Y :  ANIMATION_POS_BOTTOM;
            //Point2D point = new Point2D(pointPositionX, pointPositionY);
            PathElement pathElement = (i == 0) ? new MoveTo(pointPositionX, pointPositionY) : new LineTo(pointPositionX, pointPositionY);
            path.getElements().add(pathElement);    
            System.out.println(String.format("%s, %s", pointPositionX, pointPositionY));
        }
        path.getElements().add(new ClosePath());
       
        Circle circle = new Circle(ANIMATION_POS_X, ANIMATION_POS_Y, 5);
        System.out.println(path.getElements());
        
        pathTransition.setPath(path);
        pathTransition.setNode(circle); 
        pathTransition.setRate(1);
        pathTransition.setDuration(Duration.seconds(ANIMATION_DURATION));
        pathTransition.setCycleCount(Animation.INDEFINITE);
        //pathTransition.play();
        
        //Ellipse
        Ellipse ellipse = new Ellipse(ELLIPSE_POS_X, ELLIPSE_POS_Y, ELLIPSE_SIZE_X, ELLIPSE_SIZE_Y);
        ellipse.setFill(null);
        ellipse.setStroke(Color.DARKBLUE);
        
        FadeTransition fadeTrans = new FadeTransition();
        ScaleTransition scaleTrans = new ScaleTransition();
        RotateTransition rotTrans = new RotateTransition();
        TranslateTransition tranTrans = new TranslateTransition();
        final Transition[] TRANSITIONS = {fadeTrans, scaleTrans, rotTrans, tranTrans, pathTransition};
        
        for (int i = 0; i < 5; i++) {
            Transition transition = TRANSITIONS[i];
            transition.setCycleCount(Animation.INDEFINITE);

        }
        fadeTrans.setDuration(Duration.seconds(ANIMATION_DURATION));
        fadeTrans.setNode(ellipse);
        fadeTrans.setFromValue(1);
        fadeTrans.setToValue(0.25);
        
        rotTrans.setDuration(Duration.seconds(ANIMATION_DURATION));
        rotTrans.setNode(ellipse);
        rotTrans.setByAngle(360);
        rotTrans.setCycleCount(100);
        
        scaleTrans.setDuration(Duration.seconds(ANIMATION_DURATION));
        scaleTrans.setNode(ellipse);
        scaleTrans.setFromY(1);
        scaleTrans.setFromX(1);
        scaleTrans.setToY(2);
        scaleTrans.setToX(2);
        
        tranTrans.setDuration(Duration.seconds(ANIMATION_DURATION));
        tranTrans.setNode(ellipse);
        tranTrans.setFromY(ELLIPSE_POS_Y * 0.5);
        tranTrans.setToY(1);
        
        for (int i = 0; i < 4; i++) {
            Transition transition = TRANSITIONS[i];
            transition.setCycleCount(Animation.INDEFINITE);

        }
        
        //Set buttons
        BorderPane buttonsPane = new BorderPane();
        HBox buttonsHBox = new HBox();
        Button startButton = new Button("Start");
        startButton.setOnAction(event -> {
            for (Transition transition : TRANSITIONS) { 
                transition.play();
            }
        });
        startButton.setFont(new Font(BUTTONS_FONT_SIZE));
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(event -> {
            for (Transition transition : TRANSITIONS) {
                transition.playFrom(Duration.ZERO);
            }
        });
        resetButton.setFont(new Font(BUTTONS_FONT_SIZE));
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            stage.close();
        });
        exitButton.setFont(new Font(BUTTONS_FONT_SIZE));
        
        buttonsHBox.setAlignment(Pos.CENTER);
        
        rootVBox.setSpacing(BUTTONS_FONT_SIZE);
        buttonsHBox.getChildren().addAll(startButton, resetButton, exitButton);
        buttonsPane.setCenter(buttonsHBox);
        
        animationRoot.getChildren().addAll(pathRect, circle, ellipse);
        rootVBox.getChildren().addAll(animationRoot, buttonsPane);
        root.setCenter(rootVBox);
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
    
}
