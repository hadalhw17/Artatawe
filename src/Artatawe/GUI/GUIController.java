package Artatawe.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIController extends Application {




    public static Number screenWidth = 0;
    public static  Number screenHeight = 0;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Artatawe");

        Scene profileScene = new Scene(new ProfileScene("img1.jpg").getPane(), 600, 600);
        Scene profileScene1 = new Scene(new ScenePattern().getPane(), 600, 600);
        primaryStage.setScene(profileScene);
        primaryStage.show();

        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            //profileScene.get;
        });

        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            screenHeight = newVal;
        });
    }


}