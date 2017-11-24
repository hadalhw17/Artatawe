package Artatawe.GUI;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;

public class Test extends Application {


    JFXHamburger menuHamburger = new JFXHamburger();
    Label nameField = new Label("ARTATAWE");

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane border = new BorderPane();
        StackPane stack = new StackPane();
        HBox hbox = addHBox();
        border.setTop(hbox);
        primaryStage.setTitle("Artatawe");


        Scene scene = new Scene(border, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        initialize();

    }

    public HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, -25));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");


        menuHamburger.setPrefSize(100, 20);
        nameField.setStyle("-fx-font-size: 30; -fx-color-label-visible: false; -fx-text-fill: #FFFFFF");

        hbox.getChildren().addAll(menuHamburger,nameField);

        return hbox;
    }

    public void initialize() {
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(menuHamburger);
        transition.setRate(-1);
        menuHamburger.addEventHandler(MOUSE_PRESSED,(e)->{
            transition.setRate(transition.getRate()*-1);
            transition.play();
        });
    }
}