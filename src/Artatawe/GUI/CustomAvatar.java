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



    private Canvas canvas;
    private JFXButton saveButton;
    private JFXButton exitButton;
    private ProfileScene profileScene;
    private JFXSnackbar imageSaved = new JFXSnackbar(this.getPane());
    private DataController dc;
    private Profile p;
    private Profile logedInProfile;

    public CustomAvatar(DataController dc, Profile p, ProfileScene profileScene, Profile logedInProfile){
        super(dc,p, logedInProfile);
        this.logedInProfile = logedInProfile;
        this.p = p;
        this.dc = dc;
        this.profileScene = profileScene;
        setNameLabel("Custom Avatar");
        setContentPane();
    }


    public void onSave(){
        try{
            Image snapsot = canvas.snapshot(null,null);
            ImageIO.write(SwingFXUtils.fromFXImage(snapsot,null), "png",
                    new File("data/avatars/"+p.getUsername()+"Avatar.png"));
            imageSaved.show("Picture Saved!\n It will be updated after you press exit", 5000);
        } catch(Exception e){
            System.out.print("Unable to save image. " + e);
        }
    }

    public void onExit(){
        p.setPicture("file:data/avatars/"+p.getUsername()+"Avatar.png");
        dc.save();
        ((Stage)exitButton.getScene().getWindow()).setScene(new Scene(new ProfileScene(dc,profileScene.getProfile(),logedInProfile).getPane(),
                Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight()));
    }


    @Override
    public JFXMasonryPane constructContentPane(){
        HBox erasebox = new HBox();
        HBox lineBox = new HBox();
        JFXCheckBox erase = new JFXCheckBox();
        JFXCheckBox line = new JFXCheckBox();
        JFXMasonryPane root = new JFXMasonryPane();
        JFXColorPicker colorPicker = new JFXColorPicker();
        JFXSlider sizeSlider = new JFXSlider();
        saveButton = new JFXButton("Save");
        exitButton = new JFXButton("Exit");
        erasebox.getChildren().addAll(erase, new Label("Eraser"));
        lineBox.getChildren().addAll(line, new Label("Straight Line"));
        sizeSlider.setMinWidth(200);
        sizeSlider.setMinHeight(10);
        sizeSlider.setOrientation(Orientation.HORIZONTAL);
        Pane pane1 = new Pane();
        Pane pane = new Pane();
        VBox pane2 = new VBox();
        HBox btnPane = new HBox();
        saveButton.setOnMousePressed(e->{
            onSave();
        });
        exitButton.setOnMousePressed(e->{
            onExit();
        });
        canvas = new Canvas(1000, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged(e->{
            double size = sizeSlider.getValue();
            double x = e.getX() - size/2;
            double y = e.getY() - size-2;
            if(erase.isSelected()){
                gc.clearRect(x,y,size,size);
            }else if(!line.isSelected()) {
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
        pane.getChildren().add(canvas);
        pane1.getChildren().add(colorPicker);
        pane2.getChildren().addAll(erasebox,sizeSlider, new Label("Resize brush"), lineBox);
        btnPane.getChildren().addAll(saveButton,exitButton);
        root.getChildren().addAll(pane,pane1,pane2,btnPane);
        return root;
    }


    private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);
    }
}
