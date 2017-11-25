package Artatawe.GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;

public class ProfileScene extends ScenePattern{

    private Image image;
    private File file;
    private ImageView imgView;



    public ProfileScene(String pathToProfImage){

        image = new Image(getClass().getResource("/img1.png").toExternalForm());
        System.out.print(image.isError());


    }



    @Override
    public JFXMasonryPane constructContentPane(Image img){
        JFXMasonryPane contentPane = new JFXMasonryPane();
        if(imgView == null){
            imgView = new ImageView(new Image(getClass().getResource("/img1.png").toExternalForm()));
        }

        imgView.setFitWidth(200);
        imgView.setFitHeight(200);
        AnchorPane pane = new AnchorPane();
        pane.getChildren().add(imgView);
        VBox pane1 = new VBox(new Label("That's insane dude"), new JFXButton("Press Me"));
        pane1.setStyle("-fx-background-color: red");
        contentPane.getChildren().addAll(pane,pane1);







        return contentPane;
    }

}
