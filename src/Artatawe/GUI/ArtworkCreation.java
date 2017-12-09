package Artatawe.GUI;

import Artatawe.Data.*;
import com.jfoenix.controls.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;


public class ArtworkCreation extends ScenePattern {

    private DataController dc;
    private Profile logedInProfile;

    JFXTextField name = new JFXTextField();
    JFXTextField year = new JFXTextField();
    JFXTextField reservedPrice = new JFXTextField();
    JFXTextField width = new JFXTextField();
    JFXTextField height = new JFXTextField();
    JFXTextField depth = new JFXTextField();
    private JFXTextArea description = new JFXTextArea();
    JFXCheckBox isSculpture = new JFXCheckBox("Add sculpture");
    private JFXButton chooseImage = new JFXButton("Choose image");
    private FileChooser fileChooser = new FileChooser();
    private JFXButton create = new JFXButton("Create artwork!");
    private JFXSnackbar notification = new JFXSnackbar( this.getPane());
    private Picture artworkPic;

    public ArtworkCreation(DataController dc, Profile p, Profile logedInProfile){
        super(dc,p, logedInProfile);
        this.dc = dc;
        this.logedInProfile = logedInProfile;
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
        setContentPane();

    }

    @Override
    public JFXMasonryPane constructContentPane() {
        JFXMasonryPane content = new JFXMasonryPane();
        BorderPane mainField = new BorderPane();
        content.getChildren().add(mainField);

        VBox createCard = new VBox();
        mainField.setCenter(createCard);
        createCard.setPadding(new Insets(200,200,200,200));

        createCard.getChildren().addAll(name, year, reservedPrice,chooseImage, isSculpture, width,height, depth,description, create);
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
                if(name.getText().equals("") || year.getText().equals("") || reservedPrice.getText().equals("") || width.getText().equals("")
                        || height .getText().equals("") ||
                        artworkPic == null){
                    notification.show("Complete all of these fields!!!", 5000);
                } else {
                    dc.createAuction(logedInProfile,new Painting(name.getText(),description.getText(),
                                    artworkPic,
                                    Integer.parseInt(year.getText()),
                                    Integer.parseInt(reservedPrice.getText()),
                                    new Date(12,12,12),
                                    Integer.parseInt(width.getText()),
                                    Integer.parseInt(height.getText())),
                            Integer.parseInt(reservedPrice.getText()));

                    dc.save();
                    ((Stage) create.getScene().getWindow()).setScene(new Scene(new ProfileScene(dc,logedInProfile, logedInProfile).getPane(),
                            Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight()));
                }

            });
        } catch (NullPointerException e){
            notification.show("Complete all of these fields!!!", 5000);
        }
        return content;
    }

    private void initLoadImage(File f){
        artworkPic = new Picture("file:"+loadImage(f));
    }
    private String loadImage(File f){
        String path = null;
        try{
            Files.copy(f.toPath(),new File("data/avatars/"+logedInProfile.getUsername()+
                    logedInProfile.getAuctions().size()+"Avatar.png").toPath(), StandardCopyOption.REPLACE_EXISTING);
            path = "data/avatars/"+logedInProfile.getUsername()+
                    logedInProfile.getAuctions().size()+"Avatar.png";

        }catch (Exception e){

        }
        return path;
    }
}
