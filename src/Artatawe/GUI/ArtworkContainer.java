package Artatawe.GUI;

import Artatawe.Data.Auction;
import Artatawe.Data.Painting;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class ArtworkContainer extends ScenePattern {
    private final String PAGE_NAME = "Artworks";

    private ArrayList<Auction> auctions;

    public ArtworkContainer(ArrayList<Auction> auctions){
        this.auctions = auctions;
        setNameLabel(PAGE_NAME);
        setContentPane();

    }

    public JFXMasonryPane constructContentPane() {
        JFXMasonryPane contentPane = new JFXMasonryPane();
        ImageView imgView = new ImageView(new Image("file:data/artworks/img2.png"));
        imgView.setFitWidth(20);
        Random r = new Random(100);
        imgView.setFitHeight(20);
        int Low = 50;
        int High = 250;
        for(int i = 0; i<25; i++){//Gonna iterate over auctions list
            VBox imageHolder;
            int wh = r.nextInt(High - Low) + Low;
            imgView = new ImageView(new Image("file:data/artworks/img2.png"));
            imgView.setFitHeight(wh);
            imgView.setFitWidth(wh);
            imageHolder = new VBox(imgView, new Label("Mona Lisa"));
            contentPane.getChildren().addAll(imageHolder);
            imageHolder.setOnMouseClicked(e->{
                ((Stage)imageHolder.getScene().getWindow()).setScene(new Scene(new ArtworkScene(new Painting("Mona Lisa",
                        "Nice Description goes here. \nAnd it can be very long. \nArtatawe layouts itself quite nicely.", new Image("file:data/artworks/img2.png"),
                        1503,10000,5,new Date(17,07,1997),
                        50,50)).getPane(),
                        Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight()));
            });
        }

        /*imageHolder.setOnMouseClicked(e->{
            ((Stage)imageHolder.getScene().getWindow()).setScene(new Scene(new ArtworkScene(new Painting("Mona Lisa",
                    "Nice Description goes here. \nAnd it can be very long. \nArtatawe layouts itself quite nicely.", new Image("file:data/artworks/img2.png"),
                    1503,10000,5,new Date(17,07,1997),
                    50,50)).getPane(),
                    Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight()));
        });
*/
        return  contentPane;
    }

}
