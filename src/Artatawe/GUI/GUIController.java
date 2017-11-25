package Artatawe.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIController extends Application {





    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Artatawe");

        Scene profileScene = new Scene(new ProfileScene("res/profile_images/prof1.png").getPane(), 600, 600);
        primaryStage.setScene(profileScene);
        primaryStage.show();

    }


}