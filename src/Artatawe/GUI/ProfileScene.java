package Artatawe.GUI;

import Artatawe.Data.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * @author Aleksandr Slobodov
 * <p>
 * ProfileScene.java
 * <p>
 * Scene made to display profile information.
 * Inherits all of the elements from ScenePattern.java
 */

public class ProfileScene extends ScenePattern {

    //Profile to be displayed
    private Profile p;
    private AnchorPane imagePane;
    private Image profImage;
    private DataController dc;

    /**
     * Constructor for ProfileScene
     *
     * @param p profile to be displayed
     */
    public ProfileScene(DataController dc, Profile p) {
        super(dc,p);
        this.p = p;
        this.dc = dc;
        imagePane = new AnchorPane();

        profImage = new Image(p.getProfileImg().getPath());


        setNameLabel(p.getFirstname() + " " + p.getSurname());
        setContentPane();
    }


    //For now all of the paths are hard cored, but further all of the images gonna be fetched from Profile p.

    /**
     * Generates central content pane for profile.
     *
     * @return profile page.
     */
    @Override
    //TODO Once Profile.java is finished, remove all of that hard-coded shit that is in this method and make everything automatic
    public JFXMasonryPane constructContentPane() {
        JFXMasonryPane contentPane = new JFXMasonryPane();
        ImageView imgView;
        imgView = new ImageView();
        imgView.setImage(profImage);
        imgView.setCache(false);
        HBox avatarBox = new HBox();
        ScrollPane pane2 = new ScrollPane();
        HBox biddedPane = new HBox();
        JFXButton chooseImage = new JFXButton("Choose profile image");
        chooseImage.setOnMousePressed(e->{
            FileChooser fileChooser = new FileChooser();
            configureFileChooser(fileChooser);
            Window stage = GUIController.getPrimaryStage();
            changeImage(fileChooser.showOpenDialog(stage));
            GUIController.getPrimaryStage().setScene(new Scene(new ProfileScene(dc,p).getPane(),
                    GUIConstants.SCENE_WIDTH, GUIConstants.SCENE_HEIGHT));
            GUIController.centerize();
        });
        biddedPane.setSpacing(10);
        pane2.setContent(biddedPane);
        JFXButton customImage = new JFXButton("Custom avatar");
        for(Auction auction: dc.getAuctions()){
            for(Bid bid:auction.getBidList()){
                if(bid.getBuyer().getUsername().equals(p.getUsername())){
                    ImageView imgView2 = new ImageView(new Image(auction.getArtwork().getPhoto().getPath()));
                    imgView2.setFitWidth(200);
                    imgView2.setFitHeight(200);
                    biddedPane.getChildren().addAll(new Pane(imgView2));
                }
            }
        }

        imgView.setFitWidth(GUIConstants.PROFILE_WIDTH);
        imgView.setFitHeight(GUIConstants.PROFILE_HEIGHT);
        imagePane.getChildren().addAll(imgView);
        avatarBox.getChildren().addAll(imagePane, customImage, chooseImage);
        customImage.setOnMousePressed(e -> {
            GUIController.getPrimaryStage().setScene(new Scene(new CustomAvatar(dc,p,this).getPane(),
                    GUIConstants.SCENE_WIDTH, GUIConstants.SCENE_HEIGHT));
            GUIController.centerize();
        });
        pane2.setPrefSize(600, 200);
        VBox pane1 = new VBox(new Label("About"), new Label("Name: " + p.getFirstname() + " " + p.getSurname() +"\nPhone#: " + p.getMobileNo()));
        pane1.setStyle("-fx-effect: dropshadow(gaussian, silver, 5, 0, 0, 0); -fx-background-color: #E8EAF6;");
        pane2.setStyle("-fx-effect: dropshadow(gaussian, silver, 5, 0, 0, 0);-fx-background-color: #E8EAF6;");
        contentPane.getChildren().addAll(avatarBox, pane1, pane2);

        return contentPane;
    }

    public Profile getProfile() {
        return p;
    }

    private static void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("All Images", "*."),
                new FileChooser.ExtensionFilter("All Images", "*.jpg", "*.png"));
                //new FileChooser.ExtensionFilter("PNG", "*.png")

    }

    private void changeImage(File f){
        try{
            Files.copy(f.toPath(), new File("data/avatars/"+p.getUsername()+"Avatar.png").toPath(), StandardCopyOption.REPLACE_EXISTING);
            p.setProfileImg(new Picture("file:data/avatars/"+p.getUsername()+"Avatar.png"));
            dc.save();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
