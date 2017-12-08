package Artatawe.GUI;

import Artatawe.Data.Address;
import Artatawe.Data.DataController;
import Artatawe.Data.Picture;
import Artatawe.Data.Profile;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTextField;
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

public class SignInScene {
    private JFXTextField firstNameField = new JFXTextField();

    private JFXTextField secondNameField = new JFXTextField();

    private JFXTextField usernameField = new JFXTextField();

    private JFXDatePicker dOB = new JFXDatePicker();

    private JFXTextField mobileNumberField = new JFXTextField();

    private JFXButton signInButton = new JFXButton("Create account");

    private BorderPane mainPane = new BorderPane();

    private VBox signIn = new VBox();

    private Pane headerPane = new Pane();

    private DataController dc;

    public SignInScene(DataController dc){
        this.dc = dc;
        signIn = constructSignIn();
        mainPane.setCenter(signIn);
        signIn.setSpacing(20);
    }

    private VBox constructSignIn(){
        VBox content = new VBox();
        Pane buttonPane = new Pane();
        Label welcomeField = new Label("Sign in");
        content.setPrefHeight(900);
        content.setMaxWidth(500);
        content.setPadding(new Insets(50,50,50,50));
        signIn.getChildren().addAll(firstNameField, secondNameField, usernameField, mobileNumberField, dOB);
        welcomeField.setStyle("-fx-font-size: 30; -fx-color-label-visible: false; -fx-text-fill: #FFFFFF");
        headerPane.setPrefHeight(100);
        headerPane.setStyle("-fx-background-color: #336699;");
        headerPane.getChildren().addAll(welcomeField);
        firstNameField.setPromptText("First Name");
        secondNameField.setPromptText("Second Name");
        usernameField.setPromptText("Username");
        mobileNumberField.setPromptText("Mobile number");
        signInButton.getStyleClass().add("button-raised");
        dOB.setPromptText("Date Of Birth");
        buttonPane.getChildren().addAll(signInButton);
        signInButton.setLayoutX(150);
        signInButton.setLayoutY(50);

        signInButton.setOnMousePressed(e -> {
            dc.createProfile(usernameField.getText(),firstNameField.getText(),secondNameField.getText(),mobileNumberField.getText(),new Address(0,"","","",""),new Picture("file:data/avatars/img.png"));
            dc.save();
            ((Stage)signInButton.getScene().getWindow()).setScene(new Scene(new LoginScene(dc).getPane(),
                    Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight()
            ));
        });

        content.getChildren().addAll(headerPane,signIn,buttonPane);

        return content;
    }
    public BorderPane getPane() {
        return mainPane;
    }
}
