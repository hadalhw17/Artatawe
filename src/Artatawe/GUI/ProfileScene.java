package Artatawe.GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTextArea;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
        JFXMasonryPane pane2 = new JFXMasonryPane();
        ImageView imgView2 = new ImageView(new Image(getClass().getResource("/img2.png").toExternalForm()));
        ImageView imgView3 = new ImageView(new Image(getClass().getResource("/img1.png").toExternalForm()));
        imgView2.setImage(new Image(getClass().getResource("/img2.png").toExternalForm()));
        imgView3.setImage(new Image(getClass().getResource("/img1.png").toExternalForm()));
        ImageView imgView4 = new ImageView(new Image(getClass().getResource("/img2.png").toExternalForm()));
        ImageView imgView5 = new ImageView(new Image(getClass().getResource("/img1.png").toExternalForm()));
        imgView2.setImage(new Image(getClass().getResource("/img2.png").toExternalForm()));
        imgView3.setImage(new Image(getClass().getResource("/img1.png").toExternalForm()));
        pane2.getChildren().addAll(new Pane(imgView2),new Pane(imgView3),new Pane(imgView4),new Pane(imgView5));
        pane2.setPrefSize(120,150);
        pane2.setHSpacing(-1);
        //pane2.setVSpacing(1);
        imgView2.setFitWidth(50);
        imgView2.setFitHeight(50);
        imgView3.setFitWidth(50);
        imgView3.setFitHeight(50);
        imgView4.setFitWidth(50);
        imgView4.setFitHeight(50);
        imgView5.setFitWidth(50);
        imgView5.setFitHeight(50);
        VBox pane1 = new VBox(new Label("About"), new Label("Name: Aleksandr Slobodov\nAge:20y.o."));
        pane1.setStyle("-fx-effect: dropshadow(gaussian, silver, 10, 0, 0, 0); -fx-background-color: #E8EAF6;");
        pane2.setStyle("-fx-effect: dropshadow(gaussian, silver, 10, 0, 0, 0);-fx-background-color: #E8EAF6;");
        contentPane.getChildren().addAll(pane,pane1,pane2);







        return contentPane;
    }

}
