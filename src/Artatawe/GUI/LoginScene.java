package Artatawe.GUI;

import Artatawe.Data.DataController;
import Artatawe.Data.Profile;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LoginScene {

    //Field for login
    private JFXTextField loginField = new JFXTextField();


    //Button to initiate login process
    private JFXButton loginButton = new JFXButton("Log in");

    //Main pane of scene
    private BorderPane mainPane = new BorderPane();

    //Positions objects vertically one by one
    private VBox loginPane = new VBox();

    //Main header
    private Pane headerPane = new Pane();

    //Information about system
    private DataController dc;

    //Initiates process of creating new profile
    private JFXButton signInButton = new JFXButton("Create new profile");

    /**
     * Constructor for <p>LoginScene.java</p>
     * @param dc info about the system
     */
    public LoginScene(DataController dc){
        this.dc = dc;
        loginPane = constructLogin();
        mainPane.setCenter(loginPane);
    }


    /**
     * Constructs login card
     */
    private VBox constructLogin(){
        VBox content = new VBox();
        HBox buttonPane = new HBox();
        Label welcomeField = new Label("Welcome");
        content.setPrefHeight(900);
        content.setMaxWidth(500);
        content.setPadding(new Insets(50,50,50,50));
        welcomeField
                .setStyle(
                        "-fx-font-size: 30; -fx-color-label-visible: false; -fx-text-fill: #FFFFFF");
        buttonPane.setPadding(new Insets(20,10,0,70));
        headerPane.setPrefHeight(100);
        headerPane
                .setStyle(
                        "-fx-background-color: #336699;");
        headerPane.getChildren().addAll(welcomeField);
        loginField.setPromptText("Username");
        loginButton
                .getStyleClass()
                .add("button-raised");
        signInButton
                .getStyleClass()
                .add("button-raised");
        buttonPane
                .getChildren()
                .addAll(loginButton, signInButton);
        loginButton.setLayoutX(150);
        loginButton.setLayoutY(50);

        loginButton.setOnMousePressed(e -> {

            Profile p = dc.searchByUsername(loginField.getText());

            if (p != null) {
                GUIController
                        .getPrimaryStage()
                        .setScene(new Scene(new ProfileScene(dc,p,p)
                                .getPane(),
                        GUIConstants
                                .SCENE_WIDTH,GUIConstants
                                .SCENE_HEIGHT
                ));
                GUIController.centerize();
            }
        });

        signInButton.setOnMousePressed(e->{
            ((Stage)signInButton
                    .getScene()
                    .getWindow())
                    .setScene(new Scene(new SignInScene(dc).getPane(),
                    GUIConstants
                            .SIGNUP_WIDTH,GUIConstants
                            .SIGNUP_HEIGHT
            ));
        });

        content
                .getChildren()
                .addAll(headerPane,loginField,buttonPane
                );

        return content;
    }

    /**
     * Returnss main border pane
     * @return main pane
     */
    public BorderPane getPane() {
        return mainPane;
    }
}
