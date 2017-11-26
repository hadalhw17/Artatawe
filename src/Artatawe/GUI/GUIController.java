package Artatawe.GUI;

import Artatawe.Data.Profile;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIController extends Application {




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

    }



}