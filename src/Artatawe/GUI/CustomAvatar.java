package Artatawe.GUI;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CustomAvatar extends ScenePattern {



    //private Canvas canvas;



    public CustomAvatar(){
        //init();
        setContentPane();
    }

    public void init(){

    }
    public void onSave(){

    }


    public void onExit(){
        Platform.exit();
    }

    public void go(){
        Pane root = new Pane();
        Canvas canvas = new Canvas(300, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);
        root.getChildren().add(canvas);
    }

    @Override
    public JFXMasonryPane constructContentPane(){
        JFXMasonryPane root = new JFXMasonryPane();
        JFXColorPicker colorPicker = new JFXColorPicker();
        Pane pane1 = new Pane();
        Pane pane = new Pane();
        //colorPicker.setStyle("-jfx-color-picker:");
        Canvas canvas = new Canvas(800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D( );
        canvas.setOnMouseDragged(e->{
            double size = 5;
            double x = e.getX() - size/2;
            double y = e.getY() - size-2;
            gc.setFill(colorPicker.getValue());
            gc.fillRect(x,y,size,size);
        });
        //drawShapes(gc);
        pane.getChildren().add(canvas);
        pane1.getChildren().add(colorPicker);
        root.getChildren().addAll(pane,pane1);
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
