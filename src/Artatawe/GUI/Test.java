package Artatawe.GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;

public class Test extends Application {


    JFXHamburger menuHamburger = new JFXHamburger();
    Label nameField = new Label("ARTATAWE | ");
    JFXDrawer leftDrawer = new JFXDrawer();
    VBox drawePane = new VBox();


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane border = new BorderPane();
        StackPane stack = new StackPane();
        HBox hbox = addHBox();
        border.setTop(hbox);
        drawePane = addVBox();

        leftDrawer.setDefaultDrawerSize(300);
        leftDrawer.setOverLayVisible(false);
        leftDrawer.setSidePane(drawePane);
        JFXButton button = new JFXButton("Hello");

        border.setLeft(leftDrawer);
        primaryStage.setTitle("Artatawe");

        Scene scene = new Scene(border, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        initialize();

    }

    public VBox addVBox() {
        JFXButton button1 = new JFXButton("View Auctions");
        JFXButton button2 = new JFXButton("Bid artwork");
        VBox vBox = new VBox();
        vBox.setStyle("-fx-background-color: #E91E63; -fx-padding: -10;");
        vBox.setPadding(new Insets(15, 12, 15, 0));
        vBox.setSpacing(10);
        vBox.setMargin(button1,new Insets(25,25,1,25));
        button1.setMaxWidth(10000);
        vBox.setMargin(button2,new Insets(1,25,25,25));
        button2.setMaxWidth(10000);
        vBox.getChildren().addAll(button1,button2);
        return vBox;


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
        drawePane = addVBox();
        transition.setRate(-1);
        menuHamburger.addEventHandler(MOUSE_PRESSED,(e)->{
            transition.setRate(transition.getRate()*-1);
            transition.play();

            if (leftDrawer.isShown()){
                leftDrawer.close();
            } else{
                leftDrawer.open();
            }
        });
    }
}