import Artatawe.GUI.GUIController;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class
 */
public class Main extends Application {

    private GUIController gui;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        this.gui = new GUIController(stage);
    }
}
