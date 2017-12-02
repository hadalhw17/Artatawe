package Artatawe.GUI;

import Artatawe.Data.Profile;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LoginScene {

    private JFXTextField loginField = new JFXTextField();

    private JFXButton loginButton = new JFXButton("Log in");

    private BorderPane mainPane = new BorderPane();

    private VBox loginPane = new VBox();

    private Pane headerPane = new Pane();


    public LoginScene(){
        loginPane = constructLogin();
        mainPane.setCenter(loginPane);
    }

    private VBox constructLogin(){
        VBox content = new VBox();
        Pane buttonPane = new Pane();
        Label welcomeField = new Label("Welcome");
        content.setPrefHeight(900);
        content.setMaxWidth(500);
        content.setPadding(new Insets(50,50,50,50));
        welcomeField.setStyle("-fx-font-size: 30; -fx-color-label-visible: false; -fx-text-fill: #FFFFFF");
        headerPane.setPrefHeight(100);
        headerPane.setStyle("-fx-background-color: #336699;");
        headerPane.getChildren().addAll(welcomeField);
        loginField.setPromptText("Username: id1");
        loginButton.getStyleClass().add("button-raised");
        buttonPane.getChildren().addAll(loginButton);
        loginButton.setLayoutX(150);
        loginButton.setLayoutY(50);
        loginButton.setOnMousePressed(e -> {
            if(loginField.getText().equals("id1")){
                ((Stage)loginButton.getScene().getWindow()).setScene(new Scene(new ProfileScene(new Profile()).getPane(),
                        Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight()));
            }
        });
        content.getChildren().addAll(headerPane,loginField,buttonPane);
        return content;
    }
    public BorderPane getPane() {
        return mainPane;
    }
}
