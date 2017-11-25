package Artatawe.GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;

public class ScenePattern {
    private JFXHamburger menuHamburger = new JFXHamburger();
    private Label nameField = new Label("ARTATAWE | ");
    private JFXDrawer leftDrawer = new JFXDrawer();
    private VBox drawePane = new VBox();
    private BorderPane border = new BorderPane();
    private Pane contentPane;


    public ScenePattern() {
        HBox hbox = addHBox();
        border.setTop(hbox);
        drawePane = addVBox();
        leftDrawer.setDefaultDrawerSize(300);
        leftDrawer.setOverLayVisible(false);
        leftDrawer.setSidePane(drawePane);
        border.setLeft(leftDrawer);
        //contentPane = constructContentPane();
        border.setCenter(contentPane);
        initialize();
    }

    public BorderPane getPane() {
        return border;
    }

    public VBox addVBox() {
        JFXButton button1 = new JFXButton("View Auctions");
        JFXButton button2 = new JFXButton("Bid artwork");
        VBox vBox = new VBox();
        vBox.setStyle("-fx-background-color: #E91E63; -fx-padding: -10;");
        vBox.setPadding(new Insets(15, 12, 15, 0));
        vBox.setSpacing(10);
        vBox.setMargin(button1, new Insets(25, 25, 1, 25));
        button1.setMaxWidth(10000);
        vBox.setMargin(button2, new Insets(1, 25, 25, 25));
        button2.setMaxWidth(10000);
        vBox.getChildren().addAll(button1, button2);
        return vBox;


    }

    public HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, -25));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");


        menuHamburger.setPrefSize(100, 20);
        nameField.setStyle("-fx-font-size: 30; -fx-color-label-visible: false; -fx-text-fill: #FFFFFF");

        hbox.getChildren().addAll(menuHamburger, nameField);

        return hbox;
    }

    public void initialize() {
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(menuHamburger);
        drawePane = addVBox();
        transition.setRate(-1);
        menuHamburger.addEventHandler(MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (leftDrawer.isShown()) {
                leftDrawer.close();
            } else {
                leftDrawer.open();
            }
        });
    }


    public Pane constructContentPane(){
        return new Pane();//TODO retun statement
    }
}
