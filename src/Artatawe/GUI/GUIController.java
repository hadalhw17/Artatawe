package Artatawe.GUI;

import Artatawe.Data.DataController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIController extends Application {


    private final String TITLE = "Artatawe";

    private static Scene root;

    private DataController artataweDatabase;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try
        {
            //Load persistent data
            artataweDatabase = new DataController();
        }
        catch (IOException e)
        {
            //If there was some error loading the data
            //Exit early
            System.err.println(e.getMessage());
            return;
        }

        primaryStage.setTitle(TITLE);
        root = new Scene(new LoginScene(artataweDatabase).getPane(), Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight()-25);

        primaryStage.setScene(root);
        primaryStage.show();
    }
}