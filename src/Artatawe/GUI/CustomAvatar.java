package Artatawe.GUI;

import Artatawe.Data.DataController;
import Artatawe.Data.Profile;
import com.jfoenix.controls.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.imageio.ImageIO;
import java.io.File;

/**
 * @author Aleksandr Slobodov
 * <p>
 *      CustomAvatar.java
 * <p>
 * Scene made to add custom avatar creating feature.
 * Allows to draw particle trace and a straight line on canvas
 * Inherits it's style from ScenePattern.java
 */
public class CustomAvatar extends ScenePattern {

    //Canvas where a user can draw custom avatars
    private Canvas canvas;

    //Saves new custom avatar
    private JFXButton saveButton;

    //Changes avatar and sets up profile scene
    private JFXButton exitButton;

    //Scene to leave later
    private ProfileScene profileScene;

    //Notification about creating new avatar
    private JFXSnackbar imageSaved;

    //X-coordinate for canvas line drawing
    private double canvasLineX;

    //Y-coordinate for canvas line drawing
    private double canvasLineY;

    /**
     * Constructor for <p>CustomAvatar.java</p>
     * @param dc information about system
     * @param p needed for interactions with different profiles
     * @param profileScene initial scene
     * @param logedInProfile current user
     */
    public CustomAvatar(DataController dc,
                        Profile p,
                        ProfileScene profileScene,
                        Profile logedInProfile){
        super(dc,p, logedInProfile);
        this.profileScene = profileScene;
        setNameLabel("Custom Avatar");
        setContentPane();
        imageSaved = new JFXSnackbar(this.getPane());
    }

    /**
     * Saves new avatar by making a snapshot of canvas
     */
    private void onSave(){
        try{
            Image snapsot = canvas.snapshot(null,null);
            ImageIO.write(SwingFXUtils.fromFXImage(snapsot,null), "png",
                    new File("data/avatars/"+curProfile.getUsername()+"Avatar.png"));
            imageSaved.show("Picture Saved!" +
                    "\n It will be updated after you press exit", 5000);
        } catch(Exception e){
            System.out.print("Unable to save image. " + e);
        }
    }

    /**
     *Sets new avatar and leaves this nage
     */
    private void onExit(){
        curProfile.setPicture("file:data/avatars/"+ curProfile
                .getUsername() + "Avatar.png"
        );
        dc.save();
        GUIController
                .getPrimaryStage()
                .setScene(new Scene(
                        new ProfileScene(dc,profileScene
                                .getProfile(), curProfile)
                                .getPane(), GUIConstants
                        .SCENE_WIDTH, GUIConstants
                        .SCENE_HEIGHT)
                );
        GUIController.centerize();
    }


     /**
     * Generates content panel
     * @return central panel
     */
    @Override
    public Pane constructContentPane() {

        //Drawing tools
        JFXRadioButton toolErase = new JFXRadioButton("Eraser");
        JFXRadioButton toolDrawTrace = new JFXRadioButton("Particle trace");
        JFXRadioButton toolDrawLine = new JFXRadioButton("Straight line");

        toolErase.setTooltip(new Tooltip("Eraser tool"));
        toolDrawTrace.setTooltip(new Tooltip("Particle trace drawing tool"));
        toolDrawLine.setTooltip(new Tooltip("Line drawing tool"));

        //Group radio buttons
        ToggleGroup toolGroup = new ToggleGroup();
        toolErase.setToggleGroup(toolGroup);
        toolDrawTrace.setToggleGroup(toolGroup);
        toolDrawLine.setToggleGroup(toolGroup);

        //Particle trace is default drawing tool
        toolDrawTrace.setSelected(true);

        //Draw tool colour
        JFXColorPicker colorPicker = new JFXColorPicker();

        //Drawing tool size slider
        JFXSlider sizeSlider = new JFXSlider();
        sizeSlider.setMinWidth(200);
        sizeSlider.setMinHeight(10);
        sizeSlider.setOrientation(Orientation.HORIZONTAL);

        //Save/Exit operations
        saveButton = new JFXButton("Save");
        exitButton = new JFXButton("Exit");
        saveButton.getStyleClass().add("button-raised");
        exitButton.getStyleClass().add("button-raised");

        saveButton.setOnMouseClicked(e -> onSave());
        exitButton.setOnMouseClicked(e -> onExit());

        //Drawing canvas
        canvas = new Canvas(
                GUIConstants.PROFILE_WIDTH * 2,
                GUIConstants.PROFILE_HEIGHT * 2
        );
        canvas
                .setStyle("" +
                        "-fx-border-color: black");

        GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.setOnMouseDragged(e->{

            double size = sizeSlider.getValue();
            double x = e.getX() - size/2;
            double y = e.getY() - size-2;

            //Erase area
            if(toolErase.isSelected()) {
                gc.clearRect(x,y,size,size);
            }
            //Particle trace
            else if(toolDrawTrace.isSelected()) {
                gc.setFill(colorPicker.getValue());
                gc.fillOval(x, y, size, size);
            }
        });

        //Workaround because lambda's can't reference this directly
        CustomAvatar thisRef = this;

        canvas.setOnMousePressed(e->{
            //If line drawing selected
            if (toolDrawLine.isSelected())
            {
                //Save current canvas pos
                thisRef.canvasLineX = e.getX();
                thisRef.canvasLineY = e.getY();
            }
        });

        canvas.setOnMouseReleased(e->{
            //If line drawing selected
            if (toolDrawLine.isSelected())
            {
                //Draw
                gc.setStroke(colorPicker.getValue());
                gc.setLineWidth(sizeSlider.getValue());
                gc.strokeLine(
                        thisRef.canvasLineX,
                        thisRef.canvasLineY,
                        e.getX(),
                        e.getY()
                );
            }
        });


        //Panes
        BorderPane mainPane = new BorderPane();
        VBox toolPane = new VBox();
        toolPane.setSpacing(10);
        toolPane.setPadding(new Insets(10,10,10,10));
        //toolPane.setStyle("-fx-border-color: #336699;"); //line border around tool pane

        //Tool pane
        toolPane
                .getChildren()
                .add(colorPicker
                );
        toolPane
                .getChildren()
                .addAll(new Label("Resize brush"), sizeSlider
                );
        toolPane
                .getChildren()
                .addAll(toolDrawTrace, toolDrawLine, toolErase
                );
        toolPane
                .getChildren()
                .add(new HBox(saveButton,exitButton)
                );

        //Wrap canvas in pane
        Rectangle canvasRect = new Rectangle(canvas.getWidth(), canvas.getHeight());
        canvasRect.setFill(Color.TRANSPARENT);
        canvasRect.setStroke(Color.BLACK);

        StackPane canvasPane = new StackPane(
                canvasRect,
                canvas
        );

        //Set border bane canvas and tools
        mainPane.setCenter(canvasPane);
        mainPane.setRight(toolPane);

        return mainPane;
    }
}