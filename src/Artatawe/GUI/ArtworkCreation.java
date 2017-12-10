package Artatawe.GUI;

import Artatawe.Data.*;
import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
 *     ArtworkCration.java
 * </p>
 * Constructs a page where a user is able to initiate new auction
 * Inherits from <p>ScenePattern.java</p>
 */
public class ArtworkCreation extends ScenePattern {

    //Contains information about the system
    private DataController dc;

    //Current use
    private Profile loggedInProfile;

    //Field for the name of new artwork
    private JFXTextField name = new JFXTextField();

    //Field fof the year of createon of artwork
    private JFXTextField year = new JFXTextField();

    //Field for reserved price of artwork
    private JFXTextField reservedPrice = new JFXTextField();

    //Field for width of artwork
    private JFXTextField width = new JFXTextField();

    //Field for height of artwork
    private JFXTextField height = new JFXTextField();

    //Field for depth of artwork if it is sculpture
    private JFXTextField depth = new JFXTextField();

    //Field for description of artwork
    private JFXTextArea description = new JFXTextArea();

    //Checks if it is a sculpture
    private JFXCheckBox isSculpture = new JFXCheckBox("Add sculpture");

    //Approve image choice
    private JFXButton chooseImage = new JFXButton("Choose image");

    //File chooser for image
    private FileChooser fileChooser = new FileChooser();

    //Approve artwork creation
    private JFXButton create = new JFXButton("Create artwork!");

    //Notify about dummy errors
    private JFXSnackbar notification = new JFXSnackbar( this.getPane());

    //Picture of an artwork
    private Picture artworkPic;

    /**
     * Constructor for <p>ArtworkCreation.java</p>
     * @param dc information about the system
     * @param p profile for interactions with other users
     * @param loggedInProfile current user
     */
    public ArtworkCreation(DataController dc, Profile p, Profile loggedInProfile){
        super(dc,p, loggedInProfile);
        this.dc = dc;
        this.loggedInProfile = loggedInProfile;
        //------------------------------------Init of layout-------------------------------
        this.setNameLabel("Create Artwork");
        name.setPromptText("Name");
        name.setPadding(new Insets(10,0,10,0));
        year.setPromptText("Year");
        year.setPadding(new Insets(10,0,10,0));
        reservedPrice.setPromptText("Reserved price");
        reservedPrice.setPadding(new Insets(10,0,10,0));
        isSculpture.setPadding(new Insets(10,0,10,0));
        width.setPromptText("Width");
        width.setPadding(new Insets(10,0,10,0));
        height.setPromptText("Height");
        height.setPadding(new Insets(10,0,10,0));
        depth.setPromptText("Depth");
        depth.setPadding(new Insets(10,0,10,0));
        description.setPadding(new Insets(10,0,10,0));
        description.setPromptText("Description");
        depth.setDisable(true);
        //-------------------------------------------------------------------------------
        setContentPane();

    }

    /**
     * Constructs central part of stage.
     * Contains dynamical content.
     * @return main pane of the page containing all of the auctions
     */
    @Override
    public JFXMasonryPane constructContentPane() {
        JFXMasonryPane content = new JFXMasonryPane();
        BorderPane mainField = new BorderPane();
        content.getChildren().add(mainField);

        VBox createCard = new VBox();
        mainField.setCenter(createCard);
        createCard.setPadding(new Insets(200,200,200,200));

        createCard
                .getChildren()
                .addAll(name, year, reservedPrice,chooseImage, isSculpture, width,height, depth,description, create);
        isSculpture.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                depth.setDisable(!newValue);
            }
        });
        if(isSculpture.isSelected()){
            depth.setEditable(true);
        } else {
            depth.setDisable(true);
        }

        chooseImage.setOnMousePressed(e -> {
            Window stage = GUIController.getPrimaryStage();
            initLoadImage(fileChooser.showOpenDialog(stage));
        });
        try{
            create.setOnMousePressed(e -> {
                if(name.getText().equals("")
                        || year.getText().equals("")
                        || reservedPrice.getText().equals("")
                        || width.getText().equals("")
                        || height .getText().equals("")
                        || artworkPic == null) {
                    notification.show("Complete all of these fields!!!", 5000);

                } else {
                    if(isSculpture.isSelected()){
                        if(!(depth.getText().equals("")
                                || depth.getText().matches("[0-9]*"))){
                            notification.show("Complete all of these fields!!!", 5000);
                        } else {
                            dc.createAuction(loggedInProfile,new Sculpture(name.getText(),
                                    description.getText(),
                                    artworkPic,
                                    Integer.parseInt(year.getText()),
                                    Integer.parseInt(reservedPrice.getText()),
                                    new Date(12,12,12),
                                    Integer.parseInt(width.getText()),
                                    Integer.parseInt(height.getText()),
                                    Integer.parseInt(depth.getText())),
                                    Integer.parseInt(reservedPrice.getText()));
                        }
                    } else {
                        dc.createAuction(loggedInProfile,new Painting(name.getText(),description.getText(),
                                        artworkPic,
                                        Integer.parseInt(year.getText()),
                                        Integer.parseInt(reservedPrice.getText()),
                                        new Date(12,12,12),
                                        Integer.parseInt(width.getText()),
                                        Integer.parseInt(height.getText())),
                                Integer.parseInt(reservedPrice.getText()));
                    }

                    dc.save();
                    ((Stage) create
                            .getScene()
                            .getWindow())
                            .setScene(new Scene(new ProfileScene(dc,loggedInProfile, loggedInProfile)
                                    .getPane(),
                            Screen.getPrimary()
                                    .getVisualBounds()
                                    .getWidth(), Screen
                                    .getPrimary()
                                    .getVisualBounds()
                                    .getHeight()));
                }

            });
        } catch (NullPointerException e){
            notification.show("Complete all of these fields!!!", 5000);
        }
        return content;
    }

    /**
     * Just an easy and secure implementation of this method
     * @param f image
     */
    private void initLoadImage(File f){
        artworkPic = new Picture("file:"+loadImage(f));
    }

    /**
     * Loads image of artwork
     * @param f image
     * @return path to image
     */
    private String loadImage(File f){
        String path = null;
        try{
            Files.copy(f
                    .toPath(),new File("data/artworks/"+loggedInProfile
                    .getUsername()+ loggedInProfile
                    .getAuctions()
                    .size()+"Artwork.png")
                    .toPath(), StandardCopyOption.REPLACE_EXISTING
            );
            path = "data/artworks/"+loggedInProfile.getUsername()+
                    loggedInProfile.getAuctions().size()+"Artwork.png";

        }catch (Exception e){
            e.printStackTrace();
        }
        return path;
    }
}
