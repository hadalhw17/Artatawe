package Artatawe.GUI;

import Artatawe.Data.DataController;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIController {

    private final String TITLE = "Artatawe";

    private DataController artataweDatabase;
    private static Stage primaryStage;

    public static  Stage getPrimaryStage() {
        return GUIController.primaryStage;
    }

    public GUIController(Stage primaryStage) {

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

        this.primaryStage = primaryStage;

        primaryStage.setTitle(TITLE);
        primaryStage.setScene(new Scene(new LoginScene(artataweDatabase).getPane(),
                GUIConstants.LOGIN_WIDTH,GUIConstants.LOGIN_HEIGHT));
        primaryStage.show();
        centerize();
    }

    public static void centerize()
    {
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((bounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((bounds.getHeight() - primaryStage.getHeight()) / 2);
    }

    public static void alert(String text, Alert.AlertType type)
    {
        Alert alert = new Alert(type);
        alert.setContentText(text);
        alert.show();
    }
}