package Artatawe.GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;

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
        content.setPrefHeight(900);
        content.setMaxWidth(500);
        content.setPadding(new Insets(50,50,50,50));
        headerPane.setPrefHeight(100);
        headerPane.setStyle("-fx-background-color: #336699;");
        content.getChildren().addAll(headerPane,loginField,loginButton);
        return content;
    }
    public BorderPane getPane() {
        return mainPane;
    }
}
