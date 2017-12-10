package Artatawe.GUI;

import Artatawe.Data.Auction;
import Artatawe.Data.DataController;
import Artatawe.Data.Picture;
import Artatawe.Data.Profile;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXMasonryPane;
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
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
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

    private final JFXComboBox<String> combo = new JFXComboBox<>();

    //Profile to be displayed
    private Profile p;
    
    //Profile avatar holder
    private AnchorPane imagePane = new AnchorPane();
    
    //Avatar itself
    private Image profImage;
    
    //Information about the system
    private DataController dc;

    //Current user
    private Profile loggedInProfile;

    //Button to initiate artwork creation
    private JFXButton createArtwork = new JFXButton("Create Artwork");

    /**
     * Constructor for ProfileScene
     *
     * @param p profile to be displayed
     * @param dc information about system
     * @param loggedInProfile current user
     */
    public ProfileScene(DataController dc, Profile p, Profile loggedInProfile) {
        super(dc,p,loggedInProfile);
        this.loggedInProfile = loggedInProfile;
        this.p = p;
        if(loggedInProfile.getUsername().equals(p.getUsername())){
            this.p = this.loggedInProfile;
        }
        this.dc = dc;

        profImage = new Image(p.getProfileImg().getPath());


        setNameLabel(p.getFirstname() + " " + p.getSurname());
        setContentPane();

        combo.setPromptText("Sample avatars");
        combo.getItems().addAll("img1.png",
                "img2.png",
                "img3.png",
                "img4.png",
                "img5.png",
                "img6.png");
    }



    /**
     * Fetches data from Profile p and generates central content pane for profile.
     *
     * @return profile page.
     */
    @Override
    public JFXMasonryPane constructContentPane() {
        JFXMasonryPane contentPane = new JFXMasonryPane();
        Pane addressPane = new Pane();
        ImageView imgView = new ImageView();
        imgView.setImage(profImage);
        imgView.setCache(false);
        VBox avatarBox = new VBox();
        ScrollPane pane2 = new ScrollPane();
        HBox bidedPane = loadSellingAuctions();
        JFXButton editProfileInfo = new JFXButton("Edit profile");
        createArtwork.setOnMousePressed( e -> initCreateArtwork(createArtwork));

        addressPane.getChildren().add(new Label(p.getAddress().toString()));

        JFXButton chooseImage = new JFXButton("Choose profile image");
        chooseImage.setOnMousePressed(e -> chooseImage());

        combo.setOnAction(e -> chooseFromSample());

        bidedPane.setSpacing(10);
        pane2.setContent(bidedPane);

        JFXButton customImage = new JFXButton("Custom avatar");

        if(loggedInProfile
                .getUsername()
                .equals(p.getUsername())){
            avatarBox
                    .getChildren()
                    .addAll(editProfileInfo,imagePane, customImage, chooseImage,combo
                    );
        } else {
            ToggleSwitch favButton = new ToggleSwitch(dc,loggedInProfile,p);
            avatarBox
                    .getChildren()
                    .addAll(imagePane, favButton
                    );
        }
        imgView.setFitWidth(GUIConstants.PROFILE_WIDTH);
        imgView.setFitHeight(GUIConstants.PROFILE_HEIGHT);

        imagePane.getChildren().addAll(imgView);

        editProfileInfo.setOnMousePressed(e ->

                GUIController
                        .getPrimaryStage()
                        .setScene(new Scene(new EditProfile(dc,p,loggedInProfile)
                                .getPane(), GUIConstants
                                .SCENE_WIDTH, GUIConstants
                                .SCENE_HEIGHT)
                        ));

        customImage.setOnMousePressed(e ->

            GUIController
                    .getPrimaryStage()
                    .setScene(new Scene(new CustomAvatar(dc,p,this, loggedInProfile)
                            .getPane(), GUIConstants
                            .SCENE_WIDTH, GUIConstants
                            .SCENE_HEIGHT)
                    ));

        pane2.setPrefSize(600, 200);

        VBox pane1 = new VBox(
                new Label("About:"),
                new Label("Name: " + p.getFirstname() + " " + p.getSurname() +
                        "\nPhone#: " + p.getMobileNo()));
        pane1
                .setStyle("" +
                        "-fx-effect: dropshadow(gaussian, silver, 5, 0, 0, 0); " +
                        "-fx-background-color: #E8EAF6;");
        addressPane
                .setStyle("" +
                        "-fx-effect: dropshadow(gaussian, silver, 5, 0, 0, 0); " +
                        "-fx-background-color: #E8EAF6;");
        pane2
                .setStyle("" +
                        "-fx-effect: dropshadow(gaussian, silver, 5, 0, 0, 0);" +
                        "-fx-background-color: #E8EAF6;");

        contentPane
                .getChildren()
                .addAll(avatarBox,addressPane, pane1, pane2, createArtwork);


        return contentPane;
    }

    /**
     * Getter for profile displayed on the page
     * @return profile displayed
     */
    public Profile getProfile() {
        return p;
    }

    /**
     * Sets up file chooser for avatar.
     * Adds image filter and sets home directory
     * @param fileChooser file chooser that needs to be configured
     */
    private static void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser
                .getExtensionFilters()
                .addAll(
                        new FileChooser
                                .ExtensionFilter("All Images", "*.jpg", "*.png")
                );

    }

    /**
     * Loads image from file system and sets it as avatar
     * @param f image which is needed to be set as avatar
     */
    private void changeImage(File f){
        if(f != null) {
            try{
                Files.copy(f
                        .toPath(), new File("data/avatars/"+p
                        .getUsername()+"Avatar.png")
                        .toPath(), StandardCopyOption.REPLACE_EXISTING
                );
                p.setProfileImg(new Picture("file:data/avatars/"+p.getUsername()+"Avatar.png"));
                dc.save();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    /**
     * Changes scene to ArtworkCreation
     * @param btn which was pressed to call this method
     */
    private void initCreateArtwork(JFXButton btn){
        ((Stage) btn.getScene().getWindow()).setScene(new Scene(new ArtworkCreation(dc,p, loggedInProfile).getPane(),
                getPane().getWidth(), getPane().getHeight()));
    }

    /**
     * Load fileChooser
     */
    private void chooseImage(){
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        Window stage = GUIController.getPrimaryStage();
        changeImage(fileChooser.showOpenDialog(stage));
        GUIController
                .getPrimaryStage()
                .setScene(new Scene(new ProfileScene(dc,p,loggedInProfile)
                        .getPane(), GUIConstants
                        .SCENE_WIDTH, GUIConstants
                        .SCENE_HEIGHT)
                );
    }

    /**
     * Allows to choose avatar from the list of sample avatars
     */
    private void chooseFromSample(){
        p.setProfileImg(new Picture("file:data/avatars/default_avatars/"+combo.getValue()));
        dc.save();
        GUIController
                .getPrimaryStage()
                .setScene(new Scene(new ProfileScene(dc,p,loggedInProfile)
                        .getPane(), GUIConstants
                        .SCENE_WIDTH, GUIConstants
                        .SCENE_HEIGHT)
                );
    }

    /**
     * Load box with artworks profile is selling
     * @return  bidedPane
     */
    private HBox loadSellingAuctions(){
        HBox bidedPane = new HBox();
        for(Auction auction: dc.getAuctions()){
            if(auction
                    .getSeller()
                    .getUsername()
                    .equals(p.getUsername())){
                ImageView imgView2 = new ImageView(new Image(auction
                        .getArtwork()
                        .getPhoto()
                        .getPath())
                );
                imgView2.setFitWidth(200);
                imgView2.setFitHeight(200);
                bidedPane
                        .getChildren()
                        .addAll(new Pane(imgView2)
                        );
            }
        }
        return bidedPane;
    }
}
