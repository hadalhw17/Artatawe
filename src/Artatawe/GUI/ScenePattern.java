package Artatawe.GUI;

import Artatawe.Data.Profile;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;

import java.io.File;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;

public class ScenePattern {
    private JFXHamburger menuHamburger = new JFXHamburger();
    private Label nameField = new Label("ARTATAWE | ");
    private JFXDrawer leftDrawer = new JFXDrawer();
    private VBox drawePane = new VBox();
    private BorderPane border = new BorderPane();
    private JFXMasonryPane contentPane;

    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

    private Image image;
    private File file;
    private ImageView imgView;

    public ScenePattern() {
        file = new File("src/res/img1.png");
        image = new Image(file.toURI().toString());
        HBox hbox = addHBox();
        border.setTop(hbox);
        drawePane = addVBox();
        leftDrawer.setDefaultDrawerSize(300);
        leftDrawer.setOverLayVisible(false);
        leftDrawer.setSidePane(drawePane);
        leftDrawer.setPrefSize(0,0);
        border.setLeft(leftDrawer);
        contentPane = constructContentPane(image);
        border.setCenter(contentPane);
        initialize();
    }

    public BorderPane getPane() {
        return border;
    }

    public VBox addVBox() {
        JFXButton profileButton = new JFXButton("View Profile");
        JFXButton button1 = new JFXButton("View Auctions");
        JFXButton button2 = new JFXButton("Bid artwork");
        VBox vBox = new VBox();
        vBox.setStyle("-fx-background-color: #E91E63; -fx-padding: -10;");
        vBox.setPadding(new Insets(15, 12, 15, 0));
        vBox.setSpacing(10);
        button1.setMaxWidth(10000);
        button1.addEventHandler(MOUSE_CLICKED, e -> {
            border.setCenter(new AuctionScene().constructContentPane(image));
        });
        profileButton.addEventHandler(MOUSE_CLICKED, e -> {
            border.setCenter(new ProfileScene(new Profile()).constructContentPane(image));
        });
        vBox.setMargin(profileButton, new Insets(25, 25, 1, 25));
        vBox.setMargin(button1, new Insets(1, 25, 1, 25));
        vBox.setMargin(button2, new Insets(1, 25, 25, 25));
        button2.setMaxWidth(10000);
        profileButton.setMaxWidth(10000);
        vBox.getChildren().addAll(profileButton,button1, button2);
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
                leftDrawer.setPrefSize(0,0);
            } else {
                leftDrawer.open();
                leftDrawer.setPrefSize(300,0);
            }
        });
    }


    public JFXMasonryPane constructContentPane(Image img){

        return contentPane;
    }

    public void repaint(Number n){

    }
}
