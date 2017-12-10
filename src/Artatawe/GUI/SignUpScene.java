package Artatawe.GUI;

import Artatawe.Data.Address;
import Artatawe.Data.DataController;
import Artatawe.Data.Picture;
import Artatawe.Data.Profile;
import com.jfoenix.controls.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;


/**
 * @author Aleksandr Slobodov
 * <p>
 *     SignUpScene.java
 * </p>
 * This class constructs scene where new profile can be created
 */
public class SignUpScene {

    //Field for first name
    private JFXTextField firstNameField = new JFXTextField();

    //Field for second name
    private JFXTextField secondNameField = new JFXTextField();

    //Field for username
    private JFXTextField usernameField = new JFXTextField();

    //Field for date of birth
    private JFXDatePicker dOB = new JFXDatePicker();

    //Field for mobile phone number
    private JFXTextField mobileNumberField = new JFXTextField();

    //Initiates account creation
    private JFXButton signInButton = new JFXButton("Create account");

    ///Mane content pane of the scene, usually contains dynamical content
    private BorderPane mainPane = new BorderPane();

    //Position objects vertically
    private VBox signIn = new VBox();

    //Header of scene
    private Pane headerPane = new Pane();

    //Information about system
    private DataController dc;

    //Notifies if data in fields is invalid
    private JFXSnackbar notification = new JFXSnackbar( this.getPane());

    /**
     * Constructor for <p>SignUpScene.java</p>
     * @param dc information about system
     */
    public SignUpScene(DataController dc){
        this.dc = dc;
        signIn = constructSignIn();
        mainPane.setCenter(signIn);
        signIn.setSpacing(20);;
    }

    /**
     * Constructs main content card
     * @return content card
     */
    private VBox constructSignIn(){
        VBox content = new VBox();
        Pane buttonPane = new Pane();
        Label welcomeField = new Label("Sign Up");
        content.setPrefHeight(900);
        content.setMaxWidth(500);
        content.setPadding(new Insets(50,50,50,50));
        signIn
                .getChildren()
                .addAll(firstNameField, secondNameField, usernameField, mobileNumberField, dOB);
        welcomeField
                .setStyle("" +
                        "-fx-font-size: 30; " +
                        "-fx-color-label-visible: false; " +
                        "-fx-text-fill: #FFFFFF");
        headerPane.setPrefHeight(100);
        headerPane
                .setStyle("" +
                        "-fx-background-color: #336699;");
        headerPane
                .getChildren()
                .addAll(welcomeField
                );
        firstNameField.setPromptText("First Name");
        secondNameField.setPromptText("Second Name");
        usernameField.setPromptText("Username");
        mobileNumberField.setPromptText("Mobile number");
        signInButton
                .getStyleClass()
                .add("button-raised");
        dOB.setPromptText("Date Of Birth");
        buttonPane
                .getChildren()
                .addAll(signInButton);
        signInButton.setLayoutX(150);
        signInButton.setLayoutY(50);

        signInButton.setOnMousePressed(e -> {
            if (firstNameField.getText().equals("")
                    ||secondNameField.getText().equals("")
                    ||mobileNumberField.getText().equals("")
                    ||dOB.getValue() == null
                    ||!mobileNumberField.getText().matches("[0-9]*")){
                notification.show("Complete all of these fields!!!" +
                        "\nAnd make sure that your phone number\n is an actual number:3", 5000);

            } else {
                dc.createProfile(usernameField
                                .getText(),firstNameField
                                .getText(),secondNameField
                                .getText(),mobileNumberField
                                .getText(),
                        new Address(0,"","","",""),
                        new Picture("file:data/avatars/img.png"));
                dc.save();
                GUIController
                        .getPrimaryStage()
                        .setScene(new Scene(new LoginScene(dc)
                                .getPane(), GUIConstants
                                .LOGIN_WIDTH,GUIConstants
                                .LOGIN_HEIGHT
                        ));
                GUIController.centerize();
            }

        });

        content
                .getChildren()
                .addAll(headerPane,signIn,buttonPane);

        return content;
    }

    /**
     * Rerurns main pane
     * @return main pane
     */
    public BorderPane getPane() {
        return mainPane;
    }
}
