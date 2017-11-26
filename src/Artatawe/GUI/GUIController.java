package Artatawe.GUI;

import Artatawe.Data.Profile;
import Artatawe.GUI.ProfileScene;
import Artatawe.GUI.ScenePattern;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIController extends Application {




    public static Number screenWidth = 0;
    public static  Number screenHeight = 0;
    static Scene root;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Artatawe");

        root = new Scene(new ProfileScene(new Profile()).getPane(), 600, 600);

        primaryStage.setScene(root);
        primaryStage.show();

        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            //profileScene.get;
        });

        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            screenHeight = newVal;
        });
    }



}