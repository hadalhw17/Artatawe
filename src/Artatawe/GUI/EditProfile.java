package Artatawe.GUI;

import Artatawe.Data.*;
import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;


/**
 * @athor Aleksandr Slobodov
 *
 * <p>
 *     EditProfile.java
 * </p>
 * Constructs a page where a user is able to edit his profile
 * Inherits from <p>ScenePattern.java</p>
 */
public class EditProfile extends ScenePattern{

    //Field for first name
    private JFXTextField firstNameField = new JFXTextField();

    //Field for second name
    private JFXTextField secondNameField = new JFXTextField();

    //Field for username
    private JFXTextField usernameField = new JFXTextField();

    //Field for mobile phone number
    private JFXTextField mobileNumberField = new JFXTextField();

    //Initiates account creation
    private JFXButton signInButton = new JFXButton("Edit account");

    ///Mane content pane of the scene, usually contains dynamical content
    private JFXMasonryPane mainPane = new JFXMasonryPane();

    //Position objects vertically
    private VBox signIn = new VBox();

    //Header of scene
    private Pane headerPane = new Pane();

    //Information about system
    private DataController dc;

    //Notifies if data in fields is invalid
    private JFXSnackbar notification = new JFXSnackbar( this.getPane());

    //Current user
    private Profile logedInProfile;

    //Field for address entity
    private JFXTextField houseNoField = new JFXTextField();

    //Field for address entity
    private JFXTextField streetField  = new JFXTextField();

    //Field for address entity
    private JFXTextField cityField = new JFXTextField();

    //Field for address entity
    private JFXTextField countryField = new JFXTextField();

    //Field for address entity
    private JFXTextField postcodeField = new JFXTextField();

    /**
     * Constructor for <p>EditProfile.java>
     * @param dc information about system
     */
    public EditProfile(DataController dc, Profile p, Profile logedInProfile){
        super(dc,p,logedInProfile);
        this.logedInProfile = logedInProfile;
        this.dc = dc;
        setNameLabel("Edit Profile");
        setContentPane();
    }

    /**
     * Constructs main content card
     * @return content card
     */
    @Override
    public JFXMasonryPane constructContentPane(){
        JFXMasonryPane contentBox = new JFXMasonryPane();
        VBox content = new VBox();
        Pane buttonPane = new Pane();
        Label welcomeField = new Label("Edit Profile");
        content.setPrefHeight(900);
        content.setMaxWidth(500);
        content.setPadding(new Insets(50,50,50,50));
        signIn
                .getChildren()
                .addAll(firstNameField, secondNameField, usernameField, mobileNumberField, houseNoField, streetField, cityField, countryField, postcodeField);
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
        firstNameField.setText(logedInProfile.getFirstname());
        secondNameField.setText(logedInProfile.getSurname());
        usernameField.setText(logedInProfile.getUsername());
        mobileNumberField.setText(logedInProfile.getMobileNo());
        houseNoField.setText(Integer.toString(logedInProfile.getAddress().getHouseNum()));
        cityField.setText(logedInProfile.getAddress().getCity());
        streetField.setText(logedInProfile.getAddress().getStreet());
        postcodeField.setText(logedInProfile.getAddress().getPostcode());
        countryField.setText(logedInProfile.getAddress().getCounty());
        signInButton
                .getStyleClass()
                .add("button-raised");
        buttonPane
                .getChildren()
                .addAll(signInButton);
        signInButton.setLayoutX(150);
        signInButton.setLayoutY(50);

        signInButton.setOnMousePressed(e -> {
            if (firstNameField.getText().equals("")
                    ||secondNameField.getText().equals("")
                    ||mobileNumberField.getText().equals("")
                    ||!mobileNumberField.getText().matches("[0-9]*")
                    ||houseNoField.getText().equals("")
                    ||countryField.getText().equals("")
                    ||cityField.getText().equals("")
                    ||streetField.getText().equals("")
                    ||postcodeField.getText().equals("")
                    ||!houseNoField.getText().matches("[0-9]*")){
                notification.show("Complete all of these fields!!!" +
                        "\nAnd make sure that your phone number and house number\n are actual numbers:3", 5000);

            } else {
                logedInProfile.setUsername(usernameField.getText());
                logedInProfile.setFirstname(firstNameField.getText());
                logedInProfile.setSurname(secondNameField.getText());
                logedInProfile.setMobileNo(mobileNumberField.getText());
                logedInProfile.changeAddress(new Address(
                        Integer.parseInt(houseNoField.getText()),
                        streetField.getText(),
                        cityField.getText(),
                        countryField.getText(),
                        postcodeField.getText()));

                dc.save();
                GUIController
                        .getPrimaryStage()
                        .setScene(new Scene(new ProfileScene(dc,logedInProfile,logedInProfile)
                                .getPane(), GUIConstants
                                .SCENE_WIDTH,GUIConstants
                                .SCENE_HEIGHT
                        ));
                GUIController.centerize();
            }

        });

        contentBox
                .getChildren()
                .addAll(headerPane,signIn,buttonPane);

        return contentBox;
    }


}
