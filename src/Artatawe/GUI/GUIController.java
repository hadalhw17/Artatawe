package Artatawe.GUI;

import Artatawe.Data.Profile;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GUIController extends Application {




    static Scene root;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Artatawe");

       root = new Scene(new LoginScene().getPane(), Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight()-25);

        primaryStage.setScene(root);
        primaryStage.show();

    }



}