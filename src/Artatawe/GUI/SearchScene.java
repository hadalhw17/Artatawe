package Artatawe.GUI;

import Artatawe.Data.DataController;
import Artatawe.Data.Profile;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class SearchScene extends ScenePattern {

    //Current user
    private Profile loggedInProfile;

    /**
     * Constructor for <p>SearchScene.java</p>
     * @param dc information about system
     * @param p profile for iteraction with other users
     * @param loggedInProfile current user
     */
    public SearchScene(DataController dc, Profile p, Profile loggedInProfile){

        super(dc, p, loggedInProfile);
        this.loggedInProfile = loggedInProfile;
        this.setNameLabel("ArtaSearchAwe");
        setContentPane();

    }

    /**
     * Constructs main content pane
     * @return central pane
     */
    @Override
    public JFXMasonryPane constructContentPane(){
        JFXMasonryPane content = new JFXMasonryPane();
        VBox searchCard = new VBox();
        HBox result= new HBox();

        HBox searchBar = new HBox();
        JFXTextField searchAre = new JFXTextField();
        JFXButton searchButton = new JFXButton("Search");
        searchAre.setPromptText("Username here");

        searchBar
                .getChildren()
                .addAll(searchAre,searchButton);

        searchCard
                .getChildren()
                .add(searchBar);

        searchAre.setMinWidth(500);

        searchButton.setOnMousePressed(e -> {
            Profile searchRes;
            if(!searchAre.getText().equals("")){
                searchRes = dc.searchByUsername(searchAre.getText());
            } else {
                searchRes = null;
                searchAre.setPromptText("Type username here!!");
            }
            if(searchRes != null){
                ImageView avatar = new ImageView(
                        new Image(searchRes
                                .getProfileImg()
                                .getPath()));
                avatar.setFitWidth(200);
                avatar.setFitHeight(200);
                Label username = new Label(searchRes.getUsername());
                username.setMinWidth(1000);
                result.getChildren().addAll(avatar, username);
                result.setOnMouseClicked(event ->
                    GUIController
                            .getPrimaryStage()
                            .setScene(new Scene(
                                    new ProfileScene(dc,searchRes,loggedInProfile)
                                            .getPane()
                                    ,GUIConstants.SCENE_WIDTH
                                    ,GUIConstants.SCENE_HEIGHT)));

            } else {
                searchAre.setText("");
                searchAre.setPromptText("404 User not found");
            }
        });

        content
                .getChildren()
                .addAll(searchCard, result);
        return content;

    }
}
