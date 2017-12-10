package Artatawe.GUI;

import Artatawe.Data.DataController;
import Artatawe.Data.Profile;
import com.jfoenix.controls.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;

public class CustomAvatar extends ScenePattern {

    //Canvas where a user can draw custom avatars
    private Canvas canvas = new Canvas(GUIConstants.PROFILE_WIDTH * 2, GUIConstants.PROFILE_HEIGHT * 2);

    //Saves new custom avatar
    private JFXButton saveButton;

    //Changes avatar and sets up profile scene
    private JFXButton exitButton;

    //Scene to leave later
    private ProfileScene profileScene;

    //Notification about creating new avatar
    private JFXSnackbar imageSaved = new JFXSnackbar(this.getPane());

    //Contains information about Artatawe
    private DataController dc;

    //Needed for interactions with different profiles
    private Profile p;

    //Current user
    private Profile loggedInProfile;

    /**
     * Constructor for <p>CustomAvatar.java</p>
     * @param dc information about system
     * @param p needed for interactions woth different profiles
     * @param profileScene initioal scene
     * @param loggedInProfile current user
     */
    public CustomAvatar(DataController dc, 
                        Profile p, 
                        ProfileScene profileScene, 
                        Profile loggedInProfile){
        
        super(dc,p, loggedInProfile);
        this.loggedInProfile = loggedInProfile;
        this.p = p;
        this.dc = dc;
        this.profileScene = profileScene;
        setNameLabel("Custom Avatar");
        setContentPane();
    }


    /**
     * Saves new avatar by making a snapshot of canvas
     */
    public void onSave(){
        try{
            Image snapshot = canvas.snapshot(null,null);
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot,null), "png",
                    new File("data/avatars/"+p.getUsername()+"Avatar.png"));
            imageSaved.show("Picture Saved!\n It will be updated after you press exit", 5000);
        } catch(Exception e){
            System.out.print("Unable to save image. " + e);
        }
    }

    /**
     *Sets new avatar and leaves this nage
     */
    public void onExit(){
        p.setPicture("file:data/avatars/"+p.getUsername()+"Avatar.png");
        dc.save();
        GUIController
                .getPrimaryStage()
                .setScene(new Scene(new ProfileScene(dc,
                        profileScene.getProfile(), 
                        loggedInProfile).getPane(), GUIConstants
                        .SCENE_WIDTH, GUIConstants.SCENE_HEIGHT)
                );
        GUIController.centerize();
    }


    /**
     * Generates content panel
     * @return central panel
     */
    @Override
    public JFXMasonryPane constructContentPane() {
        HBox erasebox = new HBox();
        HBox lineBox = new HBox();
        JFXCheckBox erase = new JFXCheckBox();
        JFXCheckBox line = new JFXCheckBox();
        JFXMasonryPane root = new JFXMasonryPane();
        JFXColorPicker colorPicker = new JFXColorPicker();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        JFXSlider sizeSlider = new JFXSlider();
        saveButton = new JFXButton("Save");
        exitButton = new JFXButton("Exit");
        erasebox
                .getChildren()
                .addAll(erase, new Label("eraser"));
        lineBox
                .getChildren()
                .addAll(line, new Label("Straight Line"));
        sizeSlider.setMinWidth(200);
        sizeSlider.setMinHeight(10);
        sizeSlider.setOrientation(Orientation.HORIZONTAL);

        Pane pane1 = new Pane();
        Pane pane = new Pane();
        VBox pane2 = new VBox();
        HBox btnPane = new HBox();

        saveButton.setOnMousePressed(e -> onSave());
        exitButton.setOnMousePressed(e -> onExit());

        canvas.setOnMouseDragged(e->{
            double size = sizeSlider.getValue();
            double x = e.getX() - size/2;
            double y = e.getY() - size-2;
            if(erase.isSelected()){
                gc.clearRect(x,y,size,size);
            } else if(!line.isSelected()) {
                gc.setFill(colorPicker.getValue());
                gc.fillOval(x, y, size, size);
            }
        });
        line.setOnMouseClicked(e1->{
            canvas.setOnMousePressed(e->{
                if(line.isSelected()){
                    canvas.setOnMousePressed(event->{
                        if(line.isSelected()) {
                            gc.setStroke(colorPicker.getValue());
                            gc.setLineWidth(sizeSlider.getValue());
                            double x1 = e.getX();
                            double y1 = e.getY();
                            double x2 = event.getX();
                            double y2 = event.getY();
                            gc.strokeLine(x1,y1,x2,y2);
                            line.setSelected(false);
                        }
                    });
                }
            });
        });

        pane.setStyle("-fx-background-color: WHITE; ");
        pane
                .getChildren()
                .add(canvas);
        pane1
                .getChildren()
                .add(colorPicker);
        pane2
                .getChildren()
                .addAll(erasebox,sizeSlider, new Label("Resize brush"), lineBox
                );
        btnPane
                .getChildren()
                .addAll(saveButton,exitButton
                );
        root
                .getChildren()
                .addAll(pane,pane1,pane2,btnPane
                );
        return root;
    }
}
