package Artatawe.GUI;

import Artatawe.Data.DataController;
import Artatawe.Data.Profile;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;


/**
 *  @author Aleksandr Slobodov
 *
 *  ScenePattern.java
 *
 *  A pattern for all of GUI windows.
 *  Contains BorderPane as main layout, VBox as left menu, HBox as a header.
 *
 */
public class ScenePattern {

    //Name printed in top left corner
    private final Label NAME_FIELD = new Label("ARTATAWE | ");

    //Animated button for menu sliding
    private JFXHamburger menuHamburger = new JFXHamburger();

    //Left menu
    private JFXDrawer leftDrawer = new JFXDrawer();

    //Pane for left menu
    private VBox drawerPane = new VBox();

    //Main pane of scene
    private BorderPane border = new BorderPane();

    //Central content pane
    private Pane contentPane = new JFXMasonryPane();

    //Label for name of the current page
    private Label nameLabel = new Label();

    //Header panel
    private HBox topBox = new HBox();

    //Current user
    private Profile loggedInProfile;

    //Information about system
    protected DataController dc;

    //Current user
    protected   Profile curProfile;

    /**
     * A constructor for ScenePattern.
     * Adds all of the main elements to the scene.
     */
    public ScenePattern(DataController dc, Profile p, Profile loggedInProfile) {
        this.curProfile = p;
        this.loggedInProfile = loggedInProfile;
        this.dc = dc;
        topBox = addHBox();
        border.setTop(topBox);
        drawerPane = addDrawerVBox();
        border.setLeft(leftDrawer);
        menuActivity();
    }

    public void setContentPane(){
        contentPane = constructContentPane();
        border.setCenter(contentPane);
    }
    
    /**
     * A getter for a main pane.
     * @return main border pane with all of the objects constructed on it.
     */
    public BorderPane getPane() {
        return border;
    }

    /**
     * Generates VBox with a drawer menu in it.
     * @return generated VBox
     */
    //TODO Once files gonna be generated, remove hard-coded stuff.
    public VBox addDrawerVBox() {
        leftDrawer.setDefaultDrawerSize(300);
        leftDrawer.setOverLayVisible(false);
        leftDrawer.setSidePane(drawerPane);
        leftDrawer.setPrefSize(0,0);

        JFXButton profileButton = new JFXButton("View Profile");
        JFXButton button1 = new JFXButton("View Auctions");
        JFXButton button2 = new JFXButton("View Favourites");
        JFXButton searchButton = new JFXButton("Search profiles");

        VBox vBox = new VBox();
        vBox.setStyle("-fx-background-color: #E91E63; -fx-padding: -10;");
        vBox.setPadding(new Insets(15, 12, 15, 0));
        vBox.setSpacing(10);
        button1.setMaxWidth(10000);
        searchButton.setMaxWidth(10000);

        button1.addEventHandler(MOUSE_CLICKED, e -> {
            GUIController
                    .getPrimaryStage()
                    .setScene(new Scene(new ArtworkContainer(dc,curProfile,loggedInProfile)
                            .getPane(), getPane()
                            .getWidth(), getPane()
                            .getHeight())
                    );
        });

        button2.setMaxWidth(10000);
        button2.addEventHandler(MOUSE_CLICKED, e -> {
            GUIController
                    .getPrimaryStage()
                    .setScene(new Scene(new FavouriteProfileScene(dc,loggedInProfile,loggedInProfile)
                            .getPane(), getPane()
                            .getWidth(), getPane()
                            .getHeight())
                    );
        });

        profileButton.addEventHandler(MOUSE_CLICKED, e -> {
            GUIController
                    .getPrimaryStage()
                    .setScene(new Scene(new ProfileScene(dc,loggedInProfile,loggedInProfile)
                            .getPane(), getPane()
                            .getWidth(), getPane()
                            .getHeight())
                    );
        });

        searchButton.addEventHandler(MOUSE_CLICKED, e -> {
            GUIController
                    .getPrimaryStage()
                    .setScene(new Scene(new SearchScene(dc,curProfile,loggedInProfile)
                            .getPane(), getPane()
                            .getWidth(), getPane()
                            .getHeight()));
        });
        vBox.setMargin(profileButton, new Insets(25, 25, 1, 25));
        vBox.setMargin(button1, new Insets(1, 25, 1, 25));
        vBox.setMargin(button2, new Insets(1, 25, 25, 25));
        vBox.setMargin(searchButton,new Insets(1,25,25,25));
        button2.setMaxWidth(10000);
        profileButton.setMaxWidth(10000);
        vBox
                .getChildren()
                .addAll(profileButton,button1, button2, searchButton
                );
        return vBox;
    }

    /**
     * Generates HBox for a header
     * @return generated header.
     */
    public HBox addHBox() {
        HBox hbox = new HBox();
        menuHamburger = new JFXHamburger();
        hbox.setPadding(new Insets(15, 12, 15, -25));
        hbox.setSpacing(10);
        hbox
                .setStyle(
                        "-fx-background-color: #336699;");
        menuHamburger.setPrefSize(100, 20);
        NAME_FIELD
                .setStyle("" +
                        "-fx-font-size: 30; " +
                        "-fx-color-label-visible: false; " +
                        "-fx-text-fill: #FFFFFF");
        nameLabel
                .setStyle("" +
                        "-fx-font-size: 30; " +
                        "-fx-color-label-visible: false; " +
                        "-fx-text-fill: #FFFFFF");

        hbox
                .getChildren()
                .addAll(menuHamburger, NAME_FIELD,nameLabel
                );

        return hbox;
    }

    /**
     * Sets text for page name label
     * @param nameOfPage name to add
     */
    public void setNameLabel(String nameOfPage){
        nameLabel.setText(nameOfPage);
    }

    /**
     * Generates central content pane.
     * @return generated content pane.
     */
    public Pane constructContentPane(){

        return contentPane;
    }


    /**
     * Responsible for hamburger button events.
     * Also responsible for side menu animations
     */
    public void menuActivity() {
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(menuHamburger);
        drawerPane = addDrawerVBox();
        transition.setRate(-1);
        menuHamburger.addEventHandler(MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (leftDrawer.isShown()) {
                leftDrawer.close();
                leftDrawer.setPrefSize(0,0);
            } else if(!leftDrawer.isShown()) {
                leftDrawer.open();
                leftDrawer.setPrefSize(300,0);
            }
        });
    }
}
