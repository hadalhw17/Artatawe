package Artatawe.GUI;

import Artatawe.Data.Auction;
import Artatawe.Data.DataController;
import Artatawe.Data.Painting;
import Artatawe.Data.Profile;
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

    private DataController dc;
    private Profile p;
    public ArtworkContainer(DataController dc, Profile p){
        super(dc,p);
        this.auctions = dc.getAuctions();
        this.dc = dc;
        this.p = p;
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
        for(Auction auction: auctions){//Gonna iterate over auctions list
            VBox imageHolder;
            int wh = r.nextInt(High - Low) + Low;
            imgView = new ImageView(new Image(auction.getArtwork().getPhoto().getPath()));
            imgView.setFitHeight(wh);
            imgView.setFitWidth(wh);
            imageHolder = new VBox(imgView, new Label(auction.getArtwork().getName()));
            contentPane.getChildren().addAll(imageHolder);
            imageHolder.setOnMouseClicked(e->{
                GUIController.getPrimaryStage().setScene(new Scene(new ArtworkScene(dc,p, auction).getPane(),
                        GUIConstants.SCENE_WIDTH,GUIConstants.SCENE_HEIGHT));
            });

        }
        return  contentPane;
    }

}
